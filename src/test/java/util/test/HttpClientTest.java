package util.test;

import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.TypeReference;
import com.hm.common.ServerResponse;
import com.hm.common.model.PageInfo;
import com.hm.common.network.httpclient.HttpClientFactory;
import com.hm.common.network.httpclient.HttpClientResponseParse;
import com.hm.common.su.ServerResponseParse;

import util.test.bean.OrderDetailsVo;

/**
 * @author shishun.wang
 * @date 2016年12月18日 上午11:53:28
 * @version 1.0
 * @describe
 */
public class HttpClientTest {

	private String uri = "http://localhost:10000/";

	@Test
	public void testGet() {
		try {
			HttpResponse response = HttpClientFactory.GET.build(uri + "restful/api/offer/query/{0}/{1}", 1, 10)
					.execute();
			ServerResponse<PageInfo<OrderDetailsVo>> parse = HttpClientResponseParse.parseGeneric(response,
					new TypeReference<ServerResponse<PageInfo<OrderDetailsVo>>>() {
					});
			PageInfo<OrderDetailsVo> pageInfo = ServerResponseParse.parse(parse);
			Assert.assertNotEquals(pageInfo.getContent().size(), 0);
			System.out.println(pageInfo.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPost() {

	}

	@Test
	public void testPut() {

	}

	@Test
	public void testDelete() {

	}
}