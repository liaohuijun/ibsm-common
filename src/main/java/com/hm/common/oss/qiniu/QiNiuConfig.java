package com.hm.common.oss.qiniu;

import com.hm.common.exception.ServiceException;

/**
 * @author shishun.wang
 * @date 2017年4月17日 上午11:26:49
 * @version 1.0
 * @describe
 */
public class QiNiuConfig {

	private static QiNiuConfig config = null;

	private String accessKey;

	private String secretKey;

	public static QiNiuConfig load() {
		if (null == config) {
			throw ServiceException.warn("七牛云访问授权码未初始化");
		}
		return config;
	}

	public static QiNiuConfig instance() {
		if (null == config) {
			synchronized (QiNiuConfig.class) {
				if (null == config) {
					config = new QiNiuConfig();
				}
			}
		}
		return config;
	}

	public void init(String accessKey, String secretKey) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}
