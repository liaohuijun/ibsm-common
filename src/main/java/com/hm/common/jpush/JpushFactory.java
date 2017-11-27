package com.hm.common.jpush;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm.common.def.SystemSupportTerminalEnum;
import com.hm.common.util.CommonUtil;
import com.hm.common.util.StringUtil;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @author shishun.wang
 * @date 2017年11月8日 上午11:51:03
 * @version 1.0
 * @describe
 */
public enum JpushFactory {

	/**
	 * @author shishun.wang
	 * @date 2017年11月8日 下午12:06:12
	 * @version 1.0
	 * @describe 按注册（RegistrationId）推送
	 */
	REGISTRATION_ID {

		private JPushClient client;

		private Platform platform;

		private SystemSupportTerminalEnum terminal;

		private Map<String, String> extras;

		private String alertInfo;

		@Override
		public JpushFactory build(SystemSupportTerminalEnum terminal, JpushAuthorization authorization) {
			this.terminal = terminal;
			this.client = createJpushClient(authorization);
			switch (terminal) {
			case ANDROID:
				platform = Platform.android();
				break;
			case IOS:
				platform = Platform.ios();
				break;
			case ALL:
			default:
				platform = Platform.all();
				break;
			}
			return this;
		}

		@Override
		public JpushFactory addExtras(Map<String, String> extras) {
			this.extras = extras;
			return this;
		}

		@Override
		public PushResult push(List<String> tagetPushAlias, String alertInfo, String msgContent)
				throws APIConnectionException, APIRequestException {
			this.alertInfo = alertInfo;
			cn.jpush.api.push.model.notification.Notification.Builder notification = Notification.newBuilder();
			switch (terminal) {
			case ANDROID:
				notification.addPlatformNotification(androidNotification(this.extras, this.alertInfo));
				break;
			case IOS:
				notification.addPlatformNotification(iosNotification(this.extras, this.alertInfo));
				break;
			case ALL:
			default:
				notification.addPlatformNotification(androidNotification(this.extras, this.alertInfo));
				notification.addPlatformNotification(iosNotification(this.extras, this.alertInfo));
				break;
			}

			PushPayload pushPayload = null;
			if (StringUtil.isBlankOrNull(msgContent)) {
				pushPayload = buildPushObjectByRegistrationId(platform, notification.build(), tagetPushAlias);
			} else {
				pushPayload = buildPushObjectByRegistrationId(platform, notification.build(), tagetPushAlias,
						msgContent);
			}

			PushResult result = client.sendPush(pushPayload);
			delayWating();// 延迟等待第三方程序处理

			return result;
		}
	},

	/**
	 * @author shishun.wang
	 * @date 2017年11月9日 下午5:05:17
	 * @version 1.0
	 * @describe 别名推送
	 */
	ALIA {

		private JPushClient client;

		private Platform platform;

		private SystemSupportTerminalEnum terminal;

		private Map<String, String> extras;

		private String alertInfo;

		@Override
		public JpushFactory build(SystemSupportTerminalEnum terminal, JpushAuthorization authorization) {
			this.terminal = terminal;
			this.client = createJpushClient(authorization);
			switch (terminal) {
			case ANDROID:
				platform = Platform.android();
				break;
			case IOS:
				platform = Platform.ios();
				break;
			case ALL:
			default:
				platform = Platform.all();
				break;
			}
			return this;
		}

		@Override
		public JpushFactory addExtras(Map<String, String> extras) {
			this.extras = extras;
			return this;
		}

		@Override
		public PushResult push(List<String> tagetPushAlias, String alertInfo, String msgContent)
				throws APIConnectionException, APIRequestException {
			this.alertInfo = alertInfo;
			cn.jpush.api.push.model.notification.Notification.Builder notification = Notification.newBuilder();
			switch (terminal) {
			case ANDROID:
				notification.addPlatformNotification(androidNotification(this.extras, this.alertInfo));
				break;
			case IOS:
				notification.addPlatformNotification(iosNotification(this.extras, this.alertInfo));
				break;
			case ALL:
			default:
				notification.addPlatformNotification(androidNotification(this.extras, this.alertInfo));
				notification.addPlatformNotification(iosNotification(this.extras, this.alertInfo));
				break;
			}

			PushPayload pushPayload = null;
			if (StringUtil.isBlankOrNull(msgContent)) {
				pushPayload = buildPushObjectByAlia(platform, notification.build(), tagetPushAlias);
			} else {
				pushPayload = buildPushObjectByAlia(platform, notification.build(), tagetPushAlias, msgContent);
			}

			PushResult result = client.sendPush(pushPayload);
			delayWating();// 延迟等待第三方程序处理

			return result;
		}

	},

	/**
	 * @author shishun.wang
	 * @date 2017年11月8日 下午12:06:21
	 * @version 1.0
	 * @describe 按组推送
	 */
	TAGS {

		@Override
		public JpushFactory build(SystemSupportTerminalEnum terminal, JpushAuthorization authorization) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public JpushFactory addExtras(Map<String, String> extras) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PushResult push(List<String> tagetPushAlias, String alertInfo, String msgContent)
				throws APIConnectionException, APIRequestException {
			// TODO Auto-generated method stub
			return null;
		}

	};

