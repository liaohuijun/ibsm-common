package com.hm.akka.study.demo3;

import java.util.concurrent.TimeUnit;

import akka.actor.AbstractActor;
import akka.actor.Props;
import scala.concurrent.duration.Duration;

/**
 * @author shishun.wang
 * @date 2018年3月12日 下午4:06:41
 * @version 1.0
 * @describe
 */
public class DemoActor extends AbstractActor {

	public final Integer magicNumber;

	public DemoActor(Integer magicNumber) {
		this.magicNumber = magicNumber;
		
		//设置超时时间
		getContext().setReceiveTimeout(Duration.create(10, TimeUnit.SECONDS));
	}

	public static Props props(Integer magicNumber) {
		return Props.create(DemoActor.class, () -> new DemoActor(magicNumber));
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Integer.class, num -> {
			System.out.println("number=" + num);
			getSelf().tell(num + 3, getSender());
		}).match(String.class, str->{
			System.out.println("接收信息超时");
			
//			getContext().setReceiveTimeout(Duration.Undefined());
		}).match(Greeting.class, gr -> {
			System.out.println("greeting 消息 " + gr.message);
		}).build();
	}

	public static class Greeting {

		public final String message;

		public Greeting(String message) {
			this.message = message;
		}
	}
}
