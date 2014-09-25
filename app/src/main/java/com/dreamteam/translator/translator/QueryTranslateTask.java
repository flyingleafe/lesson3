package com.dreamteam.translator.translator;

import android.os.AsyncTask;

/**
 * Created by flyingleafe on 25.09.14.
 */
public class QueryTranslateTask extends AsyncTask<String, Void, String> {

    LoadingScreen screen;

    public QueryTranslateTask(LoadingScreen screen) {
        this.screen = screen;
        // do the fukken job
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}
