package com.hm.event.lambda;

/**
 * @author shishun.wang
 * @date 2017年4月1日 下午3:17:49
 * @version 1.0
 * @describe 
 */
public interface TestJdk8Inf {

	default void init(){
		System.out.println("接口初始化方法");
	}
}
