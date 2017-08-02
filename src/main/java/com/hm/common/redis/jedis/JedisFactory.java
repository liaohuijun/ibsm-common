package com.hm.common.redis.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm.common.exception.ServiceException;
import com.hm.common.util.CommonUtil;

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

	private Logger logger = LoggerFactory.getLogger(JedisFactory.class);

	private int port = 6379;

	private JedisPoolConfig config;

	private JedisPool pool;

	public JedisFactory poolConfig(JedisPoolConfig config) {
		this.config = config;
		return this;
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	private void returnResource(final Jedis jedis) {
		if (!CommonUtil.isAnyEmpty(pool, jedis)) {
			pool.returnResource(jedis);
		}
	}

	private synchronized void initPool(String address, int port, String password, int timeout) {
		if (CommonUtil.isNotEmpty(pool)) {
			return;
		}

		pool = new JedisPool(config, address, port, timeout, password);
	}

	private Jedis getJedisInstance(String address, int port, String password, int timeout) {
		Jedis jedis = null;
		try {
			if (CommonUtil.isEmpty(pool)) {
				initPool(address, port, password, timeout);
			}
			jedis = pool.getResource();
		} catch (Exception e) {
			logger.error("Get jedis error : " + e);
		} finally {
			returnResource(jedis);
		}
		return jedis;
	}

	/**
	 * 单服务器模式
	 * 
	 * @param address
	 * @return
	 */
	public Jedis buildSingle(String address, String password, int timeout) {
		if (CommonUtil.isEmpty(address)) {
			throw ServiceException.warn("单服务器模式,redis服务器地址不能为空");
		}

		if (CommonUtil.isEmpty(config)) {
			Jedis jedis = new Jedis(address, port);
			if (CommonUtil.isNotEmpty(password)) {
				jedis.auth(password);
			}
			return jedis;
		}

		return this.getJedisInstance(address, port, password, timeout);
	}

	/**
	 * 单服务器模式
	 * 
	 * @param address
	 * @return
	 */
	public Jedis buildSingle(String address, int port, String password, int timeout) {
		if (CommonUtil.isAnyEmpty(address, port)) {
			throw ServiceException.warn("单服务器模式,redis服务器地址不能为空");
		}
		if (CommonUtil.isEmpty(config)) {
			Jedis jedis = new Jedis(address, port);
			if (CommonUtil.isNotEmpty(password)) {
				jedis.auth(password);
			}

			return jedis;
		}

		return this.getJedisInstance(address, port, password, timeout);
	}

	/**
	 * 哨兵模式
	 * 
	 * @param poolConfig
	 * @param slaveAddress
	 * @return
	 */
	public Jedis buildSentinel(JedisPoolConfig poolConfig, String... slaveAddress) {
		// new JedisSentinelPool(null, null);//TODO 待完成
		return null;
	}
}
