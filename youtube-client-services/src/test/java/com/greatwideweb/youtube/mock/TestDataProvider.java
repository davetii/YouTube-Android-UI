package com.greatwideweb.youtube.mock;

import com.greatwideweb.youtube.OrderTypes;
import com.greatwideweb.youtube.vo.SearchParameters;
import com.greatwideweb.youtube.vo.SearchResultWrapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

public class TestDataProvider {
	
	String cookingChannel = "UCBOjaoOQgIJiV0itcGQIMJQ";
	String abcOndemand ="UCBYe8ZLf4IX29I3BZ89l0pQ";
	String redefiningStrength ="UCh2w-xqEwSz96sbn2afbOVQ";
	public SearchParameters getSearchParametersForABCOndemandChannelOnly() {
		SearchParameters result = new SearchParameters();
		result.setChannelId(abcOndemand);
		result.setOrder(OrderTypes.date.toString());
		return result;
	}
	
	public SearchParameters getSearchParametersForCookingChannelOnly() {
		SearchParameters result = new SearchParameters();
		result.setChannelId(cookingChannel);
		result.setOrder(OrderTypes.date.toString());
		return result;
	}
	
	public SearchParameters getSearchParametersForredefiningStrength() {
		SearchParameters result = new SearchParameters();
		result.setChannelId(redefiningStrength);
		result.setOrder(OrderTypes.date.toString());
		return result;
	}

	public SearchResult getSearchResult1() {
		SearchResultSnippet snippet = new SearchResultSnippet();
		DateTime publishedAt = new DateTime(new GregorianCalendar(2014, 2, 11).getTime());
		snippet.setPublishedAt(publishedAt );
		SearchResult result = new SearchResult();
		result.setSnippet(snippet);
		result.setEtag("2015-02-11");
		return result;
	}

	public SearchResult getSearchResult2() {
		SearchResultSnippet snippet = new SearchResultSnippet();
		DateTime publishedAt = new DateTime(new GregorianCalendar(2014, 1, 11).getTime());
		snippet.setPublishedAt(publishedAt );
		SearchResult result = new SearchResult();
		result.setSnippet(snippet);
		result.setEtag("2014-01-11");
		return result;
	}
	
	public SearchResult getSearchResult3() {
		SearchResultSnippet snippet = new SearchResultSnippet();
		DateTime publishedAt = new DateTime(new GregorianCalendar(2014, 1, 11).getTime());
		snippet.setPublishedAt(publishedAt );
		SearchResult result = new SearchResult();
		result.setSnippet(snippet);
		result.setEtag("2012-01-11");
		return result;
	}
	
	public List<SearchResultWrapper> getAllVideosMock() {
		ObjectInputStream objectinputstream = null;
		List<SearchResultWrapper> result = null;
		try {
			InputStream input =this.getClass().getClassLoader().getResourceAsStream("serialized-all-videos.txt");
		    ObjectInputStream objectinputstream1 = new ObjectInputStream(input);
		    result = (List<SearchResultWrapper>) objectinputstream1.readObject();
		    //recordList.add(readCase);
		    //System.out.println(recordList.get(i));
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    if(objectinputstream != null){
		        try {
					objectinputstream .close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } 
		}
		return result;
		
	}

	public static void main(String[] args) {
		TestDataProvider p = new TestDataProvider();
		System.out.println(p.getAllVideosMock().size());
	}
	
	
	

}
