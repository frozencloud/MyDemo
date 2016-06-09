package com.fc.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.fc.mydemo.R;
import com.fc.mydemo.activity.annotations.AnnotationsListActivity_;
import com.fc.mydemo.activity.async.AsyncListActivity_;
import com.fc.mydemo.activity.camera.CameraMainActivity_;
import com.fc.mydemo.activity.fourmodule.FourModuleListActivity_;
import com.fc.mydemo.activity.widget.actionbar.ActionBarBaseActivity;
import com.fc.mydemo.activity.xml.XMLParserActivity_;
import com.fc.mydemo.utils.CommonAdapter;
import com.fc.mydemo.utils.ToastUtil;
import com.fc.mydemo.utils.ViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById(R.id.list_view)
    ListView listView;

    private CommonAdapter<String> adapter;
    private ArrayList<String> list = new ArrayList<>();

    private String[] item = {"ListView和通用Adapter", "WebView的使用", "四大组件相关"
            , "异步操作相关", "Annotations框架相关", "xml解析", "拍照","ActionBar"};

    /**
     * annotations框架可以不写onCreate方法
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @AfterViews
    protected void init() {
        adapter = new CommonAdapter<String>(this, list, R.layout.item_list_main) {
            @Override
            public void convert(ViewHolder holder, String item) {
                holder.setText(R.id.item_tv_title, item);
            }
        };
        listView.setAdapter(adapter);
        getItemData();
    }

    /**
     * item 数据
     */
    protected void getItemData() {
        list.addAll(Arrays.asList(item));
        adapter.notifyDataSetChanged();
    }

    @ItemClick(R.id.list_view)
    protected void mItemClick(int position) {
        switch (position) {
            case 0:
                ToastUtil.showToastShort(this, "请查看MainActivity.java");
                break;
            case 1:
                Intent intent = new Intent(this, WebViewTestActivity_.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, FourModuleListActivity_.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, AsyncListActivity_.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, AnnotationsListActivity_.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, XMLParserActivity_.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(this, CameraMainActivity_.class);
                startActivity(intent);
                break;
            case 7:
                intent = new Intent(this, ActionBarBaseActivity.class);
                startActivity(intent);
                break;
        }
    }

    @ItemLongClick(R.id.list_view)
    protected void mItemLClick(int position) {
        ToastUtil.showToastShort(this, "item position : " + item[position]);
    }

}
