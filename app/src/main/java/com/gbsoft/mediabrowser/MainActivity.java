package com.gbsoft.mediabrowser;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends ActivityGroup {
    private TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = findViewById(R.id.tabHost);
        tabHost.setup(getLocalActivityManager());

        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setContent(new Intent(MainActivity.this, PhotosDisplayActivity.class));
        tab1.setIndicator("Photos");
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setContent(new Intent(MainActivity.this, VideosDisplayActivity.class));
        tab2.setIndicator("Videos");
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3");
        tab3.setContent(new Intent(this, AppsDisplayActivity.class));
        tab3.setIndicator("Apps");
        tabHost.addTab(tab3);

    }
}
