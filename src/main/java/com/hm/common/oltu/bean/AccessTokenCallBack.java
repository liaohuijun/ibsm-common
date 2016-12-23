package com.hm.common.oltu.bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author shishun.wang
 * @date 2016年12月23日 下午4:56:37
 * @version 1.0
 * @describe
 */
public class AccessTokenCallBack implements Serializable {

	private static final long serialVersionUID = -8082391973602309473L;

	@JSONField(name = "access_token")
	private String accessToken;

	@JSONField(name = "expires_in")
	private long expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AccessTokenCallBack [accessToken=" + accessToken + ", expiresIn=" + expiresIn + "]";
	}

}
