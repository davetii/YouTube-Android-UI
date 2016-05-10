package com.greatwideweb.youtube.vo;

import java.io.Serializable;
import java.util.List;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.Subscription;
import com.greatwideweb.youtube.util.YoutubeUtil;

public class YoutubeSubscription implements Serializable{

	private static final long serialVersionUID = 1L;
	private final SubscriptionVO subscription;
	private List<VideoVO> videos;


	public YoutubeSubscription(SubscriptionVO subscription, List<VideoVO> videos) {
		this.subscription = subscription;
		this.videos = videos;
	}

	public SubscriptionVO getSubscription() {
		return subscription;
	}

	public List<VideoVO> getVideos() {
		return videos;
	}
}
