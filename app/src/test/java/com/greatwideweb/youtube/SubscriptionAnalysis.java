package com.greatwideweb.youtube;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.api.services.youtube.YouTube;
import com.greatwideweb.youtube.service.SubscriptionService;
import com.greatwideweb.youtube.service.YoutubeService;
import com.greatwideweb.youtube.vo.SubscriptionVO;
import com.greatwideweb.youtube.vo.YoutubeSubscription;

import org.apache.commons.io.FileUtils;

public class SubscriptionAnalysis {
	
	private final static String PRETTY_SPACE ="*********************************************\n";
	public static void main(String[] args) {
		
		String fullyPathedFile ="c:///tmp/subscriptions.txt";
		File f = new File(fullyPathedFile);
		
		try {
			FileUtils.forceDelete(f);
			f.createNewFile();
			YoutubeService factory = new YoutubeService();
			YouTube youtubeService  = factory.getService();
			
			
			SubscriptionService subscriptionService = new SubscriptionService(youtubeService);
			List<SubscriptionVO> subscriptions = subscriptionService.getSubscriptions();
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			fw.write(PRETTY_SPACE);
			fw.write("START");
			fw.write(PRETTY_SPACE);
			for(SubscriptionVO o : subscriptions) { fw.write(o.toString()  + "\n"); }
			fw.write(PRETTY_SPACE);
			fw.write("");
			fw.write("");
			fw.write("COMPLETED");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
