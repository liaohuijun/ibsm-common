package com.hm.general;

/**
 * @author shishun.wang
 * @date 上午11:12:11 2017年8月16日
 * @version 1.0
 * @describe
 */
public class TestGeneralSuport {

	public static void main(String[] args) {
		TestA test = new TestA();
		test.setId("aa123");
		test.setName("张三");

		System.out.println(test);

		GeneralSuportVo<TestA> aa = test;
		System.out.println(aa);
		
		System.out.println(((TestA) aa).getId());
	}
}
