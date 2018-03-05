package com.hm.akka.study.demo1;

import akka.actor.AbstractActor;
import akka.actor.Props;

/**
 * @author shishun.wang
 * @date 2018年3月5日 下午3:25:11
 * @version 1.0
 * @describe
 */
public class Printer extends AbstractActor {

	public static Props props() {
		return Props.create(Printer.class, () -> new Printer());
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Greeting.class, gretting -> {
			System.out.println("我收到消息->" + gretting.message);
		}).build();
	}

	public static class Greeting {

		public final String message;

		public Greeting(String message) {
			System.out.println("---->进入Greeting构造方法");
			this.message = message;
		}
	}

}
