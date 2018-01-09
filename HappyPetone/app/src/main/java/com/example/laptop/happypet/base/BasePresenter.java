package com.example.laptop.happypet.base;

/**
 * Created by Administrator on 2018/1/9.
 */

public abstract class BasePresenter<M, V> {
    public M baseModel;
    public V baseView;

    public void setVM(M m,V v){
        baseModel=m;
        baseView=v;
    }

    public abstract void OnStart();
}
