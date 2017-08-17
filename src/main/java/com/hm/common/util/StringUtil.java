package com.hm.common.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author shishun.wang
 * @date 上午11:40:04 2016年11月18日
 * @version 1.0
 * @describe
 */
public class StringUtil extends CommonUtil {

	private StringUtil() {
	}

	/**
	 * 不区分大小写字符串比较
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static final boolean notDistinguishCaseEquals(String arg0, String arg1) {
		if (CommonUtil.isAllEmpty(arg0, arg1)) {
			return true;
		}
		if (CommonUtil.isAnyEmpty(arg0, arg1)) {
			return false;
		}
		return arg0.trim().equalsIgnoreCase(arg1.trim());
	}

	public static final boolean isBlank(String value) {
		if (isEmpty(value)) {
			return true;
		}
		if (value.trim().length() == 0) {
			return true;
		}
		return false;
	}

	public static final boolean isNotBlank(String value) {
		return !isBlank(value);
	}

	public static final boolean isBlankOrNull(String value) {
		if (isBlank(value)) {
			return true;
		}
		if ("null".equals(value.trim())) {
			return true;
		}
		return false;
	}

	public static final boolean notBlankOrNull(String value) {
		return !isBlankOrNull(value);
	}

	/**
	 * 首字母大写
	 * 
	 * @param arg0
	 * @return
	 */
	public static String capitalize(String arg0) {
		char[] chars = arg0.toCharArray();
		if (chars[0] >= 'a' && chars[0] <= 'z') {
			chars[0] -= 'a' - 'A';
		}
		return new String(chars);
	}

	/**
	 * 获取16位长度纯数字字符串
	 * 
	 * @return
	 */
	public static String getCodeNumber() {
		int machined = (int) (Math.random() * 10);
		int hash = UUID.randomUUID().toString().hashCode();
		if (hash < 0) {
			hash = -hash;
		}

		return machined + String.format("%015d", hash);
	}

	public static String token() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 不支持分布式系统---- 获取16位长度纯数字字符串
	 * 
	 * @return
	 */
	public static String rand16number() {
		int machined = (int) (Math.random() * 10);
		int hash = UUID.randomUUID().toString().hashCode();
		if (hash < 0) {
			hash = -hash;
		}

		return machined + String.format("%015d", hash);
	}

	/**
	 * 驼峰字符串转下划线字符串
	 * 
	 * @param arg0
	 * @return
	 */
	public static String camelToUnderline(String arg0) {
		if (CommonUtil.isEmpty(arg0)) {
			return null;
		}

		int length = arg0.length();
		StringBuilder builder = new StringBuilder(length);

		for (int i = 0; i < length; ++i) {
			char ch = arg0.charAt(i);
			if (Character.isUpperCase(ch)) {
				builder.append("_");
				builder.append(Character.toLowerCase(ch));
			} else {
				builder.append(ch);
			}
		}

		return builder.toString();
	}

	public static String[] splitArrays(String str) {
		if (CommonUtil.isEmpty(str)) {
			return null;
		}
		return str.split(",");
	}

	/**
	 * 下划线字符串转驼峰字符串
	 * 
	 * @param arg0
	 * @return
	 */
	public static String underlineToCamel(String arg0) {
		if (CommonUtil.isEmpty(arg0)) {
			return null;
		}

		int length = arg0.length();
		StringBuilder builder = new StringBuilder(length);

		for (int i = 0; i < length; ++i) {
			char ch = arg0.charAt(i);
			if (ch == 95) {
				++i;
				if (i < length) {
					builder.append(Character.toUpperCase(arg0.charAt(i)));
				}
			} else {
				builder.append(ch);
			}
		}

		return builder.toString();
	}

	/**
	 * 清除字符串中的所有空格
	 * 
	 * @param arg0
	 * @return
	 */
	public static String dislodgeAllBlank(String arg0) {
		if (CommonUtil.isEmpty(arg0)) {
			return null;
		}
		return arg0.replaceAll("\\s*", "");
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 大陆号码或香港号码均可
	 */
	public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
		return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
	}

	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 此方法中前三位格式有： 13+任意数 15+除4的任意数 18+除1和4的任意数
	 * 17+除9的任意数 147
	 */
	public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^((13[0-9])|(15[^4])|(18[0,1,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 香港手机号码8位数，5|6|8|9开头+7位任意数
	 */
	public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^(5|6|8|9)\\d{7}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 判断字符串全是数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumberic(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	/*
	 * 判断字符串中是否仅包含字母数字和汉字 各种字符的unicode编码的范围：
	 * 汉字：[0x4e00,0x9fa5]（或十进制[19968,40869]） 数字：[0x30,0x39]（或十进制[48, 57]）
	 * 小写字母：[0x61,0x7a]（或十进制[97, 122]） 大写字母：[0x41,0x5a]（或十进制[65, 90]）
	 * 
	 */
	public static boolean isLetterDigitOrChinese(String str) {
		String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
		return str.matches(regex);
	}

}
