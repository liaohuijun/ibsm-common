package util.test.bean;

import util.test.bean.OfferCallBackVo.CarOwnerVo;

/**
 * @author shishun.wang
 * @date 2016年12月20日 下午4:25:31
 * @version 1.0
 * @describe
 */
public class OfferUserVo extends CarOwnerVo {

	private String id;

	private String carLicenseNo;

	private String vehicleName;

	private String carProperty;

	private String vinCode;

	private String engineNo;

	private String registDate;

	private String isTransferCar;

	private String price;

	private String vehicleId;

	private String isNew;

	private String property;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarLicenseNo() {
		return carLicenseNo;
	}

	public void setCarLicenseNo(String carLicenseNo) {
		this.carLicenseNo = carLicenseNo;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getCarProperty() {
		return carProperty;
	}

	public void setCarProperty(String carProperty) {
		this.carProperty = carProperty;
	}

	public String getVinCode() {
		return vinCode;
	}

	public void setVinCode(String vinCode) {
		this.vinCode = vinCode;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public String getIsTransferCar() {
		return isTransferCar;
	}

	public void setIsTransferCar(String isTransferCar) {
		this.isTransferCar = isTransferCar;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return "OfferUserVo [id=" + id + ", carLicenseNo=" + carLicenseNo + ", vehicleName=" + vehicleName
				+ ", carProperty=" + carProperty + ", vinCode=" + vinCode + ", engineNo=" + engineNo + ", registDate="
				+ registDate + ", isTransferCar=" + isTransferCar + ", price=" + price + ", vehicleId=" + vehicleId
				+ ", isNew=" + isNew + ", property=" + property + "]";
	}

}
