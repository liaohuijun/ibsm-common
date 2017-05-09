package com.hm.common.pool.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm.common.exception.ErrorCode;
import com.hm.common.exception.ServiceException;
import com.hm.common.util.CommonUtil;

/**
 * @author shishun.wang
 * @date 2017年5月9日 下午2:45:15
 * @version 1.0
 * @describe
 */
public class ThreadFixedPool {

	private static Logger logger = LoggerFactory.getLogger(ThreadFixedPool.class);

	private int defaultPoolSize = 1;

	private ExecutorService executorService;

	private ThreadFixedPool() {
	}

	public ThreadFixedPool build() {
		if (null == executorService) {
			logger.info("加载默认线程池,初始化线程数为:{}", defaultPoolSize);
			executorService = Executors.newFixedThreadPool(defaultPoolSize);
		}
		return this;
	}

	public ThreadFixedPool build(int poolSize) {
		if (null == executorService) {
			if (CommonUtil.isEmpty(poolSize)) {
				throw ServiceException.warning(ErrorCode.PARAMETERS_MISSING, "poolSize");
			}
			if (poolSize < 1 || poolSize >= 1000) {
				throw ServiceException.warn("线程数不合法");
			}
			logger.info("加载自定义线程池,初始化线程数为:{}", poolSize);
			executorService = Executors.newFixedThreadPool(poolSize);
		}
		return this;
	}

	public void execute(Runnable runnable) {
		executorService.execute(runnable);
	}
}
