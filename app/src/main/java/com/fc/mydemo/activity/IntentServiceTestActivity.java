package com.fc.mydemo.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.fc.mydemo.R;
import com.fc.mydemo.service.MyIntentService;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * IntentService生命周期
 */
@EActivity(R.layout.activity_intent_service_test)
public class IntentServiceTestActivity extends BaseActivity {

    @ViewById
    Button start_intent_service_btn;

    @Click({R.id.start_intent_service_btn})
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.start_intent_service_btn:
                //同时启动多个Service不会阻断UI进程
                Intent intent = new Intent(this, MyIntentService.class);
                startService(intent);
                startService(intent);
                startService(intent);
                break;
        }
    }

}
