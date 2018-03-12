package com.hm.akka.study;

/**
 * @author shishun.wang
 * @date 2018年3月8日 下午3:17:42
 * @version 1.0
 * @describe
 */
public class Test {

	public static void main(String[] args) throws Exception {

		for(int i = 0 ; i < 3; i ++) {
			TestValue testValue = new TestValue();
			testValue.setName(i+"");
			change(testValue);
		}
	}
	
	public static void change(TestValue t) throws Exception {
		System.out.println(t.toString());
		Thread.sleep(3000);
		System.out.println(t.toString());
	}

}
