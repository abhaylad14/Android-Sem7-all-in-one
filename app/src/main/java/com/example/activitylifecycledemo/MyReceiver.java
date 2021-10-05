package com.example.activitylifecycledemo;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            NotificationCompat.Builder buider = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.img1)
                    .setContentTitle("New App installed")
                    .setContentText("My Receiver called")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(0, buider.build());
        }

        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            NotificationCompat.Builder buider = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.img1)
                    .setContentTitle("Application Removed")
                    .setContentText("My Receiver called")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(0, buider.build());
        }

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            NotificationCompat.Builder buider = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.img1)
                    .setContentTitle("BOOT Completed")
                    .setContentText("Device is ready to use")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(0, buider.build());
        }
        if (intent.getAction().equals("android.intent.action.ACTION_SHUTDOWN")) {
            Log.i("shutdown","Device is shutting down");
            NotificationCompat.Builder buider = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.img1)
                    .setContentTitle("Device is shutting down")
                    .setContentText("My Receiver called")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(0, buider.build());
        }
        if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String State = extras.getString(TelephonyManager.EXTRA_STATE);
                Toast.makeText(context,State,Toast.LENGTH_LONG).show();
                if (State.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {
                    String Phone = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("Call Manager")
                            .setContentText("Incoming call from : " + Phone)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setAutoCancel(true);

                    NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    nm.notify(0, builder.build());

                }
            }
        }
    }
}
