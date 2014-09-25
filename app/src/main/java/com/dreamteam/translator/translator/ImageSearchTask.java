package com.dreamteam.translator.translator;

import android.os.AsyncTask;
import android.util.Base64;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by flyingleafe on 25.09.14.
 */
public class ImageSearchTask extends AsyncTask<String, Void, ArrayList<String>> {
    static String API_KEY = "JR3pM/AAwKRgqllZrxYHzVzbW8Nc4D3TYAPySHVHcWQ";

    static String authStr;

    static {
        authStr = "Basic " + Base64.encodeToString((API_KEY + ":" + API_KEY).getBytes(), Base64.NO_WRAP);
    }

    SearchField screen;

    public ImageSearchTask(SearchField screen) {
        this.screen = screen;
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        String uri = "";
        try {
            uri = "https://api.datamarket.azure.com/Bing/Search/v1/Image?"
                    + "$format=json&"
                    + "$top=10&"
                    + "Query=%27" + URLEncoder.encode(strings[0], "utf-8") + "%27";
        } catch (UnsupportedEncodingException ignore) {
        }
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(uri);
        request.addHeader("Authorization", authStr);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseStr;
        JSONObject responseJson;
        ArrayList<String> urls = new ArrayList<String>();

        try {
            responseStr = client.execute(request, responseHandler);
            try {
                responseJson = new JSONObject(responseStr);
                JSONArray results = responseJson.getJSONObject("d").getJSONArray("results");
                int resultsCount = results.length();

                for (int i = 0; i < resultsCount; i++) {
                    JSONObject result = results.getJSONObject(i);
                    String mediaUrl = result.getString("MediaUrl");
                    urls.add(mediaUrl);
                }
            } catch (JSONException ignore) {
            } // Bing JSON is a valid JSON
        } catch (IOException e) {
            // TODO: some error reporting would be nice
            e.printStackTrace();
        }

        return urls;
    }

    @Override
    protected void onPostExecute(ArrayList<String> urls) {
        super.onPostExecute(urls);
        screen.onImageSearchFinished(urls);
    }
}
