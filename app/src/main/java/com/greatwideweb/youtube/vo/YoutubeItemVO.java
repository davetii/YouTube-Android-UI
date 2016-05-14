package com.greatwideweb.youtube.vo;

/**
 * Created by dave on 5/14/2016.
 */
public class YoutubeItemVO {

    private final SubscriptionVO subscription;
    private final VideoVO video;

    public YoutubeItemVO(SubscriptionVO subscription, VideoVO video) {
        this.subscription = subscription;
        this.video = video;
    }

    public SubscriptionVO getSubscription() { return this.subscription; }
    public VideoVO getVideo() { return this.video; }
}
