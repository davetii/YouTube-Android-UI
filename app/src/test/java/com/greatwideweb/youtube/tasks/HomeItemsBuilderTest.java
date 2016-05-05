package com.greatwideweb.youtube.tasks;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.greatwideweb.youtube.mock.TestDataProvider;
import com.greatwideweb.youtube.tasks.HomeItemsBuilder;
import com.greatwideweb.youtube.vo.SearchResultWrapper;

public class HomeItemsBuilderTest {
	
	List<SearchResultWrapper> videos;
	List<SearchResultWrapper> homevideos;
	@Before
	public void setup() {
		videos = new TestDataProvider().getAllVideosMock();
		HomeItemsBuilder builder = new HomeItemsBuilder(videos);
		homevideos = builder.getItems();
	}
	
	@Test
	public void  ensureHomeItemsBuilderReturnsMaxRows () {
		Assert.assertEquals(HomeItemsBuilder.MAX_CARDS_ON_HOME, homevideos.size());
	}
	
	@Test
	public void ensureDatesSortProperly() {
		Date first = homevideos.get(0).getPublishedAt();
		Date second = homevideos.get(1).getPublishedAt();
		Date last = homevideos.get(homevideos.size() -1).getPublishedAt();
		Assert.assertTrue(first.compareTo(second) > 0);
		Assert.assertTrue(first.compareTo(last) > 0);
		Assert.assertTrue(second.compareTo(last) > 0);
	}
}
