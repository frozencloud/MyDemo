package com.fc.mydemo.activity.annotations;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fc.mydemo.R;
import com.fc.mydemo.activity.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_rest)
public class RestActivity extends BaseActivity {

    @ViewById(R.id.get_btn)
    protected Button getBtn;
    @ViewById(R.id.post_btn)
    protected Button postBtn;
    @ViewById
    protected TextView get_result_tv;

    @RestService
    MyRestClient myRestClient;

    @AfterViews
    protected void init() {
        //为TextView添加滚动条
        get_result_tv.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Click({R.id.get_btn, R.id.post_btn})
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.get_btn:
                getRequest();
                break;
            case R.id.post_btn:
                break;
        }
    }

    @Background
    protected void getRequest() {
        String result = myRestClient.getBaidu();
        setResult(result);
    }

    @UiThread
    protected void setResult(String result) {
        get_result_tv.setText(result);
    }

}
