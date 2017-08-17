package com.hm.general;

/**
 * @author shishun.wang
 * @date 上午11:11:16 2017年8月16日
 * @version 1.0
 * @describe
 */
public class TestB extends GeneralSuportVo<TestB> {

	private static final long serialVersionUID = 1L;

	private int age;

	private String address;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "TestB [age=" + age + ", address=" + address + "]";
	}

}
