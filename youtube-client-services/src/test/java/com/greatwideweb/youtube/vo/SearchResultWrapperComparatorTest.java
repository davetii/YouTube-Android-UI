package com.greatwideweb.youtube.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

public class SearchResultWrapperComparatorTest {
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void ensureSortPushesMostRecentToTop() {
		Date d1 = new GregorianCalendar(2016,0,31).getTime();
		Date d2 = new GregorianCalendar(2015,0,31).getTime();
		Date d3 = new GregorianCalendar(2014,0,31).getTime();
		SearchResultWrapper video1 = new SearchResultWrapper(buildSearchResult("1",d1 ));
		SearchResultWrapper video2 = new SearchResultWrapper(buildSearchResult("2", d2 ));
		SearchResultWrapper video3 = new SearchResultWrapper(buildSearchResult("3" , d3));
		
		List<SearchResultWrapper> videos = new ArrayList<SearchResultWrapper>();
		videos.add(video3);
		videos.add(video1);
		videos.add(video2);
		Collections.sort(videos, new SearchResultWrapperComparator());
		Assert.assertEquals(video1.getTitle(), videos.get(0).getTitle());
	}
	
	@Test
	public void ensureSortPushesNotNullToTop() {
		Date d1 = new GregorianCalendar(2016,0,31).getTime();
		SearchResultWrapper video1 = new SearchResultWrapper(buildSearchResult("1",d1 ));
		SearchResultWrapper video2 = new SearchResultWrapper(buildSearchResult("2" ));
		SearchResultWrapper video3 = new SearchResultWrapper(buildSearchResult("3"));
		
		List<SearchResultWrapper> videos = new ArrayList<SearchResultWrapper>();
		videos.add(video3);
		videos.add(video1);
		videos.add(video2);
		Collections.sort(videos, new SearchResultWrapperComparator());
		Assert.assertEquals(video1.getTitle(), videos.get(0).getTitle());
	}

	private SearchResult buildSearchResult(String s, Date d) {
		SearchResult r = new SearchResult();
		SearchResultSnippet snip = new SearchResultSnippet();
		snip.setTitle(s);
		if(d != null) { snip.setPublishedAt(new DateTime(d)); }
		r.setSnippet(snip);
		return r;
	}
	
	private SearchResult buildSearchResult(String s) {
		SearchResult r = new SearchResult();
		SearchResultSnippet snip = new SearchResultSnippet();
		snip.setTitle(s);
		r.setSnippet(snip);
		return r;
	}

	

}
