package com.hm.common.def;

/**
 * @author shishun.wang
 * @date 下午4:00:26 2017年2月2日
 * @version 1.0
 * @describe
 */
public enum GenderDef {

	/**
	 * 男
	 */
	MALE(1, "男"),

	/**
	 * 女
	 */
	WOMAN(2, "女");

	private int value;

	private String desc;

	private GenderDef(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}

	public int value() {
		return this.value;
	}
}
