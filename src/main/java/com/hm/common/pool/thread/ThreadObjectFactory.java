package com.hm.common.pool.thread;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shishun.wang
 * @date 2017年5月8日 下午6:07:48
 * @version 1.0
 * @describe
 */
@SuppressWarnings("rawtypes")
public class ThreadObjectFactory implements PooledObjectFactory {

	private static Logger logger = LoggerFactory.getLogger(ThreadObjectFactory.class);
	
	@Override
	public void activateObject(PooledObject object) throws Exception {
		logger.debug("激活线程池实例{}", object);
	}

	@Override
	public void destroyObject(PooledObject object) throws Exception {
		logger.debug("销毁线程池实例{}", object);
	}

	@Override
	public PooledObject makeObject() throws Exception {
		logger.debug("创建线程池实例");
		return null;
	}

	@Override
	public void passivateObject(PooledObject object) throws Exception {
		logger.debug("挂起线程池实例");
	}

	@Override
	public boolean validateObject(PooledObject object) {
		logger.debug("检查线程池实例有效性{}", object);
		return false;
	}

}
