package com.example.laptop.petshappy.guide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.laptop.petshappy.MainActivity;
import com.example.laptop.petshappy.R;
import com.example.laptop.petshappy.guide.adapter.MyGuideAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laptop on 2018/1/5.
 */
public class GuideActivity extends FangfaActivity{

    private ViewPager vp;
    private List<ImageView> initimage;
    private int[] imgarr;

    @Override
    protected int getLayout() {
        return R.layout.activity_guide_page;

    }

    @Override
    protected void initView() {

        imgarr = new int[]{R.drawable.one, R.drawable.two, R.drawable.three};

        SharedPreferences baocui = getSharedPreferences("baocui", 0);
        int anInt = baocui.getInt("int", 0);
        if (anInt != 0) {
            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        vp = (ViewPager) findViewById(R.id.guide_vp);
    }

    @Override
    protected void initData() {
        initimage = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            Glide.with(GuideActivity.this).load(imgarr[i]).into(imageView);
            initimage.add(imageView);
        }
    }


    @Override
    protected void initAdapter() {
        MyGuideAdapter adapter = new MyGuideAdapter(initimage);
        vp.setCurrentItem(0);
        vp.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 3; i++) {
                    if (position == imgarr.length - 1) {
                        ImageView imageView = initimage.get(2);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SharedPreferences baocui = getSharedPreferences("baocui", 0);
                                SharedPreferences.Editor edit = baocui.edit();
                                edit.putInt("int", -100);

                                edit.commit();
                                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
