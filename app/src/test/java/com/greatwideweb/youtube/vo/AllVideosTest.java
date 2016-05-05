package com.greatwideweb.youtube.vo;

import java.util.Collections;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.api.services.youtube.model.SearchResult;
import com.greatwideweb.youtube.mock.TestDataProvider;


public class AllVideosTest {
	
	TestDataProvider testDataProvider;
	
	@Before
	public void setup() {
		testDataProvider = new TestDataProvider();
	}
	
	@Test
	public void ensureSortWorks() {
		SearchResult video1 = testDataProvider.getSearchResult1();
		SearchResult video2 = testDataProvider.getSearchResult2();
		SearchResult video3 = testDataProvider.getSearchResult3();
		
		AllVideos allVideos = new AllVideos();
		allVideos.add(video3);
		allVideos.add(video1);
		allVideos.add(video2);
		System.out.println(video2.getSnippet().getPublishedAt().getValue());
		Assert.assertEquals(video1.getEtag(), allVideos.get(0).getEtag());
	}

}
