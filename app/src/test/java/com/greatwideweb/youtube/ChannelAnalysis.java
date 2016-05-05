package com.greatwideweb.youtube;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.greatwideweb.youtube.mock.TestDataProvider;
import com.greatwideweb.youtube.service.YoutubeSearchService;
import com.greatwideweb.youtube.service.YoutubeService;
import com.greatwideweb.youtube.vo.SearchParameters;

import org.apache.commons.io.FileUtils;

public class ChannelAnalysis {

	private final static String SPACE_IND ="*********************************************\n";
	private final static String BREAK_LINE ="\n";
	
	public static void main(String[] args)  {
		TestDataProvider dataProvdider = new TestDataProvider();
		SearchParameters searchParameters = dataProvdider.getSearchParametersForredefiningStrength();
		
		YoutubeService factory;
		List<SearchResult> results = null;
		
		try {
			factory = new YoutubeService();
			YouTube youtubeService  = factory.getService();
			YoutubeSearchService service = new YoutubeSearchService(youtubeService);
			results = service.search(searchParameters);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String fullyPathedFile ="c:///tmp/output.txt";
		File f = new File(fullyPathedFile);
		FileWriter fw = null;
		
		try {
			FileUtils.forceDelete(f);
			f.createNewFile();
			fw = new FileWriter(f.getAbsoluteFile());
			fw.write(SPACE_IND);
			fw.write("START" + BREAK_LINE);
			fw.write(SPACE_IND);
			fw.write("Channel: " + searchParameters.getChannelId() + BREAK_LINE);
			fw.write(SPACE_IND);
			fw.write(BREAK_LINE);
			fw.write(BREAK_LINE);
			for(SearchResult s : results) {
				fw.write(s.toString() + BREAK_LINE);
			}
			
			fw.write("COMPLETED");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fw != null) { try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
		}
	}

}
