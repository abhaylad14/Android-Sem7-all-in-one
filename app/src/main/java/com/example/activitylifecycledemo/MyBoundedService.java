package com.example.activitylifecycledemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyBoundedService extends Service {
    private IBinder iBinder = new LocalBinder();
    private Random mGenerator = new Random();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public class LocalBinder extends Binder
    {
        MyBoundedService getService()
        {
            return MyBoundedService.this;
        }
    }

    public int getRandom()
    {
        return mGenerator.nextInt(500);
    }
}
