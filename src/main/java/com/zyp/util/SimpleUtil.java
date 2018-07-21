package com.zyp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhiyp
 * @date 2018/5/24 0024
 */
public class SimpleUtil {
	// 短日期格式
	public static String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 将长整型数字转换为日期格式的字符串
	 *
	 * @param time
	 * @return
	 */
	public static String convert2String(long time) {
		if (time > 0l) {
			SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
			Date date = new Date(time);
			return sf.format(date);
		}
		return "";
	}

	public static String cutHtml(String str){
		return str.replaceAll("</?[^>]+>", "");
	}
}
