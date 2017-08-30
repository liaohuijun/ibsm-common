package com.hm.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shishun.wang
 * @date 2017年2月24日 下午5:43:33
 * @version 1.0
 * @describe 客户端请求拦截
 */
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebClientRequestIntercept {

	/**
	 * 请求频率控制
	 * 
	 * @return
	 */
	boolean frequency() default false;

	/**
	 * 客户端请求->服务器对客户端请求身份认证
	 * 
	 * @return
	 */
	boolean requestCertification() default true;

	/**
	 * ip 黑名单,禁止访问
	 * 
	 * @return
	 */
	String[] ipBlacklist() default {};
}
