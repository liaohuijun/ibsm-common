package com.hm.common.redis.jedis;

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

	private int port = 6379;

	private JedisPoolConfig config;

	public JedisFactory poolConfig(JedisPoolConfig config) {
		this.config = config;
		return this;
	}

	@SuppressWarnings("resource")
	private Jedis getJedisInstance(String address, int port, String password) {
		JedisPool pool = new JedisPool(config, address, port);
		Jedis jedis = pool.getResource();
		if (CommonUtil.isNotEmpty(password)) {
			jedis.auth(password);
		}
		return jedis;
	}

	/**
	 * 单服务器模式
	 * 
	 * @param address
	 * @return
	 */
	public Jedis buildSingle(String address, String password) {
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

		return this.getJedisInstance(address, port, password);
	}

	/**
	 * 单服务器模式
	 * 
	 * @param address
	 * @return
	 */
	public Jedis buildSingle(String address, int port, String password) {
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

		return this.getJedisInstance(address, port, password);
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
