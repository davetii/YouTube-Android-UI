package com.greatwideweb.tmp;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by dave on 5/5/2016.
 * Testing to Classpath loading in Android Studio
 */
public class PropertyReaderTest {

    @Test
    public void verifyFirstName() throws IOException {
        PropertyReader reader = new PropertyReader();
        Assert.assertEquals("dave", reader.getFirstName());
    }
}
