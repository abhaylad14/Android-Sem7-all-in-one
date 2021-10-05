package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class GridActivity extends AppCompatActivity {
    GridView gvFlowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ControlInitialization();
    }

    private void ControlInitialization()
    {
        gvFlowers = findViewById(R.id.gvFlowers);
        ImageAdapter1 adapter = new ImageAdapter1(this);
        gvFlowers.setAdapter(adapter);

        gvFlowers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent objIntent = new Intent(getApplicationContext(),SelectedImageActivity.class);
                objIntent.putExtra("position",i);
                startActivity(objIntent);
            }
        });
    }
}