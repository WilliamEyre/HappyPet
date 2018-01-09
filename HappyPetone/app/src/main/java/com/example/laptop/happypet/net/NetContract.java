package com.example.laptop.happypet.net;

import com.example.laptop.happypet.base.BaseModel;
import com.example.laptop.happypet.base.BasePresenter;
import com.example.laptop.happypet.base.BaseView;

/**
 * Created by Administrator on 2018/1/9.
 */

public interface NetContract {

    interface View extends BaseView {
      void show(String ss);
    }

    interface Model extends BaseModel {
        void getForNameNet(String url,Callbacks callbacks);
    }

    abstract static class Presenter extends BasePresenter<Model, View> {
        @Override
        public void OnStart() {

        }

        public abstract void getForNameModel(String url);

        
    }
}