package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    SearchView svCities;
    ListView lstCities;
    ArrayAdapter<String> adapter;
   // String[] Cities = {"surat","ahemdabad","navsari","Valsad","vapi"};
   // ArrayList<String> cities;
    String[] cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ControlInitialization();
        EventsHandling();
    }

    private void ControlInitialization()
    {
        svCities = findViewById(R.id.svCities);
        lstCities = findViewById(R.id.lstCities);

        /*cities = new ArrayList<String>();
        cities.add("surat");
        cities.add("valsad");
        cities.add("vapi");*/
        cities = getResources().getStringArray(R.array.Cities);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,cities);
        lstCities.setAdapter(adapter);
    }

    private void EventsHandling()
    {
        svCities.setOnQueryTextListener(new SearchView.
                OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
    }
}