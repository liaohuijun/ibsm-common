package com.hm.jdk8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shishun.wang
 * @date 2018年1月5日 下午2:32:24
 * @version 1.0
 * @describe
 */
public class TestMapReduce {

	public static void main(String[] args) {
		System.out.println("------------------华丽分割线--------------------");
		// 不使用lambda表达式为每个订单加上12%的税
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		for (Integer cost : costBeforeTax) {
			double price = cost + .12 * cost;
			System.out.println(price);
		}
		System.out.println("------------------华丽分割线--------------------");

		costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(cost -> {
			System.out.println(cost);
		});
		System.out.println("------------------华丽分割线--------------------");

		Double double1 = costBeforeTax.stream().map(cost -> cost + .12 * cost).reduce((sum, cost) -> {
			return sum + cost;
		}).get();
		System.out.println(double1);

		System.out.println("------------------华丽分割线--------------------");
		System.out.println(costBeforeTax.stream().map(cost -> cost + .12 * cost).collect(Collectors.toList()));

		System.out.println("------------------华丽分割线--------------------");
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
		System.out.println(G7.stream().map(str -> str.toUpperCase()).collect(Collectors.joining("_")));

		//如何利用流的 distinct() 方法来对集合进行去重。复制不同的值从新创建一个子集合
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		System.out.println(numbers.stream().map(num -> num * num).distinct().collect(Collectors.toList()));
		System.out.println("------------------华丽分割线--------------------");
	
		//获取数字的个数、最小值、最大值、总和以及平均值
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Highest prime number in List : " + stats.getMax());
		System.out.println("Lowest prime number in List : " + stats.getMin());
		System.out.println("Sum of all prime numbers : " + stats.getSum());
		System.out.println("Average of all prime numbers : " + stats.getAverage());
		
	}
}
