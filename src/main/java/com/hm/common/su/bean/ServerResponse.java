package com.hm.common.su.bean;

import java.io.Serializable;

/**
 * @author shishun.wang
 * @date 2016年12月12日 上午11:28:01
 * @version 1.0
 * @describe 服务器端返还数据格式body体定义
 */
public class ServerResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String OK = "ok";

	private static final String ERROR = "error";

	private T data;

	private MetaType metaType;

	public ServerResponse<T> success() {
		this.metaType = new MetaType(true, OK);
		return this;
	}

	public ServerResponse<T> success(T data) {
		this.metaType = new MetaType(true, OK);
		this.data = data;
		return this;
	}

	public ServerResponse<T> failure() {
		this.metaType = new MetaType(false, ERROR);
		return this;
	}

	public ServerResponse<T> failure(String message) {
		this.metaType = new MetaType(false, message);
		this.data = null;
		return this;
	}

	public T getData() {
		return data;
	}

	public MetaType getMetaType() {
		return metaType;
	}

	/**
	 * @author shishun.wang
	 * @date 2016年12月12日 上午11:32:55
	 * @version 1.0
	 * @describe 消息状态
	 */
	public static class MetaType implements Serializable {

		private static final long serialVersionUID = 5227261674159084436L;

		private boolean success;

		private String message;

		public MetaType() {
		}

		public MetaType(boolean success, String message) {
			this.success = success;
			this.message = message;
		}

		public boolean isSuccess() {
			return success;
		}

		public String getMessage() {
			return message;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	public static String getOk() {
		return OK;
	}

	public static String getError() {
		return ERROR;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setMetaType(MetaType metaType) {
		this.metaType = metaType;
	}
}
