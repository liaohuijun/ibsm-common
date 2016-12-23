package com.hm.common.oltu;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.hm.common.def.OltuResponseType;
import com.hm.common.network.httpclient.HttpClientFactory;
import com.hm.common.oltu.bean.AccessToken;
import com.hm.common.oltu.bean.UserAuthorize;
import com.hm.common.util.UriUtil;

/**
 * @author shishun.wang
 * @date 2016年12月22日 下午6:15:59
 * @version 1.0
 * @describe
 */
public class OltuFactory {

	private OltuFactory() {
	}

	/**
	 * @param host
	 *            授权服务器host地址
	 * @param authorize
	 * @return
	 */
	public static String getAuthorizeCode(String host, UserAuthorize authorize) throws Exception {
		HttpResponse response = HttpClientFactory.POST
				.build(host + "/authorize?client_id={0}&response_type={1}&username={2}&password={3}&redirect_uri={4}",
						authorize.getClientId(), authorize.getResponseType(), authorize.getUserName(),
						authorize.getPassword(), authorize.getRedirectUri())
				.execute();

		Header header = response.getFirstHeader("Location");
		return UriUtil.parameters(header.getValue()).get(OltuResponseType.code.toString());
	}

	public static String getAccessToken(String host, AccessToken token) throws Exception {
		HttpResponse response = HttpClientFactory.POST.build(
				host + "/accessToken?client_id={0}&client_secret={1}&grant_type={2}&code={3}&redirect_uri={4}&response_type={5}",
				token.getClientId(), token.getClientSecret(), token.getGrantType(), token.getCode(),
				token.getRedirectUri(), token.getResponseType()).execute();

//		System.out.println(HttpClientResponseParse.parse(response));
		System.out.println(response);

		return null;
	}
}
