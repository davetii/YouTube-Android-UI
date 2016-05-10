package com.greatwideweb.youtube;

import java.util.List;
import java.util.concurrent.Callable;

import com.google.api.services.youtube.YouTube;
import com.greatwideweb.youtube.service.SubscriptionService;
import com.greatwideweb.youtube.vo.SubscriptionVO;
import com.greatwideweb.youtube.vo.YoutubeSubscription;

public class SubscriptionServiceExecutor implements Callable<List<SubscriptionVO>> {

	private final YouTube youtubeService;
	
	public SubscriptionServiceExecutor(YouTube youtubeService) {
		this.youtubeService = youtubeService;
	}

	@Override
	public List<SubscriptionVO> call() throws Exception {
		SubscriptionService subscriptionService = new SubscriptionService(youtubeService);
		return subscriptionService.getSubscriptions();
	}

}
