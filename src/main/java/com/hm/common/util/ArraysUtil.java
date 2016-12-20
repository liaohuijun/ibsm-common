package com.hm.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shishun.wang
 * @date 2016年12月20日 下午3:35:45
 * @version 1.0
 * @describe 数组工具类
 */
@SuppressWarnings("unchecked")
public class ArraysUtil extends CommonUtil {

	/**
	 * 排除数组中重复值
	 * 
	 * @param <T>
	 * 
	 * @param sources
	 * @return
	 */
	public static <T> T[] distinct(T[] sources) {
		if (CommonUtil.isEmpty(sources)) {
			return sources;
		}
		List<T> list = new ArrayList<T>();
		for (T obj : sources) {
			if (!list.contains(obj)) {
				list.add(obj);
			}
		}
		return (T[]) list.toArray();
	}

	/**
	 * 指定数组中追加元素
	 * 
	 * @param <T>
	 * 
	 * @param sources
	 * @param target
	 * @return
	 */
	public static <T> T[] join(T[] sources, T target) {
		if (null == sources) {
			return (T[]) new Object[] { target };
		}
		T[] tmp = Arrays.copyOf(sources, sources.length + 1);
		tmp[sources.length] = target;
		return tmp;
	}

	/**
	 * 移除数组中---指定元素
	 * 
	 * @param <T>
	 * 
	 * @param sources
	 * @param target
	 * @return
	 */
	public static <T> T[] remove(T[] sources, T target) {
		if (null == sources) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (T obj : sources) {
			list.add(obj);
			list.remove(target);
		}
		return (T[]) list.toArray();
	}
}
