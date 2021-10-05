package com.example.activitylifecycledemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter1 extends BaseAdapter {
    Integer[] FlowerImages = {R.drawable.img1,
    R.drawable.img2, R.drawable.img3, R.drawable.img4,
    R.drawable.img5, R.drawable.img6, R.drawable.img7};
    Context c;
    public ImageAdapter1(Context c)
    {
        this.c = c;
    }
    @Override
    public int getCount() {
        return FlowerImages.length;
    }

    @Override
    public Object getItem(int i) {
        return FlowerImages[i];
    }

    @Override
    public long getItemId(int i) {
        return FlowerImages[i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView img;
        if(view == null)
        {
            img = new ImageView(c);
            img.setLayoutParams(new GridView.LayoutParams(400,400));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setPadding(10,10,10,10);
        }
        else
        {
            img = (ImageView)view;
        }
        img.setImageResource(FlowerImages[i]);
        return img;
    }
}
