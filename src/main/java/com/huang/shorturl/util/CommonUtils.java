package com.huang.shorturl.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huang.shorturl.entity.po.ShortUrlInfo;


/**
 * @author yaohui.huang
 * @className CommonUtils
 * @date 2023/1/1 13:06
 * @description: 工具类
 */
public class CommonUtils {
	/**
	 * 存储映射数据List
	 */
	public static List<ShortUrlInfo> urlList = new ArrayList<ShortUrlInfo>();
	

	/**
	 * 日期格式
	 */
	public static String YYYYMMDDHHMMSS = "yyMMddHHmmss";

	public static String parseDateToStr(String format, Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static List<ShortUrlInfo> getUrlList() {
		return urlList;
	}

	public static void setUrlList(List<ShortUrlInfo> urlList) {
		CommonUtils.urlList = urlList;
	}
	
	
	
	

}
