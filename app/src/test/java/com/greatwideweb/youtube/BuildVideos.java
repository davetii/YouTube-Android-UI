package com.greatwideweb.youtube;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.greatwideweb.youtube.service.YoutubeServiceDelegate;
import com.greatwideweb.youtube.vo.YoutubeSubscription;
import com.google.api.services.youtube.model.SearchResult;

import org.junit.Test;


public class BuildVideos {

	private final static String SPACE_IND ="*********************************************\n";
	private final static String BREAK_LINE ="\n";
	private static final int INITIAL_CARDS =150;

	@Test
	public void runTest()  {
		String startTimeStamp = getTimeStamp();
		ExecutorService pool = Executors.newFixedThreadPool(10);
		YoutubeServiceDelegate serviceDelegate=null;
		try {
			
			serviceDelegate = new YoutubeServiceDelegate(pool);
			List<YoutubeSubscription> subscriptions = serviceDelegate.fetchSubscriptions();
			System.out.println("Subscriptions are back");
			/*
			int count =0;
			for(YoutubeSubscription subscription : subscriptions) {
				count++;
				SearchParameters searchParameters = new SearchParameters();
				searchParameters.setChannelId(subscription.getChannelId());
				searchParameters.setType("video");
				subscription.setVideos(serviceDelegate.fetchVideos(searchParameters));
				if(count > 3)
					break;
			}
			*/
			
			//serviceDelegate.buidAllVideos();
		
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		System.out.println( "EndTime: " + getTimeStamp() + "Starttime: " + startTimeStamp);
		pool.shutdown();
		
		/*
		List<SearchResultWrapper> allVideos = new ArrayList<SearchResultWrapper>();
		if(serviceDelegate != null) { allVideos = serviceDelegate.getAllVideos(); }
		
		int i=0;
		for(SearchResultWrapper o : allVideos) {
			System.out.println(o.prettyText());
			i++;
			if(i > 200)
				break;
		}
		*/
		
		/*
		try {
			ObjectSerializer serializer = new ObjectSerializer("/tmp/serialized-all-videos.txt",allVideos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/
	
		
		
		/*
		int i =0;
		System.out.println("Print first 200 VIDEOS");
		for(SearchResult result : allVideos) {
			i++;
			System.out.println(" channel" + result.getSnippet().getChannelTitle() + " title: " +  result.getSnippet().getTitle() + " published: " + result.getSnippet().getPublishedAt() + " kind: " + result.getKind());
			if(i > 500)
				break;

		}
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("\n");
		
		for(SearchResult result : allVideos) {
			
			if(homePageItems.size() > INITIAL_CARDS) { break; }
			SearchResultWrapper resultWrapper = new SearchResultWrapper(result);
			if(!channelOnHomePage.containsKey(resultWrapper.getChannelId())) {
				channelOnHomePage.put(resultWrapper.getChannelId(), 0);
			}
			int currentCount = channelOnHomePage.get(resultWrapper.getChannelId());
			currentCount++;
			channelOnHomePage.put(resultWrapper.getChannelId(), currentCount);
			if(channelOnHomePage.get(resultWrapper.getChannelId()) < 4) {
				homePageItems.add(resultWrapper);
			}
		}
		
		Collections.sort(homePageItems, new SearchResultWrapperComparator());
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("Print homepage ***********************\n");
		System.out.println("");
		for(SearchResultWrapper o : homePageItems) {
			System.out.println(o.prettyText());
		}
		*/
		
		/*
		
		try {
			
			String fullyPathedFile ="c:///tmp/all-videos.txt";
			File f = new File(fullyPathedFile);
			
			Files.deleteIfExists(f.toPath());
			f.createNewFile();
			
			fw = new FileWriter(f.getAbsoluteFile());
			fw.write(SPACE_IND);
			fw.write("START \n");
			fw.write(startTimeStamp);
			addSpace(fw);
			fw.write(SPACE_IND);
			
			for(SearchResult result : allVideos.getList()) {
				fw.write(format(result) + "\n");
			}
			
			
			addSpace(fw);
			fw.write(getTimeStamp());
			fw.write("COMPLETED");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		*/
		
	}

	private String format(SearchResult result) {
		if(result == null && result.getSnippet() != null)
			return "null";
		return "Title: " + result.getSnippet().getTitle() + " Description: " +  result.getSnippet().getDescription() + "Date: " + result.getSnippet().getPublishedAt();
	}

	private String getTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date) + BREAK_LINE;
	}

	private void addSpace(FileWriter fw) throws IOException {
		fw.write("\n");
		fw.write("\n");
	}

}
