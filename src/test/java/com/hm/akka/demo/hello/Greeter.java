package com.hm.akka.demo.hello;

import com.hm.common.util.DateUtil;

import akka.actor.UntypedActor;

/**
 * @author shishun.wang
 * @date 2018年1月5日 下午4:26:19
 * @version 1.0
 * @describe
 */
public class Greeter extends UntypedActor {

	public static enum Msg {

		GREET, DONE;
	}

	@Override
	public void onReceive(Object object) throws Exception {
		if (Msg.GREET == object) {
			System.out.println(DateUtil.now4yyyyMMddhhmm() + "_hello");
			Thread.sleep(1000);
			getSender().tell(Greeter.Msg.DONE, getSelf());
		} else {
			unhandled(object);
		}
	}

}
