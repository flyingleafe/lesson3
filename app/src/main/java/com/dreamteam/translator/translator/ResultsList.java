package com.dreamteam.translator.translator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ResultsList extends Activity {

    GridView gallery;
    TextView resultText;
    Intent starter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_list);
        starter = getIntent();
        gallery = (GridView) findViewById(R.id.gallery);
        resultText = (TextView) findViewById(R.id.result_text);
        ArrayList<String> urls = starter.getStringArrayListExtra(SearchField.IMAGES);
        Drawable[] cachedImages = new Drawable[urls.size()];
        gallery.setAdapter(new ImageAdapter(urls, gallery, cachedImages, this));
        resultText.setText(starter.getStringExtra(SearchField.TRANSLATION_RESULT));
    }

    public void goBack(View view) {
        Intent back = new Intent(this, SearchField.class);
        startActivity(back);
    }

    public void setImageView(final ImageView imageView, final Drawable d) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageDrawable(d);
            }
        });
    }
}
