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

	private ArraysUtil() {
	}

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
		List<T> list = new ArrayList<>();
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
		List<T> list = new ArrayList<>();
		if (null == sources) {
			return (T[]) list.toArray();
		}
		for (T obj : sources) {
			list.add(obj);
			list.remove(target);
		}
		return (T[]) list.toArray();
	}

	/**
	 * 集合是否有交叉
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static <T> boolean cross(T[] arg0, T[] arg1) {
		if (arg0 == null && arg1 == null) {
			return true;
		}
		List<T> arg0List = new ArrayList<>();
		if (null != arg0) {
			arg0List = Arrays.asList(arg0);
		}
		List<T> arg1List = new ArrayList<>();
		if (null != arg1) {
			arg1List = Arrays.asList(arg1);
		}

		if (arg0List.size() > arg1List.size()) {
			for (T obj : arg0List) {
				if (arg1List.contains(obj)) {
					return true;
				}
			}
		} else {
			for (T obj : arg1List) {
				if (arg0List.contains(obj)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 集合是否包含某个元素
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static <T> boolean contains(T[] source, T target) {
		if (CommonUtil.isEmpty(source)) {
			return false;
		}
		return Arrays.asList(source).contains(target);
	}
}
