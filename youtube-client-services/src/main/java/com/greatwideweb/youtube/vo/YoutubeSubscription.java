package com.greatwideweb.youtube.vo;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Subscription;

public class YoutubeSubscription implements Serializable{

	private static final long serialVersionUID = 1L;

	public YoutubeSubscription(Subscription s) {
		this.channelId = s.getSnippet().getResourceId().getChannelId();
		this.channelDescription = s.getSnippet().getDescription(); 
		this.channelTitle = s.getSnippet().getTitle();
		this.createdDate =s.getSnippet().getPublishedAt(); 
		
	}

	String channelId;
	String channelTitle;
	String channelDescription;
	DateTime createdDate;
	List<SearchResultWrapper> videos;
	
	public String getChannelId() { return channelId; }
	public String getChannelTitle() { return channelTitle; }
	public String getChannelDescription() { return channelDescription; }
	public List<SearchResultWrapper> getVideos() { return this.videos; }
	public void setVideos(List<SearchResultWrapper> videos) { this.videos = videos; }
	
	@Override
	public String toString() {
		return "YoutubeSubscription [channelId=" + channelId + ", channelTitle=" + channelTitle + ", createdDate=" + this.createdDate + "]";
	}
	
	
	
	
	
	
	
	
	

}
