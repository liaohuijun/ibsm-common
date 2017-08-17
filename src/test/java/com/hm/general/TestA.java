package com.hm.general;

/**
 * @author shishun.wang
 * @date 上午11:10:06 2017年8月16日
 * @version 1.0
 * @describe
 */
public class TestA extends GeneralSuportVo<TestA> {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TestA [id=" + id + ", name=" + name + "]";
	}

}
