package util.test;

import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.TypeReference;
import com.hm.common.network.httpclient.HttpClientFactory;
import com.hm.common.network.httpclient.HttpClientResponseParse;
import com.hm.common.network.httpclient.HttpClientStatus;
import com.hm.common.su.ServerResponseParse;
import com.hm.common.su.bean.PageInfo;
import com.hm.common.su.bean.ServerResponse;

import util.test.bean.OfferUserVo;
import util.test.bean.OrderDetailsVo;

/**
 * @author shishun.wang
 * @date 2016年12月18日 上午11:53:28
 * @version 1.0
 * @describe
 */
public class HttpClientTest {

	private String uri = "http://localhost:8899/";

	@Test
	public void testGet() throws Exception {
		HttpResponse response = HttpClientFactory.GET.build(uri + "restful/api/offer/query/{0}/{1}", 1, 10)
				.headers(HttpClientStatus.Headers.CLIENT_USER_ROLE, "SUPER")
				.headers(HttpClientStatus.Headers.CLIENT_USER_SESSION_ID, "12345").execute();
		ServerResponse<PageInfo<OrderDetailsVo>> parse = HttpClientResponseParse.parseGeneric(response,
				new TypeReference<ServerResponse<PageInfo<OrderDetailsVo>>>() {
				});
		PageInfo<OrderDetailsVo> pageInfo = ServerResponseParse.parse(parse);
		Assert.assertNotEquals(pageInfo.getContent().size(), 0);
		System.out.println(pageInfo.getContent());
	}

	@Test
	public void testPost() throws Exception {
		OfferUserVo offerUserVo = new OfferUserVo();
		{
			offerUserVo.setCarLicenseNo("setCarLicenseNo");
			offerUserVo.setEngineNo("setEngineNo");
			offerUserVo.setIsNew("YES");
			offerUserVo.setGender("gender");
			offerUserVo.setIdcardNo("idcardNo");
		}

		HttpResponse response = HttpClientFactory.POST.build(uri + "restful/api/offer/user/{0}", "54018")
				.parameters(offerUserVo).execute();
		ServerResponse<Boolean> parse = HttpClientResponseParse.parseGeneric(response,
				new TypeReference<ServerResponse<Boolean>>() {
				});
		System.out.println(ServerResponseParse.parse(parse));
	}

	@Test
	public void testPut() throws Exception {
		OfferUserVo offerUserVo = new OfferUserVo();
		{
			offerUserVo.setCarLicenseNo("setCarLicenseNo");
			offerUserVo.setEngineNo("setEngineNo");
			offerUserVo.setIsNew("NO");
			offerUserVo.setGender("gender");
			offerUserVo.setIdcardNo("idcardNo");
		}

		HttpResponse response = HttpClientFactory.PUT.build(uri + "restful/api/offer/user/{0}", "54018")
				.parameters(offerUserVo).execute();
		ServerResponse<Boolean> parse = HttpClientResponseParse.parseGeneric(response,
				new TypeReference<ServerResponse<Boolean>>() {
				});
		System.out.println(ServerResponseParse.parse(parse));
	}

	@Test
	public void testDelete() throws Exception {
		/*
		 * HttpResponse response = HttpClientFactory.DELETE.build(uri +
		 * "restful/api/offer/user/{0}", "54018") .execute();
		 * ServerResponse<Boolean> parse =
		 * HttpClientResponseParse.parseGeneric(response, new
		 * TypeReference<ServerResponse<Boolean>>() { });
		 * System.out.println(ServerResponseParse.parse(parse));
		 */
	}

}
