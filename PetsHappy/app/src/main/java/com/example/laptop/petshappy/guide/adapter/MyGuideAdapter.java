package com.example.laptop.petshappy.guide.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Laptop on 2018/1/6.
 */
public class MyGuideAdapter extends PagerAdapter{

    private List<ImageView> initimage;

    public MyGuideAdapter(List<ImageView> initimage) {
        this.initimage = initimage;
    }

    @Override
    public int getCount() {
        return initimage.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(initimage.get(position));
        return initimage.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(initimage.get(position));
    }
}
