package com.dreamteam.translator.translator;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by flyingleafe on 25.09.14.
 */
public class ImageAdapter extends BaseAdapter {

    private ArrayList<String> urls;
    private Context context;

    public ImageAdapter(ArrayList<String> urls, Context ctx) {
        this.urls = urls;
        context = ctx;
    }

    public int getCount() {
        return urls.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        ImageLoadTask loader = new ImageLoadTask(imageView);
        loader.execute(urls.get(position));

        return imageView;
    }
}
