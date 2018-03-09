package com.hm.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shishun.wang
 * @date 2018年3月9日 上午10:40:06
 * @version 1.0
 * @describe 日志记录
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WebClientOperationLog {

	/**
	 * 业务所属系统
	 * 
	 * @return
	 */
	String platform() default "IBSM_MANAGER";

	/**
	 * 系统业务模块
	 * 
	 * @return
	 */
	String module() default "OTHER";

	/**
	 * 描述
	 * @return
	 */
	String describe() default "";
}
