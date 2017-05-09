package util.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hm.common.pool.thread.ThreadPoolFactory;

/**
 * @author shishun.wang
 * @date 2017年5月9日 上午10:16:14
 * @version 1.0
 * @describe
 */
public class ThreadPoolTest {

	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			test(i);
		}
	}

	private static void test(int i) throws Exception {
		ThreadPoolFactory.instance().build().execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "执行结果：" + i + ";;当前时间" + System.currentTimeMillis());
				try {
					Thread.sleep(3000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("aasdfghjk");
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
