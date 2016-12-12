package com.hm.common;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author shishun.wang
 * @date 2016年12月12日 上午11:28:01
 * @version 1.0
 * @describe 服务器端返还数据格式body体定义
 */
public class ServerResponse {

	private static final String OK = "ok";

	private static final String ERROR = "error";

	private T data;

	private MetaType metaType;

	public ServerResponse success() {
		this.metaType = new MetaType(true, OK);
		return this;
	}

	public ServerResponse success(T data) {
		this.metaType = new MetaType(true, OK);
		this.data = data;
		return this;
	}

	public ServerResponse failure() {
		this.metaType = new MetaType(false, ERROR);
		return this;
	}

	public ServerResponse failure(String message) {
		this.metaType = new MetaType(false, message);
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
	public class MetaType {

		private boolean success;

		private String message;

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
	}
}
