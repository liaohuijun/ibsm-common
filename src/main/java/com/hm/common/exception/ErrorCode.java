package com.hm.common.exception;

/**
 * @author shishun.wang
 * @date 下午6:23:02 2016年7月21日
 * @version 1.0
 * @describe
 */
public enum ErrorCode {

	/** 数据没找到 */
	DATA_NOT_FOUND("{0}数据没找到"),

	/** 不允许为空 */
	DATA_NOT_NULL("参数错误,{0}"),

	/** 无权访问 */
	NO_DATA_ACCESS("无数据访问权限"),
	
	/** 客户端请求频繁,请稍后重试*/
	CLIENT_REQUEST_VISIT_FREQUENTLY("请求频繁,请稍后重试"),
	
	/** 会话超时*/
	SESSION_OUT_TIME("会话超时");

	private String value;

	private ErrorCode(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
