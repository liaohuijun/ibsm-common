package com.hm.akka.demo.immutableobj;

import java.util.List;

/**
 * @author shishun.wang
 * @date 2018年1月8日 上午10:58:16
 * @version 1.0
 * @describe
 */
public class Message {

	private int age;

	private List<String> list;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	/**
	 * @param age
	 * @param list
	 */
	public Message(int age, List<String> list) {
		super();
		this.age = age;
		this.list = list;
	}

}
