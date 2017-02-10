package com.hm.common.def;

/**
 * @author shishun.wang
 * @date 2017年2月9日 上午11:07:04
 * @version 1.0
 * @describe
 */
public enum MobileDevicesDef {

	/**
	 * ANDROID手机
	 */
	ANDROID("ANDROID", "ANDROID手机"),

	/**
	 * IPHONE手机
	 */
	IPHONE("IPHONE", "IPHONE手机"),
	
	/**
	 * PC浏览器
	 */
	PC("PC","PC浏览器");

	private String status;

	private String desc;

	private MobileDevicesDef(String status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}

	public String value() {
		return this.status;
	}
}
