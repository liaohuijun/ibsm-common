package com.hm.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author shishun.wang
 * @date 上午10:31:29 2017年6月16日
 * @version 1.0
 * @describe
 */
public class EmailAuthenticator extends Authenticator {

	private String username;

	private String password;

	public EmailAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

}
