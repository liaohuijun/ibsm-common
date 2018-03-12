package com.hm.akka.study.demo2;

import java.util.HashMap;
import java.util.Map;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.annotation.tailrec;

/**
 * @author shishun.wang
 * @date 2018年3月8日 下午12:06:53
 * @version 1.0
 * @describe
 */
public class DeviceGroup extends AbstractActor {

	private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	final String groupId;

	public DeviceGroup(String groupId) {
		this.groupId = groupId;
	}

	public static Props props(String groupId) {
		return Props.create(DeviceGroup.class, groupId);
	}

	final Map<String, ActorRef> deviceIdToActor = new HashMap<String, ActorRef>();

	@Override
	public void postStop() throws Exception {
		log.info("DeviceGroup {} stopped", groupId);
	}

	@Override
	public void preStart() throws Exception {
		log.info("DeviceGroup {} started", groupId);
	}

	private void onTrackDevice(DeviceManager.RequestTrackDevice track) {
		if (this.groupId.equals(track.groupId)) {
			ActorRef deviceActor = deviceIdToActor.get(track.deviceId);
			if (null != deviceActor) {
				deviceActor.forward(track, getContext());
			} else {
				log.info("Creating device actor for {}", track.deviceId);
				deviceActor = getContext().actorOf(Device.props(groupId, track.deviceId), "device-" + track.deviceId);
				deviceIdToActor.put(track.deviceId, deviceActor);

				deviceActor.forward(track, getContext());
			}
		} else {
			log.warning("Ignoring TrackDevice request for {}. This actor is responsible for {}.", groupId, this.groupId);
		}
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(DeviceManager.RequestTrackDevice.class, this::onTrackDevice).build();
	}

}
