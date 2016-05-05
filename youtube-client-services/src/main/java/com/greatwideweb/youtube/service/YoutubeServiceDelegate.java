package com.greatwideweb.youtube.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.greatwideweb.youtube.SubscriptionServiceExecutor;
import com.greatwideweb.youtube.vo.SearchParameters;
import com.greatwideweb.youtube.vo.SearchResultWrapper;
import com.greatwideweb.youtube.vo.SearchResultWrapperComparator;
import com.greatwideweb.youtube.vo.VideoListComparator;
import com.greatwideweb.youtube.vo.YoutubeSubscription;

public class YoutubeServiceDelegate {
	
	private final YouTube youtubeService;
	private final ExecutorService pool;
	private List<YoutubeSubscription> subscriptions;
	private List<SearchResultWrapper> allVideos = new ArrayList<SearchResultWrapper>();
	
	public YoutubeServiceDelegate(ExecutorService pool) throws InterruptedException, ExecutionException {
		this.pool = pool;
		Callable<YouTube> youtubeServiceExecutor = new YoutubeServiceExecutor();
		Future<YouTube> youtubeServiceFuture = this.pool.submit(youtubeServiceExecutor);
		this.youtubeService = youtubeServiceFuture.get();
	}

	public List<YoutubeSubscription> fetchSubscriptions() {
		Callable<List<YoutubeSubscription>> subscriptionServiceExecutor = new SubscriptionServiceExecutor(youtubeService);
		Future<List<YoutubeSubscription>> subscriptionFuture = this.pool.submit(subscriptionServiceExecutor);
		try { 
			return subscriptionFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<YoutubeSubscription>(); 
		}
	}

	public List<SearchResultWrapper> fetchVideos(SearchParameters searchParameters) {
		List<SearchResultWrapper> results = new ArrayList<SearchResultWrapper>(); 
		Callable<List<SearchResult>> callable = new YoutubeSearchExecutor(youtubeService, searchParameters);
		Future<List<SearchResult>> future = pool.submit(callable);
		try {
			List<SearchResult> resultsFromLookup =future.get();
			for(SearchResult s : resultsFromLookup) {
				results.add(new SearchResultWrapper(s));
			}
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return results;
	}

	public void buidAllVideos() {
		subscriptions = this.fetchSubscriptions();
		for(YoutubeSubscription o : subscriptions) { 
			
			SearchParameters searchParameters = new SearchParameters();
			searchParameters.setChannelId(o.getChannelId());
			searchParameters.setType("video");
			o.setVideos(this.fetchVideos(searchParameters));
		}
		
		// handle Dups
		Set <SearchResultWrapper> allVideosSet = new HashSet<SearchResultWrapper>();
		for(YoutubeSubscription o : subscriptions) {
			for(SearchResultWrapper video : o.getVideos()) {
				allVideosSet.add(video);
			}
		}
		
		allVideos.clear();
		allVideos.addAll(allVideosSet);
		Collections.sort(allVideos, new SearchResultWrapperComparator());
		
	}

	public List<SearchResultWrapper> getAllVideos() { return this.allVideos; }

}
