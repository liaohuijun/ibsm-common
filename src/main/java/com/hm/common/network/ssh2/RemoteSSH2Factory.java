package com.hm.common.network.ssh2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

/**
 * @author shishun.wang
 * @date 下午2:22:26 2017年8月29日
 * @version 1.0
 * @describe
 */
public class RemoteSSH2Factory {

	private Connection conn;

	private String ipAddr;

	private int port;

	private String charset = Charset.defaultCharset().toString();

	private String userName;

	private String password;

	public RemoteSSH2Factory(String ipAddr, int port, String userName, String password, String charset) {
		this.ipAddr = ipAddr;
		this.port = port;
		this.userName = userName;
		this.password = password;
		if (charset != null) {
			this.charset = charset;
		}
	}

	private boolean login() throws IOException {
		conn = new Connection(ipAddr, port);
		conn.connect(); // 连接
		return conn.authenticateWithPassword(userName, password); // 认证
	}

	public String exec(String cmds) {
		InputStream in = null;
		String result = "";
		try {
			if (this.login()) {
				Session session = conn.openSession(); // 打开一个会话
				session.execCommand(cmds);

				in = session.getStdout();
				result = this.processStdout(in, this.charset);
				session.close();
				conn.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	private String processStdout(InputStream in, String charset) {

		byte[] buf = new byte[1024];
		StringBuffer sb = new StringBuffer();
		try {
			while (in.read(buf) != -1) {
				sb.append(new String(buf, charset));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
