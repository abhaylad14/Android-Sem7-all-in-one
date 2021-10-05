package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

public class GalleryActivity extends AppCompatActivity {
    Gallery gallery;
    ImageView imgFlowers;
    Integer[] images = {R.drawable.img1, R.drawable.img2,
    R.drawable.img3, R.drawable.img4, R.drawable.img5,
    R.drawable.img6, R.drawable.img7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ControlInitialization();
    }

    private void ControlInitialization()
    {
        gallery = findViewById(R.id.gallery);
        imgFlowers = findViewById(R.id.imgFlower);
        ImageAdapter adapter = new ImageAdapter(this,images);
        gallery.setAdapter(adapter);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                imgFlowers.setImageResource(images[position]);
            }
        });
    }


}