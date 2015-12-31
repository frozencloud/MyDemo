package com.fc.mydemo.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fc.mydemo.R;

/**
 * 在Android中除了使用Thread进行异步操作外，还可以使用官方封装的AsyncTask进行异步操作。
 */
public class AsyncTaskTestActivity extends BaseActivity {

    private Button startBtn;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn_test);

        startBtn = (Button) findViewById(R.id.start_btn);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textView = (TextView) findViewById(R.id.text);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
            }
        });

    }

    /**
     * AsyncTask提供三种泛型参数，必须在UI线程中创建实例。
     * Params 启动任务执行的输入参数，比如HTTP请求的URL。
     * Progress 后台任务执行的百分比。
     * Result 后台执行任务最终返回的结果，比如String。
     */
    private class MyAsyncTask extends AsyncTask<Integer, Integer, String> {


        //在任务执行前进行调用的方法
        @Override
        protected void onPreExecute() {
            textView.setText("这里是异步魔法前摇");
            super.onPreExecute();
        }

        //进度条更新方法，是AsyncTask提供的唯一的UI操作方法
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            for (int i = 10; i <= 100; i += 10) {
            }
        }

        //该方法必须重写，异步操作，不能做UI操作，return的结果将
        @Override
        protected String doInBackground(Integer... params) {
            return null;
        }

        //该方法用于做UI操作，相当于handler的作用，如果不需要UI操作可以不实现该方法。
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
