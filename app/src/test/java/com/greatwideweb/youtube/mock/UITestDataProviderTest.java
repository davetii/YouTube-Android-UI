package com.greatwideweb.youtube.mock;

import com.greatwideweb.mock.UITestDataProvider;

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
    public void ensureUITestDataHasSize() {
        Assert.assertEquals(true,testDataProvider.getMockedSearchResults().size() > 0 );
    }

    @Test
    public void ensureUITestDataHasLargeImage() {
        Assert.assertNotNull(testDataProvider.getMockedSearchResults().get(0).getLargeImage().getURL());
    }
}
