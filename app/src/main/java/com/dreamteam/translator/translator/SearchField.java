package com.dreamteam.translator.translator;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class SearchField extends Activity {

    public static final String QUERY = "query";
    public static final String TRANSLATION_RESULT = "translationResult";
    public static final String IMAGES = "images";
    public static final long TRANSLATION_TIMEOUT = 4000;
    public static final long IMAGE_SEARCH_TIMEOUT = 4000;

    private Button searchButton;
    private EditText searchField;
    private Intent searchIntent;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_field);
        searchButton = (Button) findViewById(R.id.search_button);
        searchField = (EditText) findViewById(R.id.search_field);
        searchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                sendMessage(searchField);
                return true;
            }
        });
        searchIntent = new Intent(this, ResultsList.class);
        dialog = new ProgressDialog(this);
        dialog.setTitle(getString(R.string.dialog_title));
    }

    public void sendMessage(View view) {
        String query = searchField.getText().toString();
        Log.i("QUERY MSG", query);
        searchIntent.putExtra(QUERY, query);
        dialog.setMessage(getString(R.string.translating_msg));
        dialog.show();
        QueryTranslateTask translate = new QueryTranslateTask(this);
        TimeoutTaskRunner.runTask(translate.execute(query), TRANSLATION_TIMEOUT);
    }

    public void onTranslateFinished(String result) {
        searchIntent.putExtra(TRANSLATION_RESULT, result);
        ImageSearchTask imSearch = new ImageSearchTask(this);
        TimeoutTaskRunner.runTask(imSearch.execute(result), IMAGE_SEARCH_TIMEOUT);
        dialog.setMessage(getString(R.string.search_images_msg));
    }

    public void onTranslateCancelled() {
        dialog.dismiss();
        // TODO: show error message
    }

    public void onImageSearchFinished(ArrayList<String> urls) {
        searchIntent.putStringArrayListExtra(IMAGES, urls);
        dialog.dismiss();
        startActivity(searchIntent);
    }

    public void onImageSearchCancelled() {
        dialog.dismiss();
        // TODO: show error message
    }
}
