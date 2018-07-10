package com.gbsoft.mediabrowser;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AppsDisplayActivity extends AppCompatActivity {
    private RecyclerView recView;
    private CustomViewAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_display);

        recView = findViewById(R.id.recyclerView);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new CustomViewAdapter(this, getAllApps(), R.layout.apps_row);
        recView.setAdapter(myAdapter);

    }

    private ArrayList<String> getAllApps() {
        ArrayList<String> appsNameList = new ArrayList<>();
        List<ApplicationInfo> apps = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo pi : apps) {
            appsNameList.add(pi.loadLabel(getPackageManager()).toString());
            Log.d("appname", pi.packageName);
        }
        return appsNameList;
    }
}
