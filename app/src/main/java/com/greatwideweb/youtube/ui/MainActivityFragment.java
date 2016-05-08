package com.greatwideweb.youtube.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.greatwideweb.mock.UITestDataProvider;
import com.greatwideweb.youtube.vo.SearchResultWrapper;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    List<SearchResultWrapper> mockVideos  = new ArrayList<SearchResultWrapper>();
    UITestDataProvider testDataProvider  = new UITestDataProvider();
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mockVideos = testDataProvider.getMockedSearchResults();
    }

    @Override
    public void onPause() { super.onPause(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.video_grid, container, false);
        GridView videoGrid = (GridView) v.findViewById(R.id.videoGridView);
        videoGrid.setAdapter(new VideoGridAdaptor(this.getActivity(), mockVideos));
        return v;
    }
}
