package com.dreamteam.translator.translator;

import android.content.Context;
import android.graphics.Bitmap;
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

    private ArrayList<Bitmap> bitmaps;
    private Context context;

    public ImageAdapter(ArrayList<Bitmap> bitmaps, Context ctx) {
        this.bitmaps = bitmaps;
        context = ctx;
    }

    public int getCount() {
        return bitmaps.size();
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
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageBitmap(bitmaps.get(position));
        return imageView;
    }
}
