package CMS.BLL.core;


import CMS.DBL.handler.RetrievalHandler;

public class Account {
	private String userID;
	private String password;
	
	public String getPassword()
	{
		return password;
	}

	public String getUserID()
	{
		return userID;
	}

	public void setAll(String userID,String password)
	{
		this.userID=userID;
		this.password=password;
		
	}
	
	public String insertValues()
	{
		return "('"+userID+"','"   +password+"')";
	}

	public Object validateAccount(String ID, String Password)
	{
		RetrievalHandler validate=new  RetrievalHandler();
		return validate.validateAccount(ID, Password);
				
	}
	
	public String toString()
	{
		return userID+","+password;
	}

	// public static void main(String [] args)
	// {
	// 	Account a=new Account();
	// 	Employee a2=(Employee)a.validateAccount("E000001", "affan");
	// 	System.out.println(a2);
	// 	System.out.println(a2.getFName()+" "+a2.getLName()+" "+a2.getAddress()+" "+a2.getCNIC());
	
	// }
	

}
