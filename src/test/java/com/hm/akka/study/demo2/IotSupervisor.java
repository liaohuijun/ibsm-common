package com.hm.akka.study.demo2;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author shishun.wang
 * @date 2018年3月6日 下午2:59:54
 * @version 1.0
 * @describe
 */
public class IotSupervisor extends AbstractActor {

	private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	public static Props props() {
		return Props.create(IotSupervisor.class);
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().build();
	}

	@Override
	public void postStop() throws Exception {
		log.info("postStop");
	}

	@Override
	public void preStart() throws Exception {
		log.info("preStart");
	}

	
}
