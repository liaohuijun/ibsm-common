package com.hm.common.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author shishun.wang
 * @date 上午11:39:48 2016年11月18日
 * @version 1.0
 * @describe
 */
public abstract class CommonUtil {

	public static final boolean isDigit(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			// Ignore Sonar check for checking the varchar/char is a double
			// value
			Double.valueOf(str);// NOSONAR
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static final boolean isLong(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			// Ignore Sonar check for checking the varchar/char is a long
			// value
			Long.valueOf(str);// NOSONAR
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static final boolean isInteger(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			// Ignore Sonar check for checking the varchar/char is a Integer
			// value
			Integer.valueOf(str);// NOSONAR
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static final boolean isHexLong(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			Long.parseUnsignedLong(str, 16);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static final boolean isEmpty(Collection<? extends Object> value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		return false;
	}

	public static final boolean isAnyEmpty(Collection<? extends Object> value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		for (Object item : value) {
			if (isEmpty(item)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isEmpty(Map<? extends Object, ? extends Object> value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		return false;
	}

	public static final boolean isEmpty(Object value) {
		if (value != null && value instanceof String) {
			return isEmpty((String) value);
		} else if (value != null && value instanceof Collection) {
			return isEmpty((Collection<?>) value);
		} else if (value != null && value instanceof Map) {
			return isEmpty((Map<?, ?>) value);
		}
		return value == null;
	}

	public static final boolean isEmpty(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}

	public static final boolean isEmpty(Object[] value) {
		if (value == null || value.length < 1) {
			return true;
		}
		return false;
	}

	public static final boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}

	public static final boolean isAllEmpty(Object... value) {
		if (isEmpty(value)) {
			return true;
		}
		for (Object item : value) {
			if (!isEmpty(item)) {
				return false;
			}
		}
		return true;
	}

	public static final boolean isAnyEmpty(Object... value) {
		if (isEmpty(value)) {
			return true;
		}
		for (Object item : value) {
			if (isEmpty(item)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <T> T of(T arg0) {
		if (isEmpty(arg0)) {
			return null;
		}
		if (arg0 instanceof String) {
			if (StringUtil.isBlank(arg0.toString())) {
				return null;
			}
			return (T) arg0.toString().trim();
		}
		return arg0;
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
		String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
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
}
