package com.greatwideweb.youtube;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.greatwideweb.youtube.service.YoutubeServiceDelegate;
import com.greatwideweb.youtube.vo.SearchParameters;
import com.greatwideweb.youtube.vo.SubscriptionVO;
import com.greatwideweb.youtube.vo.YoutubeSubscription;
import com.google.api.services.youtube.model.SearchResult;

import junit.framework.Assert;

import org.junit.Test;


public class BuildVideos {

	private final static String SPACE_IND ="*********************************************\n";
	private final static String BREAK_LINE ="\n";
	private static final int INITIAL_CARDS =150;

	@Test
	public void runTest()  {
		String startTimeStamp = getTimeStamp();
		String subscriptionTime = null;
		String subAndFirst10=null;
		ExecutorService pool = Executors.newFixedThreadPool(10);
		YoutubeServiceDelegate serviceDelegate=null;
		List<YoutubeSubscription> loadedSubscriptions = new ArrayList<YoutubeSubscription>();
		try {
			
			serviceDelegate = new YoutubeServiceDelegate(pool);
			List<SubscriptionVO> subscriptions = serviceDelegate.fetchSubscriptions();
			System.out.println("Subscriptions are back");
			subscriptionTime = getTimeStamp();
			int count =0;
			for(SubscriptionVO subscription : subscriptions) {

				SearchParameters searchParameters = new SearchParameters();
				searchParameters.setChannelId(subscription.getChannelId());
				searchParameters.setType("video");
				loadedSubscriptions.add(new YoutubeSubscription(subscription, serviceDelegate.fetchVideos(searchParameters)));
				count++;
				if(count == 10) {
					subAndFirst10 = getTimeStamp();
				}
			}

		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pool.shutdown();
		System.out.println("Start: " + startTimeStamp);
		System.out.println("Subscription: " + subscriptionTime);
		System.out.println("Subscriptions + first 10: " + subAndFirst10);
		System.out.println( "EndTime: " + getTimeStamp());
		Assert.assertEquals(true, loadedSubscriptions.size() > 0);
		System.out.println( "Subscriptions size: " + loadedSubscriptions.size());

		int videoCount =0;
		for(YoutubeSubscription s : loadedSubscriptions) {
			videoCount += s.getVideos().size();
		}
		System.out.println( "total Videos: " + videoCount);
		Assert.assertEquals(true, videoCount > 0);

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