	private static int WATTING_TIME = 1000;

	public abstract JpushFactory build(SystemSupportTerminalEnum terminal, JpushAuthorization authorization);

	public abstract JpushFactory addExtras(Map<String, String> extras);

	public abstract PushResult push(List<String> tagetPushAlias, String alertInfo, String msgContent)
			throws APIConnectionException, APIRequestException;

	private static AndroidNotification androidNotification(Map<String, String> extras, String alertInfo) {
		cn.jpush.api.push.model.notification.AndroidNotification.Builder builder = AndroidNotification.newBuilder();

		if (StringUtil.notBlankOrNull(alertInfo)) {
			builder.setAlert(alertInfo.trim());
		}

		if (CommonUtil.isNotEmpty(extras)) {
			builder.addExtras(extras);
		}

		return builder.build();
	}

	private static IosNotification iosNotification(Map<String, String> extras, String alertInfo) {
		cn.jpush.api.push.model.notification.IosNotification.Builder builder = IosNotification.newBuilder();

		if (StringUtil.notBlankOrNull(alertInfo)) {
			builder.setAlert(alertInfo.trim());
		}
		builder.setBadge(5);// 角标数字为 5

		if (CommonUtil.isNotEmpty(extras)) {
			builder.addExtras(extras);
		}
		return builder.build();
	}

	private static PushPayload buildPushObjectByAlia(Platform platform, Notification notification,
			List<String> tagetPushAlias) {
		return PushPayload.newBuilder().setPlatform(platform).setAudience(Audience.alias(tagetPushAlias))
				.setNotification(notification).setOptions(Options.newBuilder().setApnsProduction(true)// 通知是APNs推送通道的，消息是JPush应用内消息通道的。APNs的推送环境是“生产”（如果不显式设置的话，Library会默认指定为开发）
						.build())
				.build();
	}

	private static PushPayload buildPushObjectByAlia(Platform platform, Notification notification,
			List<String> tagetPushAlias, String msgContent) {
		return PushPayload.newBuilder().setPlatform(platform).setAudience(Audience.alias(tagetPushAlias))
				.setNotification(notification).setMessage(Message.content(msgContent))// 消息内容是MSG_CONTENT
				.setOptions(Options.newBuilder().setApnsProduction(true)// 通知是APNs推送通道的，消息是JPush应用内消息通道的。APNs的推送环境是“生产”（如果不显式设置的话，Library会默认指定为开发）
						.build())
				.build();
	}

	private static PushPayload buildPushObjectByRegistrationId(Platform platform, Notification notification,
			List<String> tagetPushAlias) {
		return PushPayload.newBuilder().setPlatform(platform).setAudience(Audience.registrationId(tagetPushAlias))
				.setNotification(notification).setOptions(Options.newBuilder().setApnsProduction(true)// 通知是APNs推送通道的，消息是JPush应用内消息通道的。APNs的推送环境是“生产”（如果不显式设置的话，Library会默认指定为开发）
						.build())
				.build();
	}

	private static PushPayload buildPushObjectByRegistrationId(Platform platform, Notification notification,
			List<String> tagetPushAlias, String msgContent) {
		return PushPayload.newBuilder().setPlatform(platform).setAudience(Audience.registrationId(tagetPushAlias))
				.setNotification(notification).setMessage(Message.content(msgContent))// 消息内容是MSG_CONTENT
				.setOptions(Options.newBuilder().setApnsProduction(true)// 通知是APNs推送通道的，消息是JPush应用内消息通道的。APNs的推送环境是“生产”（如果不显式设置的话，Library会默认指定为开发）
						.build())
				.build();
	}

	private static JPushClient createJpushClient(JpushAuthorization authorization) {
		return new JPushClient(authorization.getMasterSecret(), authorization.getAppKey(), null,
				ClientConfig.getInstance());
	}

	private static void delayWating() {
		try {
			Thread.sleep(WATTING_TIME);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

	private static Logger log = LoggerFactory.getLogger(JpushFactory.class);

	/**
	 * @author shishun.wang
	 * @date 2017年11月8日 上午11:46:08
	 * @version 1.0
	 * @describe
	 */
	public static class JpushAuthorization implements Serializable {

		private static final long serialVersionUID = 1L;

		private String appKey;

		private String masterSecret;

		private Boolean prodEnv;

		public JpushAuthorization(String appKey, String masterSecret, Boolean prodEnv) {
			super();
			this.appKey = appKey;
			this.masterSecret = masterSecret;
			this.prodEnv = prodEnv;
		}

		public String getAppKey() {
			return appKey;
		}

		public void setAppKey(String appKey) {
			this.appKey = appKey;
		}

		public String getMasterSecret() {
			return masterSecret;
		}

		public void setMasterSecret(String masterSecret) {
			this.masterSecret = masterSecret;
		}

		public Boolean getProdEnv() {
			return prodEnv;
		}

		public void setProdEnv(Boolean prodEnv) {
			this.prodEnv = prodEnv;
		}

		@Override
		public String toString() {
			return "JpushAuthorization [appKey=" + appKey + ", masterSecret=" + masterSecret + ", prodEnv=" + prodEnv
					+ "]";
		}

	}
}
