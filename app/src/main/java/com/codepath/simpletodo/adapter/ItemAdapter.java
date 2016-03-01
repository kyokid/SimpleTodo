package com.codepath.simpletodo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mazino on 3/1/2016.
 */
public class ItemAdapter extends BaseAdapter {

    private Activity viewActivity;
    private List<Item> mItemList = new ArrayList<>();

    LayoutInflater mInflater;

    public ItemAdapter(Activity activity, List<Item> itemList) {
        viewActivity = activity;
        mItemList = itemList;

        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void remove(Item object) {
        mItemList.remove(object);
    }

    public void add(Item object) {
        mItemList.add(object);
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Item getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ItemHolder {
        public TextView tvItemName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ItemHolder itemHolder;
        if(convertView == null) {
            itemHolder = new ItemHolder();
            convertView = mInflater.inflate(R.layout.adapter_item, parent, false);
            itemHolder.tvItemName = (TextView) convertView.findViewById(R.id.item_name);

            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
        itemHolder.tvItemName.setText(mItemList.get(position).getItemName());
        return convertView;
    }
}
