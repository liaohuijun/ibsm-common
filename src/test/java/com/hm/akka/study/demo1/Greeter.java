package com.hm.akka.study.demo1;

import com.hm.akka.study.demo1.Printer.Greeting;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * @author shishun.wang
 * @date 2018年3月5日 下午3:15:42
 * @version 1.0
 * @describe
 */
public class Greeter extends AbstractActor {

	private String message;

	private ActorRef printActor;

	private String greeting = "";

	public Greeter(String message, ActorRef printActor) {
		super();
		this.message = message;
		this.printActor = printActor;
	}

	public static Props props(String message, ActorRef printActor) {
		return Props.create(Greeter.class, () -> new Greeter(message, printActor));
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(WhoToGreet.class, wtg -> {
			this.greeting = message + "," + wtg.who;
			System.out.println("WhoToGreet 值" + this.greeting);
		}).match(Greet.class, grt -> {
			printActor.tell(new Greeting(greeting), getSelf());
		}).build();
	}

	public static class WhoToGreet {

		public final String who;

		public WhoToGreet(String who) {
			this.who = who;
		}
	}

	public static class Greet {

		public Greet() {
			System.out.println("谁调用了我Greet");
		}
	}
}
