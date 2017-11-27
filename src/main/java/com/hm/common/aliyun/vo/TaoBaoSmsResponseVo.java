package com.hm.common.aliyun.vo;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author shishun.wang
 * @date 2017年11月15日 下午1:55:58
 * @version 1.0
 * @describe
 */
public class TaoBaoSmsResponseVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "alibaba_aliqin_fc_sms_num_send_response")
	private TaoBaoSmsSuccessfulRespVo smsSuccessfulResp;

	@JSONField(name = "error_response")
	private TaoBaoSmsFailRespVo failResp;

	/**
	 * @author shishun.wang
	 * @date 2017年11月15日 下午2:03:42
	 * @version 1.0
	 * @describe
	 */
	public static class TaoBaoSmsSuccessfulRespVo implements Serializable {

		private static final long serialVersionUID = 1L;

		private Map<String, String> result;

		@JSONField(name = "request_id")
		private String requestId;

		public Map<String, String> getResult() {
			return result;
		}

		public void setResult(Map<String, String> result) {
			this.result = result;
		}

		public String getRequestId() {
			return requestId;
		}

		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}

		@Override
		public String toString() {
			return "TaoBaoSmsSuccessfulRespVo [result=" + result + ", requestId=" + requestId + "]";
		}

	}

	/**
	 * @author shishun.wang
	 * @date 2017年11月15日 下午2:03:44
	 * @version 1.0
	 * @describe
	 */
	public static class TaoBaoSmsFailRespVo implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer code;

		private String msg;

		@JSONField(name = "sub_code")
		private String subCode;

		@JSONField(name = "sub_msg")
		private String subMsg;

		@JSONField(name = "request_id")
		private String requestId;

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getSubCode() {
			return subCode;
		}

		public void setSubCode(String subCode) {
			this.subCode = subCode;
		}

		public String getSubMsg() {
			return subMsg;
		}

		public void setSubMsg(String subMsg) {
			this.subMsg = subMsg;
		}

		public String getRequestId() {
			return requestId;
		}

		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}

		@Override
		public String toString() {
			return "TaoBaoSmsFailRespVo [code=" + code + ", msg=" + msg + ", subCode=" + subCode + ", subMsg=" + subMsg
					+ ", requestId=" + requestId + "]";
		}

	}

	public TaoBaoSmsSuccessfulRespVo getSmsSuccessfulResp() {
		return smsSuccessfulResp;
	}

	public void setSmsSuccessfulResp(TaoBaoSmsSuccessfulRespVo smsSuccessfulResp) {
		this.smsSuccessfulResp = smsSuccessfulResp;
	}

	public TaoBaoSmsFailRespVo getFailResp() {
		return failResp;
	}

	public void setFailResp(TaoBaoSmsFailRespVo failResp) {
		this.failResp = failResp;
	}

	@Override
	public String toString() {
		return "TaoBaoSmsResponseVo [smsSuccessfulResp=" + smsSuccessfulResp + ", failResp=" + failResp + "]";
	}

}
