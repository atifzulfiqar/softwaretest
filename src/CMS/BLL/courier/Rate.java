package CMS.BLL.courier;

import CMS.DBL.handler.*;

public class Rate {
	private String RID;
	private String zone;
	private double price;
	private String description;
	private String fid;
	
	public String getID() {
		return RID;
	}
	
	public String getFID() {
		return fid;
	}
	
	public void setFID(String f) {
		fid = f;
	}
	
	public String getDesc() {
		return description;
	}
	
	public void setAll(String Rid, String zone, double price, String description)
	{
		RID = Rid;
		this.zone=zone;
		this.price=price;
		this.description=description;
	}

	public static String generateID() {
		RetrievalHandler getLastId=new RetrievalHandler();
		String s=getLastId.getLastId("rate", "RID");
		
		String id;
		if(s!=null)
		{
			int lastID=Integer.parseInt(s);
			lastID++;
			String newID=(lastID+"");

			for(int i=newID.length();i<4;i++)
			{
				newID="0"+newID;
			}
			id=newID;

			
		}
		else
		{
			id="0001";	
		}
		return id;
	}

	public String insertValue() {
		// TODO Auto-generated method stub
		return "('"+RID+"','"+zone+"',"+price+",'"+description+"','"+fid+"')";
	}
	
	public String getZone() {
		return zone;
	}
	
	public double getPrice() {
		return price;
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
	//	UnRegisteredCourier r=new UnRegisteredCourier();
	//	r.setAll("Chandan", "PO Box kaloi","03323881213",date,512, "Regular", 98.0, null, "i141632", "i14027);
//		r.setAll("Chandan", "PO Box kaloi","03323881213",date,512, "Regular", 98.0, null, "i141632", "Omesh", "Tarani", 
		//		"PO Box Kaloi", "03323881213", "443023881213");
		//f.addCourier(r);	
		
		Rate r=new Rate();
		r.setAll("Islamabad", 1200, "Good place");;
		f.addRate(r);
	}

*/

	
}

