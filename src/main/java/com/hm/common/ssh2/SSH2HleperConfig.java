package com.hm.common.ssh2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpProgressMonitor;

/**
 * @author shishun.wang
 * @date 上午11:52:55 2017年9月26日
 * @version 1.0
 * @describe
 */
public class SSH2HleperConfig {

	private static Logger log = LoggerFactory.getLogger(SSH2HleperConfig.class);

	private String host;

	private int port;

	private String username;

	private String password;

	private Session session;

	/**
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 */
	public SSH2HleperConfig(String host, int port, String username, String password) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public void connection() {
		try {
			JSch jSch = new JSch();

			session = jSch.getSession(username, host, port);
			session.setPassword(password);
			Properties conf = new Properties();
			conf.put("StrictHostKeyChecking", "no");
			session.setConfig(conf);
			session.connect();
		} catch (Exception e) {
			log.error("远程ssh2连接失败", e);
		}
	}

	public void close() {
		if (null != session && session.isConnected()) {
			session.disconnect();
		}
	}

	public void execCmd(String command) {
		try {
			ChannelExec channel = (ChannelExec) session.openChannel("exec");
			channel.setInputStream(null);
			channel.setCommand(command);
			channel.setErrStream(System.err);
			channel.connect();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(channel.getInputStream(), Charset.forName("UTF-8")));
			String line = null;
			while (null != (line = reader.readLine())) {
				log.info(line);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			this.close();
		}
	}

	public void uploadFile(File localFile, String remoteFile) {
		FileInputStream fileInputStream = null;
		try {
			ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect(5000);// 配置连接超时时间
			fileInputStream = new FileInputStream(localFile);
			channel.setInputStream(fileInputStream);
			channel.put(fileInputStream, remoteFile, new SftpProgressMonitor() {

				private long transfered;

				@Override
				public void init(int op, String src, String dest, long max) {
					System.out.println("Transferring begin.");
				}

				@Override
				public void end() {
					log.info("Transferring done.");
				}

				@Override
				public boolean count(long count) {
					transfered = transfered + count;
					log.info("Currently transferred total size: " + transfered + " bytes");
					return true;
				}
			});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			this.close();
		}
	}

	public void downloadFile(File localFile, String remoteFile) {
		FileOutputStream fileOutputStream = null;
		try {
			ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect(5000);// 配置连接超时时间
			fileOutputStream = new FileOutputStream(localFile);
			channel.get(remoteFile, fileOutputStream, new SftpProgressMonitor() {

				private long transfered;

				@Override
				public void init(int op, String src, String dest, long max) {
					System.out.println("Transferring begin.");
				}

				@Override
				public void end() {
					log.info("Transferring done.");
				}

				@Override
				public boolean count(long count) {
					transfered = transfered + count;
					log.info("Currently transferred total size: " + transfered + " bytes");
					return true;
				}
			});
			fileOutputStream.flush();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (null != fileOutputStream) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			this.close();
		}
	}

}
