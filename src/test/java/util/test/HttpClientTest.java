package util.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm.common.network.httpclient.HttpClientFactory;
import com.hm.common.network.httpclient.HttpClientStatus;

/**
 * @author shishun.wang
 * @date 2016年12月18日 上午11:53:28
 * @version 1.0
 * @describe
 */
public class HttpClientTest {

	private static Logger logger = LoggerFactory.getLogger(HttpClientTest.class);

	private String uri = "http://localhost:8899/";

	@Test
	public void test() throws Exception {
		uri = "http://lab.ocrking.com/do.html?url=http://simg.11467.com/phone/3133393830353038303138.jpg&service=OcrKingForPhoneNumber&language=eng&charset=11&outputFormat=&email=";
//		Map<String, String> param = new HashMap<String, String>();
//		{
//			param.put("url", "http://simg.11467.com/phone/3133393830353038303138.jpg");
//			param.put("service", "OcrKingForPhoneNumber");
//			param.put("language", "eng");
//			param.put("charset", "11");
//			param.put("outputFormat", "");
//			param.put("email", "");
//		}
		HttpResponse response = HttpClientFactory.POST.build(uri).execute();
		System.out.println(EntityUtils.toString(response.getEntity(), HttpClientStatus.CHARACTER_ENCODING));
	}

	// @Test
	public void testGet() throws Exception {

		HttpResponse response = HttpClientFactory.GET
				.build("https://jingyan.baidu.com/article/e52e3615a2b18f40c60c51d1.html").execute();
		System.out.println(EntityUtils.toString(response.getEntity(), HttpClientStatus.CHARACTER_ENCODING));

		// HttpResponse response = HttpClientFactory.GET.build(uri +
		// "restful/api/offer/query/{0}/{1}", 1, 10)
		// .headers(HttpClientStatus.Headers.CLIENT_USER_ROLE,
		// "SUPER").headers(HttpClientStatus.Headers.CLIENT_USER_SESSION_ID,
		// "12345").execute();
		// ServerResponse<PageInfo<OrderDetailsVo>> parse =
		// HttpClientResponseParse.parseGeneric(response,
		// new TypeReference<ServerResponse<PageInfo<OrderDetailsVo>>>() {
		// });
		// PageInfo<OrderDetailsVo> pageInfo = ServerResponseParse.parse(parse);
		// Assert.assertNotEquals(pageInfo.getContent().size(), 0);
		// System.out.println(pageInfo.getContent());
	}

	// @Test
	public void testPost() throws Exception {
		// OfferUserVo offerUserVo = new OfferUserVo();
		// {
		// offerUserVo.setCarLicenseNo("setCarLicenseNo");
		// offerUserVo.setEngineNo("setEngineNo");
		// offerUserVo.setIsNew("YES");
		// offerUserVo.setGender("gender");
		// offerUserVo.setIdcardNo("idcardNo");
		// }
		//
		// HttpResponse response = HttpClientFactory.POST.build(uri +
		// "restful/api/offer/user/{0}",
		// "54018").parameters(offerUserVo).execute();
		// ServerResponse<Boolean> parse =
		// HttpClientResponseParse.parseGeneric(response, new
		// TypeReference<ServerResponse<Boolean>>() {
		// });
		// System.out.println(ServerResponseParse.parse(parse));
	}

	// @Test
	public void testPut() throws Exception {
		// OfferUserVo offerUserVo = new OfferUserVo();
		// {
		// offerUserVo.setCarLicenseNo("setCarLicenseNo");
		// offerUserVo.setEngineNo("setEngineNo");
		// offerUserVo.setIsNew("NO");
		// offerUserVo.setGender("gender");
		// offerUserVo.setIdcardNo("idcardNo");
		// }
		//
		// HttpResponse response = HttpClientFactory.PUT.build(uri +
		// "restful/api/offer/user/{0}",
		// "54018").parameters(offerUserVo).execute();
		// ServerResponse<Boolean> parse =
		// HttpClientResponseParse.parseGeneric(response, new
		// TypeReference<ServerResponse<Boolean>>() {
		// });
		// System.out.println(ServerResponseParse.parse(parse));
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

	@Test
	public void testHttpClient() throws Exception {
		HttpClientFactory build = HttpClientFactory.GET.build("http://api.chbtc.com/data/v1/ticker?currency=",
				"eth_cny");
		HttpResponse response = build.execute();

		System.out.println(EntityUtils.toString(response.getEntity(), HttpClientStatus.CHARACTER_ENCODING));
	}

	@Test
	public void testHttpClient11() throws Exception {
		HttpClientFactory build = HttpClientFactory.GET.build("http://api.chbtc.com/data/v1/kline?currency=",
				"eth_cny");
		HttpResponse response = build.execute();

		System.out.println(EntityUtils.toString(response.getEntity(), HttpClientStatus.CHARACTER_ENCODING));
	}

	@Test
	public void test11() {
		for (int i = 0; i < 5; i++) {
			if (i == 3)
				continue;
			System.out.println(i);
		}
	}

	public static class GetReplyVo {

		private int code;

		private String msg;

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		@Override
		public String toString() {
			return "GetReplyVo [code=" + code + ", msg=" + msg + "]";
		}

	}
}
