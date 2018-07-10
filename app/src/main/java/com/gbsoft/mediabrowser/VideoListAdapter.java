package com.gbsoft.mediabrowser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import model.Video;

/**
 * Created by Ravi Lal Pandey on 19/01/2018.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Video> videoList;
    public VideoListAdapter(Context context, ArrayList<Video> videos) {
        this.context = context;
        this.videoList = videos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.videos_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(videoList.get(position).getVideoName().trim());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.txtVideoName);
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((VideosDisplayActivity)context).playSelected(getAdapterPosition());
                }
            });
        }

    }
}

