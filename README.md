#常用工具类合集:
1. 统一web后端处理

```
package com.hm.car.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hm.common.ServerResponse;

/**
 * @author shishun.wang
 * @date 2016年12月15日 下午12:00:07
 * @version 1.0
 * @describe
 */
public class ControllerResult<T> {

	public static <T> ResponseEntity<ServerResponse<T>> success(T result) {
		return new ResponseEntity<ServerResponse<T>>(new ServerResponse<T>().success(result), HttpStatus.OK);
	}

	public static <T> ResponseEntity<ServerResponse<T>> failed(String message) {
		return new ResponseEntity<ServerResponse<T>>(new ServerResponse<T>().failure(message), HttpStatus.BAD_REQUEST);
	}

	public static <T> ResponseEntity<ServerResponse<T>> failed(Exception e) {
		return new ResponseEntity<ServerResponse<T>>(new ServerResponse<T>().failure(e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
}

```
2. java模型数据快速拷贝

```
package com.hm.car.util;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import com.hm.common.util.CommonUtil;

/**
 * @author shishun.wang
 * @date 2016年12月14日 上午10:11:15
 * @version 1.0
 * @describe
 */
public class BeanUtil {

	private static final String MODEL_ID_FIELD = "id";
	
	public static final void copyProperties(Object source, Object target, String... ignoreProperties) {
		if (com.hm.common.util.CommonUtil.isAnyEmpty(source, target)) {
			return;
		}
		BeanUtils.copyProperties(source, target, ignoreProperties);
		// Trying copy id field
		if (com.hm.common.util.CommonUtil.isNotEmpty(ignoreProperties) && Arrays.asList(ignoreProperties).contains(MODEL_ID_FIELD)) {
			return;
		}
		Field targetFiled = ReflectionUtils.findField(target.getClass(), MODEL_ID_FIELD, String.class);
		if (targetFiled == null) {
			return;
		}
		Field sourceFiled = ReflectionUtils.findField(source.getClass(), MODEL_ID_FIELD);
		if (sourceFiled == null) {
			return;
		}
		try {
			sourceFiled.setAccessible(true);
			Object sourceId = sourceFiled.get(source);
			if (sourceId == null) {
				return;
			}
			targetFiled.setAccessible(true);
			if (sourceId instanceof String) {
				ReflectionUtils.setField(targetFiled, target, sourceId);
			} else {
				ReflectionUtils.setField(targetFiled, target, sourceId.toString());
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LoggerFactory.getLogger(CommonUtil.class).error("Failed to reflect set id properties", e);
		}

	}
}

```
