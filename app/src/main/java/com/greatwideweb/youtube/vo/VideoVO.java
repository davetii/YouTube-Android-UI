package com.greatwideweb.youtube.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.greatwideweb.youtube.util.YoutubeUtil;

public class VideoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "VideoVO [channelId=" + channelId
				+ ", channelTitle=" + channelTitle + ", title=" + title + ", publishedAt=" + publishedAt
				+ ", description=" + description + ", defaultImage=" + defaultImage + ", mediumImage=" + mediumImage
				+ ", largeImage=" + largeImage + ", maxRes=" + maxRes + "]";
	}
	private final String id;
	private final String channelId;
	private final String channelTitle;
	private final String title;
	private final Date publishedAt;
	private final String formattedPublishedAt;
	private final String description;
	private RemoteImage defaultImage;
	private RemoteImage mediumImage;
	private RemoteImage largeImage;
	private RemoteImage maxRes;
	private final String kind;


	public VideoVO(SearchResult result) {
		if(YoutubeUtil.hasSnippet(result)) {
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
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-mm-dd");
				this.formattedPublishedAt = sdf.format(this.publishedAt);
			} else {
				this.publishedAt= null;
				this.formattedPublishedAt = null;
			}
			if(YoutubeUtil.hasThumbnails(result)) {
				this.defaultImage = YoutubeUtil.thumbNailToRemoteImage(result.getSnippet().getThumbnails().getDefault());
				this.mediumImage = YoutubeUtil.thumbNailToRemoteImage(result.getSnippet().getThumbnails().getMedium());
				this.largeImage = YoutubeUtil.thumbNailToRemoteImage(result.getSnippet().getThumbnails().getHigh());
				this.maxRes = YoutubeUtil.thumbNailToRemoteImage(result.getSnippet().getThumbnails().getMaxres());
			} else {
				this.defaultImage= null;
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
			this.defaultImage = null;
			this.mediumImage = null;
			this.largeImage = null;
			this.maxRes = null;
			this.formattedPublishedAt = null;
		}
	}
	

	public String getChannelId() { return this.channelId; }
	public String getChannelTitle() { return this.channelTitle; }

	public String prettyText() {
		return " published: " + this.publishedAt + "ChannelId: " + this.channelId + " Channel: " + this.channelTitle + " id:" + this.id + " kind: " + this.kind + " Show: " + this.title +  " description: " + this.description;
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

	public RemoteImage getDefaultImageImage() { return this.defaultImage; }

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

	public String getFormattedPublishedAt() { return this.formattedPublishedAt;}

	public String getDetails() {
		return this.formattedPublishedAt + "  .  " + this.channelTitle;
	}

	public String getFormattedTitle() {
		return this.title.replace("\\", "");
	}


}
