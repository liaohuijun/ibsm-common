package com.hm.common.oltu.bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.hm.common.def.OltuResponseType;

/**
 * @author shishun.wang
 * @date 2016年12月23日 上午11:37:50
 * @version 1.0
 * @describe
 */
public class AccessToken implements Serializable {

	private static final long serialVersionUID = -7833937371596432913L;

	@JSONField(name = "client_id")
	private String clientId;

	@JSONField(name = "client_secret")
	private String clientSecret;

	@JSONField(name = "grant_type")
	private String grantType;

	private String code;

	@JSONField(name = "redirect_uri")
	private String redirectUri;

	@JSONField(name = "response_type")
	private OltuResponseType responseType;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public OltuResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(OltuResponseType responseType) {
		this.responseType = responseType;
	}

	@Override
	public String toString() {
		return "AccessToken [clientId=" + clientId + ", clientSecret=" + clientSecret + ", grantType=" + grantType
				+ ", code=" + code + ", redirectUri=" + redirectUri + ", responseType=" + responseType + "]";
	}

}
