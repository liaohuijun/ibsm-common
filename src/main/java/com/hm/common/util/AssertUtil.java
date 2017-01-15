package com.hm.common.util;

import java.util.Collection;
import java.util.Map;

import com.hm.common.exception.ServiceException;

/**
 * @author shishun.wang
 * @date 下午11:00:32 2017年1月15日
 * @version 1.0
 * @describe
 */
public class AssertUtil extends CommonUtil {

	private AssertUtil() {
	}

	public static final void isEmpty(Collection<? extends Object> value, String message) {
		if (CommonUtil.isEmpty(value)) {
			throw ServiceException.warn(message);
		}
	}

	public static final void isAnyEmpty(Collection<? extends Object> value, String message) {
		if (CommonUtil.isAnyEmpty(value)) {
			throw ServiceException.warn(message);
		}
	}

	public static final void isEmpty(Map<? extends Object, ? extends Object> value, String message) {
		if (CommonUtil.isEmpty(value)) {
			throw ServiceException.warn(message);
		}
	}

	public static final void isEmpty(Object value, String message) {
		if (CommonUtil.isEmpty(value)) {
			throw ServiceException.warn(message);
		}
	}

	public static final void isEmpty(String value, String message) {
		if (CommonUtil.isEmpty(value)) {
			throw ServiceException.warn(message);
		}
	}

	public static final void isEmpty(Object[] value, String message) {
		if (CommonUtil.isEmpty(value)) {
			throw ServiceException.warn(message);
		}
	}

	public static final void isNotEmpty(Object value, String message) {
		if (CommonUtil.isNotEmpty(value)) {
			throw ServiceException.warn(message);
		}
	}

	public static final void isAllEmpty(String message, Object... value) {
		if (CommonUtil.isAllEmpty(value)) {
			throw ServiceException.warn(message);
		}
	}

	public static final void isAnyEmpty(String message, Object... value) {
		if (CommonUtil.isAnyEmpty(value)) {
			throw ServiceException.warn(message);
		}
	}
}
