package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationViewActivity extends AppCompatActivity {
    TextView txtMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);
        ControlInitialization();
    }

    private void ControlInitialization()
    {
        txtMessage = findViewById(R.id.txtMessage);

        String message = getIntent().getStringExtra("message");
        txtMessage.setText(message);
    }
}