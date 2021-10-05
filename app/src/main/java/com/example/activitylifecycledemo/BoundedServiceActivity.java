package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BoundedServiceActivity extends AppCompatActivity {
    TextView txtRandom;
    Button btnRandom;
    MyBoundedService boundedService;
    boolean IsBounded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounded_service);

        ControlInitialization();
    }

    private void ControlInitialization()
    {
        txtRandom = findViewById(R.id.txtRandom);
        btnRandom = findViewById(R.id.btnRandom);
        Intent i = new Intent(this,MyBoundedService.class);
        bindService(i,serviceConnection,Context.BIND_AUTO_CREATE);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRandom.setText(String.valueOf(boundedService.getRandom()));
            }
        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundedService.LocalBinder binder = (MyBoundedService.LocalBinder)iBinder;
            boundedService = binder.getService();
            IsBounded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            IsBounded = false;
        }
    };

}