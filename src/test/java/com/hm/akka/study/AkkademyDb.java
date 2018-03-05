package com.hm.akka.study;

import java.util.HashMap;
import java.util.Map;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author shishun.wang
 * @date 2018年3月5日 上午11:40:02
 * @version 1.0
 * @describe
 */
public class AkkademyDb extends AbstractActor {

	protected final LoggingAdapter log = Logging.getLogger(context().system(), this);

	protected final Map<String, Object> map = new HashMap<String, Object>();

	@Override
	public Receive createReceive() {

		return receiveBuilder().match(String.class, s -> {
			log.info("Received String message: {}", s);
		}).matchAny(o -> log.info("received unknown message")).build();

	}

}
