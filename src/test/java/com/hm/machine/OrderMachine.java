package com.hm.machine;

import java.util.Arrays;
import java.util.List;

import com.hm.common.exception.ErrorCode;
import com.hm.common.exception.ServiceException;
import com.hm.common.util.CommonUtil;
import com.hm.common.util.StringUtil;

/**
 * @author shishun.wang
 * @date 2017年12月4日 下午3:29:51
 * @version 1.0
 * @describe
 */
public enum OrderMachine {

	/**
	 * @author shishun.wang
	 * @date 2017年12月4日 下午3:50:56
	 * @version 1.0
	 * @describe 待付款
	 */
	WATING_PAYMENT("WATING_PAYMENT", "待付款") {

		@Override
		public OrderStatusSuport conversion(OrderStatusSuport status) {
			if (CommonUtil.isEmpty(status.getLastStatu())) {
				throw ServiceException.warning(ErrorCode.DATA_NOT_NULL);
			}

			if (status.getLastStatu() != OrderMachine.WATING_PAYMENT) {
				throw ServiceException.warn("订单状态不匹配,上次订单状态为:" + status.getLastStatu().desc());
			}

			status.setNextStatu(OrderMachine.PAYMENT_NOT_DELIVERY);
			return status;
		}

	},

	/**
	 * @author shishun.wang
	 * @date 2017年12月4日 下午3:44:02
	 * @version 1.0
	 * @describe 已付款待发货
	 */
	PAYMENT_NOT_DELIVERY("PAYMENT_NOT_DELIVERY", "已付款待发货") {

		@Override
		public OrderStatusSuport conversion(OrderStatusSuport status) {
			if (status.getLastStatu() != OrderMachine.PAYMENT_NOT_DELIVERY) {
				throw ServiceException.warn("订单状态不匹配,上次订单状态为:" + status.getLastStatu().desc());
			}

			status.setNextStatu(OrderMachine.PAYMENT_AND_DELIVERY);
			return status;
		}

	},
	/**
	 * @author shishun.wang
	 * @date 2017年12月4日 下午3:44:57
	 * @version 1.0
	 * @describe 已付款并已发货
	 */
	PAYMENT_AND_DELIVERY("PAYMENT_AND_DELIVERY", "已付款并已发货") {

		@Override
		public OrderStatusSuport conversion(OrderStatusSuport status) {
			if (CommonUtil.isEmpty(status.getLastStatu())) {
				throw ServiceException.warning(ErrorCode.DATA_NOT_NULL);
			}

			if (status.getLastStatu() != OrderMachine.PAYMENT_AND_DELIVERY) {
				throw ServiceException.warn("订单状态不匹配,上次订单状态为:" + status.getLastStatu().desc());
			}

			status.setNextStatu(OrderMachine.RECEIVE_DELIVERY);
			return status;
		}

	},
	/**
	 * @author shishun.wang
	 * @date 2017年12月4日 下午3:46:25
	 * @version 1.0
	 * @describe 已收货
	 */
	RECEIVE_DELIVERY("RECEIVE_DELIVERY", "已收货") {

		@Override
		public OrderStatusSuport conversion(OrderStatusSuport status) {
			if (CommonUtil.isEmpty(status.getLastStatu())) {
				throw ServiceException.warning(ErrorCode.DATA_NOT_NULL);
			}

			if (status.getLastStatu() != OrderMachine.RECEIVE_DELIVERY) {
				throw ServiceException.warn("订单状态不匹配,上次订单状态为:" + status.getLastStatu().desc());
			}
			status.setNextStatu(OrderMachine.FINISH_ORDER);
			return status;
		}
	},
	/**
	 * @author shishun.wang
	 * @date 2017年12月4日 下午4:05:21
	 * @version 1.0
	 * @describe 完成订单
	 */
	FINISH_ORDER("FINISH_ORDER", "完成订单") {

		@Override
		public OrderStatusSuport conversion(OrderStatusSuport status) {
			if (CommonUtil.isEmpty(status.getLastStatu())) {
				throw ServiceException.warning(ErrorCode.DATA_NOT_NULL);
			}

			if (status.getLastStatu() != OrderMachine.FINISH_ORDER) {
				throw ServiceException.warn("订单状态不匹配,上次订单状态为:" + status.getLastStatu().desc());
			}
			status.setNextStatu(null);
			return status;
		}

	},
	/**
	 * @author shishun.wang
	 * @date 2017年12月4日 下午3:49:32
	 * @version 1.0
	 * @describe 取消订单
	 */
	CANCEL_ORDER("CANCEL_ORDER", "取消订单") {

		@Override
		public OrderStatusSuport conversion(OrderStatusSuport status) {
			if (CommonUtil.isEmpty(status.getLastStatu())) {
				throw ServiceException.warning(ErrorCode.DATA_NOT_NULL);
			}

			List<OrderMachine> supportCancelStatus = Arrays.asList(WATING_PAYMENT, PAYMENT_NOT_DELIVERY);
			if (!supportCancelStatus.contains(status.getLastStatu())) {
				throw ServiceException.warn("订单状态不匹配,上次订单状态为:" + status.getLastStatu().desc());
			}
			status.setNextStatu(CANCEL_ORDER);
			return status;
		}
	};

	private OrderMachine(String status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	private String status;

	private String desc;

	public String status() {
		return status;
	}

	public String desc() {
		return desc;
	}

	public static OrderMachine trance(String status) {
		if (StringUtil.isBlankOrNull(status)) {
			return null;
		}
		for (OrderMachine machine : OrderMachine.values()) {
			if (machine.status().equals(status.trim())) {
				return machine;
			}
		}
		return null;
	}

	public abstract OrderStatusSuport conversion(OrderStatusSuport status);

}
