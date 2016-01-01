package com.fc.mydemo.activity;

import android.content.Intent;
import android.widget.ListView;

import com.fc.mydemo.R;
import com.fc.mydemo.utils.CommonAdapter;
import com.fc.mydemo.utils.ViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 四大组件相关内容的列表页面
 */
@EActivity(R.layout.activity_four_modules)
public class FourModuleListActivity extends BaseActivity {
    @ViewById(R.id.list_view)
    ListView listView;

    private CommonAdapter<String> adapter;
    private ArrayList<String> list = new ArrayList<>();

    private String[] item = {"广播的使用", "Handler和Thread的使用", "Service的使用", "Intent的使用"};

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
                Intent intent = new Intent(this, MyBdcActivity_.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, HandlerTestActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, ServiceLifecycleActivity_.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, IntentTestActivity_.class);
                startActivity(intent);
        }
    }
}
