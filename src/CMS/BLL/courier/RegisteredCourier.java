package CMS.BLL.courier;

import CMS.DBL.handler.*;

public class RegisteredCourier extends Courier {
	private String CID;						//courier ID
	private String customerID;
	private String regDate;

	public String getRegDate() {
		return regDate;
	}
	
	
	public String getCID() {
		return CID;
	}
	public String getCustomerID() {
		return customerID;
	}

	public static String generateID() {
		RetrievalHandler getLastId=new RetrievalHandler();
		String s=getLastId.getLastId("RegCourier", "CID");
		
		String id;
		if(s!=null)
		{
			int lastID=Integer.parseInt(s.substring(1));
			lastID++;
			String newID=(lastID+"");
			for(int i=newID.length();i<14;i++)
			{
				newID="0"+newID;
			}
			newID=s.charAt(0)+newID;
			id=newID;

			
		}
		else
		{
			id="R00000000000000";	
		}
		return id;
	}
	
	public void setAll(String cid, String RName, String RAddress, String RContactNumber, String type, Double weight, Double price, 
					   String RegisterBy, String customerID) {
		super.setAll(RName, RAddress, RContactNumber, price, type, weight, RegisterBy);
		CID = cid;
		this.customerID=customerID;		
	}

	public void setAll(String RName,String RAddress,String RContactNumber,
			double price,String type, double weight,String status,String RegisterBy,String customerID)
	{
		RetrievalHandler getLastId=new RetrievalHandler();
		String s=getLastId.getLastId("RegCourier", "CID");
		
		if(s!=null)
		{
			int lastID=Integer.parseInt(s.substring(1));
			lastID++;
			String newID=(lastID+"");
			for(int i=newID.length();i<14;i++)
			{
				newID="0"+newID;
			}
			newID=s.charAt(0)+newID;
			CID=newID;

			
		}
		else
			CID="R00000000000001";	

		
		super.setAll(RName, RAddress, RContactNumber, price, type, weight, RegisterBy);
		this.customerID=customerID;
	}

	public void setAll(	String CID,String RName,String RAddress,String RContactNumber, String rd, Double price, String type,
						double weight, String status, String RegisterBy, String customerID)
	{
		super.setAll(RName, RAddress, RContactNumber, price, type, weight, RegisterBy);
		this.CID=CID;
		regDate = rd;
		super.setStatus(status);
		this.customerID=customerID;
	}
	
	public String insertValue() {
		return "('"+CID+"',"+super.insertValue()+",'"+customerID+"')";
	}
}
