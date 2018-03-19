package com.hm.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shishun.wang
 * @date 2018年3月19日 下午5:09:35
 * @version 1.0
 * @describe 客户端请求频率控制
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WebClienRequestFrequency {

	/**
	 * 时间间隔,每隔一秒请求一次数据
	 * 
	 * @return
	 */
	long interval() default 1000;
}
