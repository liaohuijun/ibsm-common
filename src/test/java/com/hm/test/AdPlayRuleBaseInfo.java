package com.hm.test;

import java.io.Serializable;

import lombok.Data;

/**
 * @author shishun.wang
 * @date 2018年3月8日 下午4:31:28
 * @version 1.0
 * @describe
 */
@Data
public class AdPlayRuleBaseInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long emPlayRuleId;

	private String banner;

	private String adName;

	/**
	 * 广告资源id
	 */
	private Long adResourceId;

	/**
	 * 订单总额剩余
	 */
	private Long remainingTotalLimitPrice;

	/**
	 * 电子卷总额剩余
	 */
	private Long remainingTotalLimitCount;

}
