package com.hm.common.def;

/**
 * @author shishun.wang
 * @date 2017年2月9日 上午11:07:04
 * @version 1.0
 * @describe
 */
public enum ClientRequestDevicesDef {

	/**
	 * ANDROID手机
	 */
	ANDROID("ANDROID", "ANDROID手机"),
	
	/**
	 * 
	 */
	WINDOWS_PHONE("WINDOWS_PHONE","windows phone"),

	/**
	 * IPHONE手机
	 */
	MAC("MAC", "MAC OS"),
	
	/**
	 * PC浏览器
	 */
	PC("PC","PC浏览器");

	private String code;

	private String desc;

	private ClientRequestDevicesDef(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}

	public String code() {
		return this.code;
	}
}
