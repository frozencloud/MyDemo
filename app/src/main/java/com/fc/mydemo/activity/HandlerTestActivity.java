package com.fc.mydemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.fc.mydemo.R;
import com.fc.mydemo.utils.ToastUtil;

/**
 * 用于演示线程和Handler的使用，故此该Activity不使用注解编写
 * 知识要点：Thread中不能执行UI操作，如果需要则在Handler中进行。
 */
public class HandlerTestActivity extends BaseActivity {

    private Button button;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            ToastUtil.showToastShort(HandlerTestActivity.this
                    , bundle.get("threadName").toString()
                    + "  "
                    + bundle.get("ticketNum").toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread thread1 = new MyThread();
                MyThread thread2 = new MyThread();
                MyThread thread3 = new MyThread();
                thread1.start();
                thread2.start();
                thread3.start();
            }
        });
    }

    class MyThread extends Thread {

        private int tickets = 10;

        public void run() {
            for (int i = 0; i < 200; i++) {
                if (tickets > 0) {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putInt("ticketNum", tickets--);
                    bundle.putString("threadName", Thread.currentThread().getName());
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            }
        }
    }
}
