/**
 * 
 */
package com.hm.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shishun.wang
 * @date 2016年11月28日 上午10:14:07
 * @version 1.0
 * @describe
 */
public class CollectionUtil extends CommonUtil {

	private CollectionUtil() {
	}

	@SafeVarargs
	public static final <T> Set<T> toSet(T... paramm) {
		HashSet<T> set = new HashSet<>(paramm.length);
		for (T parameter : paramm) {
			set.add(parameter);
		}
		return set;
	}

	public static final <T> List<T> distinct(List<T> list, Comparator<T> comparator) {
		if (isEmpty(list)) {
			return Collections.emptyList();
		}
		List<T> targetList = new ArrayList<>();
		list.stream().filter(item -> item != null).forEach(item -> {
			Optional<T> tryFind = targetList.parallelStream().filter(targetItem -> comparator.compare(item, targetItem) == 0).findAny();
			if (!tryFind.isPresent()) {
				targetList.add(item);
			}
		});
		return targetList;
	}

	public static final <T> List<T> distinct(List<T> list) {
		if (isEmpty(list)) {
			return Collections.emptyList();
		}
		return list.stream().distinct().collect(Collectors.toList());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final List difference(List source, List target) {
		List sourceTmp = null;
		List targetTmp = null;
		if (source.size() >= target.size()) {
			sourceTmp = source;
			targetTmp = target;
		} else {
			sourceTmp = target;
			targetTmp = source;
		}
		List list = new ArrayList(Arrays.asList(new Object[sourceTmp.size()]));
		Collections.copy(list, sourceTmp);
		list.removeAll(targetTmp);
		return list;
	}
}
