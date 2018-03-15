package com.hm.common.def;

import com.hm.common.util.CommonUtil;

/**
 * @author shishun.wang
 * @date 下午5:40:05 2017年11月9日
 * @version 1.0
 * @describe 系统支持终端类型
 */
public enum SystemSupportTerminalEnum {

	IOS("IOS", "苹果设备"),

	ANDROID("ANDROID", "安卓设备"),

	ALL("ALL", "所有设备");

	private SystemSupportTerminalEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;

	private String desc;

	public String code() {
		return this.code;
	}

	public String desc() {
		return this.desc;
	}

	public static SystemSupportTerminalEnum trance(String statu) {
		if (CommonUtil.isEmpty(statu)) {
			return null;
		}
		for (SystemSupportTerminalEnum targetEnum : SystemSupportTerminalEnum.values()) {
			if (targetEnum.code.equals(statu)) {
				return targetEnum;
			}
		}
		return null;
	}
}
