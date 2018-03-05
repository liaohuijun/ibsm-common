package com.hm.akka.study.demo1;

import com.hm.akka.study.demo1.Greeter.Greet;
import com.hm.akka.study.demo1.Greeter.WhoToGreet;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * @author shishun.wang
 * @date 2018年3月5日 下午4:41:15
 * @version 1.0
 * @describe
 */
public class AkkaQuickstart {

	public static void main(String[] args) {

		final ActorSystem actorSystem = ActorSystem.create("helloakka");
		try {
			final ActorRef printerActor = actorSystem.actorOf(Printer.props(), "printerActor");
			final ActorRef howdyGreeter = actorSystem.actorOf(Greeter.props("Howdy", printerActor), "howdyGreeter");
			final ActorRef helloGreeter = actorSystem.actorOf(Greeter.props("Hello", printerActor), "helloGreeter");
			final ActorRef goodDayGreeter = actorSystem.actorOf(Greeter.props("Good day", printerActor), "goodDayGreeter");

			howdyGreeter.tell(new WhoToGreet("Akka"), ActorRef.noSender());
			howdyGreeter.tell(new Greet(), ActorRef.noSender());

			howdyGreeter.tell(new WhoToGreet("Lightbend"), ActorRef.noSender());
			howdyGreeter.tell(new Greet(), ActorRef.noSender());

			helloGreeter.tell(new WhoToGreet("Java"), ActorRef.noSender());
			helloGreeter.tell(new Greet(), ActorRef.noSender());

			goodDayGreeter.tell(new WhoToGreet("Play"), ActorRef.noSender());
			goodDayGreeter.tell(new Greet(), ActorRef.noSender());

			System.out.println(">>> Press ENTER to exit <<<");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			actorSystem.terminate();
		}
	}
}
