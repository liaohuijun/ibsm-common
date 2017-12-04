package com.hm.machine;

/**
 * @author shishun.wang
 * @date 2017年12月4日 下午3:30:47
 * @version 1.0
 * @describe
 */
public class OrderStatusSuport {

	private Long orderId;

	private OrderMachine lastStatu;

	private OrderMachine nextStatu;

	/**
	 * @param orderId
	 * @param lastStatu
	 */
	public OrderStatusSuport(Long orderId, OrderMachine lastStatu) {
		super();
		this.orderId = orderId;
		this.lastStatu = lastStatu;
	}

	/**
	 * @param orderId
	 * @param lastStatu
	 * @param nextStatu
	 */
	public OrderStatusSuport(Long orderId, OrderMachine lastStatu, OrderMachine nextStatu) {
		super();
		this.orderId = orderId;
		this.lastStatu = lastStatu;
		this.nextStatu = nextStatu;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public OrderMachine getLastStatu() {
		return lastStatu;
	}

	public void setLastStatu(OrderMachine lastStatu) {
		this.lastStatu = lastStatu;
	}

	public OrderMachine getNextStatu() {
		return nextStatu;
	}

	public void setNextStatu(OrderMachine nextStatu) {
		this.nextStatu = nextStatu;
	}

	@Override
	public String toString() {
		return "OrderStatusSuport [orderId=" + orderId + ", lastStatu=" + lastStatu + ", nextStatu=" + nextStatu + "]";
	}

}
