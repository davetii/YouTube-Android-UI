package com.greatwideweb.youtube.ui;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.greatwideweb.mock.UITestDataProvider;
import com.greatwideweb.youtube.vo.VideoVO;
import com.greatwideweb.youtube.vo.YoutubeItemVO;

import org.mortbay.log.Log;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    Button otherButton;
    boolean isOnHomeScreen=true;
    MainActivityFragment mainActivityFragment=null;
    SubscriptionMainFragment subscriptionMainFragment=null;
    ArrayList<YoutubeItemVO> mockVideos  = new ArrayList<YoutubeItemVO>();
    UITestDataProvider testDataProvider  = new UITestDataProvider();
    Bundle dataBundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mockVideos = (ArrayList)testDataProvider.getMockedYoutubeItemsfromGoogleTalks();
        dataBundle.putSerializable("videos", mockVideos);
        handleOtherButtonClickEvent();
        handleMainView();
    }


    private void handleMainView() {
        if(mainActivityFragment == null) {
            mainActivityFragment = new MainActivityFragment();
            mainActivityFragment.setArguments(dataBundle);
        }

        FragmentManager fragMgr  = getFragmentManager();
        fragMgr.beginTransaction()
                .replace(R.id.content_frame, mainActivityFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void handleOtherButtonClickEvent() {
        otherButton = (Button) findViewById(R.id.other_button);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnHomeScreen) {
                    showSubscriptions();
                } else {
                    handleMainView();
                }
                isOnHomeScreen = !isOnHomeScreen;
            }
        });
    }

    private void showSubscriptions() {
        if(subscriptionMainFragment == null) {
            subscriptionMainFragment = new SubscriptionMainFragment();
            subscriptionMainFragment.setArguments(dataBundle);
        }
        FragmentManager fragMgr  = getFragmentManager();
        fragMgr.beginTransaction()
                .replace(R.id.content_frame,subscriptionMainFragment)
                .commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.close_menu_item: {
                Log.debug("close_menu_item clicked");
                finish();
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
