package com.fc.mydemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by f.c. on 2016/1/5.
 * IntentService使用、生命周期
 */
public class MyIntentService extends IntentService {

    final static String TAG = "====>>>>";

    public MyIntentService() {
        super("com.fc.mydemo.service.MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, "begin onHandleIntent() in " + this);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "end onHandleIntent() in " + this);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, this + " is destroy");
    }
}
