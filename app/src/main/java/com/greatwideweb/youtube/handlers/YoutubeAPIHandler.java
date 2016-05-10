package com.greatwideweb.youtube.handlers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.greatwideweb.youtube.service.YoutubeAPIObserver;
import com.greatwideweb.youtube.vo.SubscriptionVO;
import com.greatwideweb.youtube.vo.VideoVO;
import com.greatwideweb.youtube.vo.YoutubeSubscription;

public class YoutubeAPIHandler {
	
	private Set<YoutubeAPIObserver> observers = new  HashSet<YoutubeAPIObserver>();
	public void registerObserver(YoutubeAPIObserver o) { observers .add(o); }
	public void unregisterObserver(YoutubeAPIObserver o) { observers.remove(o); }
	
	private void notifySubscriptionUpdate(List<SubscriptionVO> subscriptions) {
		for(YoutubeAPIObserver o : observers) {  o.onSubscriptionsUpdate(subscriptions); }
	}
	
	private void notifyVideoUpdate(List<VideoVO> videos) {
		for(YoutubeAPIObserver o : observers) { o.onVideosUpdate(videos); }
	}
	
	

}
