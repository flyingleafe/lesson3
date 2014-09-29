package com.dreamteam.translator.translator;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class ImagePreview extends Activity {
    public static final long IMAGE_PREVIEW_TIMEOUT = 4000;

    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        imgView = (ImageView) findViewById(R.id.preview_container);
        String url = getIntent().getStringExtra("url");
        url = url.replace("_q.jpg", ".jpg");
        ImageLoadTask loadTask = new ImageLoadTask(this, imgView, url);
        TimeoutTaskRunner.runTask(loadTask, IMAGE_PREVIEW_TIMEOUT);
    }
}
