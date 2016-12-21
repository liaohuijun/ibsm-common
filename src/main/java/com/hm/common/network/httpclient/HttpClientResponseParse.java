package com.hm.common.network.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * @author shishun.wang
 * @date 2016年12月19日 上午10:06:33
 * @version 1.0
 * @describe
 */
public class HttpClientResponseParse {

	public static <T> T parse(HttpResponse response, Class<T> type) throws Exception {
		return JSON.parseObject(parseContent(response), type);
	}

	public static <T> T parseGeneric(HttpResponse response, TypeReference<T> type) throws Exception {
		return JSON.parseObject(parseContent(response), type);
	}

	public static String parse(HttpResponse response) throws Exception {
		return parseContent(response);
	}

	private static String parseContent(HttpResponse response) throws Exception {
		HttpEntity entity = response.getEntity();
		if (response.getStatusLine().getStatusCode() == HttpClientStatus.ResponseCode.OK) {
			return EntityUtils.toString(entity, HttpClientStatus.CHARACTER_ENCODING);
		} else {
			throw new Exception(response.getStatusLine().getStatusCode() + ":"
					+ EntityUtils.toString(entity, HttpClientStatus.CHARACTER_ENCODING));
		}
	}
}
