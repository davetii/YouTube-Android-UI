package com.greatwideweb.youtube.vo;

public class RemoteImage {
	
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

}
