package CMS.BLL.courier;

import java.util.Date;

public abstract class Courier {
	private String RName;
	private String RAddress;
	private String RContactNumber;
	private Date RegDateTime ;
	private double price;
	private String type;
	private double weight;
	private String status;
	private String RegisterBy;

	
	public void setAll(	String RName,String RAddress,String RContactNumber,
						double price,String type, double weight, String RegisterBy)
	{
		this.price=price;
		this.RName=RName;
		this.RAddress=RAddress;
		this.RContactNumber=RContactNumber;
		this.RegDateTime=new Date();
		this.status="Pending";
		this.type=type;
		this.weight=weight;
		this.RegisterBy=RegisterBy;
	}


	public void setStatus(String s) {
		status = s;
	}
	
	public String getRName() {
		return RName;
	}



	public String getRAddress() {
		return RAddress;
	}



	public String getRContactNumber() {
		return RContactNumber;
	}



	public Date getRegDateTime() {
		return RegDateTime;
	}



	public double getPrice() {
		return price;
	}



	public String getType() {
		return type;
	}



	public double getWeight() {
		return weight;
	}



	public String getStatus() {
		return status;
	}



	public String getRegisterBy() {
		return RegisterBy;
	}



	public String insertValue() {
		// TODO Auto-generated method stub
		return "'"+RName+"','"+RAddress+"','"+RContactNumber+"','"+RegDateTime+"',"+price+",'"+type
				+ "', "+weight+",'"+status+"','"+ RegisterBy+"'";
	}
	public String toString() {
		// TODO Auto-generated method stub
		return RName+","+RAddress+","+RContactNumber+","+RegDateTime+","+price+","+type
				+ ", "+weight+","+status+","+ RegisterBy;
	}
	
}
