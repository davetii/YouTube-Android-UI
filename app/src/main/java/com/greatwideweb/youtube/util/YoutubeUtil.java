package com.greatwideweb.youtube.util;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.Thumbnail;
import com.greatwideweb.youtube.vo.RemoteImage;

/**
 * Created by dave on 5/7/2016.
 */
public class YoutubeUtil {

    public static RemoteImage thumbNailToRemoteImage(Thumbnail thumbnail) {
        if(thumbnail == null) {
            return null;
        } else {
            return new RemoteImage(thumbnail.getHeight().toString(), thumbnail.getWidth().toString(), thumbnail.getUrl());
        }
    }

    public static  boolean hasThumbnails(SearchResult arg) {
        if(arg.getSnippet().getThumbnails() != null) return true;
        return false;
    }

    public static  boolean hasThumbnails(Subscription arg) {
        if(arg.getSnippet().getThumbnails() != null) return true;
        return false;
    }

    public static  boolean hasSnippet(SearchResult arg) {
        if(arg != null && arg.getSnippet() != null) return true;
        return false;
    }
}
