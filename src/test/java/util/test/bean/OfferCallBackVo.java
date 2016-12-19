package util.test.bean;

import java.util.List;

/**
 * @author shishun.wang
 * @date 下午11:45:38 2016年12月13日
 * @version 1.0
 * @describe
 */
public class OfferCallBackVo {

	private String taskId;

	private String prvId;

	private String prvName;

	private String code;

	private String msg;

	private String channelId;

	private String channelUserId;

	private String taskState;

	private String taskStateDescription;

	private CarInfoVo carInfo;

	private CarOwnerVo carOwner;

	private InsureInfoVo insureInfo;

	private String payValidTime;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getPrvId() {
		return prvId;
	}

	public void setPrvId(String prvId) {
		this.prvId = prvId;
	}

	public String getPrvName() {
		return prvName;
	}

	public void setPrvName(String prvName) {
		this.prvName = prvName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelUserId() {
		return channelUserId;
	}

	public void setChannelUserId(String channelUserId) {
		this.channelUserId = channelUserId;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getTaskStateDescription() {
		return taskStateDescription;
	}

	public void setTaskStateDescription(String taskStateDescription) {
		this.taskStateDescription = taskStateDescription;
	}

	public CarInfoVo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfoVo carInfo) {
		this.carInfo = carInfo;
	}

	public CarOwnerVo getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(CarOwnerVo carOwner) {
		this.carOwner = carOwner;
	}

	public InsureInfoVo getInsureInfo() {
		return insureInfo;
	}

	public void setInsureInfo(InsureInfoVo insureInfo) {
		this.insureInfo = insureInfo;
	}

	public String getPayValidTime() {
		return payValidTime;
	}

	public void setPayValidTime(String payValidTime) {
		this.payValidTime = payValidTime;
	}

	public static class InsureInfoVo {

		private EfcInsureInfoVo efcInsureInfo;

		private TaxInsureInfoVo taxInsureInfo;

		private BizInsureInfoVo bizInsureInfo;

		private String totalPremium;

		public EfcInsureInfoVo getEfcInsureInfo() {
			return efcInsureInfo;
		}

		public void setEfcInsureInfo(EfcInsureInfoVo efcInsureInfo) {
			this.efcInsureInfo = efcInsureInfo;
		}

		public TaxInsureInfoVo getTaxInsureInfo() {
			return taxInsureInfo;
		}

		public void setTaxInsureInfo(TaxInsureInfoVo taxInsureInfo) {
			this.taxInsureInfo = taxInsureInfo;
		}

		public BizInsureInfoVo getBizInsureInfo() {
			return bizInsureInfo;
		}

		public void setBizInsureInfo(BizInsureInfoVo bizInsureInfo) {
			this.bizInsureInfo = bizInsureInfo;
		}

		public String getTotalPremium() {
			return totalPremium;
		}

		public void setTotalPremium(String totalPremium) {
			this.totalPremium = totalPremium;
		}

		public static class BizInsureInfoVo {

			private String startDate;

			private String endDate;

			private String premium;

			private String discountRate;

			private String nfcPremium;

			private List<RiskKindsVo> riskKinds;

			public String getStartDate() {
				return startDate;
			}

			public void setStartDate(String startDate) {
				this.startDate = startDate;
			}

			public String getEndDate() {
				return endDate;
			}

			public void setEndDate(String endDate) {
				this.endDate = endDate;
			}

			public String getPremium() {
				return premium;
			}

			public void setPremium(String premium) {
				this.premium = premium;
			}

			public String getDiscountRate() {
				return discountRate;
			}

			public void setDiscountRate(String discountRate) {
				this.discountRate = discountRate;
			}

			public String getNfcPremium() {
				return nfcPremium;
			}

			public void setNfcPremium(String nfcPremium) {
				this.nfcPremium = nfcPremium;
			}

			public List<RiskKindsVo> getRiskKinds() {
				return riskKinds;
			}

			public void setRiskKinds(List<RiskKindsVo> riskKinds) {
				this.riskKinds = riskKinds;
			}

			public static class RiskKindsVo {

				private String riskCode;

				private String riskName;

				private String amount;

				private String notDeductible;

				private String premium;

				private String ncfPremium;

				public String getRiskCode() {
					return riskCode;
				}

				public void setRiskCode(String riskCode) {
					this.riskCode = riskCode;
				}

				public String getRiskName() {
					return riskName;
				}

				public void setRiskName(String riskName) {
					this.riskName = riskName;
				}

				public String getAmount() {
					return amount;
				}

				public void setAmount(String amount) {
					this.amount = amount;
				}

				public String getNotDeductible() {
					return notDeductible;
				}

				public void setNotDeductible(String notDeductible) {
					this.notDeductible = notDeductible;
				}

				public String getPremium() {
					return premium;
				}

				public void setPremium(String premium) {
					this.premium = premium;
				}

				public String getNcfPremium() {
					return ncfPremium;
				}

				public void setNcfPremium(String ncfPremium) {
					this.ncfPremium = ncfPremium;
				}

				@Override
				public String toString() {
					return "RiskKindsVo [riskCode=" + riskCode + ", riskName=" + riskName + ", amount=" + amount
							+ ", notDeductible=" + notDeductible + ", premium=" + premium + ", ncfPremium=" + ncfPremium
							+ "]";
				}
			}
		}

		public static class TaxInsureInfoVo {

			private String lateFee;

			private String taxFee;

			public String getLateFee() {
				return lateFee;
			}

			public void setLateFee(String lateFee) {
				this.lateFee = lateFee;
			}

			public String getTaxFee() {
				return taxFee;
			}

			public void setTaxFee(String taxFee) {
				this.taxFee = taxFee;
			}

			@Override
			public String toString() {
				return "TaxInsureInfoVo [lateFee=" + lateFee + ", taxFee=" + taxFee + "]";
			}
		}

		public static class EfcInsureInfoVo {

			private String startDate;

			private String endDate;

			private String amount;

			private String premium;

			private String discountRate;

			public String getStartDate() {
				return startDate;
			}

			public void setStartDate(String startDate) {
				this.startDate = startDate;
			}

			public String getEndDate() {
				return endDate;
			}

			public void setEndDate(String endDate) {
				this.endDate = endDate;
			}

			public String getAmount() {
				return amount;
			}

			public void setAmount(String amount) {
				this.amount = amount;
			}

			public String getPremium() {
				return premium;
			}

			public void setPremium(String premium) {
				this.premium = premium;
			}

			public String getDiscountRate() {
				return discountRate;
			}

			public void setDiscountRate(String discountRate) {
				this.discountRate = discountRate;
			}

			@Override
			public String toString() {
				return "EfcInsureInfoVo [startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount
						+ ", premium=" + premium + ", discountRate=" + discountRate + "]";
			}
		}
	}

	public static class CarOwnerVo {

		private String idcardNo;

		private String idcardType;

		private String name;

		private String gender;

		private String mobile;

		public String getIdcardNo() {
			return idcardNo;
		}

		public void setIdcardNo(String idcardNo) {
			this.idcardNo = idcardNo;
		}

		public String getIdcardType() {
			return idcardType;
		}

		public void setIdcardType(String idcardType) {
			this.idcardType = idcardType;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		@Override
		public String toString() {
			return "CarOwnerVo [idcardNo=" + idcardNo + ", idcardType=" + idcardType + ", name=" + name + ", gender="
					+ gender + ", mobile=" + mobile + "]";
		}
	}

	public static class CarInfoVo {

		private String vehicleName;

		private String carLicenseNo;

		private String carProperty;

		private String vinCode;

		private String engineNo;

		private String registDate;

		private String isTransferCar;

		private String price;

		public String getVehicleName() {
			return vehicleName;
		}

		public String getCarLicenseNo() {
			return carLicenseNo;
		}

		public String getCarProperty() {
			return carProperty;
		}

		public String getVinCode() {
			return vinCode;
		}

		public String getEngineNo() {
			return engineNo;
		}

		public String getRegistDate() {
			return registDate;
		}

		public String getIsTransferCar() {
			return isTransferCar;
		}

		public String getPrice() {
			return price;
		}

		public String getVehicleId() {
			return vehicleId;
		}

		public String getIsNew() {
			return isNew;
		}

		public String getProperty() {
			return property;
		}

		private String vehicleId;

		private String isNew;

		private String property;

		@Override
		public String toString() {
			return "CarInfoVo [vehicleName=" + vehicleName + ", carLicenseNo=" + carLicenseNo + ", carProperty="
					+ carProperty + ", vinCode=" + vinCode + ", engineNo=" + engineNo + ", registDate=" + registDate
					+ ", isTransferCar=" + isTransferCar + ", price=" + price + ", vehicleId=" + vehicleId + ", isNew="
					+ isNew + ", property=" + property + "]";
		}
	}
}
