package com.greatwideweb.mock;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.ThumbnailDetails;
import com.greatwideweb.youtube.vo.SearchResultWrapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by dave on 5/6/2016.
 */
public class UITestDataProvider {
    public List<SearchResultWrapper> getMockedSearchResults() {
        List<SearchResult> data = new ArrayList<SearchResult>();


        ThumbnailDetails thumbnails = new ThumbnailDetails();
        thumbnails.setDefault(buildImage("https://i.ytimg.com/vi/ORSDGg3hTao/default.jpg", 90, 120));
        thumbnails.setHigh(buildImage("https://i.ytimg.com/vi/ORSDGg3hTao/hqdefault.jpg", 360, 480));
        thumbnails.setMedium(buildImage("https://i.ytimg.com/vi/ORSDGg3hTao/mqdefault.jpg", 180, 320));
        SearchResultSnippet snippet = new SearchResultSnippet();
        snippet.setThumbnails(thumbnails);
        snippet.setChannelId("UCbmNph6atAoGfqLoCL_duAg");
        snippet.setChannelTitle("Talks at Google");
        snippet.setDescription("Dr. Anne Yoder, Director of the Duke Lemur Center in Durham, NC, presents a talk on Madagascar's mouse lemurs. This talk focuses on the integrative ...");
        snippet.setTitle("Anne Yoder: Madagascar’s mouse lemurs and speciation, climate change, & (possibly) Alzheimer's");
        Calendar c = new GregorianCalendar(2016, 3, 14);
        snippet.setPublishedAt(new DateTime(c.getTime()));
        SearchResult searchResult = new SearchResult();
        searchResult.setSnippet(snippet);
        data.add(searchResult);


        thumbnails = new ThumbnailDetails();
        thumbnails.setDefault(buildImage("https://i.ytimg.com/vi/Uk1ZpxuYic8/default.jpg", 90, 120));
        thumbnails.setHigh(buildImage("https://i.ytimg.com/vi/Uk1ZpxuYic8/hqdefault.jpg", 360, 480));
        thumbnails.setMedium(buildImage("https://i.ytimg.com/vi/Uk1ZpxuYic8/mqdefault.jpg", 180, 320));
        snippet = new SearchResultSnippet();
        snippet.setThumbnails(thumbnails);
        snippet.setChannelId("UCbmNph6atAoGfqLoCL_duAg");
        snippet.setChannelTitle("Talks at Google");
        snippet.setDescription("Barbara Arrowsmith-Young visited Google's office in Seattle, WA to discuss her book \\\"The Woman Who Changed Her Brain\\\" and the latest research in cognitive ...");
        snippet.setTitle("Barbara Arrowsmith-Young: \\\"The Woman Who Changed Her Brain\\\"");
        c = new GregorianCalendar(2016, 3, 21);
        snippet.setPublishedAt(new DateTime(c.getTime()));
        searchResult = new SearchResult();
        searchResult.setSnippet(snippet);
        data.add(searchResult);


        thumbnails = new ThumbnailDetails();
        thumbnails.setDefault(buildImage("https://i.ytimg.com/vi/u4acIpWrnzo/default.jpg", 90, 120));
        thumbnails.setHigh(buildImage("https://i.ytimg.com/vi/u4acIpWrnzo/hqdefault.jpg", 360, 480));
        thumbnails.setMedium(buildImage("https://i.ytimg.com/vi/u4acIpWrnzo/mqdefault.jpg", 180, 320));
        snippet = new SearchResultSnippet();
        snippet.setThumbnails(thumbnails);
        snippet.setChannelId("UCbmNph6atAoGfqLoCL_duAg");
        snippet.setChannelTitle("Talks at Google");
        snippet.setDescription("As a Gamification Pioneer, Author, and International Keynote Speaker, Yu-Kai Chou explains the reason why so many products are function-focused (as ...");
        snippet.setTitle("Yu-kai Chou: \\\"A Framework on Actionable Gamification\\\"");
        c = new GregorianCalendar(2016, 3, 20);
        snippet.setPublishedAt(new DateTime(c.getTime()));
        searchResult = new SearchResult();
        searchResult.setSnippet(snippet);
        data.add(searchResult);

        thumbnails = new ThumbnailDetails();
        thumbnails.setDefault(buildImage("https://i.ytimg.com/vi/3Ta4lpf2i84/default.jpg", 90, 120));
        thumbnails.setHigh(buildImage("https://i.ytimg.com/vi/3Ta4lpf2i84/hqdefault.jpg", 360, 480));
        thumbnails.setMedium(buildImage("https://i.ytimg.com/vi/3Ta4lpf2i84/mqdefault.jpg", 180, 320));
        snippet = new SearchResultSnippet();
        snippet.setThumbnails(thumbnails);
        snippet.setChannelId("UCbmNph6atAoGfqLoCL_duAg");
        snippet.setChannelTitle("Talks at Google");
        snippet.setDescription("David Burkus visited Google's office in Cambridge, MA to discuss his book, \\\"Under New Management: How Leading Organizations Are Upending Business as ...\"");
        snippet.setTitle("David Burkus: \\\"Under New Management\\\" ");
        c = new GregorianCalendar(2016, 3, 12);
        snippet.setPublishedAt(new DateTime(c.getTime()));
        searchResult = new SearchResult();
        searchResult.setSnippet(snippet);
        data.add(searchResult);

        List<SearchResultWrapper> result = new ArrayList<SearchResultWrapper>();
        for (SearchResult s : data) {
            result.add(new SearchResultWrapper(s));
        }

        return result;
    }

    private Thumbnail buildImage(String url, long h, long w) {
        Thumbnail t = new Thumbnail();
        t.setUrl(url);
        t.setHeight(h);
        t.setWidth(w);
        return t;
    }
}