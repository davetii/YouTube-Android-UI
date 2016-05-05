package com.greatwideweb.youtube.service;

import java.util.List;

import com.greatwideweb.youtube.vo.SearchResultWrapper;
import com.greatwideweb.youtube.vo.YoutubeSubscription;

public class YoutubeAPIServiceTest implements YoutubeAPIObserver{
	
	public void setup() {
		
	}

	@Override
	public void onSubscriptionsUpdate(List<YoutubeSubscription> subscriptions) {
		System.out.println(subscriptions.size());
		
	}

	@Override
	public void onVideosUpdate(List<SearchResultWrapper> videos) {
		System.out.println(videos.size());
		
	}
	
	

}
