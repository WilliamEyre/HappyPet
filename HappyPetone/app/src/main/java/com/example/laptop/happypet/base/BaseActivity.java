package com.example.laptop.happypet.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.laptop.happypet.R;
import com.example.laptop.happypet.login.App;
import com.example.laptop.happypet.login.utitls.Tutils;

/**
 * Created by Administrator on 2018/1/9.
 */

public abstract class BaseActivity<P extends BasePresenter,M extends BaseModel> extends AppCompatActivity {

    public P mPresenter;
    public M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        App.baseActivity=this;
        mPresenter= Tutils.getT(this,0);
        mModel=Tutils.getT(this,1);
        if (this instanceof BaseView){
            mPresenter.setVM(mModel,this);
        }
        initData();
        initView();
    }

    protected abstract void initData();

    protected abstract void initView();

    public abstract int getLayoutId();
}

