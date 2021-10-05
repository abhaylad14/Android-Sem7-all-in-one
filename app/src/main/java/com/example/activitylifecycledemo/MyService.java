package com.example.activitylifecycledemo;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    Timer timer = new Timer();
    int count = 0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Toast.makeText(getApplicationContext(),
               // "Service is created",Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
      //  PrintMessageRepetedly();
        try {
            new BackgroundTasks().execute(new URL("http://www.gadgetsaint.com/wp-content/uploads/2016/11/cropped-web_hi_res_512.png"));
            new BackgroundTasks().execute(new URL("http://utu.ac.in/bmiit/download/2021-22/Sem7/AAD_AP.pdf"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    private class BackgroundTasks extends AsyncTask<URL,Integer,Integer>
    {
        @Override
        protected Integer doInBackground(URL... urls) {
            int count = urls.length;
            int progress = 0;
            for(int i=0; i<count;i++)
            {
                FileDownload(urls[i]);
                progress += (((i+1)/count) * 100);
                publishProgress(progress);
            }
            return progress;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Toast.makeText(getApplicationContext(),"Download completed",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText(getApplicationContext(), " Downloaded " + values[0] + "%",
                    Toast.LENGTH_LONG).show();

        }
    }
    private void FileDownload(URL url)
    {
        DownloadManager manager = (DownloadManager)getBaseContext().getSystemService
                (DOWNLOAD_SERVICE);
        Uri Download_URI = Uri.parse(url.toString());

        DownloadManager.Request request = new DownloadManager.Request(Download_URI);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setNotificationVisibility(DownloadManager.
                Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                "MyDownloads/" + "Sample");

        manager.enqueue(request);
    }
    private void PrintMessageRepetedly()
    {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.d("Repeated Service",String.valueOf(++count));
            }
        },0,1000);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),
                "Service is stopped",Toast.LENGTH_LONG).show();
        if(timer != null)
        {
            timer.cancel();
        }
    }


}
