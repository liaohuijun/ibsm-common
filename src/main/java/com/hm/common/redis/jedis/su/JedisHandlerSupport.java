package com.hm.common.redis.jedis.su;

import redis.clients.jedis.Jedis;

/**
 * @author shishun.wang
 * @date 下午12:01:27 2017年6月15日
 * @version 1.0
 * @describe
 */
public final class JedisHandlerSupport {

	private Jedis jedis;

	public JedisHandlerSupport(Jedis jedis) {
		this.jedis = jedis;
	}

	public Object executeCommand(JedisHandler handler) {
		return handler.execute(this.jedis);
	}
}
