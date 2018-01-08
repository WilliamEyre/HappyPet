package com.example.laptop.happypet.ui.fujin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.laptop.happypet.R;

import java.util.ArrayList;

/**
 * Created by Laptop on 2018/1/6.
 */
public class MyAdapter extends BaseAdapter{
    private ArrayList<String> mList;
    private Context mContext;

    public MyAdapter(ArrayList<String> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holdrer holdrer = null;
        if (view==null){
            holdrer = new Holdrer();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter,null);
            holdrer.mName = (TextView) view.findViewById(R.id.Name);
            view.setTag(holdrer);
        }else {
            holdrer = (Holdrer) view.getTag();
        }
        String s = mList.get(i);
        holdrer.mName.setText(s);
        return view;
    }
    class Holdrer{
        private TextView mName;
    }
}
