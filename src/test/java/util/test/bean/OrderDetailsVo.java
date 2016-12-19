package util.test.bean;

import java.util.List;

import util.test.bean.OfferCallBackVo.CarInfoVo;
import util.test.bean.OfferCallBackVo.CarOwnerVo;

/**
 * @author shishun.wang
 * @date 2016年12月15日 上午11:08:15
 * @version 1.0
 * @describe
 */
public class OrderDetailsVo {

	private String taskId;

	private String createTime;

	private CarInfoVo carInfo;

	private CarOwnerVo insured;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public CarInfoVo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfoVo carInfo) {
		this.carInfo = carInfo;
	}

	public CarOwnerVo getInsured() {
		return insured;
	}

	public void setInsured(CarOwnerVo insured) {
		this.insured = insured;
	}

	public List<ProvidersVo> getProviders() {
		return providers;
	}

	public void setProviders(List<ProvidersVo> providers) {
		this.providers = providers;
	}

	private List<ProvidersVo> providers;

	public static class ProvidersVo {

		private String prvId;

		public String getPrvId() {
			return prvId;
		}

		public void setPrvId(String prvId) {
			this.prvId = prvId;
		}

		public String getPrvCode() {
			return prvCode;
		}

		public void setPrvCode(String prvCode) {
			this.prvCode = prvCode;
		}

		public String getPrvDesc() {
			return prvDesc;
		}

		public void setPrvDesc(String prvDesc) {
			this.prvDesc = prvDesc;
		}

		public String getPrvName() {
			return prvName;
		}

		public void setPrvName(String prvName) {
			this.prvName = prvName;
		}

		public String getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(String totalPrice) {
			this.totalPrice = totalPrice;
		}

		public String getPayValidTime() {
			return payValidTime;
		}

		public void setPayValidTime(String payValidTime) {
			this.payValidTime = payValidTime;
		}

		public String getQuoteValidTime() {
			return quoteValidTime;
		}

		public void setQuoteValidTime(String quoteValidTime) {
			this.quoteValidTime = quoteValidTime;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		private String prvCode;

		private String prvDesc;

		private String prvName;

		private String totalPrice;

		private String payValidTime;

		private String quoteValidTime;

		private String msg;

		@Override
		public String toString() {
			return "ProvidersVo [prvId=" + prvId + ", prvCode=" + prvCode + ", prvDesc=" + prvDesc + ", prvName="
					+ prvName + ", totalPrice=" + totalPrice + ", payValidTime=" + payValidTime + ", quoteValidTime="
					+ quoteValidTime + ", msg=" + msg + "]";
		}
		
		
	}

	@Override
	public String toString() {
		return "OrderDetailsVo [taskId=" + taskId + ", createTime=" + createTime + ", carInfo=" + carInfo + ", insured="
				+ insured + ", providers=" + providers + "]";
	}

}
