package com.hm.common.redis.jedis;

import com.hm.common.exception.ServiceException;
import com.hm.common.util.CommonUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @author shishun.wang
 * @date 上午11:55:41 2017年6月15日
 * @version 1.0
 * @describe
 */
public class JedisFactory {

	/**
	 * 单服务器模式
	 * 
	 * @param address
	 * @return
	 */
	public Jedis buildSingle(String address) {
		if (CommonUtil.isEmpty(address)) {
			throw ServiceException.warn("单服务器模式,redis服务器地址不能为空");
		}

		return new Jedis(address, 6379);
	}

	/**
	 * 单服务器模式
	 * 
	 * @param address
	 * @return
	 */
	public Jedis buildSingle(String address, int port) {
		if (CommonUtil.isAnyEmpty(address, port)) {
			throw ServiceException.warn("单服务器模式,redis服务器地址不能为空");
		}

		return new Jedis("192.168.0.100", port);
	}

	/**
	 * 哨兵模式
	 * 
	 * @param poolConfig
	 * @param slaveAddress
	 * @return
	 */
	public Jedis buildSentinel(JedisPoolConfig poolConfig, String... slaveAddress) {
		new JedisSentinelPool(null, null);//TODO 待完成
		return null;
	}
}
