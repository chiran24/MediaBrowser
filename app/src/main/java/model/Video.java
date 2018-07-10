package model;

import java.io.Serializable;

/**
 * Created by Ravi Lal Pandey on 18/01/2018.
 */

public class Video implements Serializable {
    private int id;
    private String videoName;
    private String videoSize;
    private String videoPath;
    private String videoHeight;
    private String videoWidth;
    private String videoDuration;

    public Video(String videoName, String videoSize, String videoPath, String videoHeight, String videoWidth, String videoDuration) {
        this.videoName = videoName;
        this.videoSize = videoSize;
        this.videoPath = videoPath;
        this.videoHeight = videoHeight;
        this.videoWidth = videoWidth;
        this.videoDuration = videoDuration;
    }

    public Video(int id, String videoName, String videoSize, String videoPath, String videoHeight, String videoWidth, String videoDuration) {
        this.id = id;
        this.videoName = videoName;
        this.videoSize = videoSize;
        this.videoPath = videoPath;
        this.videoHeight = videoHeight;
        this.videoWidth = videoWidth;
        this.videoDuration = videoDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(String videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(String videoWidth) {
        this.videoWidth = videoWidth;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }
}
