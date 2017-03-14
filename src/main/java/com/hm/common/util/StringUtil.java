package com.hm.common.util;

import java.util.UUID;

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

}
