package com.greatwideweb.youtube.vo;

import com.greatwideweb.youtube.OrderTypes;
import com.greatwideweb.youtube.YoutubeConstants;

public class SearchParameters {
	
	private String channelId;
	private String order = OrderTypes.date.toString();
	private String type;
	private long maxResults = YoutubeConstants.MAX_REQUEST;
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(long maxResults) {
		this.maxResults = maxResults;
	}
	public boolean hasChannelId() {
		 return isPopulated(this.channelId);
	}
	public boolean hasType() {
		return isPopulated(this.type);
	}
	
	private boolean isPopulated(String s) {
		if(s != null && s.length() > 0) { return true; }
		return false;
	}
	
	
	

}
