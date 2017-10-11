package com.example.xiao.myappdemobaidu.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerBase;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.example.xiao.myappdemobaidu.Perserent.GetLatitudeAndLongitudePerserent;
import com.example.xiao.myappdemobaidu.R;
import com.example.xiao.myappdemobaidu.View.IsGetLatitudeAndLongitudeView;
import com.example.xiao.myappdemobaidu.entity.GuiJiShuJuBean;
import com.example.xiao.myappdemobaidu.entity.JingWeiDuBean;
import com.example.xiao.myappdemobaidu.entity.LatitudeandlongitudeBean;
import com.example.xiao.myappdemobaidu.entity.MyBeanEntibity;
import com.example.xiao.myappdemobaidu.utils.GetTime;
import com.example.xiao.myappdemobaidu.utils.L;
import com.example.xiao.myappdemobaidu.utils.StringValueof;
import com.example.xiao.myappdemobaidu.utils.ZhuoBiaoZhuanHuan;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.ui
 * 文件名：ShowBaidu
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/4/26 0026 下午 3:25
 * 描述：TODO
 */
public class ShowBaidu extends AppCompatActivity implements LocationSource, AMapLocationListener,View.OnClickListener, AMap.OnMarkerClickListener, IsGetLatitudeAndLongitudeView {
    private AMap aMap;
    private MapView mapView;
    private Polyline polyline;
    private MarkerOptions markerOption;
    /*
	 * 为方便展示多线段纹理颜色等示例事先准备好的经纬度
	 */
    private double Lat_A = 35.909736;
    private double Lon_A = 80.947266;

    private double Lat_B = 35.909736;
    private double Lon_B = 89.947266;

    private double Lat_C = 31.909736;
    private double Lon_C = 89.947266;

    private double Lat_D = 31.909736;
    private double Lon_D = 99.947266;
    private List<GuiJiShuJuBean.MsgBean> list2;
    private String j;
    private String w;
    private String stopj;
    private String stopw;
    private String stopjkiss;
    private String stopwkiss;
    private List<LatLng> list1 = new ArrayList<>();

    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

    List<LatLng> latLngs = new ArrayList<LatLng>();
    private double jingdu;
    private double weidu;
    private String shijian;
    private String startshijian;
    private String stopshijian;
    private LatLng latLng;
    private Marker marker;
    private double J;
    private double W;
    private StringBuilder nian;
    private String qishishijian;
    private String jiesushijian;
    private LatLng stoplatLng;
    private ImageView back_img;
    private int starts;
    private int stops;
    private String id;
    private AMap.OnMarkerClickListener markerClickListener;
    /*声明一个集合用来封装有几个marke*/

    private Marker marker2;
    private Marker marker3;
    private LatLng ll;
    private LatLng StratlatLng;
    private LatLng stoplatLngss;
    private String xianlushij;
    long db = 80000;
    private String xianlushijs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoubaidu);
        /*
         * 设置离线地图存储目录，在下载离线地图或初始化地图设置;
         * 使用过程中可自行设置, 若自行设置了离线地图存储的路径，
         * 则需要在离线地图下载和使用地图页面都进行路径设置
         * */
        //Demo中为了其他界面可以使用下载的离线地图，使用默认位置存储，屏蔽了自定义设置
