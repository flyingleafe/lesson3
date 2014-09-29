package com.dreamteam.translator.translator;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by anton on 25/09/14.
 */
public class ImageLoadTask extends AsyncTask<String, Void, Drawable> {
    private ImageView imageView;
    private Drawable[] cachedImages;
    private Integer position;
    Context ctx;

    public ImageLoadTask(ImageView imageView, Drawable[] cachedImages, int position, Context ctx) {
        this.imageView = imageView;
        this.cachedImages = cachedImages;
        this.position = position;
        this.ctx = ctx;
    }

    public ImageLoadTask(Context ctx, ImageView imageView) {
        this.ctx = ctx;
        this.imageView = imageView;
    }

    @Override
    protected Drawable doInBackground(String... urls) {
        String url = urls[0];

        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable initial = Drawable.createFromStream(is, "");
            return initial;
        } catch (IOException e) {
            return ctx.getResources().getDrawable(R.drawable.image_error);
        }
    }

    @Override
    protected void onCancelled(Drawable drawable) {
        super.onCancelled(drawable);
        Drawable error = ctx.getResources().getDrawable(R.drawable.image_error);
        imageView.setImageDrawable(error);
        cachedImages[position] = error;
    }

    @Override
    protected void onPostExecute(Drawable d) {
        super.onPostExecute(d);
        if (cachedImages != null && cachedImages[position] == null) {
            cachedImages[position] = d;
        }
        imageView.setImageDrawable(d);
    }
}
