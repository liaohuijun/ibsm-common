package com.hm.event.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shishun.wang
 * @date 2017年4月1日 上午11:11:54
 * @version 1.0
 * @describe
 */
public class StreamTest {

	public static void main(String[] args) {
		List<Integer> nums = Arrays.asList(1, null, 3, 4, null, 6);
		List<Integer> list = nums.stream().filter(value -> value != null).collect(Collectors.toList());
		System.out.println(list);
		
		Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);

//		Stream.generate(new Supplier<Double>() {
//
//			@Override
//			public Double get() {
//				return Math.random();
//			}
//		}).forEach(value -> {
//			System.out.println(value);
//		});
		
//		Stream.generate(() -> Math.random()).forEach(value ->{
//			System.out.println(value);
//		});;
		
//		Stream.generate(Math::random).forEach(value ->{
//			System.out.println(value);
//		});
		

		List<Integer> counts = Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10);
		//这段代码演示了上面介绍的所有转换方法（除了flatMap），简单解释一下这段代码的含义：给定一个Integer类型的List，
		//获取其对应的Stream对象，然后进行过滤掉null，再去重，再每个元素乘以2，再每个元素被消费的时候打印自身，在跳过前两个元素，
		//最后去前四个元素进行加和运算
		System.out.println("sum is : "+ counts.stream().filter(count -> null!= count)
				.distinct().mapToInt(num -> num*2).limit(4).sum());
		
	
		//这个函数有两个参数，第一个参数是上次函数执行的返回值（也称为中间结果），第二个参数是stream中的元素，这个函数把这两个值相加，
		//得到的和会被赋值给下次执行这个函数的第一个参数。要注意的是：**第一次执行的时候第一个参数的值是Stream的第一个元素，第二个参数是Stream的第二个元素**。
		//这个方法返回值类型是Optional，这是Java8防止出现NPE的一种可行方法，后面的文章会详细介绍，这里就简单的认为是一个容器，其中可能会包含0个或者1个对象。
		System.out.println(Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().reduce((sum,item) -> {
			System.out.println("sum:"+sum+";item:"+item);
			return sum+item;
		}).get());
		
		System.out.println(Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().reduce(0,(sum,item) -> sum+item));
		
		/*
		– 搜索相关
		– allMatch：是不是Stream中的所有元素都满足给定的匹配条件
		– anyMatch：Stream中是否存在任何一个元素满足匹配条件
		– findFirst: 返回Stream中的第一个元素，如果Stream为空，返回空Optional
		– noneMatch：是不是Stream中的所有元素都不满足给定的匹配条件
		– max和min：使用给定的比较器（Operator），返回Stream中的最大|最小值
		*/
		
		System.out.println(
				Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().allMatch(item -> {
					System.out.println("--->"+item);
					return item > 100;
				})
		);
		
		//判断所有数字都是大于100，返回判断结果
		System.out.println(Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().allMatch(item -> item > 100));
		
		//获取最大值
		Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().max((o1,o2) -> o1.compareTo(o2)).ifPresent(value ->{
			System.out.println(value);
		});
		
		System.out.println("-----------------------------------------------------------------------");
		
//		DefaulableF
		
	}
}
