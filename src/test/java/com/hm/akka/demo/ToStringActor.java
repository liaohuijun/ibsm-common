package com.hm.akka.demo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * @author shishun.wang
 * @date 下午2:07:36 2017年6月19日
 * @version 1.0
 * @describe
 */
public class ToStringActor extends UntypedActor {

	@Override
	public void onReceive(Object message) {
		System.out.println(message.toString());
		try {
			Thread.sleep((int) (Math.random() * 100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("toStringActor");
		final ActorRef toString = system.actorOf(Props.create(ToStringActor.class), "toString");
		for (int j = 0; j < 3; j++) {
			new Thread() {
				public void run() {
					for (int i = 0; i < 10000000; i++) {
						toString.tell("test" + i, toString);
					}
					System.out.println("[结束]=======================," + Thread.currentThread().getName());
				};
			}.start();
		}
	}
}