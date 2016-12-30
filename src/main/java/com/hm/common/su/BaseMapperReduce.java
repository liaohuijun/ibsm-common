package com.hm.common.su;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author shishun.wang
 * @date 上午10:18:46 2016年12月30日
 * @version 1.0
 * @param <T>
 * @describe
 */
public abstract class BaseMapperReduce<T> {

	protected static int POOL_SIZE = 2;

	protected int futureExecTimeOut = 100;

	private ExecutorService newFixedThreadPool = null;

	public final BaseMapperReduce<T> build(int poolSize, int futureExecTimeOut) {
		newFixedThreadPool = Executors.newFixedThreadPool(poolSize);
		this.futureExecTimeOut = futureExecTimeOut;
		return this;
	}

	public abstract Object execute(List<T> entitys);

	public final List<Future<T>> mapper(List<T> entitys, Mapper executorFuture) {
		if (null == newFixedThreadPool) {
			this.build(POOL_SIZE, futureExecTimeOut);
		}

		final List<Future<T>> futures = new ArrayList<Future<T>>();
		for (T entity : entitys) {
			futures.add(newFixedThreadPool.submit(new Callable<T>() {

				@Override
				public T call() throws Exception {
					return executorFuture.execute(entity);
				}
			}));
		}

		return futures;
	}

	public final List<T> reduce(List<Future<T>> futures)
			throws InterruptedException, ExecutionException, TimeoutException {
		List<T> results = new ArrayList<T>();
		for (Future<T> future : futures) {
			results.add(future.get(futureExecTimeOut, TimeUnit.SECONDS));
		}
		return results;
	}

	public final void release() {
		newFixedThreadPool.shutdown();
	}
}
