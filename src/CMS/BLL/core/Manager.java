package CMS.BLL.core;


import CMS.BLL.courier.Rate;
import CMS.BLL.franchise.Allowance;
import CMS.BLL.franchise.Franchise;
import CMS.DBL.handler.InsertionHandler;
import CMS.DBL.handler.RetrievalHandler;
import CMS.DBL.handler.UpdationHandler;
import CMS.UIL.model.AllowanceModel;
import CMS.UIL.model.CourierModel;
import CMS.UIL.model.CustomerModel;
import CMS.UIL.model.EmployeeModel;
import CMS.UIL.model.RateModel;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Manager extends Person {
	private String MID;
	private String qualification;
	private Date joiningDate;
	private double basicSalary;
	private String FID;
	private boolean status = true;
	
	public void setStatus(boolean s) {
		status = s;
	}
	
	public boolean getStatus() {
		 return status;
	}
	
	
	public String toString() {
		return super.toString()+","+MID+","+qualification+","+joiningDate+","+basicSalary+","+FID;
	}
	
	public String getMID() {
		return MID;
	}


	public String getQualification() {
		return qualification;
	}


	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date date) {
		joiningDate=date;
	}

	public double getBasicSalary() {
		return basicSalary;
	}


	public String getFID() {
		return FID;
	}
	
	public static String generateMID() {
		RetrievalHandler getLastId=new RetrievalHandler();
		String s= getLastId.getLastId("Manager", "MID");
		
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
			id=newID;	
		}
		else {
			id="M000000";	
		}
		return id;
	}


	public void setAll(String FName, String LName,String address,
		String contactNumber,String CNIC, String Date_Of_Birth, double basicSalary,
	   String Qualifaction, String fid)
	{
		RetrievalHandler getLastId=new RetrievalHandler();
		String s=getLastId.getLastId("Manager", "MID");
		
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
			MID=newID;

			
		}
		else
		{
			MID="M000000";	
		}
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
	

		super.setAll(FName, LName, address, contactNumber, CNIC, Date_Of_Birth);
		this.basicSalary=basicSalary;
		this.joiningDate=date;
		this.qualification=Qualifaction;
		//FID="1234567";
		FID = fid;
		
	}
	
	public void setAll(	String MID,String FName, String LName,String address,
						String contactNumber,String CNIC, String Date_Of_Birth, 
						double basicSalary, String Qualifaction, String fid) {
		
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
	
		this.MID=MID;
		super.setAll(FName, LName, address, contactNumber, CNIC, Date_Of_Birth);
		this.basicSalary=basicSalary;
		this.joiningDate=date;
		this.qualification=Qualifaction;
		// get the franchise id against the given frachise name
		FID = fid;
	}
	

	
	public String insertValue()
	{
		return "('"+MID+"', '"+super.insertValue()
				+"', '"+qualification+"', '"+joiningDate+"', "
				+basicSalary+",'"+FID+"',1)";
	}
	
	//Maintain Employee
	public void addEmployee(Employee newEmployee)
	{
		InsertionHandler handler = new InsertionHandler();
		// store the information of newEmployee
		handler.insert(newEmployee);
		// create a new account against the new employee
		Account acc = new Account();
		acc.setAll(newEmployee.getEID(), "1234567");
		handler.insert(acc);
	}
	
	public void updateEmployee(Employee EmployeeData)
	{
		Franchise f=new Franchise();
		f.updateEmployee(EmployeeData);
	}
	
	public void updateEmployee(EmployeeModel emp)
	{
		UpdationHandler update = new UpdationHandler();
		Employee employee = new Employee();
		employee.setAll(emp.id.get(), emp.fname.get(), emp.lname.get(), emp.address.get(), emp.contact.get(), emp.cnic.get(), 
						emp.dob.get(), emp.qal.get(), emp.designation.get(), Double.parseDouble(emp.salary.get()));
		
		update.update(employee);
	}	
	
	public boolean fireEmployee(String id) {
		 //update the status of  manager
		 UpdationHandler updateManager=new UpdationHandler();
		 
		 if((updateManager.fireEmployee(id))>0)
		 {
			// remove the account from account table			 
			if(updateManager.deleteAccount(id) )
			{
				return true;
			}
		 }
		 return false;		
	}
	
	public boolean addCustomer(Customer customer) {
		InsertionHandler handler = new InsertionHandler();
		return handler.insert(customer);
	}
	
	public ObservableList<CustomerModel> getCustomerList() {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getCustomerList();
	}
	
	public void updateCutomer(Customer customer) {
		UpdationHandler handler = new UpdationHandler();
		handler.update(customer);
	}
	
	public void deleteCustomer(CustomerModel customer) {
		// update the status of customer
		UpdationHandler handler = new UpdationHandler();
		Customer cus = new Customer();
		cus.initAll(customer.id.get(), customer.fname.get(), customer.lname.get(), customer.address.get(), customer.contact.get(),
					customer.cnic.get(), customer.organization.get());
		handler.update(cus, false);
	}
	
	//Maintain Account
	public void addAccount(Account account)
	{
		InsertionHandler handler = new InsertionHandler();
		handler.insert(account);
	}
	
	public boolean updateAccount(Account account) 
	{
		UpdationHandler update = new UpdationHandler();
		return update.update(account);
	}

	public boolean addAllowance(Allowance newAllowance) {
		InsertionHandler handler = new InsertionHandler();
		return handler.insert(newAllowance);
	}
	
	
	public boolean updateAllowance(Allowance allowanceData) {
		UpdationHandler handler = new UpdationHandler();
		handler.update(allowanceData);
		return true;
	}
	
	public boolean addRate(Rate rate) {
		InsertionHandler handler = new InsertionHandler();
		return handler.insert(rate);
	}

	public ObservableList<RateModel> retrieveRateList() {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.retrieveRateList(FID);
	}
	
	public boolean updateRate(Rate rate) {
		UpdationHandler handler = new UpdationHandler();
		return handler.update(rate);
	}
	
	public ObservableList<EmployeeModel> getRegEmployeeList(String fid) {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getRegEmployeeList(fid);
	}
	
	public ObservableList<AllowanceModel> getAllowanceList() {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getAllowanceList();
	}
	
	public ArrayList<String> getList(String TableName, String ColNum) {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.toArrayList(handler.getList(TableName, ColNum));	
	}
	
	public EmployeeModel getEmployeeInfo(String eid) {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getEmployeeInfo(eid);
	}
	
	public ObservableList<CourierModel> getCourierList(StringBuilder p,String fid) {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getCourierList(p, fid);
	}
}
