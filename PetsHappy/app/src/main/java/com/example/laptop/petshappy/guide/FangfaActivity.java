package com.example.laptop.petshappy.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Laptop on 2018/1/6.
 */
public abstract class FangfaActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        initView();

        initData();

        initAdapter();

        initListener();
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initAdapter();

    protected abstract void initListener();
}
