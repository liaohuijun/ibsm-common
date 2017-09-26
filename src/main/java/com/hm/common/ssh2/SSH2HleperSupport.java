package com.hm.common.ssh2;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shishun.wang
 * @date 下午2:50:58 2017年9月26日
 * @version 1.0
 * @describe
 */
public class SSH2HleperSupport {

	private static Logger log = LoggerFactory.getLogger(SSH2HleperSupport.class);

	private SSH2HleperConfig ssh2HleperConfig;

	public SSH2HleperSupport(SSH2HleperConfig ssh2HleperConfig) {
		super();
		this.ssh2HleperConfig = ssh2HleperConfig;
	}

	public void execCmd(String command) {
		ssh2HleperConfig.connection();
		log.info("#---执行command---->{}", command);
		ssh2HleperConfig.execCmd(command);
	}

	public void uploadFile(File localFile, String remoteFilePath) {
		ssh2HleperConfig.connection();
		log.info("上传文件:{},到远程服务器:{}", localFile.getName(), remoteFilePath);
		ssh2HleperConfig.uploadFile(localFile, remoteFilePath);
	}

	public void downloadFile(File localFile, String remoteFilePath) {
		ssh2HleperConfig.connection();
		log.info("从远程服务器:{},下载文件:{}", remoteFilePath, localFile.getName());
		ssh2HleperConfig.downloadFile(localFile, remoteFilePath);
	}

}
