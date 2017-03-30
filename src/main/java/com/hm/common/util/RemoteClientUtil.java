package com.hm.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shishun.wang
 * @date 上午10:53:19 2016年11月22日
 * @version 1.0
 * @describe
 */
public class RemoteClientUtil extends CommonUtil {

	private RemoteClientUtil() {
	}

	public static String getPackHost(HttpServletRequest request) {
		String ip = getHost(request);
		return ("0:0:0:0:0:0:0:1".equals(ip)) ? "127.0.0.1" : ip;
	}

	/**
	 * 获取客户端ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getHost(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取本地ip地址
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getLocalIpAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
}
