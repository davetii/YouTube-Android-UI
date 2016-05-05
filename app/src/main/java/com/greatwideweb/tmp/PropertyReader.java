package com.greatwideweb.tmp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dave on 5/5/2016.
 * Testing to Classpath loading in Android Studio
 */
public class PropertyReader {

   private final String firstName;

    public PropertyReader() throws IOException {
        Properties props=new Properties();
        InputStream in=this.getClass().getClassLoader().getResourceAsStream("test.properties");
        props.load(in);
        this.firstName=props.getProperty("firstname");
    }

    public String getFirstName(){
        return this.firstName;
    }

    public static void main(String[] args) throws IOException {
        PropertyReader reader = new PropertyReader();
        System.out.println(reader.getFirstName());
    }
}
