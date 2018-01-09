package com.example.laptop.happypet.net;

/**
* Created by TMVPHelper on 2018/01/09
*/
public class NetPresenter extends NetContract.Presenter{

    @Override
    public void getForNameModel(String url) {
        baseModel.getForNameNet(url, new Callbacks() {
            @Override
            public void succ(String res) {
                baseView.show(res);
            }

        });
    }
}