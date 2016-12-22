package com.hm.common.oltu.bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.hm.common.def.OltuResponseType;

/**
 * @author shishun.wang
 * @date 2016年12月22日 下午4:24:54
 * @version 1.0
 * @describe
 */
public class UserAuthorize implements Serializable {

	private static final long serialVersionUID = -2240121037577165240L;

	@JSONField(name = "username")
	private String userName;

	private String password;

	@JSONField(name = "client_id")
	private String clientId;

	@JSONField(name = "response_type")
	private OltuResponseType responseType;

	@JSONField(name = "redirect_uri")
	private String redirectUri;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public OltuResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(OltuResponseType responseType) {
		this.responseType = responseType;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserAuthorize [userName=" + userName + ", password=" + password + ", clientId=" + clientId
				+ ", responseType=" + responseType + ", redirectUri=" + redirectUri + "]";
	}

}
