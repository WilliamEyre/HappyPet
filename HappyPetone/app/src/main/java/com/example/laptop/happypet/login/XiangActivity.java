package com.example.laptop.happypet.login;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.laptop.happypet.R;
import com.example.laptop.happypet.base.BaseActivity;
import com.example.laptop.happypet.net.NetModel;
import com.example.laptop.happypet.net.NetPresenter;
import com.umeng.socialize.media.Base;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by 丁军明 on 2018/1/4.
 */

public class XiangActivity extends BaseActivity<NetPresenter,NetModel> {


    @InjectView(R.id.Img_back)
    ImageView ImgBack;
    @InjectView(R.id.Img_return)
    ImageView ImgReturn;
    @InjectView(R.id.Img_more)
    ImageView ImgMore;
    @InjectView(R.id.evaluate_lin)
    LinearLayout evaluateLin;
    @InjectView(R.id.call_him)
    LinearLayout callHim;
    @InjectView(R.id.order_him)
    Button orderHim;
    private PopupWindow popupWindow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_entry;
    }

    @Override
    protected void initData() {
        ButterKnife.inject(this);
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.Img_return, R.id.Img_more, R.id.evaluate_lin, R.id.call_him, R.id.order_him})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Img_return:
                finish();
                break;
            case R.id.Img_more:
                Toast.makeText(this, "没有更多了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.evaluate_lin:
                break;
            case R.id.call_him:
                initPopup();
                break;
            case R.id.order_him:
                break;
        }
    }

    private void initPopup() {
        popupWindow = new PopupWindow();
        View view = LayoutInflater.from(XiangActivity.this).inflate(R.layout.pipup_moban, null);
        popupWindow.setContentView(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(findViewById(R.id.Rela_xiang), Gravity.CENTER, 0, 0);

    }
}
