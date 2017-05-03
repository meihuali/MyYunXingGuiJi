package com.example.xiao.myappdemobaidu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

public class GetTime {
	/**
	 * ��������ת��ʱ���
	 * @param time
	 * @return
	 */
	public static String dataOne(String time) {

		SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒",
				Locale.CHINA);
		Date date;
		String times = null;
		try {
			date = sdr.parse(time);
			long l = date.getTime();
			String stf = String.valueOf(l);
			times = stf.substring(0, 10);
			Log.d("--444444---", times);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return times;
	}

}
