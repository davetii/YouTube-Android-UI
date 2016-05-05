package com.greatwideweb.youtube.service;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.greatwideweb.youtube.service.YoutubeService;

public class YouTubeServiceTest {
	
	@Test
	public void ensureYouTubeServiceDoesNotError() throws IOException {
		YoutubeService s = new YoutubeService();
		Assert.assertNotNull(s.getService());
	}

}
