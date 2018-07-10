package model;

import java.io.Serializable;

/**
 * Created by Ravi Lal Pandey on 18/01/2018.
 */

public class Image implements Serializable {
    private String imageName ;
    private String imagePath;
    private String imageHeight;
    private String imageWidth;
    private String imageSize;
    private String imageTakeDate;

    public Image(String imageName, String imagePath, String imageHeight, String imageWidth, String imageSize, String imageTakeDate) {
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.imageSize = imageSize;
        this.imageTakeDate = imageTakeDate;
    }


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageTakeDate() {
        return imageTakeDate;
    }

    public void setImageTakeDate(String imageTakeDate) {
        this.imageTakeDate = imageTakeDate;
    }

}
