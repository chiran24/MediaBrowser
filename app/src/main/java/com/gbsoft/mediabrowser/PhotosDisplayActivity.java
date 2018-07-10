package com.gbsoft.mediabrowser;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterViewFlipper;

import java.io.IOException;
import java.util.ArrayList;

import model.Image;

public class PhotosDisplayActivity extends AppCompatActivity {
    private ConstraintLayout constLayout;
    private AdapterViewFlipper adapterViewFlipper;
    private Adapter flipperAdapter;
    private ArrayList<Image> imageList;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_display);

        try {
            imageList = getImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
        constLayout = findViewById(R.id.constLayout);
        adapterViewFlipper = findViewById(R.id.adapterFlipper);
        flipperAdapter = new MyAdapter(this, imageList);
        adapterViewFlipper.setAdapter(flipperAdapter);
        constLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterViewFlipper.showNext();
            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_photos_display, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_video_view) {
//            startActivity(new Intent(this, VideosDisplayActivity.class));
//        } else if (id == R.id.action_app_view) {
//            startActivity(new Intent(this, AppsDisplayActivity.class));
//        }
//        return true;
//    }

    public ArrayList<Image> getImages() throws IOException {
        ArrayList<Image> images = new ArrayList<>();
        Uri imagesUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor imageCursor = getContentResolver().query(imagesUri, null, null, null, null);
        if (imageCursor != null) {
            if (imageCursor.moveToFirst()) {
                do {
                    String imageName = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                    String imagePath = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    String imageSize = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.SIZE));
                    String imageHeight = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.HEIGHT));
                    String imageWidth = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.WIDTH));
                    String imageDateTaken = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN));
                    images.add(new Image(imageName, imagePath, imageHeight, imageWidth, imageSize, imageDateTaken));
                } while (imageCursor.moveToNext());
            }
        }
        imageCursor.close();
        imagesUri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
        imageCursor = getContentResolver().query(imagesUri, null, null, null, null);
        if (imageCursor != null) {
            if (imageCursor.moveToFirst()) {
                do {
                    String imageName = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                    String imagePath = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    String imageSize = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.SIZE));
                    String imageHeight = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.HEIGHT));
                    String imageWidth = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.WIDTH));
                    String imageDateTaken = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN));
                    images.add(new Image(imageName, imagePath, imageHeight, imageWidth, imageSize, imageDateTaken));
                } while (imageCursor.moveToNext());
            }
        }
        imageCursor.close();
        return images;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqHeight, int reqWidth) {
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

    public static Bitmap decodeFromPath(String filePath, int reqHeight, int reqWidth) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqHeight, reqWidth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

}
