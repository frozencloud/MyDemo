package com.fc.mydemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Button;

import com.fc.mydemo.R;
import com.fc.mydemo.utils.ToastUtil;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_my_bdc)
public class MyBdcActivity extends BaseActivity {

    @ViewById(R.id.static_button)
    Button static_button;
    @ViewById(R.id.dynamic_button)
    Button dynamic_button;
    @ViewById(R.id.sys_button)
    Button sys_button;
    @ViewById(R.id.orderly_button1)
    Button orderly_button1;
    @ViewById(R.id.orderly_button2)
    Button orderly_button2;

    private String STATIC_RECEIVER = "com.fc.demo.static.receiver";
    private String DYNAMIC_RECEIVER = "com.fc.demo.dynamic";
    private String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    private MyBroadcastReceiver receiver;

    @Click({R.id.static_button, R.id.dynamic_button, R.id.orderly_button1, R.id.orderly_button2})
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.static_button:
                Intent intent = new Intent(STATIC_RECEIVER);
                sendBroadcast(intent);
                break;
            case R.id.dynamic_button:
                intent = new Intent(DYNAMIC_RECEIVER);
                sendBroadcast(intent);
                break;
            case R.id.orderly_button1:
                break;
            case R.id.orderly_button2:
                break;
        }

    }

    @Override
    protected void onStart() {
        super.onResume();
        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(DYNAMIC_RECEIVER);
        filter.addAction(SMS_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (DYNAMIC_RECEIVER.equals(action)) {
                ToastUtil.showToastShort(context, "动态注册：" + action);
            } else if (SMS_ACTION.equals(action)) {
                ToastUtil.showToastShort(context, "系统广播：" + action);
            }
        }
    }
}
