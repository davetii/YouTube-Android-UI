package com.greatwideweb.youtube.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import com.google.api.services.youtube.model.SearchResult;

public class AllVideos {

	List<SearchResult> allVideos = new ArrayList<SearchResult>();
	private VideoListComparator comparator = new VideoListComparator();

	public void add(List<SearchResult> videos) {
		System.out.println("AllVideos add");
		allVideos.addAll(videos);
		sort();
		
	}
	
	public SearchResult get(int i) {
		if(this.allVideos != null && this.allVideos.size() >= (i+1)) {
			return this.allVideos.get(i);
		}
		return null;
	}
	
	public void add(SearchResult video) {
		allVideos.add(video);
		sort();
	}
	
	private void sort() { Collections.sort(this.allVideos, comparator); }
	public List<SearchResult> getList() { return this.allVideos; }


	
	
}
