package com.example.laptop.happypet.login;

import android.app.Application;

import com.example.laptop.happypet.login.utitls.AppUtils;
import com.example.laptop.happypet.login.utitls.TokenUtil;
import com.lzy.okhttputils.OkHttpUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class App extends Application {

    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.setAppContext(getApplicationContext());
        TokenUtil.init(getApplicationContext());
        //必须调用初始化
        OkHttpUtils.init(this);
        //SDK初始化
        UMShareAPI.get(this);
        //数据统计
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //打开友盟的日志
        Config.DEBUG = true;

    }
}
