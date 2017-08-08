package util.test;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author shishun.wang
 * @date 2017年2月24日 下午2:18:30
 * @version 1.0
 * @describe
 */
public class TestRedis {
	
	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(10); // 可用连接实例的最大数目,如果赋值为-1,表示不限制.
		config.setMaxIdle(5); // 控制一个Pool最多有多少个状态为idle(空闲的)jedis实例,默认值也是8
		config.setMaxWaitMillis(1000 * 100); // 等待可用连接的最大时间,单位毫秒,默认值为-1,表示永不超时/如果超过等待时间,则直接抛出异常
		config.setTestOnBorrow(false); // 在borrow一个jedis实例时,是否提前进行validate操作,如果为true,则得到的jedis实例均是可用的
//		Jedis jedis = new JedisFactory().poolConfig(config).buildSingle("115.28.66.183", "123456");d
//		System.out.println(jedis.set("张三", "lisi"));
//		System.out.println(jedis.get("张三"));

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
