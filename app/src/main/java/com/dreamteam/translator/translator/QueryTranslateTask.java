package com.dreamteam.translator.translator;

import android.net.Uri;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by flyingleafe on 25.09.14.
 */
public class QueryTranslateTask extends AsyncTask<String, Void, String> {
    static String API_KEY = "trnsl.1.1.20140925T142452Z.a164cd5cbcda1edb.289f6c284c4f62a96de837c630c7a11356265282";

    SearchField screen;

    public QueryTranslateTask(SearchField screen) {
        this.screen = screen;
    }

    @Override
    protected String doInBackground(String... strings) {
        // build a GET request's URI
        String uri = Uri.parse("https://translate.yandex.net/api/v1.5/tr.json/translate")
                        .buildUpon()
                        .appendQueryParameter("key", API_KEY)
                        .appendQueryParameter("lang", "en-ru")
                        .appendQueryParameter("text", strings[0])
                        .build().toString();
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(uri);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseStr;
        String translatedText = "";
        JSONObject responseJson;
        try {
            responseStr = client.execute(request, responseHandler);
            try {
                responseJson = new JSONObject(responseStr);
                translatedText = responseJson.getJSONArray("text").get(0).toString();
            } catch (JSONException ignore) {} // Yandex JSON is a valid JSON
        } catch (IOException e) {
            // TODO: some error reporting would be nice
            e.printStackTrace();
        }
        return translatedText;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        screen.onTranslateCancelled();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        screen.onTranslateFinished(s);
    }
}
