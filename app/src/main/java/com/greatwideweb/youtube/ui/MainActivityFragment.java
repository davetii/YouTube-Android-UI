package com.greatwideweb.youtube.ui;

import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.greatwideweb.mock.UITestDataProvider;
import com.greatwideweb.youtube.ui.support.GridSpacingItemDecoration;
import com.greatwideweb.youtube.vo.VideoVO;
import com.greatwideweb.youtube.vo.YoutubeItemVO;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    List<YoutubeItemVO> mockVideos  = null;
    RecyclerView videoContainer=null;
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            mockVideos = (ArrayList<YoutubeItemVO>)getArguments().getSerializable("videos");
        }catch (Exception e){
            mockVideos = new ArrayList<YoutubeItemVO>();
        }
    }

    @Override
    public void onPause() { super.onPause(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.video_grid, container, false);
        videoContainer = (RecyclerView) v.findViewById(R.id.videoGridView);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            //mRecycler.setLayoutManager(new GridLayoutManager(mContext, 2));
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
            videoContainer.setLayoutManager(layoutManager);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //mRecycler.setLayoutManager(new GridLayoutManager(mContext, 4));
            videoContainer.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
            int spanCount = 1; // 3 columns
            int spacing = 10; // 50px
            boolean includeEdge = false;
            videoContainer.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        }

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        videoContainer.setHasFixedSize(true);

        // use a linear layout manager

        VideoContainerAdaptor adaptor  = new  VideoContainerAdaptor(mockVideos);
        videoContainer.setAdapter(adaptor);
        return v;
    }
}
