package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContentProviderActivity extends ListActivity {

    ArrayList<String> languages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        languages = new ArrayList<String>();
        languages.add("C");
        languages.add("C++");
        languages.add("Java");
        languages.add("Android");
        languages.add("C++");
        languages.add("Java");
        languages.add("Android");
        languages.add("C++");
        languages.add("Java");
        languages.add("Android");
        languages.add("C++");
        languages.add("Java");
        languages.add("Android");
        languages.add("C++");
        languages.add("Java");
        languages.add("Android");
        languages.add("C++");
        languages.add("Java");
        languages.add("Android");
        languages.add("jkdfhdfjh");
        languages.add("tertry");
        languages.add("cxbcbcvn");

       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.row_item,R.id.listitem,languages);
        setListAdapter(adapter);*/
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String SelectedItem = (String) getListView().getItemAtPosition(position);
        Toast.makeText(getApplicationContext(),"Selected item is : " + SelectedItem,Toast.LENGTH_LONG).show();
    }
}