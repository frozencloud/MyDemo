package com.fc.mydemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.fc.mydemo.utils.ToastUtil;

/**
 * Created by fc on 2015/12/23.
 * 静态广播演示
 */
public class StaticBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        ToastUtil.showToastShort(context, "静态注册：" + action);
    }
}
