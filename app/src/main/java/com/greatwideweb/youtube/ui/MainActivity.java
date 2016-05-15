package com.greatwideweb.youtube.ui;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import com.greatwideweb.mock.UITestDataProvider;
import com.greatwideweb.youtube.ui.support.HomeTabsPagerAdapter;
import com.greatwideweb.youtube.vo.YoutubeItemVO;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home_white_36dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_subscriptions_black_36dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_search_black_36dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final HomeTabsPagerAdapter adapter = new HomeTabsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch(tab.getPosition()) {
                    case 0: tab.setIcon(R.drawable.ic_home_white_36dp); break;
                    case 1: tab.setIcon(R.drawable.ic_subscriptions_white_36dp); break;
                    case 2: tab.setIcon(R.drawable.ic_search_white_36dp); break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0: tab.setIcon(R.drawable.ic_home_black_36dp); break;
                    case 1: tab.setIcon(R.drawable.ic_subscriptions_black_36dp); break;
                    case 2: tab.setIcon(R.drawable.ic_search_black_36dp); break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.close_menu_item: {
                finish();
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
