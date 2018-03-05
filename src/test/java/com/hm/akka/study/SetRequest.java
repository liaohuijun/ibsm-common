package com.hm.akka.study;

/**
 * @author shishun.wang
 * @date 2018年3月5日 上午11:39:40
 * @version 1.0
 * @describe
 */
public class SetRequest {

	private final String key;
	
	private final Object value;

	public SetRequest(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}
}
