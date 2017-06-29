package com.hm.common.exception;

/**
 * @author shishun.wang
 * @date 下午6:23:02 2016年7月21日
 * @version 1.0
 * @describe
 */
public enum ErrorCode {

	/** 网络繁忙,请稍后重试 */
	NETWORK_ERROR("NETWORK_ERROR", "网络繁忙,请稍后重试"),

	/** 系统错误,请联系管理员 */
	SYSTEM_ERROR("SYSTEM_ERROR", "系统错误,请联系管理员"),

	/** {0}数据没找到 */
	DATA_NOT_FOUND("DATA_NOT_FOUND", "{0}数据没找到"),

	/** 参数错误,{0} */
	DATA_NOT_NULL("DATA_NOT_NULL", "参数错误,{0}"),

	/** 参数缺失,{0}不允许为空 */
	PARAMETERS_MISSING("PARAMETERS_MISSING", "参数缺失,{0}不允许为空"),

	/** 必填参数缺失 */
	REQUIRED_PARAMETERS_MISSING("REQUIRED_PARAMETERS_MISSING", "必填参数缺失"),

	/** 参数非法 */
	ILLEGAL_PARAMETERS("ILLEGAL_PARAMETERS", "参数非法"),

	/** 数据已存在 */
	DATA_EXISTING("DATA_EXISTING", "数据已存在"),

	/** 无权访问 */
	NO_DATA_ACCESS("NO_DATA_ACCESS", "无数据访问权限"),

	/** 客户端请求频繁,请稍后重试 */
	CLIENT_REQUEST_VISIT_FREQUENTLY("CLIENT_REQUEST_VISIT_FREQUENTLY", "请求频繁,请稍后重试"),

	/** 会话超时 */
	SESSION_TIME_OUT("SESSION_TIME_OUT", "会话超时");

	private String status;

	private String describe;

	private ErrorCode(String status, String describe) {
		this.status = status;
		this.describe = describe;
	}

	public String status() {
		return this.status;
	}

	public String describe() {
		return this.describe;
	}
}
