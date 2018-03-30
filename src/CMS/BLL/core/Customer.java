package CMS.BLL.core;

import java.util.Calendar;

import CMS.DBL.handler.RetrievalHandler;

public class Customer extends Person{
	
	private String CID;
	private String organization;
	private String DOJ;
	private boolean status = true;
	
	public String getJoiningDate() {
		return DOJ;
	}
	
	public void setJoiningDate(String s) {
		DOJ = s;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean s) {
		status = s;
	}
	
	public String getCID() {
		return CID;
	}

	public String getOrganization() {
		return organization;
	}

	public static String generateID() {
		RetrievalHandler getLastId = new RetrievalHandler();
		String s = getLastId.getLastId("Customer", "CID");
		
		String id;
		if(s!=null)
		{
			int lastID=Integer.parseInt(s.substring(1));
			lastID++;
			String newID=(lastID+"");
			for(int i=newID.length();i<6;i++)
			{
				newID="0"+newID;
			}
			newID=s.charAt(0)+newID;
			id = newID;
		}
		else
			id = "C000001";	

		return id;
	}

	public void setAll(	String FName, String LName,String address, String contactNumber,String CNIC, String organization)
	{	
		RetrievalHandler getLastId = new RetrievalHandler();
		String s = getLastId.getLastId("Customer", "CID");
		
		if(s!=null)
		{
			int lastID=Integer.parseInt(s.substring(1));
			lastID++;
			String newID=(lastID+"");
			for(int i=newID.length();i<6;i++)
			{
				newID="0"+newID;
			}
			newID=s.charAt(0)+newID;
			CID=newID;
		}
		else
			CID="C000001";
		
		super.setAll(FName, LName, address, contactNumber, CNIC, null);
		this.organization=organization;
	}

	public void setAll(	String CID, String FName, String LName,String address, 
						String contactNumber,String CNIC, String organization)
	{
		this.CID=CID;
		super.setAll(FName, LName, address, contactNumber, CNIC, null);
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		DOJ = date.toString();
		this.organization=organization;
	}	
	
	public void initAll(String id, String fn, String ln, String add, String con, String cn, String org) {
		CID = id;
		super.setAll(fn, ln, add, con, cn, null);
		organization = org;
	}

	public String insertValue()
	{
		return "('"+CID+"', '"+ getFName()+"', '"+getLName()+"', '"+getAddress()+"', '"+getContactNumber()+"', '"+getCNIC()+"', '"+DOJ+"', '"+organization+"',1)";
	}
	
	public String toString()
	{
		return CID+","+super.insertValue()+","+organization;
	}

}
