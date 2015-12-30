package com.fc.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.fc.mydemo.utils.Logger;

/**
 * Create by frozen cloud on 2015/12/26
 * 用于演示service生命周期
 */
public class MyFirstService extends Service {
    public static final String ACTION = "com.fc.mydemo.service.MyFirstService";

    public MyFirstService() {
    }

    @Override
    public void onCreate() {
        Logger.e("MyFirstService.onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.e("MyFirstService.onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 返回 null，表示客服端不能建立到此服务的连接。
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        Logger.e("MyFirstService.onBind()");
        return null;
    }

    /**
     * 2.0之前使用该方法，2.0之后的版本使用onStartCommand()方法。
     *
     * @param intent
     * @param startId
     */
    @Override
    public void onStart(Intent intent, int startId) {
        Logger.e("MyFirstService.onStart()");
        super.onStart(intent, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.e("MyFirstService.onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Logger.e("MyFirstService.onDestroy()");
        super.onDestroy();
    }
}
