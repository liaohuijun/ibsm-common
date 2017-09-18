package util.test;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.hm.common.network.httpclient.HttpClientFactory;
import com.hm.common.network.httpclient.HttpClientStatus;

/**
 * @author shishun.wang
 * @date 上午10:44:38 2017年9月11日
 * @version 1.0
 * @describe
 */
public class CheckUnionNumber {

	public static void main(String[] args) throws Exception {

		String uri = "http://jisuyhkgsd.market.alicloudapi.com/bankcard/query?bankcard=6212261202011584349";
		String auth = "APPCODE 5357888eb27c4616a1c873da9ba7bf26";
		// Map<String, String> querys = new HashMap<String, String>();
		// querys.put("bankcard", "6212261202011584349");

		HttpResponse response = HttpClientFactory.GET.build(uri).headers("Authorization", auth).execute();
		System.out.println(response);
		System.out.println(EntityUtils.toString(response.getEntity(), HttpClientStatus.CHARACTER_ENCODING));
	}
}
