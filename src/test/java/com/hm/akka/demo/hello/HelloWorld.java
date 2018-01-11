package com.hm.akka.demo.hello;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * @author shishun.wang
 * @date 2018年1月5日 下午4:24:20
 * @version 1.0
 * @describe
 */
public class HelloWorld extends UntypedActor {

	@Override
	public void preStart() throws Exception {
		ActorRef actorOf = getContext().actorOf(Props.create(Greeter.class), "greeter");
		actorOf.tell(Greeter.Msg.GREET, getSelf());
	}

	@Override
	public void onReceive(Object object) throws Exception {
		if (object == Greeter.Msg.DONE) {
			getContext().stop(getSelf());
		} else {
			unhandled(object);
		}
	}

}
