package com.hm.common.def;

/**
 * @author shishun.wang
 * @date 2017年3月28日 下午2:40:38
 * @version 1.0
 * @describe 
 */
public enum LoggerRecordChannelDef {

	LOGIN("LOGIN", "登陆"),

	REGISTERED("REGISTERED", "注册"),

	DOWNLOAD("DOWNLOAD", "下载"),
	
	ACTIVATION("ACTIVATION", "激活");
	
	private String status;

	private String desc;

	private LoggerRecordChannelDef(String status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	
	public String desc() {
		return this.desc;
	}

	public String status() {
		return this.status;
	}
}
