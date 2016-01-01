package com.fc.mydemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fc.mydemo.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 主要表现一些系统自带的Intent。
 */
@EActivity(R.layout.activity_intent_test)
public class IntentTestActivity extends BaseActivity {

    @ViewById(R.id.phone_number)
    protected EditText phoneNumber;

    @ViewById
    protected Button call;

    @Click({R.id.call})
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.call:
                //拨打电话需要相应的权限
                Intent intent = new Intent(Intent.ACTION_DIAL);
                //打电话的两种方式
                //Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + phoneNumber.getText().toString());
                intent.setData(data);
                startActivity(intent);
                break;
        }
    }
}
