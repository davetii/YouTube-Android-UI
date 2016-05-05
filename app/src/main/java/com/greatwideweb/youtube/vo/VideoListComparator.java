package com.greatwideweb.youtube.vo;

import java.util.Comparator;
import java.util.Date;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.SearchResult;

public class VideoListComparator implements Comparator<SearchResult> {

	@Override
	public int compare(SearchResult o1, SearchResult o2) {
		if(!hasSnippet(o1) && !hasSnippet(o2)) { return 0; }
		if(!hasSnippet(o1)) { return -1; }
		if(!hasSnippet(o2)) { return 1; }
		Date d1 =new Date(o1.getSnippet().getPublishedAt().getValue());
		Date d2= new Date(o2.getSnippet().getPublishedAt().getValue());
		return d2.compareTo(d1);
	}
	
	private boolean hasSnippet(SearchResult o) {
		if(null != o  && 
		   null != o.getSnippet() && 
		   null != o.getSnippet().getPublishedAt()) return true;
		return false;
	}

}
