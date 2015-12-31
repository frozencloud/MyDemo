package com.fc.mydemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.fc.mydemo.R;
import com.fc.mydemo.utils.ToastUtil;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * 用于演示Annotations框架的Background注解;
 * 与UiThread配合使用，可以代替AsyncTask;
 */
@EActivity(R.layout.activity_annotations_back_test)
public class AnnotationsBackTestActivity extends AppCompatActivity {

    @ViewById(R.id.progress_bar)
    protected ProgressBar progress_bar;
    @ViewById(R.id.button)
    protected Button button;

    @Click(R.id.button)
    protected void click(View view) {
        doSomethingInBackground();
    }

    /**
     * 异步操作，方法必须为void;
     * 网络请求，耗时操作等都需要异步执行。
     */
    @Background
    protected void doSomethingInBackground() {
        for (int i = 0; i < 10000; i++) {
            doSomethingInUIThread(i);
        }
    }

    /**
     *
     */
    @UiThread
    protected void doSomethingInUIThread(int progress) {

        if (progress < 100) {
            progress_bar.setProgress(progress);
        } else {
            ToastUtil.showToastShort(this, "异步完成了，开始操作UI了");
        }
    }
}
