package com.hm.general;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.TypeReference;
import com.hm.common.network.httpclient.HttpClientFactory;
import com.hm.common.network.httpclient.HttpClientResponseParse;
import com.hm.common.network.httpclient.HttpClientStatus;
import com.hm.common.su.ServerResponseParse;
import com.hm.common.su.bean.PageInfo;
import com.hm.common.su.bean.ServerResponse;

/**
 * @author shishun.wang
 * @date 下午12:00:11 2017年8月16日
 * @version 1.0
 * @describe
 */
public class Test {

	public static void main(String[] args) throws Exception {

		HttpResponse response = HttpClientFactory.GET
				.build("http://10.28.16.172:8080/restful/v1.0/export/1/11?currentUserId=1&dataSource=erp").execute();
		// System.out.println(EntityUtils.toString(response.getEntity(),
		// HttpClientStatus.CHARACTER_ENCODING));

		ServerResponse<PageInfo<ExportFileRecordBean>> parse = HttpClientResponseParse.parseGeneric(response,
				new TypeReference<ServerResponse<PageInfo<ExportFileRecordBean>>>() {
				});
		System.out.println(parse.getData());
	}
}
