package com.example.activitylifecycledemo;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import java.net.MalformedURLException;
import java.net.URL;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("My Intent Service");
    }

    @SuppressLint("WrongThread")
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String message = intent.getStringExtra("message");
        Log.d("message",message);
        SystemClock.sleep(3000);
        intent.setAction(ServiceActivity.FILTER_KEY);
        String broadcastMessage = "Intent service is called after the " +
                "pause of 3 seconds " + message;
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                intent.putExtra("broadcastMessage",broadcastMessage));
      /*  DownloadManager manager = (DownloadManager)getBaseContext().getSystemService(DOWNLOAD_SERVICE);

        Uri download = Uri.parse("http://www.gadgetsaint.com/wp-content/uploads/2016/11/cropped-web_hi_res_512.png");

        DownloadManager.Request request = new DownloadManager.Request(download);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/MyIntentDownloads/" + "Sample");

        manager.enqueue(request);*/
    }


}
