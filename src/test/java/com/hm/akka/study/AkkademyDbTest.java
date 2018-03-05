package com.hm.akka.study;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author shishun.wang
 * @date 2018年3月5日 下午12:11:11
 * @version 1.0
 * @describe
 */
public class AkkademyDbTest {

	public static void main(String[] args) {

		ActorRef actorOf = ActorSystem.create().actorOf(Props.create(AkkademyDb.class));
		actorOf.tell(new SetRequest("key", "value"), ActorRef.noSender());
		System.out.println("----->"+actorOf.path());
//		AkkademyDb akkademyDb = actorOf.underlyingActor();
//		assertEquals(akkademyDb.map.get("key"), "value");
	}
}
