package com.greatwideweb.youtube.vo;

import java.util.Comparator;


public class SearchResultWrapperComparator implements Comparator<VideoVO> {
	
	@Override
	public int compare(VideoVO o1, VideoVO o2) {
		if(!hasDate(o1) && !hasDate(o2)) {return 0;}
		if(!hasDate(o1)) {return 1;}
		if(!hasDate(o2)) {return -1;}
		return o2.getPublishedAt().compareTo(o1.getPublishedAt());
	}

	private boolean hasDate(VideoVO arg) {
		if(arg != null && arg.getPublishedAt() != null) return true;
		return false;
	}

	

}
