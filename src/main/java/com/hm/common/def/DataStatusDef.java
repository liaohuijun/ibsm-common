package com.hm.common.def;

/**
 * @author shishun.wang
 * @date 下午10:41:28 2017年2月1日
 * @version 1.0
 * @describe 数据状态
 */
public enum DataStatusDef {

	/**
	 * 禁用（已删除）
	 */
	DISABLE("DISABLE", "禁用（已删除）"),

	/**
	 * 启用（正在使用）
	 */
	ENABLE("ENABLE", "启用（正在使用）");

	private String code;

	private String desc;

	private DataStatusDef(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}

	public String code() {
		return this.code;
	}

	public static DataStatusDef trance(String code) {
		for (DataStatusDef statusDef : DataStatusDef.values()) {
			if (statusDef.code().equals(code)) {
				return statusDef;
			}
		}
		return null;
	}
}
