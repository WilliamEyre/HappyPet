package com.example.laptop.happypet.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.laptop.happypet.R;
import com.example.laptop.happypet.login.entity.LoginUser;
import com.example.laptop.happypet.login.utitls.AppUtils;
import com.example.laptop.happypet.login.utitls.IPUtils;
import com.example.laptop.happypet.login.utitls.Md5Utils;
import com.example.laptop.happypet.login.utitls.SignUtil;
import com.example.laptop.happypet.login.utitls.TokenUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity implements UMAuthListener {


    @InjectView(R.id.loging_back)
    ImageView logingBack;
    @InjectView(R.id.loging_regist)
    TextView logingRegist;
    @InjectView(R.id.loging_phone)
    EditText logingPhone;
    @InjectView(R.id.loging_psw)
    EditText logingPsw;
    @InjectView(R.id.loging_forget)
    TextView logingForget;
    @InjectView(R.id.btn_loging)
    ImageView btnLoging;
    @InjectView(R.id.loging_weixin)
    ImageView logingWeixin;
    @InjectView(R.id.loging_qq)
    ImageView logingQq;
    private boolean inClick = true;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    //拼接url字符串
    String url = AppUtils.REQUESTURL + "/user/register.jhtml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String nameV = sharedPreferences.getString("name", "");
        String passV = sharedPreferences.getString("pass", "");
        logingPhone.setText(nameV);
        logingPsw.setText(passV);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        logingPhone.setText(sharedPreferences.getString("name", ""));
        if (inClick) {
            logingPsw.setText(sharedPreferences.getString("pass", ""));
        }

    }

    @OnClick({R.id.loging_back, R.id.loging_regist, R.id.btn_loging, R.id.loging_weixin, R.id.loging_qq, R.id.loging_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loging_back:
                finish();
                break;
            case R.id.loging_regist:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.btn_loging:
                String name = logingPhone.getText().toString();
                String pass = logingPsw.getText().toString();
                if (name.equals("") || pass.equals("")) {
                    Toast.makeText(this, "输入有误，请重新输入", Toast.LENGTH_SHORT).show();

                } else {
                    edit = sharedPreferences.edit();
                    edit.putString("name", name);
                    edit.putString("pass", pass);
                    edit.commit();
                    getLogin(name, pass);
                }
                break;
            case R.id.loging_forget:
                logingPsw.setText("");
                inClick = false;
                sharedPreferences.edit().remove("pass").commit();
                break;
            case R.id.loging_weixin:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, this);
                break;
            case R.id.loging_qq:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, this);
                break;
        }
    }

    /**
     * 这个是所有对象的post请求方式,按照这个可以完成网络请求
     *
     * @param phone 用户电话号码
     * @param psw   用户密码
     */
    private void getLogin(String phone, String psw) {
        //获取bean对象的相应参数
        String password = Md5Utils.md5(psw, "UTF-8");
        String ip = IPUtils.getIp(LoginActivity.this);
        String token = TokenUtil.createToken();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("userPhone", phone);
        map1.put("password", password);
        String signString = SignUtil.getSign(map1);

        //创建bean对象
        LoginUser loginUser = new LoginUser();

        //创建bean对象的body体
        LoginUser.BodyBean bodyBean = new LoginUser.BodyBean();
        bodyBean.setUserPhone(phone);
        bodyBean.setPassword(password);


        //将参数添加到bean对象的请求头中
        LoginUser.HeaderBean headerBean = new LoginUser.HeaderBean();
        headerBean.setChannel("android");
        headerBean.setIp(ip);
        headerBean.setSign(signString);
        headerBean.setToken(token);


        //填充请求头和请求体到请求中
        loginUser.setBody(bodyBean);
        loginUser.setHeader(headerBean);


        //使用JSON.toJSONString(obj)方式来实现Bean对象转化为json字符串,并打印输出
        String loginString = JSON.toJSONString(loginUser);
        System.out.println("转化后的json字符串为==" + loginString);

        //使用okhttp发送请求
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder bodyBuilder = new FormBody.Builder();

        bodyBuilder.add("data", loginString);
        Request.Builder requestBuilder = new Request.Builder();

        Request request = requestBuilder.url(url).post(bodyBuilder.build()).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                Log.e("onResponse: ", data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LoginActivity.this, XiangActivity.class));
                    }
                });

            }
        });
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
