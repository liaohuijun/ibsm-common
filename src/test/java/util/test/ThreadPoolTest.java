package util.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.hm.common.network.httpclient.HttpClientFactory;
import com.hm.common.network.httpclient.HttpClientStatus;
import com.hm.common.pool.thread.ThreadPoolFactory;

/**
 * @author shishun.wang
 * @date 2017年5月9日 上午10:16:14
 * @version 1.0
 * @describe
 */
public class ThreadPoolTest {

	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 50; i++) {
			test(i);
		}
	}

	private static void test(int i) throws Exception {
		ThreadPoolFactory.instance().build().execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + "执行结果：" + i + ";;当前时间" + System.currentTimeMillis());
//					Thread.sleep(3000l);
					HttpResponse response = HttpClientFactory.GET.build("http://localhost:8080/discover").execute();
					String json = EntityUtils.toString(response.getEntity(), HttpClientStatus.CHARACTER_ENCODING);
					System.out.println(json);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		fixedThreadPool.execute(new Runnable() {
//
//			@Override
//			public void run() {
//				System.out.println(Thread.currentThread().getName() + "执行结果：" + i + ";;当前时间" + System.currentTimeMillis());
//				try {
//					Thread.sleep(3000l);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
}
