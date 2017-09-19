package com.hm.common.def;

/**
 * @author shishun.wang
 * @date 下午5:32:02 2017年9月19日
 * @version 1.0
 * @describe 
 */
public enum WhetherDef {
	
	YES("YES","是"),
	
	NO("NO","否");

	private String status;

	private String desc;

	private WhetherDef(String status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}

	public String status() {
		return this.status;
	}
	
	public static WhetherDef trance(String status) {
		for (WhetherDef enumTmp : WhetherDef.values()) {
			if (enumTmp.status.equals(status)) {
				return enumTmp;
			}
		}
		return null;
	}
}
