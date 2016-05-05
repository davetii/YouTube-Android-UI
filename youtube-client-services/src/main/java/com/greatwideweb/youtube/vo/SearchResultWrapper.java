package com.greatwideweb.youtube.vo;

import java.io.Serializable;
import java.util.Date;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

public class SearchResultWrapper implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "SearchResultWrapper [hasSnippet=" + hasSnippet + ", channelId=" + channelId
				+ ", channelTitle=" + channelTitle + ", title=" + title + ", publishedAt=" + publishedAt
				+ ", description=" + description + ", smallImage=" + smallImage + ", mediumImage=" + mediumImage
				+ ", largeImage=" + largeImage + ", maxRes=" + maxRes + "]";
	}
	private final String id;
	private boolean hasSnippet;
	private final String channelId;
	private final String channelTitle;
	private final String title;
	private final Date publishedAt;
	private final String description;
	private RemoteImage smallImage;
	private RemoteImage mediumImage;
	private RemoteImage largeImage;
	private RemoteImage maxRes;
	private final String kind;
	
	
	
	
	
	public SearchResultWrapper(SearchResult result) {
		if(hasSnippet(result)) {
			this.kind = result.getKind();
			if(result.getId() != null)
				this.id = result.getId().toString();
			else
				this.id = null;
			this.channelTitle = result.getSnippet().getChannelTitle();
			this.channelId = result.getSnippet().getChannelId();
			this.title = result.getSnippet().getTitle();
			this.description = result.getSnippet().getDescription();
			if(result.getSnippet().getPublishedAt() != null) {
				this.publishedAt = new Date(result.getSnippet().getPublishedAt().getValue());
			} else {
				this.publishedAt= null;
			}
			
			if(hasThumbnails(result)) {
				maybePopulateImage(result.getSnippet().getThumbnails().getStandard(), this.smallImage);
				maybePopulateImage(result.getSnippet().getThumbnails().getMedium(), this.mediumImage);
				maybePopulateImage(result.getSnippet().getThumbnails().getHigh(), this.largeImage);
				maybePopulateImage(result.getSnippet().getThumbnails().getMaxres(), this.maxRes);
			} else {
				this.smallImage= null;
				this.mediumImage= null;
				this.largeImage= null;
				this.maxRes= null;
			}
		} else {
			this.kind = null;
			this.id = null;
			this.channelTitle = null;
			this.channelId = null;
			this.title = null;
			this.description = null;
			this.publishedAt = null;
			this.smallImage = null;
			this.mediumImage = null;
			this.largeImage = null;
			this.maxRes = null;
		}
	}
	
	private void maybePopulateImage(Thumbnail thumbnail, RemoteImage remoteImage) {
		if(thumbnail == null) { remoteImage = null; } 
		else {
			remoteImage = new RemoteImage(thumbnail.getHeight().toString(), thumbnail.getWidth().toString(), thumbnail.getUrl()); 
		}
	}

	private boolean hasThumbnails(SearchResult arg) {
		if(arg.getSnippet().getThumbnails() != null) { return true; }
		return false;
	}
	private boolean hasSnippet(SearchResult arg) {
		if(arg != null && arg.getSnippet() != null){ return true; }
		return false;
	}
	public String getChannelId() { return this.channelId; }
	public String getChannelTitle() { return this.channelTitle; }

	public String prettyText() {
		return " published: " + this.publishedAt + "ChannelId: " + this.channelId + " Channel: " + this.channelTitle + " id:" + this.id + " kind: " + this.kind + " Show: " + this.title +  " description: " + this.description;
	}

	public boolean isHasSnippet() {
		return hasSnippet;
	}

	public String getTitle() {
		return title;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public String getDescription() {
		return description;
	}

	public RemoteImage getSmallImage() {
		return smallImage;
	}

	public RemoteImage getMediumImage() {
		return mediumImage;
	}

	public RemoteImage getLargeImage() {
		return largeImage;
	}

	public RemoteImage getMaxRes() {
		return maxRes;
	}
	
	public String getId() { return this.id; }

}
