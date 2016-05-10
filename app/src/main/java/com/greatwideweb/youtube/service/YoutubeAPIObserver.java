package com.greatwideweb.youtube.service;

import java.util.List;

import com.greatwideweb.youtube.vo.SubscriptionVO;
import com.greatwideweb.youtube.vo.VideoVO;
import com.greatwideweb.youtube.vo.YoutubeSubscription;

public interface YoutubeAPIObserver {

	void onSubscriptionsUpdate(List<SubscriptionVO> subscriptions);
	void onVideosUpdate(List<VideoVO> videos);

}
