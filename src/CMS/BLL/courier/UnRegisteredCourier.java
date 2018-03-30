package CMS.BLL.courier;

import java.util.Calendar;
import java.util.Date;

import CMS.DBL.handler.*;

public class UnRegisteredCourier extends Courier {
	private String CID;//courier ID
	private String SFName;
	private String SLName;
	private String Saddress;
	private String ScontactNumber;
	private String SCNIC;
	private String regDate;
	
	public String getRegDate() {
		return regDate;
	}
	
	public void setRegDate() {
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		regDate = date.toString();
	}
	
	
	public String getCID()  {
		return CID;
	}
	
	public String getSender() {
		return SFName + " " + SLName;
	}
	
	public static String generateID() {
		RetrievalHandler getLastId = new RetrievalHandler();
		String s = getLastId.getLastId("UnRegCourier", "CID");
		
		String id;
		if(s != null) {

			int lastID = Integer.parseInt(s.substring(1));
			lastID++;
			String newID=(lastID + "");
			for(int i=newID.length();i<14;i++)
				newID = "0" + newID;

			newID = s.charAt(0) + newID;
			id = newID;
		}
		else
			id = "U00000000000001";

		return id;
	}
	
	public void setAll(	String RName,String RAddress,String RContactNumber,Date RDateTime, 
						double price,String type, double weight,String status,String RegisterBy,
						String SFName, String SLName,String Saddress,String ScontactNumber,String SCNIC) 
	{

		RetrievalHandler getLastId = new RetrievalHandler();
		String s = getLastId.getLastId("RegCourier", "CID");
		
		if(s != null) {

			int lastID = Integer.parseInt(s.substring(1));
			lastID++;
			String newID=(lastID + "");
			for(int i=newID.length();i<14;i++)
				newID = "0" + newID;

			newID = s.charAt(0) + newID;
			CID = newID;
		}
		else
			CID="U00000000000001";

		super.setAll(RName, RAddress, RContactNumber, price, type, weight, RegisterBy);
		this.SFName=SFName;
		this.SLName=SLName;
		this.Saddress=Saddress;
		this.ScontactNumber=ScontactNumber;
		this.SCNIC=SCNIC;
	}
	
	public void setAll(	String CID, String RName, String RAddress, String RContactNumber, 
						String sfname, String slname, String saddr, String scontact, String scnic, Double price,
						String type, Double weight, String RegisterBy) {
		
		super.setAll(RName, RAddress, RContactNumber , price, type, weight, RegisterBy);
		this.CID=CID;
		this.SFName=sfname;
		this.SLName=slname;
		this.Saddress=saddr;
		this.ScontactNumber=scontact;
		this.SCNIC=scnic;
	}

	public void setAll(	String CID,String RName,String RAddress,String RContactNumber, String rd,
						double price,String type, double weight, String status, String RegisterBy, 
						String sfname, String slname, String saddr, String scontact, String scnic
					  )
	{
		super.setAll(RName, RAddress, RContactNumber , price, type, weight, RegisterBy);
		this.CID=CID;
		this.SFName=sfname;
		this.SLName=slname;
		this.Saddress=saddr;
		this.ScontactNumber=scontact;
		regDate = rd;
		this.SCNIC=scnic;
		super.setStatus(status);
	}
	
	public String insertValue() {
		return "('"+CID+"',"+super.insertValue()+",'"+SFName+"','"+SLName+"','"+Saddress+"','"+ScontactNumber+"','"
				+SCNIC+"')";
	}
	
/*	public static void main(String [] args)
	{
		
		
		Franchise f=new Franchise();
		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// get a java date (java.util.Date) from the Calendar instance.
		// this java date will represent the current date, or "now".
		java.util.Date currentDate = calendar.getTime();

		// now, create a java.sql.Date from the java.util.Date
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
//		f.setAll("Chandan", "He is a good boy",    "Lives in heart", "0332381213", date);
	//	Employee m=new Employee();
	//	m.setAll("Chandan", "Kumar", "POBOX Kaloi", "03323881213", "98",date,89,date,"BA","Chandan");
		//f.addManager(m);
		//f.updateEmployee(m);
	//	f.addFranchise();
		UnRegisteredCourier r=new UnRegisteredCourier();
	//	r.setAll("Chandan", "PO Box kaloi","03323881213",date,512, "Regular", 98.0, null, "i141632", "i14027);
		r.setAll("Chandan", "PO Box kaloi","03323881213",date,512, "Regular", 98.0, null, "i141632", "Omesh", "Tarani", 
				"PO Box Kaloi", "03323881213", "443023881213");
		f.addCourier(r);	
	}*/

}
