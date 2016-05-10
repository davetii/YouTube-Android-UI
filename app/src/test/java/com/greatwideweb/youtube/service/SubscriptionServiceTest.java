package com.greatwideweb.youtube.service;

import java.io.IOException;
import java.util.List;

import org.junit.Before;

import org.junit.Assert;
import org.junit.Test;

import com.google.api.services.youtube.YouTube;
import com.greatwideweb.youtube.vo.SubscriptionVO;
import com.greatwideweb.youtube.vo.YoutubeSubscription;


public class SubscriptionServiceTest {
	
	YouTube youtubeService;
	
	@Before
	public void setup() throws IOException {
		YoutubeService s = new YoutubeService();
		youtubeService = s.getService();
	}
	
	@Test
	public void ensureSubscriptionsHasResult() throws IOException {
		SubscriptionService subService = new SubscriptionService(youtubeService);
		List<String> channels = subService.getChannelIds();
		System.out.println("channels: " + channels);
		Assert.assertNotNull(channels);
		Assert.assertNotEquals(0, channels.size());
		List<SubscriptionVO> subscriptions = subService.getSubscriptions();
		Assert.assertNotNull(subscriptions.get(0).getDefaultImageURL());
	}
}
