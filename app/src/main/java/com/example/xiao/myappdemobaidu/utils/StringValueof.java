package com.example.xiao.myappdemobaidu.utils;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.utils
 * 文件名：StringValueof
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/2 0002 上午 10:15
 * 描述：TODO
 */
public class StringValueof {
    public static double StringZhuanHuan(String text) {
        if (!text.isEmpty()) {
            return Double.parseDouble(text);
        }
      return 1;
    }
}
