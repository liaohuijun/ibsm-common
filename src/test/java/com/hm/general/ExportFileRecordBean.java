package com.hm.general;

import java.io.Serializable;

/**
 * @author shishun.wang
 * @date 2017年7月5日 下午5:04:47
 * @version 1.0
 * @describe
 */
public class ExportFileRecordBean implements Serializable {

	private static final long serialVersionUID = -2541174903799311799L;

	private long id;

	private long createTime;

	private long updateTime;

	private long version;

	private String createdBy;

	private String updatedBy;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 下载链接地址
	 */
	private String downUrl;

	/**
	 * 上传文件数据记录状态
	 */

	/**
	 * 文件上传内容描述
	 */
	private String message;

	/**
	 * 数据来源
	 */
	private String dataSource;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String toString() {
		return "ExportFileRecordBean [id=" + id + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", version=" + version + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", title=" + title
				+ ", downUrl=" + downUrl + ", message=" + message + ", dataSource=" + dataSource + "]";
	}

}
