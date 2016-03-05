package com.fc.mydemo.activity.fourmodule;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fc.mydemo.R;
import com.fc.mydemo.service.MyFirstService;
import com.fc.mydemo.utils.Logger;
import com.fc.mydemo.utils.ToastUtil;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_service_lifecycle)
public class ServiceLifecycleActivity extends AppCompatActivity {

    @ViewById(R.id.bind_service_btn)
    Button bindServiceBtn;
    @ViewById(R.id.start_service_btn)
    Button startServiceBtn;
    @ViewById(R.id.unbind_service_btn)
    Button unbindServiceBtn;
    @ViewById(R.id.stop_service_btn)
    Button stopServiceBtn;

    ServiceConnection conn = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.v("onServiceConnected");
        }

        public void onServiceDisconnected(ComponentName name) {
            Logger.v("onServiceDisconnected");
        }
    };

    /**
     * Service的两种启动方式，bind和start。bind方式
     * @param view
     */
    @Click({R.id.bind_service_btn, R.id.start_service_btn, R.id.unbind_service_btn, R.id.stop_service_btn})
    protected void click(View view) {
        ToastUtil.showToastShort(this,"查看Log日志，观察Service的生命周期");
        switch (view.getId()) {
            case R.id.bind_service_btn:
                bindService(new Intent(MyFirstService.ACTION), conn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.start_service_btn:
                startService(new Intent(MyFirstService.ACTION));
                break;
            case R.id.unbind_service_btn:
                unbindService(conn);
                break;
            case R.id.stop_service_btn:
                stopService(new Intent(MyFirstService.ACTION));
                break;
        }
    }

}
