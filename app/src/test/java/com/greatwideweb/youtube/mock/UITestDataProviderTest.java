package com.greatwideweb.youtube.mock;

import com.greatwideweb.mock.UITestDataProvider;
import com.greatwideweb.youtube.vo.SubscriptionVO;
import com.greatwideweb.youtube.vo.VideoVO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dave on 5/6/2016.
 */
public class UITestDataProviderTest {

    UITestDataProvider testDataProvider=null;
    @Before
    public void setup() {
        testDataProvider = new UITestDataProvider();
    }

    @Test
    public void ensureSubscriptionHasChannel() {
        SubscriptionVO s = testDataProvider.getMockedYoutubeItemsfromGoogleTalks().get(0).getSubscription();
        Assert.assertNotNull(s.getChannelId());
    }
    @Test
    public void ensureVideosHaveSize() {
        Assert.assertEquals(true,testDataProvider.getMockedYoutubeItemsfromGoogleTalks().size() > 0 );
    }

    @Test
    public void ensureUITestDataHasLargeImage() {
        VideoVO v = testDataProvider.getMockedYoutubeItemsfromGoogleTalks().get(0).getVideo();
        Assert.assertNotNull(v.getLargeImage().getURL());
    }

    @Test
    public void ensureUITestDataHasSubscriptionHighIMage() {
        SubscriptionVO s = testDataProvider.getMockedYoutubeItemsfromGoogleTalks().get(0).getSubscription();
        Assert.assertNotNull(s.getLargeImageURL());
    }
}
