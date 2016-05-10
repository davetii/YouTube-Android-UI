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
import com.greatwideweb.youtube.vo.SubscriptionVO;
import com.greatwideweb.youtube.vo.VideoVO;
import com.greatwideweb.youtube.vo.YoutubeSubscription;

public class YoutubeAPIService {
	
	private Set<YoutubeAPIObserver> observers = new  HashSet<YoutubeAPIObserver>();
	public void registerObserver(YoutubeAPIObserver o) { observers .add(o); }
	public void unregisterObserver(YoutubeAPIObserver o) { observers.remove(o); }
	
	private void notifySubscriptionUpdate(List<SubscriptionVO> subscriptions) {
		for(YoutubeAPIObserver o : observers) { o.onSubscriptionsUpdate(subscriptions); }
	}
	
	private void notifyVideoUpdate(List<VideoVO> videos) {
		for(YoutubeAPIObserver o : observers) { o.onVideosUpdate(videos); }
	}
	
	public List<SubscriptionVO> fetchUserContent() throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		YoutubeServiceDelegate serviceDelegate = new YoutubeServiceDelegate(pool);
		List<YoutubeSubscription> youtubeSubscriptions = new ArrayList<YoutubeSubscription>();
		List<SubscriptionVO> subscriptions = serviceDelegate.fetchSubscriptions();
		notifySubscriptionUpdate(subscriptions);
		List<VideoVO> allVideos= new ArrayList<VideoVO>();
		for(SubscriptionVO subscription : subscriptions) {
			SearchParameters searchParameters = new SearchParameters();
			searchParameters.setChannelId(subscription.getChannelId());
			searchParameters.setType("video");
			List<VideoVO> searchResults = serviceDelegate.fetchVideos(searchParameters);
			//subscription.setVideos(searchResults);
			allVideos.addAll(searchResults);
		}
		pool.shutdown();
		HomeItemsBuilder homeItemsBuilder = new HomeItemsBuilder(allVideos);
		notifyVideoUpdate(homeItemsBuilder.getItems());
		return subscriptions;
	}

}
