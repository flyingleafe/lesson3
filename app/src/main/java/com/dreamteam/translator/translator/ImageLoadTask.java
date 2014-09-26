package com.dreamteam.translator.translator;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by anton on 25/09/14.
 */
public class ImageLoadTask extends AsyncTask<String, Void, Drawable> {
    private Drawable[] cachedImages;
    private int position;

    public ImageLoadTask(Drawable[] cachedImages, int position) {
        this.cachedImages = cachedImages;
        this.position = position;
    }

    @Override
    protected Drawable doInBackground(String... urls) {
        String url = urls[0];

        try {
            InputStream is = (InputStream) new URL(url).getContent();
            return Drawable.createFromStream(is, "");
        } catch (IOException e) {
            // TODO: maybe inform user on error?
            return null;
        }
    }

    @Override
    protected void onPostExecute(Drawable d) {
        super.onPostExecute(d);
        cachedImages[position] = d;
    }
}
