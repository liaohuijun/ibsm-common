package com.hm.jdk8;

/**
 * @author shishun.wang
 * @date 2017年4月24日 下午3:14:34
 * @version 1.0
 * @describe 
 */
public interface ITest {

	default void aa(){
		System.out.println("aa");
		bb();
	}
	
	default void bb(){
		System.out.println("bb");
		cc();
	}
	
	default void cc(){
		System.out.println("cc");
		dd();
	}
	
	default void dd(){
		System.out.println("dd");
		test();
	}
	
	void test();
}
