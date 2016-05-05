package com.greatwideweb.youtube.vo;

import java.util.Comparator;


public class SearchResultWrapperComparator implements Comparator<SearchResultWrapper> {
	
	@Override
	public int compare(SearchResultWrapper o1, SearchResultWrapper o2) {
		if(!hasDate(o1) && !hasDate(o2)) {return 0;}
		if(!hasDate(o1)) {return 1;}
		if(!hasDate(o2)) {return -1;}
		return o2.getPublishedAt().compareTo(o1.getPublishedAt());
	}

	private boolean hasDate(SearchResultWrapper arg) {
		if(arg != null && arg.getPublishedAt() != null) return true;
		return false;
	}

	

}
