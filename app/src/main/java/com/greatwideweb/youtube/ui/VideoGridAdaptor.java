package com.greatwideweb.youtube.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.greatwideweb.youtube.vo.SearchResultWrapper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dave on 5/5/2016.
 */
public class VideoGridAdaptor extends BaseAdapter {
    private Context ctx;
    private List<SearchResultWrapper> items;
    public VideoGridAdaptor(Context context, List<SearchResultWrapper> items) {
        this.ctx = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public SearchResultWrapper getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View card = view;
        if(card == null) {
            LayoutInflater inflater = LayoutInflater.from(this.ctx);
            card = inflater.inflate(R.layout.video_card, viewGroup, false);
            //card.setLayoutParams( new GridView.LayoutParams(GridView.AUTO_FIT, 500));
            TextView videoTitle = (TextView) card.findViewById(R.id.videoTitle);
            videoTitle.setText(items.get(i).getTitle());

            TextView videoDescription = (TextView) card.findViewById(R.id.videoDescription);
            videoDescription.setText(items.get(i).getDescription());

            TextView videoDetails = (TextView) card.findViewById(R.id.videoDetails);
            videoDetails.setText(items.get(i).getDetails());
            String videoThumbnailURL = items.get(i).getLargeImage().getURL();
            ImageView iv = (ImageView) card.findViewById(R.id.videoThumbnail);
            Picasso.with(this.ctx).load(videoThumbnailURL).into(iv);
        }
        return card;
    }
}
