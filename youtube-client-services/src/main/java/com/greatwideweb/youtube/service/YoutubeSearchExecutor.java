package com.greatwideweb.youtube.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.greatwideweb.youtube.vo.SearchParameters;

public class YoutubeSearchExecutor implements Callable<List<SearchResult>> {
	
	private static Logger logger =  LogManager.getLogger(YoutubeSearchExecutor.class);
	private YouTube youtubeService;
	private SearchParameters parameters;
	
	public YoutubeSearchExecutor (YouTube youtubeService, SearchParameters parameters) {
		this.youtubeService = youtubeService;
		this.parameters = parameters;
	}

	@Override
	public List<SearchResult> call() throws Exception {
		YouTube.Search.List searchRequest = youtubeService.search().list("snippet");
		if(parameters.hasChannelId()) { searchRequest.setChannelId(parameters.getChannelId()); }
		if(parameters.hasType()) { searchRequest.setType(parameters.getType()); }
		searchRequest.setMaxResults(parameters.getMaxResults());
		searchRequest.setOrder(parameters.getOrder());
		SearchListResponse response =  searchRequest.execute();
		
		if(response.getItems() !=  null) {
			logger.debug("YoutubeSearchService search service returned " + response.getItems() + " rows");
			return response.getItems();
		} else {
			logger.debug("YoutubeSearchService search service returned empty results");
			return new ArrayList<SearchResult>();
		}
	}

}
