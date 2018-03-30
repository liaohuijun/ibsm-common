package com.hm.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shishun.wang
 * @date 2018年3月29日 下午4:54:48
 * @version 1.0
 * @describe
 */
public class MainApp {

	public static void main(String[] args) {

		{// 协变
			// 不可变
			// List<Fruit> fruits = new ArrayList<Aplle>();
			
			// 协变
			List<? extends Fruit> fruits = new ArrayList<Aplle>();
			Aplle aplle = new Aplle();
			Fruit fruit = new Fruit();

//			Fruit fruit2 = fruits.get(0);
		}

		{// 逆变
			// 编译不通过
			// List<Apple> apples = new ArrayList<Fruit>();

			// 逆变
			List<? super Aplle> aplles = new ArrayList<Fruit>();
			
			Aplle aplle = new Aplle();
			Fruit fruit = new Fruit();
			
			aplles.add(aplle);
			Fruit object = (Fruit) aplles.get(0);
			System.out.println(object.fun1());
		}

	}
}
