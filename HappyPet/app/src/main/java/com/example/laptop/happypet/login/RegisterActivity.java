package com.example.laptop.happypet.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.laptop.happypet.R;
import com.example.laptop.happypet.login.entity.RegisterUser;
import com.example.laptop.happypet.login.utitls.AppUtils;
import com.example.laptop.happypet.login.utitls.IPUtils;
import com.example.laptop.happypet.login.utitls.Md5Utils;
import com.example.laptop.happypet.login.utitls.SignUtil;
import com.example.laptop.happypet.login.utitls.TokenUtil;

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


/**
 * Created by 丁军明 on 2018/1/4.
 */

public class RegisterActivity extends AppCompatActivity {


    //拼接url字符串
    String url = AppUtils.REQUESTURL + "/user/register.jhtml";
    @InjectView(R.id.register_cancel)
    TextView registerCancel;
    @InjectView(R.id.register_loging)
    TextView registerLoging;
    @InjectView(R.id.register_phone)
    EditText registerPhone;
    @InjectView(R.id.btn_register_verificationCode)
    Button btnRegisterVerificationCode;
    @InjectView(R.id.register_verificationCode)
    EditText registerVerificationCode;
    @InjectView(R.id.register_name)
    EditText registerName;
    @InjectView(R.id.register_psw)
    EditText registerPsw;
    @InjectView(R.id.btn_register)
    ImageView btnRegister;


    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);

    }

    @OnClick({R.id.register_cancel, R.id.register_loging, R.id.btn_register_verificationCode, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_cancel:
                finish();
                break;
            case R.id.register_loging:
                finish();
                break;
            case R.id.btn_register_verificationCode:
                break;
            case R.id.btn_register:
                String phone = registerPhone.getText().toString();
                String code = registerVerificationCode.getText().toString();
                String name = registerName.getText().toString();
                String pass = registerPsw.getText().toString();
                if (name.equals("") || pass.equals("") || code.equals("") || phone.equals("")) {
                    Toast.makeText(this, "输入有误，请输入重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("name", phone);
                    edit.putString("pass", pass);
                    edit.commit();
                    getRegister(name, code, pass);
                    finish();
                }
                break;
        }
    }


    private void getRegister(String phone, String psw, String name) {

        String password = Md5Utils.md5(psw, "UTF-8");
        String ip = IPUtils.getIp(RegisterActivity.this);
        String token = TokenUtil.createToken();
        Map<String, Object> map = new HashMap<>();
        map.put("userPhone", phone);
        map.put("userName", name);
        map.put("password", password);
        String signString = SignUtil.getSign(map);


        RegisterUser reginUser = new RegisterUser();

        RegisterUser.BodyBean bodyBean = new RegisterUser.BodyBean();
        bodyBean.setUserPhone(phone);
        bodyBean.setPassword(password);
        bodyBean.setUserName(name);


        RegisterUser.HeaderBean headerBean = new RegisterUser.HeaderBean();
        headerBean.setChannel("android");
        headerBean.setIp(ip);
        headerBean.setSign(signString);
        headerBean.setToken(token);


        reginUser.setHeader(headerBean);
        reginUser.setBody(bodyBean);


        //使用JSON.toJSONString(obj)方式来实现javaBean到json
        String reginString = JSON.toJSONString(reginUser);
        System.out.println("转化后的json字符串为==" + reginString);

        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder bodyBuilder = new FormBody.Builder();


        bodyBuilder.add("data", reginString);

        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.url(url).post(bodyBuilder.build()).build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String message = e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                Log.e("onResponse: ", data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
