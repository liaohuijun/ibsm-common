package com.hm.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author shishun.wang
 * @date 2018年1月5日 下午12:07:09
 * @version 1.0
 * @describe 使用lambda表达式和函数式接口Predicate
 * 
 *           除了在语言层面支持函数式编程风格，Java 8也添加了一个包，叫做
 *           java.util.function。它包含了很多类，用来支持Java的函数式编程。其中一个便是Predicate，使用
 *           java.util.function.Predicate
 *           函数式接口以及lambda表达式，可以向API方法添加逻辑，用更少的代码支持更多的动态行为。下面是Java 8 Predicate
 *           的例子，展示了过滤集合数据的多种常用方法。Predicate接口非常适用于做过滤。
 */
public class TestPredicate {

	public static void main(String[] args) {
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		filter(languages, (str) -> str.startsWith("J"));
		System.out.println("------------------华丽分割线--------------------");
		filter(languages, (str) -> str.endsWith("a"));
		System.out.println("------------------华丽分割线--------------------");
		filter(languages, (str) -> true);
		System.out.println("------------------华丽分割线--------------------");
		filter(languages, (str) -> false);
		System.out.println("------------------华丽分割线--------------------");
		filter(languages, (str) -> str.length() > 4);
		System.out.println("------------------华丽分割线--------------------");
		// java.util.function.Predicate 允许将两个或更多的 Predicate
		// 合成一个。它提供类似于逻辑操作符AND和OR的方法，名字叫做and()、or()和xor()，用于将传入 filter()
		// 方法的条件合并起来。例如，要得到所有以J开始，长度为四个字母的语言，可以定义两个独立的 Predicate 示例分别表示每一个条件，然后用
		// Predicate.and() 方法将它们合并起来，如下所示：

		 Predicate<String> startWidth = (str) -> str.startsWith("J");
		 Predicate<String> length = (str) -> str.length() > 2;

		languages.stream().filter(startWidth.and(length)).forEach(name ->{
			System.out.println(name);
		});
	}

	public static void filter(List<String> languages, Predicate<String> condition) {
		/*
		 * for (String name : languages) { if (condition.test(name)) {
		 * System.out.println(name); } }
		 */

		languages.stream().filter((name) -> condition.test(name)).forEach(name -> {
			System.out.println(name);
		});
		;
	}

}
