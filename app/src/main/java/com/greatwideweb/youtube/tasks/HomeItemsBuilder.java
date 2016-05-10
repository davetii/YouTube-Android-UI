package com.greatwideweb.youtube.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.greatwideweb.youtube.vo.VideoVO;
import com.greatwideweb.youtube.vo.SearchResultWrapperComparator;

public class HomeItemsBuilder {
	
	
	public static final int MAX_CARDS_ON_HOME = 250;
	private static final int MAX_CARDS_SHOWS_PER_CHANNEL = 4;
	private List<VideoVO> items=null;
	public List<VideoVO> getItems() { return this.items; }
	
	public HomeItemsBuilder(List<VideoVO> videos) {
		System.out.println("Videos given to HomeItemsBuilder: " + videos.size());
		this.items = new ArrayList<VideoVO>();
		Map<String, Integer> channelsMap= new HashMap<String, Integer>();
		for(VideoVO result : videos) {
			
			if(this.items.size() >= MAX_CARDS_ON_HOME) { break; }
			
			if(!channelsMap.containsKey(result.getChannelId())) {
				channelsMap.put(result.getChannelId(), 0);
			}
			int currentCount = channelsMap.get(result.getChannelId());
			currentCount++;
			channelsMap.put(result.getChannelId(), currentCount);
			if(channelsMap.get(result.getChannelId()) <= MAX_CARDS_SHOWS_PER_CHANNEL) {
				this.items.add(result);
			}
		}
		Collections.sort(this.items, new SearchResultWrapperComparator());
	}

	

}
