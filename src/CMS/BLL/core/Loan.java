package CMS.BLL.core;

import java.util.Calendar;

import CMS.DBL.handler.RetrievalHandler;

public class Loan {
	private String LID;
	private double amount;
	private String issueDate;
	private String description;

	public void getNewID() {
		RetrievalHandler getLastId=new RetrievalHandler();
		String s = getLastId.getLastId("Loan", "LID");
		if(s!=null)
		{
			int lastID=Integer.parseInt(s.substring(1));
			lastID++;
			String newID=(lastID+"");
			for(int i=newID.length();i<6;i++)
				newID = "0" + newID;
			
			newID = s.charAt(0)+newID;
			LID = newID;			
		}
		else
			LID="L000001";
	}	
	
	public String getLID() {
		return LID;
	}

	public void setLID(String lID) {
		LID = lID;
	}

	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAll(String lid, double n, String desc) {
		LID = lid;
		amount = n;
		description = desc;
		
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		
		issueDate = date.toString();
	}
	
	
	public String insertValue() {
		return "('"+LID+"',"+amount+",'" + issueDate + "','" + description + "')";
	}
}
