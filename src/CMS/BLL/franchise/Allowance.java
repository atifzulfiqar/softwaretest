package CMS.BLL.franchise;

import CMS.DBL.handler.*;

public class Allowance {
	private String AllID;
	private String description;
	private String type;
	private double amount;
	
	
	public String getAllID() {
		return AllID;
	}
	public String getDescription() {
		return description;
	}
	public String getType() {
		return type;
	}
	public double getAmount() {
		return amount;
	}

	public static String generateID() {
		RetrievalHandler getLastId=new RetrievalHandler();
		String s=getLastId.getLastId("Allowance", "AllID");
		
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

	public void setAll(String description,String type,double amount)
	{
		RetrievalHandler getLastId=new RetrievalHandler();
		String s=getLastId.getLastId("Allowance", "AllID");
		
		if(s!=null)
		{
			int lastID=Integer.parseInt(s);
			lastID++;
			String newID=(lastID+"");

			for(int i=newID.length();i<4;i++)
			{
				newID="0"+newID;
			}
			AllID=newID;

			
		}
		else
		{
			AllID="0000";	
		}

		this.description=description;
		this.type=type;
		this.amount=amount;
	}
	public void setAll(String AllID, String description, String type, double amount)
	{
		this.AllID=AllID;
		this.description=description;
		this.type=type;
		this.amount=amount;
	}
	
	public String insertValue() {
		
		return "('"+AllID+"','"+description+"','"+type+"',"+amount+")";
	}
	
	public String toString() {
		
		return AllID+","+description+","+type+       ","+amount;
	}

/*	public static void main(String [] args)
	{
		
		
		Manager m=new Manager();
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
		
		Allowance r=new Allowance();
		r.setAll("0004","health", "medical", 10000);
		//m.addAllowance(r);
		m.updateAllowance(r);
	}*/

}
