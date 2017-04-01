package com.hm.event.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shishun.wang
 * @date 2017年4月1日 上午10:45:22
 * @version 1.0
 * @describe
 */
public class Demo {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("ad23", "AA2", "43", "5", "32", "24");
		Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
		System.out.println(list);

		List<String> list2 = list.stream().map((String count) -> {
			return count.toLowerCase();
		}).collect(Collectors.toList());
		System.out.println(list2);

		list2 = list.stream().map(count -> {
			return count.toLowerCase();
		}).collect(Collectors.toList());
		System.out.println(list2);

		list2 = list.stream().map(count -> count.toLowerCase()).collect(Collectors.toList());
		System.out.println(list2);

		list2 = list.stream().map(String::toLowerCase).collect(Collectors.toList());
		System.out.println(list2);

	}
}
