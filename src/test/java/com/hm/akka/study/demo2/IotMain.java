package com.hm.akka.study.demo2;

import static org.junit.Assert.assertEquals;

import com.hm.akka.study.demo2.Device.RespondTemperature;
import com.hm.akka.study.demo2.Device.TemperatureRecorded;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;

/**
 * @author shishun.wang
 * @date 2018年3月6日 下午3:04:21
 * @version 1.0
 * @describe 
 */
public class IotMain {

	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("iot-system");
		TestKit probe = new TestKit(actorSystem);
		try {
			ActorRef deviceActor = actorSystem.actorOf(Device.props("group", "device"));
			
			deviceActor.tell(new DeviceManager.RequestTrackDevice("group", "device"),probe.getRef());
			probe.expectMsgClass(DeviceManager.DeviceRegistered.class);
			
//			System.out.println(deviceActor);
//			System.out.println(deviceRegistered);
			assertEquals(deviceActor, probe.getLastSender());
			System.in.read();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			actorSystem.terminate();
		}
	}
	
	private static void testA() {
		ActorSystem actorSystem = ActorSystem.create("iot-system");
		TestKit probe = new TestKit(actorSystem);
		try {
//			actorSystem.actorOf(IotSupervisor.props(), "iot-supervisor");
			
			ActorRef deviceActor = actorSystem.actorOf(Device.props("group", "device"));
			
			deviceActor.tell(new Device.RecordTemperature(1l, 24.0), probe.getRef());
			TemperatureRecorded temperatureRecorded = probe.expectMsgClass(Device.TemperatureRecorded.class);
			System.out.println("1------>"+temperatureRecorded.toString());
			
			deviceActor.tell(new Device.ReadTemperature(2L), probe.getRef());
			RespondTemperature respon = probe.expectMsgClass(Device.RespondTemperature.class);
			System.out.println("2------>"+respon.toString());
			
			deviceActor.tell(new Device.RecordTemperature(3l, 55.0), probe.getRef());
			System.out.println("3------>"+probe.expectMsgClass(Device.TemperatureRecorded.class).toString());
			
			deviceActor.tell(new Device.ReadTemperature(4l), probe.getRef());
			System.out.println("4------>"+probe.expectMsgClass(Device.RespondTemperature.class).toString());
			
			System.in.read();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			actorSystem.terminate();
		}
	}
}
