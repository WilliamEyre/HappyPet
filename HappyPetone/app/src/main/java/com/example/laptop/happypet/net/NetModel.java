package com.example.laptop.happypet.net;

import com.example.laptop.happypet.login.App;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
* Created by TMVPHelper on 2018/01/09
*/
public class NetModel implements NetContract.Model{
    private static NetModel netModel;
    private OkHttpClient okhttpclient;

    public static NetModel getInstance(){
        if (netModel == null) {
            synchronized (NetModel.class){
                netModel=new NetModel();
            }
        }
        return netModel;
    }
    public NetModel(){
        okhttpclient=new OkHttpClient();
    }
    @Override
    public void getForNameNet(String url, final Callbacks callbacks) {

        Request request = new Request.Builder().url(url).build();
        okhttpclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                App.baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callbacks.succ(string);
                    }
                });
            }
        });

    }
}