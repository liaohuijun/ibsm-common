package com.hm.common.jpush;

import java.io.Serializable;

/**
 * @author shishun.wang
 * @date 2017年11月30日 下午4:14:33
 * @version 1.0
 * @describe 
 */
public class JpushAuthorization implements Serializable {

	private static final long serialVersionUID = 1L;

	private String appKey;

	private String masterSecret;

	private Boolean prodEnv;

	public JpushAuthorization(String appKey, String masterSecret, Boolean prodEnv) {
		super();
		this.appKey = appKey;
		this.masterSecret = masterSecret;
		this.prodEnv = prodEnv;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}

	public Boolean getProdEnv() {
		return prodEnv;
	}

	public void setProdEnv(Boolean prodEnv) {
		this.prodEnv = prodEnv;
	}

	@Override
	public String toString() {
		return "JpushAuthorization [appKey=" + appKey + ", masterSecret=" + masterSecret + ", prodEnv=" + prodEnv
				+ "]";
	}

}