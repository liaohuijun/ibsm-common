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
}
