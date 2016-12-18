package util.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.hm.common.network.httpclient.HttpClientFactory;

/**
 * @author shishun.wang
 * @date 2016年12月18日 上午11:53:28
 * @version 1.0
 * @describe
 */
public class HttpClientTest {

	@Test
	public void testGet() {
		try {
			HttpResponse response = HttpClientFactory.GET.build("https://www.baidu.com/").execute();
			HttpEntity entity = response.getEntity();
			
			if (response.getStatusLine().getStatusCode() == 200) {
				String json = EntityUtils.toString(entity, "UTF-8");
				System.out.println(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPost(){
		
	}
	
	@Test
	public void testPut(){
		
	}
	
	@Test
	public void testDelete(){
		
	}
}
