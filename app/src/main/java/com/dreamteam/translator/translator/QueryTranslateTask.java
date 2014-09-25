package com.dreamteam.translator.translator;

import android.os.AsyncTask;

/**
 * Created by flyingleafe on 25.09.14.
 */
public class QueryTranslateTask extends AsyncTask<String, Void, String> {

    SearchField screen;

    public QueryTranslateTask(SearchField screen) {
        this.screen = screen;
    }

    @Override
    protected String doInBackground(String... strings) {
        // do the fukken job
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OLOLOLOLO";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        screen.onTranslateFinished(s);
    }
}
