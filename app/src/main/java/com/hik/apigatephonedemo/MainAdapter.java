package com.hik.apigatephonedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 【说明】：
 *
 * @author zhangchuanyi
 * @data 2018/6/22 19:11
 */

public class MainAdapter extends BaseAdapter {

    private List<String> data = new ArrayList<>();
    private Context context;

    public MainAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        if (data == null) {
            return 0;
        } else {
            return data.size();
        }
    }

    @Override
    public String getItem(int position) {
        if (data == null) {
            return null;
        } else {
            return data.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.main_adapter_item_layout, parent, false);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.tv.setText(data.get(position));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder {
        private TextView tv;
    }

}
