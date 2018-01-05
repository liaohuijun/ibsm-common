package com.hm.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author shishun.wang
 * @date 2018年1月5日 下午3:48:02
 * @version 1.0
 * @describe
 */
public class TesrtOptional {

	public static void main(String[] args) {
		System.out.println("------------------华丽分割线--------------------");
		List<String> users = null;

		Optional.ofNullable(users).ifPresent(a -> {
			System.out.println(a);
		});
		System.out.println("------------------华丽分割线--------------------");

		System.out.println(Optional.ofNullable(users).isPresent());
		System.out.println("------------------华丽分割线--------------------");

		users = new ArrayList<String>();
		System.out.println(Optional.ofNullable(users).get());
		System.out.println("------------------华丽分割线--------------------");
		users.add("aa");
		users.add("bb");
		System.out.println(Optional.ofNullable(users).get());

		Optional.ofNullable(users).ifPresent(a -> {
			System.out.println(a);
		});

	}
}
