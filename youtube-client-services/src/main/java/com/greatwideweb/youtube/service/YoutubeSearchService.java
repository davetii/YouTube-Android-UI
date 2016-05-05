package com.greatwideweb.youtube.service;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.greatwideweb.youtube.vo.SearchParameters;

import java.util.ArrayList;
import java.util.List;

public class YoutubeSearchService {

	private static Logger logger =  LogManager.getLogger(YoutubeSearchService.class);
	private YouTube youtubeService;
	
	public YoutubeSearchService(YouTube youtubeService) {
		this.youtubeService = youtubeService;
		logger.debug("YoutubeSearchService initialized");
	}
	
	public List<SearchResult> search(SearchParameters p) throws IOException {
		logger.debug("YoutubeSearchService search");
		YouTube.Search.List searchRequest = youtubeService.search().list("snippet");
		if(p.hasChannelId()) {
			searchRequest.setChannelId(p.getChannelId());
		}
		if(p.hasType()) {
			searchRequest.setType(p.getType());
		}
		searchRequest.setMaxResults(p.getMaxResults());
		searchRequest.setOrder(p.getOrder());
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
