package com.example.xiao.myappdemobaidu.model;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.model
 * 文件名：IsGetLatitudeAndLongitudeModle
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/10 0010 16:57
 * 描述：获取服务器返回的所有经纬度
 */
public interface IsGetLatitudeAndLongitudeModle {
    void getjingweidu(getjingweiduLineres lineres,String num,String limit);

    interface getjingweiduLineres{

        void onComptle(Object object);
    }
}
