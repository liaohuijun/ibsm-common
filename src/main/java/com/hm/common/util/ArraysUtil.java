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
		if (null == sources) {
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
		if (null == source) {
			return false;
		}
		return Arrays.asList(source).contains(target);
	}

	/**
	 * 聚合多个数组合并为一个数组
	 * 
	 * @param source
	 * @param targets
	 * @return
	 */
	public static <T> T[] concat(T[] source, T[]... targets) {
		if (CommonUtil.isEmpty(targets)) {
			return source;
		}
		int offset = null == source ? 0 : source.length;
		int index = offset;
		for (T[] objs : targets) {
			index += null == objs ? 0 : objs.length;
		}
		T[] result = null == source ? (T[]) new Object[index] : Arrays.copyOf(source, index);
		for (T[] objs : targets) {
			if (null != objs) {
				System.arraycopy(objs, 0, result, offset, objs.length);
				offset += objs.length;
			}
		}
		return result;
	}
}
