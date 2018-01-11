package com.hm.akka.demo.hello;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author shishun.wang
 * @date 2018年1月5日 下午5:12:27
 * @version 1.0
 * @describe
 */
public class MainApp {

	public static void main(String[] args) throws Exception {
//		akka.Main.main(new String[] { HelloWorld.class.getName() });

		ActorSystem actorSystem = ActorSystem.create("aa");
		ActorRef ref = actorSystem.actorOf(Props.create(HelloWorld.class), "bb");
		System.out.println("---------->"+ref.path());
		
		System.out.println("-------------------华丽的分割线-----------------------------------");
		
//		ActorRef actorOf = getContext().actorOf(Props.create(Greeter.class), "greeter");
//		actorOf.tell(Greeter.Msg.GREET, getSelf());
		
		
		ActorSystem actorSystem2 = ActorSystem.create("cc");
		ActorRef actorOf = actorSystem2.actorOf(Props.create(Greeter.class));
		actorOf.tell(Greeter.Msg.GREET, actorOf);
		System.out.println("==============>"+actorOf.path());
	}
}
