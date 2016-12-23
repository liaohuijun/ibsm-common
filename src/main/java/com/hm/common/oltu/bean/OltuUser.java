package com.hm.common.oltu.bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author shishun.wang
 * @date 2016年12月23日 下午5:01:29
 * @version 1.0
 * @describe
 */
public class OltuUser implements Serializable {

	private static final long serialVersionUID = -5033680921913136581L;

	private String id;

	@JSONField(name = "username")
	private String userName;

	private String password;

	private String salt;

	private long createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OltuUser [id=" + id + ", userName=" + userName + ", password=" + password + ", salt=" + salt
				+ ", createTime=" + createTime + "]";
	}

}
