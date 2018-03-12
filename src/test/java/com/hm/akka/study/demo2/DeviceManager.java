package com.hm.akka.study.demo2;

/**
 * @author shishun.wang
 * @date 2018年3月7日 下午5:45:31
 * @version 1.0
 * @describe
 */
public class DeviceManager {

	public static final class RequestTrackDevice {
		public final String groupId;
		public final String deviceId;

		public RequestTrackDevice(String groupId, String deviceId) {
			this.groupId = groupId;
			this.deviceId = deviceId;
		}
	}

	public static final class DeviceRegistered {
	}
}
