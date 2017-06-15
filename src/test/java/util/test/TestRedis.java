package util.test;

import org.junit.Test;

import com.hm.common.redis.jedis.JedisFactory;

import redis.clients.jedis.Jedis;

/**
 * @author shishun.wang
 * @date 2017年2月24日 下午2:18:30
 * @version 1.0
 * @describe
 */
public class TestRedis {

	@Test
	public void testId() {

		Jedis jedis = new JedisFactory().buildSingle("localhost");
		System.out.println(jedis.set("张三","lisi"));
		System.out.println(jedis.get("张三"));

		// RedissonClient redissonClient = new
		// RedissonFactory().buildSingle("localhost:6379");
		// for (int i = 0; i < 10; i++) {
		// new Thread() {
		// public void run() {
		// try {
		// for (int j = 0; j < 3000; j++) {
		// System.out.println(Thread.currentThread().getName() + ";" + new
		// RedissonManager(redissonClient).id("aabbc").nextId());
		// }
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// };
		// }.start();
		// new Thread() {
		// public void run() {
		// try {
		// for (int j = 0; j < 3000; j++) {
		// System.out.println(Thread.currentThread().getName() + ";" + new
		// RedissonManager(redissonClient).id("aabbc").nextId());
		// }
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// };
		// }.start();
		// }

	}
}
