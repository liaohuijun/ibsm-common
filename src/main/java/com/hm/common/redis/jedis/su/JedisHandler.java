package com.hm.common.redis.jedis.su;

import com.hm.common.redis.jedis.JedisFactory;

/**
 * @author shishun.wang
 * @date 上午10:47:25 2017年8月8日
 * @version 1.0
 * @describe 
 */
public interface JedisHandler {

	public Object handler(JedisFactory jedis);
}
