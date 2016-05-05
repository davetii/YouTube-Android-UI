package com.greatwideweb.youtube.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.greatwideweb.youtube.tasks.HomeItemsBuilder;
import com.greatwideweb.youtube.vo.SearchParameters;
import com.greatwideweb.youtube.vo.SearchResultWrapper;
import com.greatwideweb.youtube.vo.YoutubeSubscription;

public class YoutubeAPIService {
	
	private Set<YoutubeAPIObserver> observers = new  HashSet<YoutubeAPIObserver>();
	public void registerObserver(YoutubeAPIObserver o) { observers .add(o); }
	public void unregisterObserver(YoutubeAPIObserver o) { observers.remove(o); }
	
	private void notifySubscriptionUpdate(List<YoutubeSubscription> subscriptions) {
		for(YoutubeAPIObserver o : observers) { o.onSubscriptionsUpdate(subscriptions); }
	}
	
	private void notifyVideoUpdate(List<SearchResultWrapper> videos) {
		for(YoutubeAPIObserver o : observers) { o.onVideosUpdate(videos); }
	}
	
	public List<YoutubeSubscription> fetchUserContent() throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		YoutubeServiceDelegate serviceDelegate = new YoutubeServiceDelegate(pool);
		List<YoutubeSubscription> subscriptions = serviceDelegate.fetchSubscriptions();
		notifySubscriptionUpdate(subscriptions);
		List<SearchResultWrapper> allVideos= new ArrayList<SearchResultWrapper>(); 
		for(YoutubeSubscription subscription : subscriptions) {
			SearchParameters searchParameters = new SearchParameters();
			searchParameters.setChannelId(subscription.getChannelId());
			searchParameters.setType("video");
			List<SearchResultWrapper> searchResults = serviceDelegate.fetchVideos(searchParameters);
			subscription.setVideos(searchResults);
			allVideos.addAll(searchResults);
		}
		pool.shutdown();
		HomeItemsBuilder homeItemsBuilder = new HomeItemsBuilder(allVideos);
		notifyVideoUpdate(homeItemsBuilder.getItems());
		return subscriptions;
	}

}