//        MapsInitializer.sdcardDir =OffLineMapUtils.getSdCacheDir(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        /*获取第一个页面传过来的ID*/
        getDatas();
        init();
        initData();
    }

    private void getDatas() {
        Intent intent = getIntent();
        id = intent.getStringExtra("mid");
        L.e("idsss"+id);
    }


    private void init() {
        //初始化控件以及地图
        back_img = (ImageView) findViewById(R.id.back_img);
        back_img.setOnClickListener(this);
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    private void setUpMap() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
//       设置marker点击 监听
        aMap.setOnMarkerClickListener(this);

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if(null != mlocationClient){
            mlocationClient.onDestroy();
        }
        marker = null;
    }
    private void initData() {
        GetLatitudeAndLongitudePerserent getjingweidu = new GetLatitudeAndLongitudePerserent(this);
        getjingweidu.getjingweidu(id,"200");
    }

    /*
* 获取服务器返回的经纬度 的 接口回调
* */
    @Override
    public void showResult(Object object) {
        LatitudeandlongitudeBean latitude = (LatitudeandlongitudeBean) object;
        List<LatitudeandlongitudeBean.MsgBean> mlist = latitude.getMsg();
        pares(mlist);
    }


    //    解析
    private void pares(List<LatitudeandlongitudeBean.MsgBean> list2) {

        for (int i= 0;i<list2.size();i++){
            String jingweidu =  list2.get(i).getP_position();
            String jingdu = jingweidu.substring(0,jingweidu.lastIndexOf(":"));
            String weidu =  jingweidu.substring(jingweidu.lastIndexOf(":")+1, jingweidu.length()-1);
            L.e("测试 "+jingdu);
            if (i == 0) {
                //  起始位子的
                j = jingdu;
                w = weidu;
                if (list2.get(i).getP_time() != null) {
                    //获取起始时间
                    startshijian = list2.get(i).getP_time();

                    StringBuilder sb = new StringBuilder(startshijian+"");
                    nian = sb.insert(0,"20").insert(4,"年").insert(7,"月")
                            .insert(10,"日").insert(13,"时").insert(16,"分");
                    qishishijian =  nian.toString();
                    double startJ =  StringValueof.StringZhuanHuan(j);
                    double startW =  StringValueof.StringZhuanHuan(w);
                    latLng = new LatLng(startW,startJ);
//                                起始位子坐标转换
                    StratlatLng  = ZhuoBiaoZhuanHuan.transformFromWGSToGCJ(latLng);
                    L.e("qishishijian"+qishishijian);
                }

                // 这里显示起始位子跟终点位子
                addMarkersToMapss();
            }
            if (i%20 == 0) {  //这里判断I 每次循环多次 以后执行·if里面的业务
                try {
                    if (!jingdu.equals("000.0000000") && !weidu.equals("00.0000000")) {
                        j = jingdu;
                        w = weidu;
                        if (list2.get(i).getP_time().length() == 12) {
                            xianlushij = list2.get(i).getP_time();

                            if (list2.get(i).getP_time().length() == 12) {
                                StringBuilder sb = new StringBuilder(xianlushij+"");
                                nian = sb.insert(0,"20").insert(4,"年").insert(7,"月")
                                        .insert(10,"日").insert(13,"时").insert(16,"分");
                                xianlushijs =  nian.toString();
                                double startJ =  StringValueof.StringZhuanHuan(j);
                                double startW =  StringValueof.StringZhuanHuan(w);

                                LatLng latLng_xianludian = new LatLng(startW,startJ);
                                // 坐标转换
                                LatLng llxianludian =  ZhuoBiaoZhuanHuan.transformFromWGSToGCJ(latLng_xianludian);
                                addMarkers(llxianludian,xianlushijs);
                            }
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"有一个坐标的经纬度不正确",Toast.LENGTH_SHORT).show();
                }
            }

            else if (list2.size() - 5 == i) {  //这里是I 最后一次循环 然后 处理业务逻辑
                // 结束位子
                stopj = jingdu;
                stopw = weidu;
                //结束时间
                stopshijian = list2.get(i).getP_time();
                //将结束位子的 时间都 相加7个小时 也就是70000
                if (stopshijian.length()==12) {

                    StringBuilder sb = new StringBuilder(stopshijian+"");
                    nian = sb.insert(0, "20").insert(4, "年").insert(7, "月")
                            .insert(10, "日").insert(13, "时").insert(16, "分");
                    jiesushijian = nian.toString();
                    double stopJ = StringValueof.StringZhuanHuan(stopj);
                    double stopW = StringValueof.StringZhuanHuan(stopw);
                    stoplatLng = new LatLng(stopW, stopJ);
                    L.e("jiesushijian" + qishishijian);
                    //结束位子 坐标转换
                    stoplatLngss  = ZhuoBiaoZhuanHuan.transformFromWGSToGCJ(stoplatLng);
                    addMarkersToMapss();
                    //   addMarkerToMop(); //进来第一次显示结束位子
                } else {
                    Toast.makeText(getApplicationContext(),"最后一次获取时间失败",Toast.LENGTH_SHORT).show();
                }

            }
                 //这里有点问题

                if (!jingdu.equals("000.0000000") && !weidu.equals("00.0000000")) {
                    try {
                        if (jingdu.length() >= 8 && weidu.length() >=8) {
                            stopjkiss = jingdu;
                            stopwkiss = weidu;
                            //   这里是将字符串类型的经纬度 转成 double类型的 然后 添加到集合对象中
                            J =  StringValueof.StringZhuanHuan(stopjkiss);
                            W =  StringValueof.StringZhuanHuan(stopwkiss);
                            //将字符串类型的经纬度转成double  类型的 后 存放到对象 然后在存储放到集合后再去划线
                            ll = new LatLng(W,J);
                      //  =================下面这句话是世界坐标转成国内坐标
                            LatLng lls =  ZhuoBiaoZhuanHuan.transformFromWGSToGCJ(ll);
                            latLngs.add(lls);
                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"服务器返回经纬度不正常",Toast.LENGTH_SHORT).show();
                    }

                }

        }

        int size = latLngs.size();
        L.e("sizessss"+size);
        addPolylinesWithTexture(); //画文理线
//            addPolylinesdotted();//画虚线

    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除

            mlocationClient.startLocation();//启动定位
        }
    }
    /*停止定位*/
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }
    /*定位成功后回调函数*/
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null&&amapLocation != null) {
            if (amapLocation != null
                    &&amapLocation.getErrorCode() == 0) {
                //设置缩放级别
                aMap.moveCamera(CameraUpdateFactory.zoomTo(30));
                mListener.onLocationChanged(amapLocation);
                String city = amapLocation.getCity();
                String address = amapLocation.getAddress();
                L.e("city"+city);
                L.e("address"+address);
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
            }
        }
    }

