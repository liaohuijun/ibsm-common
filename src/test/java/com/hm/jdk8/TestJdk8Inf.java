package com.hm.jdk8;

/**
 * @author shishun.wang
 * @date 2017年4月24日 下午3:20:37
 * @version 1.0
 * @describe 
 */
public class TestJdk8Inf {

	public static void main(String[] args) {
		((ITest)()->{
			System.out.println("11");
		}).test();
	}
}
