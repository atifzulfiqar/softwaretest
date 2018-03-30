package CMS.BLL.courier;

import java.sql.Date;

public class Shipment {
	private String SID;
	private String CID;
	private String vehicleNumber;
	private Date DeliveredOn;
	private String DeliveredBy;
	
	public String getCID() {
		return CID;
	}
	
	public void setCID(String cid) {
		CID = cid;
	}
	
	public String getSID() {
		return SID;
	}
	
	public void setSID(String sID) {
		SID = sID;
	}
	
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	
	public void setVehicleNumber(String vn) {
		vehicleNumber = vn;
	}
	
	public Date getDeliveredOn() {
		return DeliveredOn;
	}
	
	public void setDeliveredOn(Date deliveredOn) {
		DeliveredOn = deliveredOn;
	}
	
	public String getDeliveredBy() {
		return DeliveredBy;
	}
	
	public void setDeliveredBy(String deliveredBy) {
		DeliveredBy = deliveredBy;
	}
	
}
