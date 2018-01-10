package com.example.laptop.petshappy;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.laptop.petshappy.city.CityActivity;
import com.example.laptop.petshappy.login.LoginActivity;
import com.example.laptop.petshappy.map.MapActivity;
import com.example.laptop.petshappy.ui.fujin.adapter.FuJinAdapter;
import com.example.laptop.petshappy.ui.fujin.adapter.MyAdapter;
import com.example.laptop.petshappy.ui.fujin.adapter.bean.FuJinBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mImageViewRen;
    private NavigationView mnav_view;
    private DrawerLayout mDrawLayout;
    private ActionBarDrawerToggle toggle;
    private boolean isQuit = false;

    //附近优先、宠物类型、筛选
    private ImageView mFuJin,mLeiXing,mShaiXun;
    //地图、选择其他城市
    private ImageView mMapPuticer;
    private ListView lv;
    private PopupWindow popupWindow;
    private ArrayList<String> mList = new ArrayList<>();
    private boolean boo = true;
    private boolean boo1 = true;

    private PopupWindow popupWindow1;
    private ArrayList<String> mList1 = new ArrayList<>();
    private boolean popup = true;
    private boolean popup1 = true;
    
    private PopupWindow popupWindow2;
    int shai = 1;
    
    private RecyclerView recyclerView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String anme = (String) msg.obj;
            Gson gson = new Gson();
            FuJinBean student = gson.fromJson(anme, FuJinBean.class);
            List<FuJinBean.DescBean> desc = student.getDesc();
            FuJinAdapter myReadapter = new FuJinAdapter(desc,MainActivity.this);
            recyclerView.setAdapter(myReadapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();

        initData();
    }

    private void initData() {
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutmanager);
        mFuJin = (ImageView) findViewById(R.id.FuJin_Image);
        mLeiXing = (ImageView) findViewById(R.id.LeiXing_Image);
        mShaiXun = (ImageView) findViewById(R.id.ShaiXuan_Image);
        mMapPuticer = (ImageView) findViewById(R.id.MapPuticer);

        mFuJin.setOnClickListener(this);
        mLeiXing.setOnClickListener(this);
        mShaiXun.setOnClickListener(this);
        mMapPuticer.setOnClickListener(this);
    }

    private void initView() {
        mImageViewRen = (ImageView) findViewById(R.id.ImageView_ren);
        mImageViewRen.setOnClickListener(this);
        //----------------侧滑菜单-----------------
        mDrawLayout = (DrawerLayout) findViewById(R.id.DrawLayout);
        toggle = new ActionBarDrawerToggle(this,mDrawLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawLayout.addDrawerListener(toggle);
        toggle.syncState();

        mnav_view = (NavigationView) findViewById(R.id.nav_view);
        mnav_view.setItemIconTintList(null);
        View headerView = mnav_view.getHeaderView(0);

        //---------------------点击头像跳转登录页面------------

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "登录", Toast.LENGTH_SHORT).show();
            }
        });
        //---------------------侧滑菜单点击事件-----------------

        mnav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_message:
                        Toast.makeText(MainActivity.this, "消息", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_chongwu:
                        Toast.makeText(MainActivity.this, "宠物", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_dingdan:
                        Toast.makeText(MainActivity.this, "订单", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_monney:
                        Toast.makeText(MainActivity.this, "钱包", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_xuzhi:
                        Toast.makeText(MainActivity.this, "需知", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_shezhi:
                        Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
    //点击头像弹出侧滑菜单
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageView_ren:
                if (!mDrawLayout.isDrawerOpen(mnav_view)) {
                    mDrawLayout.openDrawer(mnav_view);
                }
                break;
//---------------------附近优先、宠物类型、筛选点击事件-----------------
            case R.id.FuJin_Image:
                if (boo){
                    mFuJin.setImageResource(R.drawable.up_arrow);
                    initPopup();
                    popupWindow.showAsDropDown(mFuJin);
                    MyAdapter myadapter = new MyAdapter(mList,MainActivity.this);
                    lv.setAdapter(myadapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            initInfo();
                        }
                    });
                    boo = false;
                }else {
                    mFuJin.setImageResource(R.drawable.down_arrow);
                    boo = true;
                    popupWindow.dismiss();
                }
                break;
            case R.id.LeiXing_Image:
                if (popup){
                    mLeiXing.setImageResource(R.drawable.up_arrow);
                    initpopup1();
                    popupWindow1.showAsDropDown(mLeiXing);
                    MyAdapter myadapter = new MyAdapter(mList1,MainActivity.this);
                    lv.setAdapter(myadapter);
                    popup = false;
                }else {
                    mLeiXing.setImageResource(R.drawable.down_arrow);
                    popup = true;
                    popupWindow1.dismiss();
                }
                break;
            case R.id.ShaiXuan_Image:
                if (shai==1){
                    mShaiXun.setBackground(getResources().getDrawable(R.drawable.up_arrow));
                    initPopup2();
                    popupWindow2.showAsDropDown(mShaiXun);
                    //更多城市的监听
                    mOther_City.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //跳转到更多城市
                            startActivity(new Intent(MainActivity.this, CityActivity.class));
                        }
                    });

                    shai = 2;
                }else {
                    popupWindow2.dismiss();
                    mShaiXun.setBackground(getResources().getDrawable(R.drawable.down_arrow));
                    shai = 1;
                }
                break;

            //跳转地图
            case R.id.MapPuticer:
                Intent intentMap = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intentMap);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (!isQuit) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            isQuit = true;

            //这段代码意思是,在两秒钟之后isQuit会变成false
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        isQuit = false;
                    }
                }
            }).start();

        } else {
            System.exit(0);
        }
    }
    //---------------------附近优先、宠物类型、筛选Popupwindown-----------------
