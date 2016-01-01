package com.fc.mydemo.activity;

import android.content.Intent;
import android.widget.ListView;

import com.fc.mydemo.R;
import com.fc.mydemo.activity.annotations.AnnotationsBackTestActivity_;
import com.fc.mydemo.utils.CommonAdapter;
import com.fc.mydemo.utils.ViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 异步操作相关的，包括网络请求、下载等耗时操作；
 */
@EActivity(R.layout.activity_async)
public class AsyncListActivity extends BaseActivity {

    @ViewById(R.id.list_view)
    ListView listView;

    private CommonAdapter<String> adapter;
    private ArrayList<String> list = new ArrayList<>();

    private String[] item = {"Handler和Thread的使用", "AsyncTask的使用", "Background和UIThread注解"};

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
                Intent intent = new Intent(this, HandlerTestActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, AsyncTaskTestActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, AnnotationsBackTestActivity_.class);
                startActivity(intent);
                break;
        }
    }
}
