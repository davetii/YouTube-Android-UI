package com.greatwideweb.youtube.service;

import java.util.List;

import com.greatwideweb.youtube.vo.SubscriptionVO;
import com.greatwideweb.youtube.vo.VideoVO;
import com.greatwideweb.youtube.vo.YoutubeSubscription;

public class YoutubeAPIServiceTest implements YoutubeAPIObserver{
	
	public void setup() {
		
	}

	@Override
	public void onSubscriptionsUpdate(List<SubscriptionVO> subscriptions) {
		System.out.println(subscriptions.size());
		
	}

	@Override
	public void onVideosUpdate(List<VideoVO> videos) {
		System.out.println(videos.size());
		
	}
	
	

}
