package com.hm.common.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shishun.wang
 * @date 2016年12月22日 下午5:57:28
 * @version 1.0
 * @describe
 */
public class UriUtil extends CommonUtil {

	public static String parse(String uri) {
		String uris[] = uri.split("[?]");
		return uris.length < 2 ? null : uris[1];
	}

	public String host(String uri) throws Exception {
		return new URI(uri).getHost();
	}

	public static Map<String, String> parameters(String uri) {
		Map<String, String> content = new HashMap<String, String>();
		String parameter = parse(uri);
		if (CommonUtil.isEmpty(parameter))
			return content;
		for (String param : parameter.split("[&]")) {
			String temps[] = param.split("[=]");
			if (temps.length == 0)
				continue;
			content.put(temps[0], temps.length == 2 ? temps[1] : null);
		}
		return content;
	}
}
