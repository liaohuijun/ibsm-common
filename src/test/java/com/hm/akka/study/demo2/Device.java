package com.hm.akka.study.demo2;

import java.util.Optional;

import com.hm.akka.study.demo2.DeviceManager.DeviceRegistered;
import com.hm.akka.study.demo2.DeviceManager.RequestTrackDevice;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author shishun.wang
 * @date 2018年3月6日 下午4:22:21
 * @version 1.0
 * @describe
 */
public class Device extends AbstractActor {

	private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	final String groupId;

	final String deviceId;

	public Device(String groupId, String deviceId) {
		this.groupId = groupId;
		this.deviceId = deviceId;
	}

	public static Props props(String groupId, String deviceId) {
		return Props.create(Device.class, groupId, deviceId);
	}

	public static final class ReadTemperature {
		long requestId;

		public ReadTemperature(long requestId) {
			this.requestId = requestId;
		}

		@Override
		public String toString() {
			return "ReadTemperature [requestId=" + requestId + "]";
		}

	}

	public static final class RespondTemperature {
		long requestId;
		Optional<Double> value;

		public RespondTemperature(long requestId, Optional<Double> value) {
			this.requestId = requestId;
			this.value = value;
		}

		@Override
		public String toString() {
			return "RespondTemperature [requestId=" + requestId + ", value=" + value + "]";
		}

	}

	Optional<Double> lastTemperatureReading = Optional.empty();

	public static final class RecordTemperature {
		final long requestId;
		final double value;

		public RecordTemperature(long requestId, double value) {
			this.requestId = requestId;
			this.value = value;
		}

		@Override
		public String toString() {
			return "RecordTemperature [requestId=" + requestId + ", value=" + value + "]";
		}

	}

	public static final class TemperatureRecorded {
		final long requestId;

		public TemperatureRecorded(long requestId) {
			this.requestId = requestId;
		}

		@Override
		public String toString() {
			return "TemperatureRecorded [requestId=" + requestId + "]";
		}

	}

	@Override
	public void postStop() throws Exception {
		log.info("Device actor {}-{} stopped", groupId, deviceId);
	}

	@Override
	public void preStart() throws Exception {
		log.info("Device actor {}-{} started", groupId, deviceId);
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(RequestTrackDevice.class, re -> {
			if (this.groupId.equals(re.groupId) && this.deviceId.equals(re.deviceId)) {
				getSender().tell(new DeviceRegistered(), getSelf());
			} else {
				log.warning("Ignoring TrackDevice request for {}-{}.This actor is responsible for {}-{}.", re.groupId, re.deviceId, this.groupId, this.deviceId);
			}
		}).match(ReadTemperature.class, re -> {
			getSender().tell(new RespondTemperature(re.requestId, lastTemperatureReading), getSelf());
		}).match(RecordTemperature.class, re -> {
			log.info("Recorded temperature reading {} with {}", re.value, re.requestId);
			lastTemperatureReading = Optional.of(re.value);

			getSender().tell(new TemperatureRecorded(re.requestId), getSelf());
		}).build();
	}
}
