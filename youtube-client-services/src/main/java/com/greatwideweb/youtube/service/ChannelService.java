package com.greatwideweb.youtube.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.greatwideweb.youtube.YoutubeConstants;

public class ChannelService {

	private final YouTube youtube;
	private static Logger logger =  LogManager.getLogger(ChannelService.class);
	
	public ChannelService(YouTube youtube) {
		logger.debug("ChannelService constructor called");
		this.youtube = youtube;
		
	}

	public List<Playlist> fetchPlayLists(String channelId) throws IOException {
		logger.debug("fetchPlayLists called channelId: " + channelId);
		YouTube.Playlists.List playListRequest;
		playListRequest = this.youtube.playlists().list(YoutubeConstants.CONTENT_DETAILS);
      	playListRequest.setPart("snippet");
    	playListRequest.setMaxResults(YoutubeConstants.MAX_REQUEST);
    	playListRequest.setChannelId(channelId);
    	PlaylistListResponse playListResponse = playListRequest.execute();
    	List<Playlist> result =null;
    	if(playListResponse != null) {
    		result = playListResponse.getItems();
    		logger.debug("fetchPlayLists returned " + result.size());
    	}else {
    		logger.debug("fetchPlayLists returned null");
    	}
    	logger.debug("fetchPlayLists returned " + result);
		return result;
	}

	public List<PlaylistItem> fetchPlayListItems(String id) throws IOException {
		logger.debug("fetchPlayListItems called id: " + id);
		YouTube.PlaylistItems.List playListItemsRequest;
		playListItemsRequest = youtube.playlistItems().list(YoutubeConstants.CONTENT_DETAILS);
    	playListItemsRequest.setPart("snippet");
    	playListItemsRequest.setMaxResults(YoutubeConstants.MAX_REQUEST);
    	playListItemsRequest.setPlaylistId(id);
		PlaylistItemListResponse  response= playListItemsRequest.execute();
		List<PlaylistItem> items = response.getItems();
		if(items != null)
    		logger.debug("fetchPlayListItems returns " + items.size());
    	else
    		logger.debug("fetchPlayListItems returned null");
		return items;
	}

	public List<PlaylistItem> fetchAllPlayListItems(String channelId) throws IOException {
		logger.debug("fetchAllPlayListItems called channelId: " + channelId);
		List<PlaylistItem> result = new ArrayList<PlaylistItem>();
		List<Playlist> playLists = this.fetchPlayLists(channelId);
		if(playLists != null) {
			for(Playlist p : playLists) {
				List<PlaylistItem> items =fetchPlayListItems(p.getId()); 
				result.addAll(items);
			}
		}
		logger.debug("fetchAllPlayListItems execute returned " + result.size());
		logger.debug("print out ALL playlists");
		logger.debug("**************************************************************************");
		logger.debug(result);
		logger.debug("**************************************************************************");
		return result;
	}

}
