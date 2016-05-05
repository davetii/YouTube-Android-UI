package com.greatwideweb.youtube.service;

import java.util.List;

import com.greatwideweb.youtube.vo.SearchResultWrapper;
import com.greatwideweb.youtube.vo.YoutubeSubscription;

public interface YoutubeAPIObserver {

	void onSubscriptionsUpdate(List<YoutubeSubscription> subscriptions);
	void onVideosUpdate(List<SearchResultWrapper> videos);

}