//    点击返回按钮

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                marker = null;
                finish();
                break;
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        String id = marker.getId();
        L.e("marker     "+id);

        if (aMap != null) {
            if (marker.equals(marker2)) {
//				jumpPoint(marker);
            } else if (marker.equals(marker3)) {
//                growInto(marker);
            }
        }


        return false;
    }

    //绘制一条纹理线
    private void addPolylinesWithTexture() {
        //用一个数组来存放纹理
        List<BitmapDescriptor> texTuresList = new ArrayList<BitmapDescriptor>();
        texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.map_alr));
        //指定某一段用某个纹理，对应texTuresList的index即可, 四个点对应三段颜色
        List<Integer> texIndexList = new ArrayList<Integer>();
        texIndexList.add(0);//对应上面的第0个纹理
        texIndexList.add(2);
        texIndexList.add(1);
        PolylineOptions options = new PolylineOptions();
        options.width(40);//设置宽度
        //加入四个点
//      options.add(A,B,C,D); //这个是假数据
        options.addAll(latLngs); //这个是真是数据

        //加入对应的颜色,使用setCustomTextureList 即表示使用多纹理；
        options.setCustomTextureList(texTuresList);

        //设置纹理对应的Index
        options.setCustomTextureIndex(texIndexList);

        aMap.addPolyline(options);
    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMapss() {
        /*这里是在起点位子画点*/
        markerOption = new MarkerOptions();
        markerOption.position(StratlatLng);
        markerOption.title("起始位子").snippet(qishishijian);
        markerOption.draggable(true);
        markerOption.icon(
                // BitmapDescriptorFactory
                // .fromResource(R.drawable.location_marker)
                BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.start)));
        // 将Marker设置为贴地显示，可以双指下拉看效果
        markerOption.setFlat(true);
        /*下面是在终点位子画点*/
        MarkerOptions markerOption1 = new MarkerOptions().anchor(0.5f, 0.5f)
                .position(stoplatLngss).title("终点位子")
                .snippet(jiesushijian).icon( BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.stop)))
                .draggable(true).period(10);
        ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();
        markerOptionlst.add(markerOption);
        markerOptionlst.add(markerOption1);
        List<Marker> markerlst = aMap.addMarkers(markerOptionlst, true);
        marker2 = markerlst.get(0);

    }

    /*点击线条中间覆盖物显示气泡*/
    private void addMarkers(LatLng latLng,String qishishijian) {
        MarkerOptions markerOption1 = new MarkerOptions().anchor(0.5f, 0.5f)
                .position(latLng).title("路过的时间")
                .snippet(qishishijian)
//                .icon( BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.purple_pin)))
                .draggable(true).period(10);
        ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();

        markerOptionlst.add(markerOption1);
        List<Marker> markerlst = aMap.addMarkers(markerOptionlst, true);
        marker2 = markerlst.get(0);
    }


}
