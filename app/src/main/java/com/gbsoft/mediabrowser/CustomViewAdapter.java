package com.gbsoft.mediabrowser;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi Lal Pandey on 19/01/2018.
 */

public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> appNames;
    private List<ApplicationInfo> listAppsInfo;
    private int layoutResource;
    public CustomViewAdapter(Context context, ArrayList<String> appNames, int resource) {
        this.context = context;
        this.appNames = appNames;
        this.layoutResource = resource;
        this.listAppsInfo = context.getPackageManager().getInstalledApplications(
                PackageManager.GET_META_DATA);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutResource, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(appNames.get(position));
        holder.appIcon.setImageDrawable(getDrawable(position));
    }

    @Override
    public int getItemCount() {
        return appNames.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView appIcon;
        private RelativeLayout relLayout;
        public MyViewHolder(View view) {
            super(view);
            appIcon = view.findViewById(R.id.appIcon);
            tvName = view.findViewById(R.id.tvName);
            relLayout = view.findViewById(R.id.relLayout);

            appIcon.setWillNotCacheDrawing(false);
            appIcon.setDrawingCacheQuality(ImageView.DRAWING_CACHE_QUALITY_LOW);

            relLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    relLayout.setSelected(!relLayout.isSelected());
                }
            });
        }

    }
    private int calculateInSampleSize(BitmapFactory.Options options, int reqHeight, int reqWidth) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private Bitmap decodeFromPath(String filePath, int reqHeight, int reqWidth) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqHeight, reqWidth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public Drawable getDrawable(int appIndex) {
         return listAppsInfo.get(appIndex).loadIcon(context.getPackageManager());
    }
}
