package com.fc.mydemo.activity.widget.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fc.mydemo.R;

/**
 * 修改actionbar自带的up按钮有两种方式：
 * 1.在style中修改，设置android:homeAsUpIndicator属性为想要替换的图片；
 * 2.在代码中修改，详见setUpButton方法。
 *
 * 去掉title icon的方法是修改style中的
 */
public class IconActionBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_action_bar);
        ActionBar actionBar = getActionBar();
        //唤出up按钮
//        actionBar.setDisplayHomeAsUpEnabled(true);
        //从代码中修改系统返回按钮图片
        setUpButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //设置返回按钮
    private void setUpButton(){
        int upid = Resources.getSystem().getIdentifier("home", "id", "android");
        ImageView img = (ImageView) findViewById(upid);
        img.setImageResource(R.drawable.actionbar_back);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(100,100);
        params.width = 100;
        params.height = 100;
        params.leftMargin= 50;
        img.setLayoutParams(params);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
