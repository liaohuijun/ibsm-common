package com.hm.common.def;

/**
 * @author shishun.wang
 * @date 下午6:41:40 2017年2月2日
 * @version 1.0
 * @describe 激活状态
 */
public enum ActivateDef {

	/**
	 * 禁用（未激活）
	 */
	DISABLE("DISABLE", "禁用（未激活）"),

	/**
	 * 启用（已激活）
	 */
	ENABLE("ENABLE", "启用（已激活）");

	private String code;

	private String desc;

	private ActivateDef(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}

	public String code() {
		return this.code;
	}
	
	public static ActivateDef trance(String code) {
		for (ActivateDef statusDef : ActivateDef.values()) {
			if (statusDef.code().equals(code)) {
				return statusDef;
			}
		}
		return null;
	}
}
