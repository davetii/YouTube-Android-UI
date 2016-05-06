package com.greatwideweb.youtube.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.greatwideweb.youtube.vo.SearchResultWrapper;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    List<SearchResultWrapper> mockVideos  = new ArrayList<SearchResultWrapper>();
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mockVideos.add(new SearchResultWrapper("Days of Our Lives", "Lukes return", "11/7/1984", "A length description saying something"));
        mockVideos.add(new SearchResultWrapper("General Hospital", "Something is amiss at the drinking fountain", "11/8/1984", "When Rick and Monnica meet at the drinking fountain sparks fly, Martina has a sex chnage operation"));
        mockVideos.add(new SearchResultWrapper("Days of Our Lives", "Lukes return", "11/7/1984", "A length description saying something"));
        mockVideos.add(new SearchResultWrapper("General Hospital", "Something is amiss at the drinking fountain", "11/8/1984", "When Rick and Monnica meet at the drinking fountain sparks fly, Martina has a sex chnage operation"));
        mockVideos.add(new SearchResultWrapper("Days of Our Lives", "Lukes return", "11/7/1984", "A length description saying something"));
        mockVideos.add(new SearchResultWrapper("General Hospital", "Something is amiss at the drinking fountain", "11/8/1984", "When Rick and Monnica meet at the drinking fountain sparks fly, Martina has a sex chnage operation"));
        mockVideos.add(new SearchResultWrapper("Days of Our Lives", "Lukes return", "11/7/1984", "A length description saying something"));
        mockVideos.add(new SearchResultWrapper("General Hospital", "Something is amiss at the drinking fountain", "11/8/1984", "When Rick and Monnica meet at the drinking fountain sparks fly, Martina has a sex chnage operation"));
        mockVideos.add(new SearchResultWrapper("Days of Our Lives", "Lukes return", "11/7/1984", "A length description saying something"));
        mockVideos.add(new SearchResultWrapper("General Hospital", "Something is amiss at the drinking fountain", "11/8/1984", "When Rick and Monnica meet at the drinking fountain sparks fly, Martina has a sex chnage operation"));
        mockVideos.add(new SearchResultWrapper("Days of Our Lives", "Lukes return", "11/7/1984", "A length description saying something"));
        mockVideos.add(new SearchResultWrapper("General Hospital", "Something is amiss at the drinking fountain", "11/8/1984", "When Rick and Monnica meet at the drinking fountain sparks fly, Martina has a sex chnage operation"));

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
