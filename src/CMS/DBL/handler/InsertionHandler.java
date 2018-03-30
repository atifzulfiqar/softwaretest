package CMS.DBL.handler;

import CMS.DBL.dbConnector.*;
import ErrorHandler.UIError;

import java.util.ArrayList;
import java.util.Calendar;

import CMS.BLL.core.*;
import CMS.BLL.franchise.*;
import CMS.BLL.courier.*;

public class InsertionHandler {
	String query;
	DBConnection connection;
	
	public InsertionHandler() {
		query = "";
		connection = new DBConnection();
	}
	
	public boolean execute(String query) {
		System.out.println("Add query : " + query);		
		 int result = connection.executeUpdate(query);
		 if (result > 0) {
			 return true;
		 }
		 else {
			 return false;
		 }
		//return true;		
	}
	
	public boolean insert(Employee newEmployee) {

		query = "insert into Employee (EID ,FName, LName, address, contactNumber, CNIC, "
				 +"Date_Of_Birth, Designation, Qualification, Joining_Date, basicSalary, FID, status)" 
				 +" values" + newEmployee.insertValue();
				 
		return execute(query);
	}
	
	public boolean insert(Manager newManager) {

		query = "insert into Manager (MID ,FName, LName, address, contactNumber, CNIC, "
				+ "Date_Of_Birth, Qualification, Joining_Date, basicSalary, FID, status)" +
				" values" + newManager.insertValue();
		
		return execute(query);
	}
	
	public boolean insert(Customer newCustomer)
	{

		query = "insert into Customer(CID,FName, LName, address, contactNumber, CNIC, "
				+ "Date_Of_Joining, ORGANIZATION, Status) values"+newCustomer.insertValue();
				
		return execute(query);
	}
	
	public boolean insert(Franchise newFranchise, ArrayList<String> ids)
	{
		query = "insert into Franchise(FID, Name, description,"
				+ "loc, contactNumber, createdOn, status) values"+newFranchise.insertValue();
		
		if (execute(query)) {
			return insert(newFranchise.getFID(), ids);
		}
		else {
			UIError.showError("Franchise Did not add successfully!", null);
			return false;
		}
	}
	
	public boolean insert(String fid, ArrayList<String> ids) {
		for (int i=0; i<ids.size(); i++) {
			query = "insert into allocated_allowances values ('" + fid + "','" + ids.get(i) + "')";
			execute(query);
		}
		return true;
	}
	
	public boolean insert(Account newAccount)
	{
		query = "insert into Account(userID, password) values " +newAccount.insertValues();
		
		return execute(query);
	}
	
	public boolean insert(RegisteredCourier newCourier)
	{
		query = "insert into RegCourier(CID, RName, Raddress,RContactNumber,"
				+ "RegDateTime, Price, type, weight, status, RegisterBy, "
				+ "CustomerID) values"+newCourier.insertValue();
		
		return execute(query);
	}
	
	public boolean insert(UnRegisteredCourier newCourier)
	{
		query = "insert into UnRegCourier(CID, RName, Raddress,RContactNumber,"
				+ "RegDateTime, Price, type, weight, status, RegisterBy, "
				+ "SFName, SLName, Saddress,SContactNumber, SCNIC) values "+newCourier.insertValue();
	
		return execute(query);
	}
	
	public boolean makeShipment(String empid, String vehicle, ArrayList<String> list) {
		return true;
	}
	
	public boolean insert(Rate newRate)
	{
		query = "insert into Rate(RID, zone, price, description, FID) values" + newRate.insertValue();
		return execute(query);
	}

	public boolean insert(Allowance newAllowance)
	{
		query="insert into Allowance(AllID, description, type, amount) values" + newAllowance.insertValue();
				
		return execute(query);
	}
	
	public boolean insert(Loan newLoan)
	{
		query="insert into Loan(LID, amount, issueDate, description) values" + newLoan.insertValue();
		
		return execute(query);
	}
	
	public boolean insert(Shipment newShipment)
	{
		query="insert into Shipment(SID, deliveredBy, deliveredOn) values" + "";

		return execute(query);
	}
	
	public boolean InsertShipment(String empid, String vehicle, ArrayList<String> list) {
		query="Insert into shipment values ";		
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());

		for(int i=0;i<list.size();i++)
		{
			query+="(null,'"+list.get(i)+"','"+empid+"','"+date+"','"+vehicle+"')";
			if(i!=list.size()-1)
			{
				query+=',';
			}
		}
		return execute(query);		
	}
	
	
}
