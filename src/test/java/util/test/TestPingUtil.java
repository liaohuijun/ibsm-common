package util.test;

import java.io.IOException;

import com.hm.common.util.PingUtil;

/**
 * @author shishun.wang
 * @date 2016年12月26日 上午10:40:26
 * @version 1.0
 * @describe
 */
public class TestPingUtil {

	private String host = "10.0.0.10";
	
//	@Test
	public void ping() throws IOException{
		System.out.println(PingUtil.ping(host));
	}
	
//	@Test
	public void pingPrint() throws IOException{
		System.out.println(PingUtil.pingPrint(host));
	}
	
//	@Test
	public void cusPing() throws IOException{
		System.out.println(PingUtil.ping(host, 5, 5000));
	}
}
