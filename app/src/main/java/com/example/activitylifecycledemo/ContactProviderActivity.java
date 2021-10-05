package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ContactProviderActivity extends AppCompatActivity {
    ListView lstContacts;
    SimpleCursorAdapter adapter;
    MatrixCursor matrixCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_provider);
        ControlInitialization();
    }

    private void ControlInitialization() {
        lstContacts = findViewById(R.id.lstContacts);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) +
                ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS,
                    Manifest.permission.SEND_SMS}, 0);

        } else {
            matrixCursor = new MatrixCursor(new String[]{"_Id", "Name", "PhoneNo", "Email"});

            Uri contactUri = ContactsContract.Contacts.CONTENT_URI;
            Log.i("uri", contactUri.toString());


            Cursor contactCursor = getContentResolver().query(contactUri, null,
                    null, null,
                    ContactsContract.Contacts.DISPLAY_NAME + " DESC");
            if (contactCursor.moveToFirst()) {
                do {
                    long contactId = contactCursor.getLong(contactCursor.getColumnIndex("_ID"));
                    String Name = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.
                            Contacts.DISPLAY_NAME));

                    Integer HasPhone = contactCursor.getInt(contactCursor.getColumnIndex(ContactsContract.Contacts.
                            HAS_PHONE_NUMBER));
                    String PhoneNo = "";
                    if (HasPhone > 0) {
                        Cursor cp = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                                new String[]{String.valueOf(contactId)}, null);
                        if (cp != null && cp.moveToFirst()) {
                            PhoneNo = cp.getString(cp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        }
                    }

                    String Email = "";
                    Cursor ce = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=?",
                            new String[]{String.valueOf(contactId)}, null);
                    if (ce != null & ce.moveToFirst()) {
                        Email = ce.getString(ce.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    }

                    matrixCursor.addRow(new Object[]{Long.toString(contactId), Name, PhoneNo, Email});
                } while (contactCursor.moveToNext());

                adapter = new SimpleCursorAdapter(this, R.layout.row_item, matrixCursor,
                        new String[]{"_Id", "Name", "PhoneNo", "Email"}, new int[]{R.id.lblID, R.id.lblName,
                        R.id.lblPhoneNo,
                        R.id.lblEmail
                });
                lstContacts.setAdapter(adapter);

                lstContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String MobileNo = matrixCursor.getString(2);

                        /*SmsManager manager = SmsManager.getDefault();
                        manager.sendTextMessage(MobileNo,null,"Hello...Good Morning",null,null);
                        Toast.makeText(getApplicationContext(),"SMS Sent successfully",Toast.LENGTH_LONG).show();*/

                        Intent objIntent= new Intent(Intent.ACTION_SENDTO);
                        objIntent.putExtra("sms_body","Good Morning");
                        objIntent.putExtra("address",MobileNo);
                        objIntent.setData(Uri.parse("smsto:"));
                        startActivity(objIntent);
                    }
                });
            }
        }
    }


}