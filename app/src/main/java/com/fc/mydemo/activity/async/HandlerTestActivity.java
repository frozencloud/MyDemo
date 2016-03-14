package com.fc.mydemo.activity.async;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.fc.mydemo.R;
import com.fc.mydemo.activity.BaseActivity;
import com.fc.mydemo.utils.ToastUtil;

/**
 * 用于演示线程和Handler的使用，故此该Activity不使用注解编写
 * 1)Looper:一个线程可以产生一个Looper对象，由它来管理此线程里的MessageQueue(消息队列)。
 * 2)Handler:构造Handler对象来与Looper沟通，以便push新消息到MessageQueue里;或者接收Looper从Message Queue取出)所送来的消息。
 * 3)Message Queue(消息队列):用来存放线程放入的消息。
 * 4)线程：UIThread 通常就是main thread，而Android启动程序时会替它建立一个MessageQueue。
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
