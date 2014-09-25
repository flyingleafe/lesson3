package com.dreamteam.translator.translator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by flyingleafe on 25.09.14.
 */
public class ImageSearchTask extends AsyncTask<String, Void, ArrayList<Bitmap>> {

    SearchField screen;

    public ImageSearchTask(SearchField screen) {
        this.screen = screen;
    }

    @Override
    protected ArrayList<Bitmap> doInBackground(String... strings) {
        // do the fukken job
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // replace all the following with proper result
        ArrayList<Bitmap> list = new ArrayList<Bitmap>();
        Bitmap sample = BitmapFactory.decodeResource(screen.getResources(), R.drawable.sample);
        list.add(sample);
        list.add(sample);
        list.add(sample);
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<Bitmap> bitmaps) {
        super.onPostExecute(bitmaps);
        screen.onImageSearchFinished(bitmaps);
    }
}
