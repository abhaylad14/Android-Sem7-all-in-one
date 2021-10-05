package com.example.activitylifecycledemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<Contact> ContactList;
    LayoutInflater inflater = null;

    public CustomAdapter(Activity ac,ArrayList ContactList)
    {
        //super(ac,R.layout.list_item,ContactList);
        this.activity = ac;
        this.ContactList = ContactList;
        //inflater = ac.getLayoutInflater();
        //inflater = (LayoutInflater)ac.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return ContactList.size();
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
        LayoutInflater inflater=activity.getLayoutInflater();
        //view = LayoutInflater.from(activity).inflate(R.layout.list_item, viewGroup, false);
        View rowView=inflater.inflate(R.layout.list_item, null,true);
        //Log.i("row",view.findViewById(R.id.lblID) + "");
        TextView lblKey = (TextView) rowView.findViewById(R.id.lblKey);
        TextView Name = (TextView) rowView.findViewById(R.id.lblName);
        TextView PhoneNo = (TextView) rowView.findViewById(R.id.lblPhoneNo);

        //Log.i("ID", ContactList.get(i).get_ID() + "");
       // lblKey.setText(String.valueOf(ContactList.get(i).get_ID()));
        Name.setText(ContactList.get(i).get_Name());
        PhoneNo.setText(ContactList.get(i).get_PhoneNo());
        /*View v = view;
        int position = i;
        if(v == null)
        {
            holder = new ViewHolder();
            v = inflater.inflate(R.layout.list_item,null);
            holder.lblID = (TextView)v.findViewById(R.id.lblID);
            holder.lblName = (TextView)v.findViewById(R.id.lblName);
            holder.lblPhoneNo = (TextView) v.findViewById(R.id.lblPhoneNo);
            v.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)v.getTag();
        }
        holder.lblID.setText(ContactList.get(position).get_ID());
        holder.lblName.setText(ContactList.get(position).get_Name());
        holder.lblPhoneNo.setText(ContactList.get(position).get_PhoneNo());*/
        return rowView;
    }
}
