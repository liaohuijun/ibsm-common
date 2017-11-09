package com.hm.common.aliyun;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @author shishun.wang
 * @date 2017年11月7日 下午4:04:18
 * @version 1.0
 * @describe
 */
public final class AliyuncsUtil {

	private static Logger log = LoggerFactory.getLogger(AliyuncsUtil.class);

	private AliyuncsUtil() {

	}

	/**
	 * @author shishun.wang
	 * @date 2017年11月7日 下午4:05:00
	 * @version 1.0
	 * @describe
	 */
	public final static class SMS {

		/**
		 * 产品名称:云通信短信API产品
		 */
		private static final String product = "Dysmsapi";

		/**
		 * 产品域名
		 */
		private static final String domain = "dysmsapi.aliyuncs.com";

		private static final String CONNECT_TIMEOUT = "15000";

		private static final String READ_TIMEOUT = "15000";

		private static final int WATING_CALLBACK_TIME = 1500;

		static {
			System.setProperty("sun.net.client.defaultConnectTimeout", CONNECT_TIMEOUT);
			System.setProperty("sun.net.client.defaultReadTimeout", READ_TIMEOUT);
		}

		private SMS() {

		}

		private static SendSmsResponse execute(SmsAuthorization authorization, SendSmsRequest request)
				throws ClientException {
			SendSmsResponse response = buildIAcsClient(authorization).getAcsResponse(request);
			try {
				// 等待第三方处理结果返回,程序自动延迟处理，业务需要对及时发送短信，确认是否发送成功使用
				Thread.sleep(WATING_CALLBACK_TIME);
			} catch (InterruptedException e) {
				log.error(e.getMessage(), e);
			}
			return response;
		}

		private static IAcsClient buildIAcsClient(SmsAuthorization authorization) throws ClientException {
			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", authorization.getAccessKey(),
					authorization.getAccessKeySecret());
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			return acsClient;
		}

		/**
		 * 发送短信
		 * 
		 * @param authorization
		 * @param templateCode
		 * @param signName
		 * @param phoneNumbers
		 * @return
		 * @throws ClientException
		 */
		public static SendSmsResponse sendSms(SmsAuthorization authorization, String templateCode, String signName,
				String phoneNumbers) throws ClientException {
			log.info("调用阿里大于发送短信:templateCode=" + templateCode + ";signName=" + signName + ";phoneNumbers="
					+ phoneNumbers);
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(phoneNumbers);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName(signName);
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(templateCode);

			// hint 此处可能会抛出异常，注意catch
			return execute(authorization, request);
		}

		/**
		 * 发送短信
		 * 
		 * @param authorization
		 * @param templateCode
		 * @param signName
		 * @param content
		 * @param phoneNumbers
		 * @return
		 * @throws ClientException
		 */
		public static SendSmsResponse sendSms(SmsAuthorization authorization, String templateCode, String signName,
				String content, String phoneNumbers) throws ClientException {
			log.info("调用阿里大于发送短信:templateCode=" + templateCode + ";signName=" + signName + ";phoneNumbers="
					+ phoneNumbers + ";content=" + content);
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(phoneNumbers);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName(signName);
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(templateCode);
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			request.setTemplateParam(content);

			// hint 此处可能会抛出异常，注意catch
			return execute(authorization, request);
		}

		/**
		 * @author shishun.wang
		 * @date 2017年11月7日 下午5:22:59
		 * @version 1.0
		 * @describe
		 */
		public static class SmsAuthorization implements Serializable {

			private static final long serialVersionUID = 9052108254649204343L;

			private String accessKey;

			private String accessKeySecret;

			/**
			 * @param accessKey
			 * @param accessKeySecret
			 */
			public SmsAuthorization(String accessKey, String accessKeySecret) {
				super();
				this.accessKey = accessKey;
				this.accessKeySecret = accessKeySecret;
			}

			public String getAccessKey() {
				return accessKey;
			}

			public void setAccessKey(String accessKey) {
				this.accessKey = accessKey;
			}

			public String getAccessKeySecret() {
				return accessKeySecret;
			}

			public void setAccessKeySecret(String accessKeySecret) {
				this.accessKeySecret = accessKeySecret;
			}

			@Override
			public String toString() {
				return "SmsAuthorization [accessKey=" + accessKey + ", accessKeySecret=" + accessKeySecret + "]";
			}

		}
	}
}
