package com.greatwideweb.youtube.ui;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greatwideweb.youtube.vo.SubscriptionVO;
import com.greatwideweb.youtube.vo.VideoVO;
import com.greatwideweb.youtube.vo.YoutubeItemVO;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dave on 5/13/2016.
 */
public class VideoContainerAdaptor extends RecyclerView.Adapter<VideoContainerAdaptor.VideoViewHolder> {

    private List<YoutubeItemVO> items;

    public VideoContainerAdaptor(List<YoutubeItemVO> items) {
        this.items = items;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card, parent, false);
        VideoViewHolder vvh = new VideoViewHolder(v);
        return vvh;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int i) {
        VideoVO video = this.items.get(i).getVideo();
        SubscriptionVO subscription =this.items.get(i).getSubscription();
        Uri videoThumbnailURL = Uri.parse(video.getLargeImage().getURL());
        Uri channelThumbnailURL = Uri.parse(subscription.getLargeImageURL());
        Context videoCtx = holder.videoThumbnail.getContext();
        Context channelThumbCtx = holder.channelThumbnail.getContext();
        Picasso.with(videoCtx).load(videoThumbnailURL).into(holder.videoThumbnail) ;
        Picasso.with(channelThumbCtx).load(channelThumbnailURL).into(holder.channelThumbnail) ;
        holder.videoTitle.setText(video.getFormattedTitle());
        holder.videoDescription.setText(video.getFormattedDescription());
        holder.videoDetails.setText(video.getDetails());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView videoTitle;
        TextView videoDescription;
        TextView videoDetails;
        ImageView videoThumbnail;
        ImageView channelThumbnail;

        public VideoViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.videoCardView);
            videoTitle = (TextView) itemView.findViewById(R.id.videoTitle);
            videoDescription = (TextView) itemView.findViewById(R.id.videoDescription);
            videoDetails = (TextView) itemView.findViewById(R.id.videoDetails);
            videoThumbnail = (ImageView)itemView.findViewById(R.id.videoThumbnail);
            channelThumbnail = (ImageView)itemView.findViewById(R.id.channelThumbnail);
        }
    }
}
