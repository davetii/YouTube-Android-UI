package com.greatwideweb.youtube.ui.support;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.greatwideweb.mock.UITestDataProvider;
import com.greatwideweb.youtube.ui.MainActivityFragment;
import com.greatwideweb.youtube.ui.SearchFragment;
import com.greatwideweb.youtube.ui.SubscriptionMainFragment;
import java.util.ArrayList;


/**
 * Created by dave on 5/15/2016.
 */
public class HomeTabsPagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;
    private MainActivityFragment homeFragment;
    private SubscriptionMainFragment subscriptionFragment;
    SearchFragment searchFragment;


    public HomeTabsPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;

        homeFragment = new MainActivityFragment();
        homeFragment.setArguments(buildMockDataBundle());
        subscriptionFragment  = new SubscriptionMainFragment();
        searchFragment = new SearchFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return homeFragment;
            case 1: return subscriptionFragment;
            case 2: return searchFragment;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return this.numOfTabs;
    }

    private Bundle buildMockDataBundle() {
        UITestDataProvider testDataProvider  = new UITestDataProvider();
        Bundle dataBundle = new Bundle();
        dataBundle.putSerializable("videos", (ArrayList)testDataProvider.mockVideosgetMockedYoutubeItemsfromGoogleTalks());
        return dataBundle;
    }
}
