package com.greatwideweb.youtube.service;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;

public class ChannelServiceTest {
	
	YouTube youtubeService;
	
	@Before
	public void setup() throws IOException {
		YoutubeService s = new YoutubeService();
		youtubeService = s.getService();
	}
	
	@Test
	public void ensureChannelServicePlayListsNotNull() throws IOException {
		String testChannelId = "UC3yA8nDwraeOfnYfBWun83g";
		ChannelService channelService = new ChannelService(youtubeService);
		List<Playlist> playLists = channelService.fetchPlayLists(testChannelId);
		Assert.assertNotNull(playLists);
		Assert.assertNotEquals(0, playLists.size());
	}
	
	@Test
	public void ensurePlayListReturnsItems() throws IOException {
		String testPlayListId = "PLN_VEYjh8gCByLq9iBB_yP4Qyhh95DMyH";
		ChannelService channelService = new ChannelService(youtubeService);
		List<PlaylistItem> playListItems = channelService.fetchPlayListItems(testPlayListId);
		Assert.assertNotNull(playListItems);
		Assert.assertNotEquals(0, playListItems.size());
	}
	
	@Test
	public void ensureAllPlayListItemsReturnsItems() throws IOException {
		String testChannelId = "UC3yA8nDwraeOfnYfBWun83g";
		ChannelService channelService = new ChannelService(youtubeService);
		List<PlaylistItem> playListItems = channelService.fetchAllPlayListItems(testChannelId);
		Assert.assertNotNull(playListItems);
		Assert.assertNotEquals(0, playListItems.size());
	}
}
