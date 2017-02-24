package com.hm.common.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import com.hm.common.exception.ServiceException;
import com.hm.common.util.CommonUtil;

/**
 * @author shishun.wang
 * @date 2017年2月24日 上午11:38:13
 * @version 1.0
 * @describe
 */
public class RedissonFactory {

	/**
	 * 主从模式
	 * 
	 * @param masterAddress
	 * @param slaveAddress
	 * @return
	 */
	public RedissonClient buildMasterSlave(String masterAddress, String... slaveAddress) {
		if (CommonUtil.isAnyEmpty(masterAddress, slaveAddress)) {
			throw ServiceException.warn("redis主从模式,主从服务器地址不能为空");
		}
		Config config = new Config();
		{
			config.useMasterSlaveServers().setMasterAddress(masterAddress).addSlaveAddress(slaveAddress);
		}
		return Redisson.create(config);
	}

	/**
	 * 单服务器模式
	 * 
	 * @param address
	 * @return
	 */
	public RedissonClient buildSingle(String address) {
		if (CommonUtil.isEmpty(address)) {
			throw ServiceException.warn("单服务器模式,redis服务器地址不能为空");
		}
		Config config = new Config();
		{
			config.useSingleServer().setAddress(address);
		}

		return Redisson.create(config);
	}

	/**
	 * 哨兵模式
	 * 
	 * @param slaveAddress
	 * @return
	 */
	public RedissonClient buildSentinel(String... slaveAddress) {
		if (CommonUtil.isEmpty(slaveAddress)) {
			throw ServiceException.warn("哨兵模式,redis服务器地址不能为空");
		}
		Config config = new Config();
		{
			config.useSentinelServers().setMasterName("SentinelMaster").addSentinelAddress(slaveAddress);
		}

		return Redisson.create(config);
	}
}
