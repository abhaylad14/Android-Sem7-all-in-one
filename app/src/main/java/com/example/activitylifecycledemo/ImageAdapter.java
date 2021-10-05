package com.example.activitylifecycledemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    Context c;
    Integer[]  imgs;
    public ImageAdapter(Context c, Integer[] i)
    {
        this.c = c;
        this.imgs = i;
    }
    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return imgs[i];
    }

    @Override
    public long getItemId(int i) {
        return imgs[i];
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imgView = new ImageView(c);
        imgView.setLayoutParams(new Gallery.LayoutParams(400,400));
        imgView.setImageResource(imgs[position]);
        return imgView;
    }
}
