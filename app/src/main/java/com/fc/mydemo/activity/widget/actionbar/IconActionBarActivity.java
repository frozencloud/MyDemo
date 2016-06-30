package com.fc.mydemo.activity.widget.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ActionMenuView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.fc.mydemo.R;

import java.util.ArrayList;

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
        //修改overflow
        setOverflowButtonColor(this,R.drawable.ics_overflow);
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

    /**
     * 修改overflow的三个点也有两种方式：
     * 1.代码修改
     * 需要在style中添加添加一个Description。
     * 2.style修改：在style添加主题，并设置<item  name="android:actionOverflowButtonStyle">@style/MyOverflowStyle</item>
     *
     * <style name="MyOverflowStyle" parent="@android:style/Widget.Holo.Light.ActionButton.Overflow">
     * <item name="android:src">@drawable/ic_menu_overflow</item>
     * </style>
     * 这种方法的坏处是不能控制位置和大小。
     * @param activity
     * @param rid
     */
    public static void setOverflowButtonColor(final Activity activity, final int rid) {
        final String overflowDescription = "action_menu_overflow_description";
        final ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        final ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final ArrayList<View> outViews = new ArrayList<View>();
                decorView.findViewsWithText(outViews, overflowDescription,
                        View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
                if (outViews.isEmpty()) {
                    return;
                }
                ImageView overflow = (ImageView) outViews.get(0);
                ActionMenuView.LayoutParams params = new ActionMenuView.LayoutParams(180, 100);
                params.width = 180;
                params.height = 180;
                overflow.setLayoutParams(params);
                overflow.setImageResource(rid);
                overflow.setScaleType(ImageButton.ScaleType.CENTER_CROP);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.base_actionbar_menu, menu);
        return true;
    }
}