private void initPopup(){
    if (boo1){
        mList.add("附近优先");
        mList.add("好评优先");
        mList.add("订单优先");
        mList.add("价格从高到低");
        mList.add("价格从低到高");
        boo1 = false;
    }
    popupWindow = new PopupWindow();
    View view = getLayoutInflater().inflate(R.layout.fujinpopup,null);
    lv = (ListView) view.findViewById(R.id.ListView);
    popupWindow.setContentView(view);
    popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
    popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    popupWindow.setBackgroundDrawable(new ColorDrawable());
    popupWindow.setFocusable(true);
    popupWindow.setOutsideTouchable(true);
}
    private void initpopup1(){
        if (popup1){
            mList1.add("小型犬");
            mList1.add("中型犬");
            mList1.add("大型犬");
            mList1.add("猫");
            mList1.add("小宠");
            mList1.add("幼犬");
            popup1 = false;
        }
        popupWindow1 = new PopupWindow();
        View view1 = getLayoutInflater().inflate(R.layout.fujinpopup,null);
        popupWindow1.setContentView(view1);
        lv = (ListView) view1.findViewById(R.id.ListView);
        popupWindow1.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow1.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow1.setBackgroundDrawable(new ColorDrawable());
        popupWindow1.setFocusable(true);
        popupWindow1.setOutsideTouchable(true);
    }
    //更多城市
    private ImageView mOther_City;

    private void initPopup2(){
        popupWindow2 = new PopupWindow();
        View view2 = getLayoutInflater().inflate(R.layout.shaixuanpopup,null);
        //找到ID
        mOther_City = (ImageView) view2.findViewById(R.id.Other_City);

        popupWindow2.setContentView(view2);
        popupWindow2.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow2.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow2.setBackgroundDrawable(new ColorDrawable());
        popupWindow2.setFocusable(true);
        popupWindow2.setOutsideTouchable(true);
    }
    private void initInfo(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://172.16.52.34:8080/Pet/HappyPet.json").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message msg = new Message();
                msg.obj = string;
                handler.sendMessage(msg);
            }
        });
    }
}
