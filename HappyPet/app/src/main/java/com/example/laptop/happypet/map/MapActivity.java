package com.example.laptop.happypet.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.laptop.happypet.R;
import com.example.laptop.happypet.map.overlay.DrivingRouteOverlay;

public class MapActivity extends AppCompatActivity implements View.OnClickListener, RouteSearch.OnRouteSearchListener {
    MapView mMapView = null;
    AMap aMap = null;
    private EditText ed_from;
    private EditText ed_to;
    private Button brn_login;
    private MapView map;
    private RouteSearch routeSearch;
    private GeocodeSearch search;
    private GeocodeSearch search2;
    private LatLonPoint latLonPoint2;
    private MyLocationStyle myLocationStyle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initView();

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        initListener();
    }

    private void initListener() {
        search2.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {


            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                GeocodeAddress geocodeAddress = geocodeResult.getGeocodeAddressList().get(0);
                latLonPoint2 = geocodeAddress.getLatLonPoint();


            }
        });

        search.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                GeocodeAddress geocodeAddress = geocodeResult.getGeocodeAddressList().get(0);
                LatLonPoint latLonPoint = geocodeAddress.getLatLonPoint();


                routeSearch = new RouteSearch(MapActivity.this);
                routeSearch.setRouteSearchListener((RouteSearch.OnRouteSearchListener) MapActivity.this);
                RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(new LatLonPoint(latLonPoint.getLatitude(), latLonPoint.getLongitude()),
                        new LatLonPoint(latLonPoint2.getLatitude(), latLonPoint2.getLongitude()));
                // fromAndTo包含路径规划的起点和终点，drivingMode表示驾车模式
                // 第三个参数表示途经点（最多支持16个），第四个参数表示避让区域（最多支持32个），第五个参数表示避让道路
                RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DrivingDefault, null, null, "");
                routeSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    private void initView() {
        ed_from = (EditText) findViewById(R.id.ed_from);
        ed_to = (EditText) findViewById(R.id.ed_to);
        brn_login = (Button) findViewById(R.id.brn_login);
        map = (MapView) findViewById(R.id.map);
        search = new GeocodeSearch(this);
        search2 = new GeocodeSearch(this);
        routeSearch = new RouteSearch(this);
        brn_login.setOnClickListener(this);
        routeSearch.setRouteSearchListener(this);
    }
//    DriveRouteQuery(RouteSearch.FromAndTo fromAndTo, int mode, List<LatLonPoint> passedByPoints, List<List<LatLonPoint>> avoidpolygons, String avoidRoad)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.brn_login:
                String from = ed_from.getText().toString().trim();
                //开始地址
                if (TextUtils.isEmpty(from)) {
                    Toast.makeText(this, "请输入有效地址", Toast.LENGTH_SHORT).show();
                    Log.e("------------","=-----------------------");
                } else {

                    GeocodeQuery query = new GeocodeQuery(from, null);
                    search.getFromLocationNameAsyn(query);
//                    routeSearch.calculateDriveRouteAsyn(query);
                }
                //终点地址
                String to = ed_to.getText().toString().trim();
                if (TextUtils.isEmpty(to)) {
                    Toast.makeText(this, "请输入有效地址", Toast.LENGTH_SHORT).show();
                    Log.e("------------","=-----------------------");
                } else {
                    GeocodeQuery query = new GeocodeQuery(to, null);
                    search2.getFromLocationNameAsyn(query);
                }

                break;
        }
    }


    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int i) {
//自驾查询结果
        aMap.clear();// 清理地图上的所有覆盖物

        final DrivePath drivePath = result.getPaths()
                .get(0);
        DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                MapActivity.this, aMap, drivePath,
                result.getStartPos(),
                result.getTargetPos(), null);
        drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
        drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
        drivingRouteOverlay.removeFromMap();
        drivingRouteOverlay.addToMap();
        drivingRouteOverlay.zoomToSpan();
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }
}
