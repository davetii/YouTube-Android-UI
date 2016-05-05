package com.greatwideweb.youtube.service;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.greatwideweb.youtube.mock.TestDataProvider;
import com.greatwideweb.youtube.vo.SearchParameters;

public class YoutubeSearchServiceTest {
	
	YouTube youtubeService;
	TestDataProvider dataProvdider = new TestDataProvider();
	
	@Before
	public void setup() throws IOException {
		YoutubeService s = new YoutubeService();
		youtubeService = s.getService();
	}
	
	@Test
	public void ensureChannelSearchReturnsResults() throws IOException {
		SearchParameters searchParameters = dataProvdider.getSearchParametersForABCOndemandChannelOnly();
		YoutubeSearchService service = new YoutubeSearchService(youtubeService);
		List<SearchResult> results = service.search(searchParameters);
		Assert.assertNotNull(results);
		Assert.assertNotEquals(0, results.size());
		
	}

	

}
