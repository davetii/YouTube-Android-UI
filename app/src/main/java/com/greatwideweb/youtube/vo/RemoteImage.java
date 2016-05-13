package com.greatwideweb.youtube.vo;

import java.io.Serializable;

public class RemoteImage implements Serializable {
	
	@Override
	public String toString() {
		return "RemoteImage [height=" + height + ", width=" + width + ", url=" + url + "]";
	}

	private final String height;
	private final String width;
	private final String url;
	
	public RemoteImage(String height, String width, String url) {
		this.height = height;
		this.width = width;
		this.url = url;
	}

	public String getURL() {
		return this.url;
	}
}
