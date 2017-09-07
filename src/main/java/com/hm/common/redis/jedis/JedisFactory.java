package com.hm.common.redis.jedis;

import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author shishun.wang
 * @date 上午11:55:41 2017年6月15日
 * @version 1.0
 * @describe
 */
public class JedisFactory {

	protected static ReentrantLock lockPool = new ReentrantLock();

	protected static ReentrantLock lockJedis = new ReentrantLock();

	private Logger logger = LoggerFactory.getLogger(JedisFactory.class);

	private JedisPool jedisPool;

	private JedisPoolConfig config;
	private String address;
	private int port;
	private String password;
	private int timeout;

	public JedisFactory build(JedisPoolConfig config, String address, int port, String password, int timeout) {
		this.config = config;
		this.address = address;
		this.port = port;
		this.password = password;
		this.timeout = timeout;

		// this.poolInit();
		return this;
	}

	private void initialPool() {
		jedisPool = new JedisPool(config, address, port, timeout, password);
	}

	/**
	 * 在多线程环境同步初始化
	 */
	private synchronized void poolInit() {
		if (jedisPool == null) {
			initialPool();
		}
	}

	/**
	 * 同步获取Jedis实例
	 * 
	 * @return Jedis
	 */
	public synchronized Jedis getJedis() {
		if (jedisPool == null) {
			poolInit();
		}
		Jedis jedis = null;
		try {
			if (jedisPool != null) {
				jedis = jedisPool.getResource();
			}
		} catch (Exception e) {
			logger.error("Get jedis error : " + e);
		} finally {
			returnResource(jedis);
		}
		return jedis;
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public void returnResource(final Jedis jedis) {
		if (jedis != null && jedisPool != null) {
			jedisPool.returnResource(jedis);
		}
	}

	public synchronized void set(byte[] key, byte[] value) {
		try {
			getJedis().set(key, value);
		} catch (Exception e) {
			logger.error("Set key error : " + e);
		}
	}

	public synchronized void set(String key, String value) {
		try {
			getJedis().set(key, value);
		} catch (Exception e) {
			logger.error("Set key error : " + e);
		}
	}

	/**
	 * 设置 过期时间
	 * 
	 * @param key
	 * @param seconds
	 * @param value
	 */
	public synchronized void set(String key, int seconds, String value) {
		try {
			getJedis().setex(key.getBytes(), seconds, value.getBytes());
		} catch (Exception e) {
			logger.error("Set key error : " + e);
		}
	}

	/**
	 * 设置 过期时间
	 * 
	 * @param key
	 * @param seconds
	 *            以秒为单位
	 * @param value
	 */
	public synchronized void set(byte[] key, int seconds, byte[] value) {
		try {
			getJedis().setex(key, seconds, value);
		} catch (Exception e) {
			logger.error("Set keyex error : " + e);
		}
	}

	public synchronized String get(String key) {
		if (getJedis() == null || !getJedis().exists(key)) {
			return null;
		}
		return getJedis().get(key);
	}

	public synchronized byte[] get(byte[] key) {
		if (getJedis() == null || !getJedis().exists(key)) {
			return null;
		}
		return getJedis().get(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public synchronized Long incr(String key) {
		Long sequence = getJedis().incr(key);
		return sequence;
	}

	/**
	 * @param key
	 * @param seconds
	 *            以秒为单位
	 * @return
	 */
	public synchronized Long incr(String key, int seconds) {
		Jedis jedis = getJedis();
		Long sequence = jedis.incr(key);
		jedis.expire(key, seconds);
		return sequence;
	}
}
