package com.hm.common.event.ops;

/**
 * @author shishun.wang
 * @date 2017年2月28日 下午4:20:25
 * @version 1.0
 * @describe
 */
public class OpsTarget {

	private String name;

	private String note;

	private String className;

	/**
	 * json 参数
	 */
	private String ext;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Override
	public String toString() {
		return "OpsTarget [name=" + name + ", note=" + note + ", className=" + className + ", ext=" + ext + "]";
	}

}
