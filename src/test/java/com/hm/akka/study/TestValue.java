package com.hm.akka.study;

/**
 * @author shishun.wang
 * @date 2018年3月8日 下午3:23:08
 * @version 1.0
 * @describe 
 */
public class TestValue {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TestValue [name=" + name + "]";
	}
}
