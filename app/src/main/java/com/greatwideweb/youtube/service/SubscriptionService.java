package com.greatwideweb.youtube.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.SubscriptionListResponse;
import com.greatwideweb.youtube.YoutubeConstants;
import com.greatwideweb.youtube.vo.YoutubeSubscription;

public class SubscriptionService {

	List<String> channelIds = new ArrayList<String>();
	private YouTube.Subscriptions.List subscriptionRequest;
	private List<YoutubeSubscription> subscriptions = new ArrayList<YoutubeSubscription>();
	private static Logger logger =  LogManager.getLogger(SubscriptionService.class);
	public SubscriptionService(YouTube youtubeService) throws IOException {
		
		logger.debug("Intialize SubscriptionService");
		subscriptionRequest = youtubeService.subscriptions().list(YoutubeConstants.CONTENT_DETAILS);
        subscriptionRequest.setMine(true);
        subscriptionRequest.setPart(YoutubeConstants.SNIPPET);
        subscriptionRequest.setMaxResults(YoutubeConstants.MAX_REQUEST);
        
        
        String nextToken = processChannel(subscriptionRequest);
        if(nextToken != null) {
        	for(int i=0; i<YoutubeConstants.SANITY_REQUEST_COUNT; i++) {
        		subscriptionRequest.setPageToken(nextToken);
        		nextToken = processChannel(subscriptionRequest);
        		if(nextToken == null) {
        			break;
        		}
        	}
        }
        
        logger.debug("SubscriptionService total Channels: " + channelIds.size());
        
		
	}
	
	private String processChannel(com.google.api.services.youtube.YouTube.Subscriptions.List request) throws IOException {
    	SubscriptionListResponse result = request.execute();
    	String nextToken=null;
    	List<Subscription> items = result.getItems();
	   	if(items != null) {
        	nextToken = result.getNextPageToken();
        	for(Subscription s : items) {
        		subscriptions.add(new YoutubeSubscription(s));
        		channelIds.add(s.getSnippet().getResourceId().getChannelId());
        	}
	    }
		return nextToken;
	}
	
	public List<YoutubeSubscription> getSubscriptions() {
		return this.subscriptions;
	}

	public List<String> getChannelIds() {
		return channelIds;
	}

}
