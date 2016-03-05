package com.fc.mydemo.activity.javabase;

import android.content.Intent;
import android.widget.ListView;

import com.fc.mydemo.R;
import com.fc.mydemo.activity.BaseActivity;
import com.fc.mydemo.utils.CommonAdapter;
import com.fc.mydemo.utils.ViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;

@EActivity(R.layout.activity_java_base)
public class JavaBaseActivity extends BaseActivity {

    @ViewById(R.id.list_view)
    ListView listView;

    private CommonAdapter<String> adapter;
    private ArrayList<String> list = new ArrayList<>();

    private String[] item = {" String,StringBuffer与StringBuilder比较", "ArrayList,Vector,LinkedList比较"};

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
                Intent intent = new Intent(this, StringCompareActivity_.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, ListCompareActivity_.class);
                startActivity(intent);
                break;
        }
    }
}
