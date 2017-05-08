package com.hm.common.pool.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shishun.wang
 * @date 2017年5月8日 下午6:17:16
 * @version 1.0
 * @describe
 */
public class ThreadPoolFactory {

	private static Logger logger = LoggerFactory.getLogger(ThreadPoolFactory.class);

	public ThreadPoolFactory build() {
		return this;
	}

	public static class ThreadPoolConfig {

		private int maxTotal = 10;

		private int maxTotalPerKey = 5;

		private boolean blockWhenExhausted = true;

		private int minIdlePerKey = 0;

		private int maxWaitMillis = -1;

		private int numTestsPerEvictionRun = Integer.MAX_VALUE;

		private boolean testOnBorrow = true;

		private boolean testOnReturn = false;

		private boolean testWhileIdle = false;

		private long timeBetweenEvictionRunsMillis = 1 * 60000L;

		private long minEvictableIdleTimeMillis = 10 * 60000L;

		public int getMaxTotal() {
			return maxTotal;
		}

		public void setMaxTotal(int maxTotal) {
			this.maxTotal = maxTotal;
		}

		public int getMaxTotalPerKey() {
			return maxTotalPerKey;
		}

		public void setMaxTotalPerKey(int maxTotalPerKey) {
			this.maxTotalPerKey = maxTotalPerKey;
		}

		public boolean isBlockWhenExhausted() {
			return blockWhenExhausted;
		}

		public void setBlockWhenExhausted(boolean blockWhenExhausted) {
			this.blockWhenExhausted = blockWhenExhausted;
		}

		public int getMinIdlePerKey() {
			return minIdlePerKey;
		}

		public void setMinIdlePerKey(int minIdlePerKey) {
			this.minIdlePerKey = minIdlePerKey;
		}

		public int getMaxWaitMillis() {
			return maxWaitMillis;
		}

		public void setMaxWaitMillis(int maxWaitMillis) {
			this.maxWaitMillis = maxWaitMillis;
		}

		public int getNumTestsPerEvictionRun() {
			return numTestsPerEvictionRun;
		}

		public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
			this.numTestsPerEvictionRun = numTestsPerEvictionRun;
		}

		public boolean isTestOnBorrow() {
			return testOnBorrow;
		}

		public void setTestOnBorrow(boolean testOnBorrow) {
			this.testOnBorrow = testOnBorrow;
		}

		public boolean isTestOnReturn() {
			return testOnReturn;
		}

		public void setTestOnReturn(boolean testOnReturn) {
			this.testOnReturn = testOnReturn;
		}

		public boolean isTestWhileIdle() {
			return testWhileIdle;
		}

		public void setTestWhileIdle(boolean testWhileIdle) {
			this.testWhileIdle = testWhileIdle;
		}

		public long getTimeBetweenEvictionRunsMillis() {
			return timeBetweenEvictionRunsMillis;
		}

		public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
			this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
		}

		public long getMinEvictableIdleTimeMillis() {
			return minEvictableIdleTimeMillis;
		}

		public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
			this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
		}

		@Override
		public String toString() {
			return "ThreadPoolConfig [maxTotal=" + maxTotal + ", maxTotalPerKey=" + maxTotalPerKey + ", blockWhenExhausted=" + blockWhenExhausted
					+ ", minIdlePerKey=" + minIdlePerKey + ", maxWaitMillis=" + maxWaitMillis + ", numTestsPerEvictionRun=" + numTestsPerEvictionRun
					+ ", testOnBorrow=" + testOnBorrow + ", testOnReturn=" + testOnReturn + ", testWhileIdle=" + testWhileIdle
					+ ", timeBetweenEvictionRunsMillis=" + timeBetweenEvictionRunsMillis + ", minEvictableIdleTimeMillis=" + minEvictableIdleTimeMillis
					+ "]";
		}

	}
}
