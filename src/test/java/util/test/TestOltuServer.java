package util.test;

import org.junit.Test;

import com.hm.common.def.OltuResponseType;
import com.hm.common.oltu.OltuFactory;
import com.hm.common.oltu.bean.UserAuthorize;

/**
 * @author shishun.wang
 * @date 2016年12月22日 下午4:03:48
 * @version 1.0
 * @describe
 */
public class TestOltuServer {

	private String uri = "http://localhost:10000";

	private String clientId = "696cd38ec88646d39dfc5a069fc8230c";

//	private String clientSecret = "231ee6bbe2e9490dbaae5f4c43fe1771";

	private String redirectUri = "http://test.admin.com";

	/**
	 * 授权
	 */
	@Test
	public void authorize() throws Exception {
		UserAuthorize authorize = new UserAuthorize();
		{
			authorize.setUserName("admin");
			authorize.setPassword("password");
			authorize.setResponseType(OltuResponseType.code);
			authorize.setClientId(clientId);
			authorize.setRedirectUri(redirectUri);
		}
		String code = OltuFactory.getAuthorizeCode(uri, authorize);
		System.out.println(code);
	}
}
