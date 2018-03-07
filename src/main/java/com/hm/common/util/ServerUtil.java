package com.hm.common.util;

import java.io.Serializable;
import java.util.Properties;

import lombok.Data;

/**
 * @author shishun.wang
 * @date 2018年3月7日 上午11:18:24
 * @version 1.0
 * @describe
 */
public final class ServerUtil {

	private ServerUtil() {

	}

	/**
	 * 获取系统基本信息
	 * 
	 * @return
	 */
	public static ServerInfoVo baseInfo() {
		Properties props = System.getProperties();
		ServerInfoVo infoVo = new ServerInfoVo();
		{
			infoVo.setJdkVersion(props.getProperty("java.version"));
			infoVo.setDefTmpFilePath(props.getProperty("java.io.tmpdir"));
			infoVo.setOpSystemName(props.getProperty("os.name"));
			infoVo.setOpSystemArchitecture(props.getProperty("os.arch"));
			infoVo.setOpSystemVersion(props.getProperty("os.version"));
			infoVo.setOpSystemAccount(props.getProperty("user.name"));
			infoVo.setCurrWorkPath(props.getProperty("user.dir"));
		}

		return infoVo;
	}

	@Data
	public static final class ServerInfoVo implements Serializable {

		private static final long serialVersionUID = 1L;

		/**
		 * java运行环境版本
		 */
		private String jdkVersion;

		/**
		 * 默认的临时文件路径
		 */
		private String defTmpFilePath;

		/**
		 * 操作系统的名称
		 */
		private String opSystemName;

		/**
		 * 操作系统的构架
		 */
		private String opSystemArchitecture;

		/**
		 * 操作系统的版本
		 */
		private String opSystemVersion;

		/**
		 * 系统用户的账户名称
		 */
		private String opSystemAccount;

		/**
		 * 用户的当前工作目录
		 */
		private String currWorkPath;
	}
}
