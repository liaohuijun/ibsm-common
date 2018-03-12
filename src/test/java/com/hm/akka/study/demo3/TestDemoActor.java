package com.hm.akka.study.demo3;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * @author shishun.wang
 * @date 2018年3月12日 下午4:35:59
 * @version 1.0
 * @describe 
 */
public class TestDemoActor {

	public static void main(String[] args) throws Exception{
		
		ActorSystem system = ActorSystem.create();
		ActorRef actorOf = system.actorOf(DemoActor.props(10));
		
//		actorOf.tell(new DemoActor.Greeting("张三"), ActorRef.noSender());
		actorOf.tell(13, ActorRef.noSender());
		
		system.terminate();

	}
}
