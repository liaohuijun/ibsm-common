package com.hm.akka.study;

import akka.actor.ActorSystem;

/**
 * @author shishun.wang
 * @date 2018年3月6日 下午3:04:21
 * @version 1.0
 * @describe 
 */
public class IotMain {

	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("iot-system");
		try {
			actorSystem.actorOf(IotSupervisor.props(), "iot-supervisor");
			
			System.in.read();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			actorSystem.terminate();
		}
	}
}
