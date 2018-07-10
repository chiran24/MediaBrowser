package com.gbsoft.mediabrowser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import model.Image;

/**
 * Created by Ravi Lal Pandey on 19/01/2018.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Image> imageList;
    public MyAdapter(Context context, ArrayList<Image> images){
        this.context = context;
        this.imageList = images;
    }
    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int i) {
        return imageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view == null || view.getTag()== null){
            view = LayoutInflater.from(context).inflate(R.layout.photos_col, viewGroup, false);
            vh = new ViewHolder(view);
            final ViewHolder vh1 = vh;
            final int i1 = i;
            vh.imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            vh.imgView.setImageBitmap(PhotosDisplayActivity.decodeFromPath(imageList.get(i).getImagePath(),
                    50,50));
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    vh1.imgView.setImageBitmap(PhotosDisplayActivity.decodeFromPath(imageList.get(i1).getImagePath(),
                            300, 300));
                }
            }, 1500);
            vh.imgView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT));
            vh.imgView.setDrawingCacheQuality(ImageView.DRAWING_CACHE_QUALITY_AUTO);
            vh.imgView.setWillNotCacheDrawing(false);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }
        return view;
    }
    class ViewHolder{
        ImageView imgView;
        public ViewHolder(View view){
            imgView = view.findViewById(R.id.imgView);
        }
    }
}
