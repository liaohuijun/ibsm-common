package com.hm.common.redis.jedis.su;

import com.hm.common.redis.jedis.JedisFactory;

/**
 * @author shishun.wang
 * @date 上午10:46:46 2017年8月8日
 * @version 1.0
 * @describe 
 */
public class JedisHandlerSupport {
	
	private JedisFactory jedisFactory;
	
	public JedisHandlerSupport(JedisFactory jedisFactory){
		this.jedisFactory = jedisFactory;
	}

	public  Object executeCommand(JedisHandler handler){
		return handler.handler(this.jedisFactory);
	}
}
