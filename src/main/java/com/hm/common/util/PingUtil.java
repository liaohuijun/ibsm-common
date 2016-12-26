package com.hm.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shishun.wang
 * @date 2016年12月26日 上午10:35:45
 * @version 1.0
 * @describe
 */
public class PingUtil extends CommonUtil {

	private PingUtil() {
	}

	/**
	 * @param host
	 * @return true：可用；false：不可用
	 * @throws IOException
	 */
	public static boolean ping(String host) throws IOException {
		int timeout = 5000;
		return InetAddress.getByName(host).isReachable(timeout);
	}

	/**
	 * 打印ping 日志
	 * 
	 * @param host
	 * @return
	 * @throws IOException
	 */
	public static String pingPrint(String host) throws IOException {
		StringBuilder builder = new StringBuilder();
		Process pro = Runtime.getRuntime().exec("ping " + host);
		BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream(), "GBK"));
		String line = null;
		while ((line = buf.readLine()) != null) {
			builder.append(line).append("\r\n");
		}
		return builder.toString();
	}

	/**
	 * @param host
	 * @param pingTimes ping 次数
	 * @param timeOut 超时时间（毫秒）
	 * @return true：可用；false：不可用
	 * @throws IOException
	 */
	public static boolean ping(String host, int pingTimes, int timeOut) throws IOException {
		BufferedReader bufferedReader = null;
		String pingCommand = "ping " + host + " -n " + pingTimes + " -w " + timeOut;
		try {
			System.out.println(pingCommand);
			Process process = Runtime.getRuntime().exec(pingCommand);
			if (process == null) {
				return false;
			}
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			int connectedCount = 0;
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				connectedCount += getCheckResult(line);
			}
			return connectedCount == pingTimes;
		} finally {
			if (null != bufferedReader) {
				bufferedReader.close();
			}
		}
	}

	private static int getCheckResult(String line) {
		Matcher matcher = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE).matcher(line);
		while (matcher.find()) {
			return 1;
		}
		return 0;
	}
}
