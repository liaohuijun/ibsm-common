package com.hm.common.exception;

import java.text.MessageFormat;

/**
 * @author shishun.wang
 * @date 2016年5月12日 下午9:49:55
 * @version 1.0
 * @describe
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;

	private String message;

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	private ServiceException() {
		super();
	}

	private ServiceException(String message) {
		super(message);
		this.message = message;
	}

	private ServiceException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	private ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	private ServiceException(Throwable cause) {
		super(cause);
	}

	public static ServiceException warn(String message) {
		return new ServiceException(message);
	}

	public static ServiceException warn(String code, String message) {
		return new ServiceException(code, message);
	}

	public static ServiceException warning(ErrorCode errorCode, Object... params) {
		return new ServiceException(errorCode.status(), MessageFormat.format(errorCode.describe(), params));
	}

	public static ServiceException warning(ErrorCode errorCode) {
		return new ServiceException(errorCode.status(), errorCode.describe());
	}

}
