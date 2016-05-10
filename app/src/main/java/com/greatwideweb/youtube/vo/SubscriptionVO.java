package com.greatwideweb.youtube.vo;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.Subscription;
import com.greatwideweb.youtube.util.YoutubeUtil;

import java.util.List;

/**
 * Created by dave on 5/7/2016.
 */
public class SubscriptionVO {

    private final String channelId;
    private final String channelTitle;
    private final String channelDescription;
    private final DateTime createdDate;
    private final String defaultImageURL;
    private final String mediumImageURL;
    private final String largeImageURL;

    public SubscriptionVO(Subscription s) {
        this.channelId = s.getSnippet().getResourceId().getChannelId();
        this.channelDescription = s.getSnippet().getDescription();
        this.channelTitle = s.getSnippet().getTitle();
        this.createdDate =s.getSnippet().getPublishedAt();
        if(YoutubeUtil.hasThumbnails(s)) {
            this.defaultImageURL = s.getSnippet().getThumbnails().getDefault().getUrl();
            this.mediumImageURL = s.getSnippet().getThumbnails().getMedium().getUrl();
            this.largeImageURL = s.getSnippet().getThumbnails().getHigh().getUrl();
        } else {
            this.defaultImageURL= null;
            this.mediumImageURL= null;
            this.largeImageURL= null;
        }
    }

    public String getChannelId() { return channelId; }
    public String getChannelTitle() { return channelTitle; }
    public String getChannelDescription() { return channelDescription; }
    public String getDefaultImageURL() { return this.defaultImageURL; }
    public String getMediumImageURL() { return this.mediumImageURL; }
    public String getLargeImageURL() { return this.largeImageURL; }

    @Override
    public String toString() {
        return "YoutubeSubscription [channelId=" + channelId + ", channelTitle=" + channelTitle + ", createdDate=" + this.createdDate + "]";
    }


}
