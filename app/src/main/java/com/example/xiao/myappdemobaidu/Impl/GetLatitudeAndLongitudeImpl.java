package com.example.xiao.myappdemobaidu.Impl;

import android.widget.EditText;

import com.example.xiao.myappdemobaidu.entity.LatitudeandlongitudeBean;
import com.example.xiao.myappdemobaidu.model.IsGetLatitudeAndLongitudeModle;
import com.example.xiao.myappdemobaidu.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.Impl
 * 文件名：GetLatitudeAndLongitudeImpl
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/10 0010 17:01
 * 描述：TODO
 */
public class GetLatitudeAndLongitudeImpl implements IsGetLatitudeAndLongitudeModle {
    @Override
    public void getjingweidu(final getjingweiduLineres lineres, String num, String limit) {
        // 862151031232711
       // 862151031207812

        String url =  "http://lock.sunsyi.com/trajectory/public/index.php/index/index/getdata";
        OkGo.<String>post(url)
                .params("num",num)
                .params("limit",limit)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        L.e("测试结果"+response.body());
                        try {
                            JSONObject obje = new JSONObject(response.body());
                            int state = obje.optInt("status");
                            if (state == 1) {
                                Gson gson = new Gson();
                                LatitudeandlongitudeBean latiude = gson.fromJson(response.body(), LatitudeandlongitudeBean.class);
                                lineres.onComptle(latiude);
                            }
                        } catch (Exception e) {
                            e.getMessage();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }
}
