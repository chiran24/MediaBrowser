package com.gbsoft.mediabrowser;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;

import model.Video;

public class VideosDisplayActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    private ArrayList<Video> videos;
    private ConstraintLayout constraintLayout;
    private RecyclerView recyclerView;
    private VideoListAdapter adapter;
    private VideoView videoView;
    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_display);

        videos = getAllVideos();

        recyclerView = findViewById(R.id.recyclerVideoList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VideoListAdapter(this, videos);
        recyclerView.setAdapter(adapter);

        constraintLayout = findViewById(R.id.constraintLayout);
        if(videos.isEmpty()){
            Snackbar.make(constraintLayout, "There are no videos", Snackbar.LENGTH_SHORT).show();
        }else{
            Snackbar.make(constraintLayout, "There are "+videos.size()+" videos", Snackbar.LENGTH_SHORT).show();
        }

        videoView = findViewById(R.id.videoView);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(this);

        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(new MediaController.MediaPlayerControl() {
            @Override
            public void start() {
                videoView.start();
            }

            @Override
            public void pause() {
                videoView.pause();
            }

            @Override
            public int getDuration() {
                return videoView.getDuration();
            }

            @Override
            public int getCurrentPosition() {
                return videoView.getCurrentPosition();
            }

            @Override
            public void seekTo(int i) {
                videoView.seekTo(i);
            }

            @Override
            public boolean isPlaying() {
                return videoView.isPlaying();
            }

            @Override
            public int getBufferPercentage() {
                return videoView.getBufferPercentage();
            }

            @Override
            public boolean canPause() {
                return videoView.canPause();
            }

            @Override
            public boolean canSeekBackward() {
                return videoView.canSeekBackward();
            }

            @Override
            public boolean canSeekForward() {
                return videoView.canSeekForward();
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public int getAudioSessionId() {
                return videoView.getAudioSessionId();
            }
        });
        controller.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++index;
                if(index == videos.size()) index = 0;
                videoView.setVideoURI(Uri.fromFile(new File(videos.get(index).getVideoPath())));
                videoView.start();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --index;
                if(index == -1) index = videos.size()-1;
                videoView.setVideoURI(Uri.fromFile(new File(videos.get(index).getVideoPath())));
                videoView.start();
            }
        });

        videoView.setMediaController(controller);
        videoView.setScaleY(1f);
        videoView.setScaleX(1f);
        controller.show();
    }
    public void playSelected(int pos){
        index = pos;
        videoView.setVideoURI(Uri.fromFile(new File(videos.get(index).getVideoPath())));
        videoView.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        ++index;
        if(index == videos.size()) index = 0;
        videoView.setVideoURI(Uri.fromFile(new File(videos.get(index).getVideoPath())));
        videoView.start();
    }

    private ArrayList<Video> getAllVideos(){
        ArrayList<Video> videoArrayList = new ArrayList<>();
        Uri videoUri = MediaStore.Video.Media.INTERNAL_CONTENT_URI;
        Cursor cursor = getContentResolver().query(videoUri, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                String size = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                String height = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.HEIGHT));
                String width = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.WIDTH));
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                videoArrayList.add(new Video(name, size, path, height, width, duration));
            }while(cursor.moveToNext());
        }

        videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        cursor = getContentResolver().query(videoUri, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                String size = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                String heigth = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.HEIGHT));
                String width = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.WIDTH));
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                videoArrayList.add(new Video(name, size, path, heigth, width, duration));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return videoArrayList;
    }

}
