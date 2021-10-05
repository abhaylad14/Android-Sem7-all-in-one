package com.example.activitylifecycledemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {
    EditText txtName, txtPhoneNo;
    Button btnAdd, btnUpdate;
    DatabaseHelper db;
    ListView lstContacts;
    ArrayAdapter<String> adapter;
    int contactID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ControlInitialization();
        ButtonClicks();
    }

    private void ControlInitialization()
    {
        txtName = findViewById(R.id.txtName);
        txtPhoneNo = findViewById(R.id.txtPhoneNo);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        lstContacts = findViewById(R.id.lstContacts);
        db = new DatabaseHelper(this);
        BindContacts();
    }

    private void ButtonClicks()
    {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = txtName.getText().toString();
                String PhoneNo = txtPhoneNo.getText().toString();

                int ID = db.AddContacts(new Contact(Name, PhoneNo));
                if(ID >= 1)
                {
                    Toast.makeText(getApplicationContext(),"data inserted successfully",Toast.LENGTH_LONG).show();
                    BindContacts();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error in insertion",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String PhoneNo = txtPhoneNo.getText().toString();

                int ID = db.UpdateContact(new Contact(contactID,name,PhoneNo));
                Toast.makeText(getApplicationContext(),ID + " ", Toast.LENGTH_LONG).show();
                if(ID >= 1)
                {

                }
                BindContacts();
            }
        });
    }

    private void BindContacts()
    {
        ArrayList<Contact> ContactList = db.GetAllContacts();
        MyAdapter myAdapter = new MyAdapter(ContactActivity.this,ContactList);
       // adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ContactList);
        lstContacts.setAdapter(myAdapter);
        registerForContextMenu(lstContacts);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Option");
        menu.add(0,v.getId(),0,"Update");
        menu.add(0,v.getId(),0,"Delete");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getTitle() == "Update")
        {
            int ID = GetID(item);

            Contact c = db.GetContactDetails(ID);
            txtName.setText(c.get_Name());
            txtPhoneNo.setText(c.get_PhoneNo());
            contactID = c.get_ID();

        }else if(item.getTitle() == "Delete")
        {
            int ID = GetID(item);
            db.DeleteContact(new Contact(ID));
            BindContacts();
        }
        return true;
    }

    private int GetID(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = menuInfo.position;
        String SelectedItem = adapter.getItem(position).toString();
        String[] SelectedColumn = SelectedItem.split(",");
        int ID = Integer.parseInt(SelectedColumn[0]);
        return ID;
    }
}