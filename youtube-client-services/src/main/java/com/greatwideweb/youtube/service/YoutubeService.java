package com.greatwideweb.youtube.service;

import java.io.IOException;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.youtube.YouTube;
import com.google.common.collect.Lists;
import com.greatwideweb.youtube.Auth;

public class YoutubeService {
	
	private final YouTube service;
	
	public YoutubeService() throws IOException {
		List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.readonly");
		Credential credential = Auth.authorize(scopes, "mysubscriptions");
		service = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName(
                "youtube-cmdline-mysubscriptions-sample").build();
		
	}

	public YouTube getService() { return service; }

}
