package com.example.activitylifecycledemo;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyAppReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.img1)
                    .setContentTitle("BOOT COMPLETED")
                    .setContentText("My APP RECEIVER CALLED")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(0,builder.build());
        }

        if(intent.getAction().equals("android.intent.action.PACKAGE_ADDED"))
        {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.img1)
                    .setContentTitle("NEW APPLICATION IS INSTALLED")
                    .setContentText("My APP RECEIVER CALLED")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(0,builder.build());
        }

        if(intent.getAction().equals("android.intent.action.PACKAGE_REMOVED"))
        {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.img1)
                    .setContentTitle("APPLICATION IS REMOVED")
                    .setContentText("My APP RECEIVER CALLED")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(0,builder.build());
        }
        if(intent.getAction().equals("android.intent.action.ACTION_SHUTDOWN"))
        {
            Log.d("SHUTDOWN","DEVICE IS SHUTTING DOWN");
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.img1)
                    .setContentTitle("Device is shutting down")
                    .setContentText("My APP RECEIVER CALLED")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(0,builder.build());
        }
    }
}
