package com.hm.common.su;

/**
 * @author shishun.wang
 * @date 上午10:23:39 2016年12月30日
 * @version 1.0
 * @describe
 */
public interface Mapper {

	public <T> T execute(T entity);
}
