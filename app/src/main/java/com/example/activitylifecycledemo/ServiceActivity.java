package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServiceActivity extends AppCompatActivity {
    Button btnStartService, btnStopService;
    EditText txtMessage;
    TextView lblDetails;
    MyReceiver receiver;
    public static String FILTER_KEY = "broadcastkey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ControlInitialization();

    }
    private void ControlInitialization()
    {
        btnStartService = findViewById(R.id.btnStartService);
        btnStopService = findViewById(R.id.btnStopService);
        lblDetails = findViewById(R.id.lblDetails);
        txtMessage = findViewById(R.id.txtMessage);


        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //String Message = txtMessage.getText().toString();
            Intent objIntent = new Intent(getApplicationContext(),
                    MyService.class);
            //objIntent.putExtra("message",Message);
            startService(objIntent);

            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(ServiceActivity.this,
                        MyService.class));
            }
        });


    }

    @Override
    protected void onStart() {
        setReceiver();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void setReceiver()
    {
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(FILTER_KEY);
        LocalBroadcastManager.getInstance(getApplicationContext()).
                registerReceiver(receiver,filter);
    }
    private class MyReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            String Message = intent.getStringExtra("broadcastMessage");
            lblDetails.setText(lblDetails.getText() + "\n" + Message);
        }
    }
}