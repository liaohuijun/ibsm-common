package util.test;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hm.common.network.httpclient.HttpClientFactory;
import com.hm.common.network.httpclient.HttpClientStatus;

/**
 * @author shishun.wang
 * @date 2017年4月17日 下午3:22:22
 * @version 1.0
 * @describe
 */
public class Test {

	private static Logger logger = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) {
		System.out.println(x(142.5, 100, 80));
		System.out.println(pay(205, 100, 80));
		
		try{
			HttpResponse response = HttpClientFactory.GET.build("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx5191fcc3effa8fce&secret=aee6c2f72961d7671da698836365da29").execute();
//			System.out.println(response.getStatusLine().getStatusCode());
//			System.out.println(EntityUtils.toString(response.getEntity(), HttpClientStatus.CHARACTER_ENCODING));
			
			//HttpResponse response = HttpClientFactory.GET.build("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf7d278806fcff847&secret=70e5556e5da79449d1a4a4898fa003a5").execute();
//			HttpResponse response = HttpClientFactory.GET.build("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf7d278806fcff847&secret=70e5556e5da79449d1a4a4898fa003a5").execute();
			String json = EntityUtils.toString(response.getEntity(), HttpClientStatus.CHARACTER_ENCODING);
			System.out.println(json);
			JSONObject obj =JSON.parseObject(json);  
			if(obj.containsKey("access_token")){
				String accessToken = obj.getString("access_token");
				String str = EntityUtils.toString(HttpClientFactory.GET.build("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=jsapi",accessToken).execute().getEntity(), HttpClientStatus.CHARACTER_ENCODING);
				System.out.println(str);
				JSONObject js =JSON.parseObject(str);
				System.out.println(js.getString("ticket"));
			}
			
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	public static double pay(double x, double l, double r) {
		return x + (x / l) * r;
	}

	public static double x(double p, double l, double r) {
		return (p * l) / (l + r);
	}
}
