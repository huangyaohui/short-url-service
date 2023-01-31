package com.huang.shorturl.util;

import java.util.ArrayList;
import java.util.List;

import com.huang.shorturl.entity.po.ShortUrlInfo;

/**
 * @author yaohui.huang
 * @className BaseConvertUtil
 * @date 2023/1/24 9:43
 * @description: 进制转换工具 B（Binary)表示二进制 O（Octal）表示八进制 D（Decimal）表示十进制
 *               H（Hexadecimal）表示十六进制 62进制
 */
public class BaseConvertUtil {

	
	
	private static String MATCHES_STR="[a-zA-Z\\d]+";
	
	/**
	 * ASCII字符代码表 十进制57
	 */
	private static int ASCII_57=57;
	/**
	 * ASCII字符代码表 十进制90
	 */
	private static int ASCII_90=90;

	/**
	 * 62进制转换率
	 */
	private static int SCALE_62 = 62;
	/**
	 * 62进制，索引位置代表转换字符的数值 0-61，比如 A代表10，z代表61
	 */
	private static String CHARS_62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/**
	 * 十进制数字转换为62进制字符串 0123456789 0-9 ABCDEFGHIJKLMNOPQRSTUVWXYZ 10-35
	 * abcdefghijklmnopqrstuvwxyz 36-61
	 *
	 * @param value 十进制数字
	 * @return 62进制字符串
	 */
	public static String encode10to62(long value) {
		if (value < 0) {
			throw new IllegalArgumentException("参数非法(必须为非负数): " + value);
		}
		StringBuilder stringBuilder = new StringBuilder();
		while (value > SCALE_62 - 1) {
			stringBuilder.append(CHARS_62.charAt((int) (value % SCALE_62)));
			value = value / SCALE_62;
		}
		// 获取最高位
		stringBuilder.append(CHARS_62.charAt((int) (value % SCALE_62)));
		return stringBuilder.reverse().toString();
	}

	/**
	 * 将10进制数字转换为长度为length的62进制字符串 原始62进制字符串长度小于length,左侧用‘0’填充补齐
	 *
	 * @param value  十进制数字
	 * @param length 长度
	 * @return 长度为length或大于length的62进制字符串
	 */
	public static String encode10to62(long value, int length) {
		if (length < 1) {
			throw new IllegalArgumentException("参数非法(长度必须大于0): " + value);
		}
		String str62Base = encode10to62(value);
		if (str62Base.length() < length) {
			long num = (long) Math.pow(10, length);
			str62Base = num + str62Base;
			str62Base = str62Base.substring(str62Base.length() - length);
		}
		return str62Base;
	}

	/**
	 * 62进制编码转换为10进制编码
	 *
	 * @param str62Base 62进制编码
	 * @return 十进制编码
	 */
	public static long encode62to10(String str62Base) {
		if (str62Base == null || !str62Base.matches(BaseConvertUtil.MATCHES_STR)) {
			throw new IllegalArgumentException("参数非法(非62进制): " + str62Base);
		}
		int length = str62Base.length();
		long value = 0;
		for (int index = 0; index < length; index++) {
			value = value * SCALE_62 + base62To10(str62Base.charAt(index));
		}
		return value;
	}

	/**
	 * 62进制字符转换成对应十进制表示 根据ASCII字符代码表
	 * 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
	 *
	 * @param base62 62进制
	 * @return 十进制
	 */
	private static int base62To10(char base62) {
		int value = base62;
		// ‘0-9’ 0-9
		// ‘0’ ASCII字符代码表 十进制48
		// ‘9’ ASCII字符代码表 十进制57
		if (value <= ASCII_57) {
			value = value - 48;
		}
		// ‘A-Z’ 10-35
		// ‘A’ ASCII字符代码表 十进制65
		// ‘Z’ ASCII字符代码表 十进制90
		else if (value <= ASCII_90) {
			value = value - 65 + 10;
		}
		// ‘a-z’ 36-61
		// ‘a’ ASCII字符代码表 十进制97
		// ‘Z’ ASCII字符代码表 十进制122
		else {
			value = value - 97 + 36;
		}
		return value;
	}

	

}
