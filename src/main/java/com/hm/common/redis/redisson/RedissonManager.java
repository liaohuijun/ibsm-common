package com.hm.common.redis.redisson;

import org.redisson.api.RedissonClient;

/**
 * @author shishun.wang
 * @date 2017年2月24日 下午12:05:10
 * @version 1.0
 * @describe
 */
public class RedissonManager {

	public RedissonClient redissonClient;

	private String nextIdKey;

	public RedissonManager(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	public RedissonManager id(String idKey) {
		this.nextIdKey = idKey;
		return this;
	}

	public long nextId() {
		return redissonClient.getAtomicLong(nextIdKey).incrementAndGet();
	}
	

}
