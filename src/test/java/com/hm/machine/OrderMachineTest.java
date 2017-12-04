package com.hm.machine;

/**
 * @author shishun.wang
 * @date 2017年12月4日 下午4:41:31
 * @version 1.0
 * @describe 
 */
public class OrderMachineTest {

	public static void main(String[] args) {
		
		System.out.println(OrderMachine.WATING_PAYMENT.desc());
		OrderStatusSuport status = new OrderStatusSuport(123l, OrderMachine.WATING_PAYMENT);
		status = OrderMachine.WATING_PAYMENT.conversion(status);
		System.out.println(status.getNextStatu().desc());
		
		status.setLastStatu(status.getNextStatu());
		status = OrderMachine.PAYMENT_NOT_DELIVERY.conversion(status);
		System.out.println(status.getNextStatu().desc());
		
		status.setLastStatu(status.getNextStatu());
		status = OrderMachine.PAYMENT_AND_DELIVERY.conversion(status);
		System.out.println(status.getNextStatu().desc());
		
		status.setLastStatu(status.getNextStatu());
		status = OrderMachine.RECEIVE_DELIVERY.conversion(status);
		System.out.println(status.getNextStatu().desc());
		
		status.setLastStatu(status.getNextStatu());
		status = OrderMachine.FINISH_ORDER.conversion(status);
		System.out.println(status.getNextStatu());
	}
}
