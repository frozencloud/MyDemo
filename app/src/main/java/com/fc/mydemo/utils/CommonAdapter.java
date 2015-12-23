package com.fc.mydemo.utils;

/**
 * Created by fc on 2015/9/7.
 * 一个通用Adapter，具体用法在TestActivity类中演示，与ViewHolder一起配合使用
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    private int layoutId;

    public CommonAdapter(Context context, List<T> mDatas, int layoutId) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, layoutId, position);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T item);

}
