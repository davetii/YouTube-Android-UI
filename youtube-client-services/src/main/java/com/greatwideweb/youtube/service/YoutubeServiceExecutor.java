package com.greatwideweb.youtube.service;

import java.util.concurrent.Callable;

import com.google.api.services.youtube.YouTube;

public class YoutubeServiceExecutor implements Callable<YouTube> {

	@Override
	public YouTube call() throws Exception {
		YoutubeService factory = new YoutubeService();
		return factory.getService();
	}

}
