package com.hm.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shishun.wang
 * @date 下午12:02:03 2017年10月25日
 * @version 1.0
 * @describe
 */
public final class WebUtil {

	private WebUtil() {

	}

	public static String clearHtmlTags(String body) {
		if (StringUtil.isBlankOrNull(body)) {
			return null;
		}

		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(body);
		body = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(body);
		body = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(body);
		body = m_html.replaceAll(""); // 过滤html标签

		return body.trim(); // 返回文本字符串
	}
}
