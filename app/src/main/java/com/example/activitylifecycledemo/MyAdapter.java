package com.example.activitylifecycledemo;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<Contact> contactList;

    public MyAdapter(Activity a, ArrayList cList)
    {
        this.activity = a;
        this.contactList = cList;
    }
    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View rowview = inflater.inflate(R.layout.custom_list,null,true);
        Log.i("c","called");
        TextView txtID = (TextView)rowview.findViewById(R.id.lblKeyID);
        TextView txtName = (TextView)rowview.findViewById(R.id.lblName);
        TextView txtPhoneNo = (TextView)rowview.findViewById(R.id.lblPhoneNo);
        Log.i("ID",contactList.get(i).get_ID() + "");
        txtID.setText(String.valueOf(contactList.get(i).get_ID()));
        txtName.setText(contactList.get(i).get_Name());
        txtPhoneNo.setText(contactList.get(i).get_PhoneNo());
        return rowview;
    }
}
