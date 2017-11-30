package com.hm.common.jpush;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm.common.util.CommonUtil;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;

/**
 * @author shishun.wang
 * @date 2017年11月30日 下午4:13:31
 * @version 1.0
 * @describe jpush高级（自定义消息）
 */
public enum JpushSeniorFactory {

	/**
	 * @author shishun.wang
	 * @date 2017年11月8日 下午12:06:12
	 * @version 1.0
	 * @describe 按注册（RegistrationId）推送
	 */
	REGISTRATION_ID {

		private JPushClient client;

		@Override
		public JpushSeniorFactory build(JpushAuthorization authorization) {
			this.client = createJpushClient(authorization);
			return this;
		}

		@Override
		public PushResult push(String alertInfo, List<String> tagetPushAlias, Map<String, String> extras,
				String msgContent) throws APIConnectionException, APIRequestException {

			cn.jpush.api.push.model.Message.Builder msgBuilder = Message.newBuilder();
			if (CommonUtil.isNotEmpty(alertInfo)) {
				msgBuilder.setTitle(alertInfo);
			}

			if (CommonUtil.isNotEmpty(msgContent)) {
				msgBuilder.setMsgContent(msgContent);
			}

			if (CommonUtil.isNotEmpty(extras)) {
				msgBuilder.addExtras(extras);
			}

			PushPayload payload = PushPayload
					.newBuilder().setPlatform(Platform.all()).setAudience(Audience.newBuilder()
							.addAudienceTarget(AudienceTarget.registrationId(tagetPushAlias)).build())
					.setMessage(msgBuilder.build()).build();

			PushResult result = client.sendPush(payload);
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

		@Override
		public JpushSeniorFactory build(JpushAuthorization authorization) {
			this.client = createJpushClient(authorization);
			return this;
		}

		@Override
		public PushResult push(String alertInfo, List<String> tagetPushAlias, Map<String, String> extras,
				String msgContent) throws APIConnectionException, APIRequestException {

			cn.jpush.api.push.model.Message.Builder msgBuilder = Message.newBuilder();
			if (CommonUtil.isNotEmpty(alertInfo)) {
				msgBuilder.setTitle(alertInfo);
			}

			if (CommonUtil.isNotEmpty(msgContent)) {
				msgBuilder.setMsgContent(msgContent);
			}

			if (CommonUtil.isNotEmpty(extras)) {
				msgBuilder.addExtras(extras);
			}

			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android_ios())
					.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(tagetPushAlias)).build())
					.setMessage(msgBuilder.build()).build();

			PushResult result = client.sendPush(payload);
			delayWating();// 延迟等待第三方程序处理

			return result;
		}
	};

	private static int WATTING_TIME = 1000;

	public abstract JpushSeniorFactory build(JpushAuthorization authorization);

	public abstract PushResult push(String alertInfo, List<String> tagetPushAlias, Map<String, String> extras,
			String msgContent) throws APIConnectionException, APIRequestException;

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

	private static Logger log = LoggerFactory.getLogger(JpushSeniorFactory.class);
}
