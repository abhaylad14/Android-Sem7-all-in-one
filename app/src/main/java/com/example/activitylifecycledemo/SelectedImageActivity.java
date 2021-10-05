package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class SelectedImageActivity extends AppCompatActivity {
    ImageView imgSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_image);
        ControlInitialization();
    }

    private void ControlInitialization()
    {
        imgSelected = findViewById(R.id.imgSelected);
        int Position = getIntent().getExtras().getInt("position");
        ImageAdapter1 adapter = new ImageAdapter1(this);
        imgSelected.setImageResource(adapter.FlowerImages[Position]);
    }
}