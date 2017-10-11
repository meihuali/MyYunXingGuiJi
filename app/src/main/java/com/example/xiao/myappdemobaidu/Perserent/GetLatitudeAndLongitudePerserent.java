package com.example.xiao.myappdemobaidu.Perserent;

import com.example.xiao.myappdemobaidu.Impl.GetLatitudeAndLongitudeImpl;
import com.example.xiao.myappdemobaidu.View.IsGetLatitudeAndLongitudeView;
import com.example.xiao.myappdemobaidu.model.IsGetLatitudeAndLongitudeModle;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.Perserent
 * 文件名：GetLatitudeAndLongitudePerserent
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/10 0010 17:03
 * 描述：TODO
 */
public class GetLatitudeAndLongitudePerserent {
    IsGetLatitudeAndLongitudeModle getLatitudeAndLongitudeModle;
    IsGetLatitudeAndLongitudeView getLatitudeAndLongitudeView;

    public GetLatitudeAndLongitudePerserent(IsGetLatitudeAndLongitudeView getLatitudeAndLongitudeView) {
        this.getLatitudeAndLongitudeView = getLatitudeAndLongitudeView;
        this.getLatitudeAndLongitudeModle = new GetLatitudeAndLongitudeImpl();
    }

    public void getjingweidu(String num, String limit) {
        if (getLatitudeAndLongitudeModle != null) {
            getLatitudeAndLongitudeModle.getjingweidu(new IsGetLatitudeAndLongitudeModle.getjingweiduLineres() {
                @Override
                public void onComptle(Object object) {
                    getLatitudeAndLongitudeView.showResult(object);
                }
            },num,limit);
        }
    }
}
