package CMS.BLL.franchise;

import CMS.DBL.handler.*;
import CMS.BLL.core.Account;
import CMS.BLL.core.Customer;
import CMS.BLL.core.Employee;
import CMS.BLL.core.Manager;
import CMS.BLL.courier.Rate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Franchise {
	private String FID;
	private String name;
	private String description;
	private String loc;
	private String contactNumber;
	private Date createdOn;
	private boolean status = true;
	private ArrayList<String> allocated_allowances = new ArrayList<String>();
	
	public void setAllowances(ArrayList<String> list) {
		allocated_allowances = list;
	}
	
	public ArrayList<String> getAllowances() {
		return allocated_allowances;
	}
	
	public void setStatus(boolean s) {
		status = s;
	}
	
	public boolean getStatus() {
		return status;
	}
		
	public String getFID() {
		return FID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCreatedOn(Date date)
	{
		createdOn=date;
	}
	
	public void setAll(String id, String name, String desc, String loc, String con, Date cre) {
		FID = id;
		this.name = name;
		description = desc;
		this.loc = loc;
		contactNumber = con;
		createdOn = cre;
	}
	
	public static String generateID() {
		
		RetrievalHandler getLastId=new RetrievalHandler();
		
		String s = getLastId.getLastId("Franchise", "FID");		
		
		String newID = null;
		if(s!=null)
		{
			int lastID=Integer.parseInt(s.substring(1));
			lastID++;
			
			newID = (lastID+"");
			for(int i=newID.length();i<6;i++)
				newID = "0"+newID;		
			
			newID = 'F'+newID;
		}
		else
			newID = "F000001";	
		
		
		return newID;
	}

	
	public void setAll(String id, String name,String description,String loc, String contactNumber)
	{
		FID = id;
		this.name=name;
		this.description=description;
		this.loc=loc;
		this.contactNumber=contactNumber;
		
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		this.createdOn=date;
	}
	
	public String insertValue() {
		if (status)
			return "('"+FID+"','"+name+"','"+description+"','"+loc+"','"+contactNumber+"','"+createdOn+"',1)";
		else 
			return "('"+FID+"','"+name+"','"+description+"','"+loc+"','"+contactNumber+"','"+createdOn+"',0)";			
	}
	
	public String toString() {
		return FID+","+name+","+description+","+loc+","+contactNumber+","+createdOn;
	}

//	public void addFranchise()
//	{
//		InsertionHandler insertFranchise=new InsertionHandler();
//		insertFranchise.insert(this);
//	}

	///****Employee Related Functions	
	public void addEmployee(Employee e) {
		InsertionHandler employeeInsert=new InsertionHandler();
		employeeInsert.insert(e);


	}

	public void updateEmployee(Employee employeeData) {
		UpdationHandler updateEmployee=new UpdationHandler();
		updateEmployee.update(employeeData);

	}

	public void addAccount(Account account) {
		InsertionHandler insertEmployeeAccount=new InsertionHandler();
		insertEmployeeAccount.insert(account);
	}



	public void addManager(Manager m) {
		InsertionHandler managerInsert=new InsertionHandler();
		managerInsert.insert(m);


	}

	public void assignFranchise() {
		// TODO Auto-generated method stub

	}

	public void deleteAccount() {
		// TODO Auto-generated method stub

	}

	public void fireEmployee() {
		// TODO Auto-generated method stub

	}

	public void getAccountDetail() {
		// TODO Auto-generated method stub

	}

	public void getEmployeeData() {
		// TODO Auto-generated method stub

	}

	public void promoteEmployee() {
		// TODO Auto-generated method stub

	}

	public void removeFranchiseFromList() {
		// TODO Auto-generated method stub

	}

	public void updateAccount() {
		// TODO Auto-generated method stub

	}

	public void addCustomer(Customer newCustomer) {
		InsertionHandler insertCustomer=new InsertionHandler();
		insertCustomer.insert(newCustomer);
		
	}

	/*public void addCourier(RegisteredCourier newCourier)
	{
		InsertionHandler insertCourier=new InsertionHandler();
		insertCourier.insert(newCourier);
	}

	public void addCourier(UnRegisteredCourier newCourier    ) {
		// TODO Auto-generated method stub
		InsertionHandler insertCourier=new InsertionHandler();
		insertCourier.insert(newCourier);
	}*/

	public void addRate(Rate newRate) {
		// TODO Auto-generated method stub
		InsertionHandler insertCourier=new InsertionHandler();
		insertCourier.insert(newRate);
	}

	public void addAllowance(Allowance newAllowance) {
		InsertionHandler insertCourier=new InsertionHandler();
		insertCourier.insert(newAllowance);
		
	}

	public void updateAllowance(Allowance allowanceData) {
		UpdationHandler updateAllowance=new UpdationHandler();
		updateAllowance.update(allowanceData);
		
		
	}

	/*public static void main(String [] args)
	{
		Franchise f=new Franchise();
		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// get a java date (java.util.Date) from the Calendar instance.
		// this java date will represent the current date, or "now".
		java.util.Date currentDate = calendar.getTime();

		// now, create a java.sql.Date from the java.util.Date
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		f.setAll("Chandan", "He is a good boy",    "Lives in heart", "0332381213", date);
	//	Employee m=new Employee();
	//	m.setAll("Chandan", "Kumar", "POBOX Kaloi", "03323881213", "98",date,89,date,"BA","Chandan");
		//f.addManager(m);
		//f.updateEmployee(m);
		f.addFranchise();
	}*/




}
