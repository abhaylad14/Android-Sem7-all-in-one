package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity {
    Button btnNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ControlInitialization();
    }

    private void ControlInitialization()
    {
        btnNotification = findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.img1)
                        .setContentTitle("Notification Demo")
                        .setContentText("This is notification demo")
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                Intent objIntent = new Intent(getApplicationContext(),NotificationViewActivity.class);
                objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                objIntent.putExtra("message","Hello...Good Morning Everyone");

                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,objIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pi);

                NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                nm.notify(0,builder.build());
            }
        });
    }
}