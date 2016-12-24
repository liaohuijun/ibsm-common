package com.hm.common.util;

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
}
