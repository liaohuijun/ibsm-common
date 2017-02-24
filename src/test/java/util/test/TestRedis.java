package util.test;

import org.junit.Test;
import org.redisson.api.RedissonClient;

import com.hm.common.redis.RedissonFactory;
import com.hm.common.redis.RedissonManager;

/**
 * @author shishun.wang
 * @date 2017年2月24日 下午2:18:30
 * @version 1.0
 * @describe
 */
public class TestRedis {

	@Test
	public void testId() {
		RedissonClient redissonClient = new RedissonFactory().buildSingle("localhost:6379");
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					try {
						for (int j = 0; j < 3000; j++) {
							System.out.println(Thread.currentThread().getName() + ";" + new RedissonManager(redissonClient).id("aabbc").nextId());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			}.start();
			new Thread() {
				public void run() {
					try {
						for (int j = 0; j < 3000; j++) {
							System.out.println(Thread.currentThread().getName() + ";" + new RedissonManager(redissonClient).id("aabbc").nextId());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			}.start();
		}

	}
}
