package com.dreamteam.translator.translator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class ImagePreview extends Activity {

    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        imgView = (ImageView) findViewById(R.id.preview_container);
        String url = getIntent().getStringExtra("url");
        url = url.replace("_q.jpg", ".jpg");
        ImageLoadTask loadTask = new ImageLoadTask(this, imgView);
        loadTask.execute(url);
    }
}
