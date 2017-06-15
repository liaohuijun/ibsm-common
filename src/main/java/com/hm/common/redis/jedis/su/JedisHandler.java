package com.hm.common.redis.jedis.su;

import redis.clients.jedis.Jedis;

/**
 * @author shishun.wang
 * @date 下午2:07:54 2017年6月15日
 * @version 1.0
 * @describe 
 */
public interface JedisHandler {

	public Object execute(Jedis jedis);
}
