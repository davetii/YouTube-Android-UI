package com.greatwideweb.youtube.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.greatwideweb.youtube.vo.SearchResultWrapper;

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
            card.setLayoutParams( new GridView.LayoutParams(GridView.AUTO_FIT, 500));
            TextView VideoCardTitle = (TextView) card.findViewById(R.id.videoCardTitle);
            VideoCardTitle.setText(items.get(i).getTitle());
            TextView videoCardDate = (TextView) card.findViewById(R.id.videoCardDate);
            videoCardDate.setText(items.get(i).getFormattedPublishedAt());
            TextView videoCardDescription = (TextView) card.findViewById(R.id.videoCardDescription);
            videoCardDescription.setText(items.get(i).getDescription());
            TextView videoCardChannel = (TextView) card.findViewById(R.id.videoCardChannelTitle);
            videoCardChannel.setText(items.get(i).getChannelTitle());
        }
        return card;
    }
}
