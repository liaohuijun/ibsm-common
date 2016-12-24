package util.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hm.common.def.OltuResponseType;
import com.hm.common.oltu.OltuFactory;
import com.hm.common.oltu.bean.AccessToken;
import com.hm.common.oltu.bean.AccessTokenCallBack;
import com.hm.common.oltu.bean.OltuUser;
import com.hm.common.oltu.bean.UserAuthorize;

/**
 * @author shishun.wang
 * @date 2016年12月22日 下午4:03:48
 * @version 1.0
 * @describe
 */
public class TestOltuServer {

	private String host = "http://localhost:10000";

	private String clientId = "696cd38ec88646d39dfc5a069fc8230c";

	private String clientSecret = "231ee6bbe2e9490dbaae5f4c43fe1771";

	private String redirectUri = "http://test.admin.com";

	private String GRANT_TYPE = "authorization_code";

	/**
	 * 授权
	 */
	// @Test
	public String authorize() throws Exception {
		UserAuthorize authorize = new UserAuthorize();
		{
			authorize.setUserName("admin");
			authorize.setPassword("password");
			authorize.setResponseType(OltuResponseType.code);
			authorize.setClientId(clientId);
			authorize.setRedirectUri(redirectUri);
		}
		return OltuFactory.getAuthorizeCode(host, authorize);
	}

	/**
	 * 获取授权token
	 * 
	 * @throws Exception
	 */
	@Test
	public void accessToken() throws Exception {
		String code = authorize();
		System.out.println(code);

		AccessToken accessToken = new AccessToken();
		{
			accessToken.setClientId(clientId);
			accessToken.setClientSecret(clientSecret);
			accessToken.setCode(code);
			accessToken.setGrantType(GRANT_TYPE);
			accessToken.setRedirectUri(redirectUri);
			accessToken.setResponseType(OltuResponseType.code);
		}
		AccessTokenCallBack token = OltuFactory.getAccessToken(host, accessToken);
		System.out.println(token);

		OltuUser oltuUser = OltuFactory.getOltuUserByAccessToken(host, token.getAccessToken());
		System.out.println(oltuUser);
	}

	@Test
	public void registerOltuUser() throws Exception {
		OltuUser oltuUser = new OltuUser();
		{
			oltuUser.setUserName("Tom");
			oltuUser.setPassword("11111");
			oltuUser.setSalt("1234");
			Map<String, Object> extension = new HashMap<String, Object>();
			{
				extension.put("parent", "TOM0");
				extension.put("age", 15);
			}
			oltuUser.setExtension(extension);
		}
		System.out.println(OltuFactory.registerOltuUser(host, oltuUser));
	}

}
