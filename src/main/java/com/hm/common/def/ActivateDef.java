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
	DISABLE(0, "禁用（未激活）"),

	/**
	 * 启用（已激活）
	 */
	ENABLE(1, "启用（已激活）");

	private int status;

	private String desc;

	private ActivateDef(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}

	public int status() {
		return this.status;
	}
}
