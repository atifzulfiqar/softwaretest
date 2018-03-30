package CMS.DBL.handler;

import CMS.DBL.dbConnector.*;

import java.util.ArrayList;

import CMS.BLL.core.*;
import CMS.BLL.franchise.*;
import CMS.BLL.courier.*;

public class UpdationHandler {
	String query;
	DBConnection connection;
	public UpdationHandler()
	{
		query="";
		connection=new DBConnection();
	}
	
	public void update(Employee employeeData)
	{
		query = "UPDATE EMPLOYEE SET "
				+ "FName='"+employeeData.getFName()
				+"', LName='"+employeeData.getLName()
				+"', address='"+employeeData.getAddress()
				+"', contactNumber='"+employeeData.getContactNumber()
				+"', CNIC='"+employeeData.getCNIC()
				+"', Qualification='"+employeeData.getQualification()
				+"', designation='"+employeeData.getDesignation()
				+"', basicSalary="+employeeData.getBasicSalary()
				+" where EID='"+employeeData.getEID()+"'";
		
		connection.executeUpdate(query);
	}
	public void update(Manager managerData)
	{
		query="UPDATE Manager SET FName='"+managerData.getFName()+"', LName='"+managerData.getLName()
				+"', address='"+managerData.getAddress()+"', contactNumber='"+managerData.getContactNumber()
				+"', CNIC='"+managerData.getCNIC()+"', Qualification='"+managerData.getQualification()
				+"', basicSalary="+managerData.getBasicSalary()+" where MID='"+managerData.getMID()+"'";
		
		System.out.println(query);
		connection.executeUpdate(query);
	}
	public void update(Customer customerData)
	{
		query="UPDATE Customer SET FName='"+customerData.getFName()+"', LName='"+customerData.getLName()
				+"', address='"+customerData.getAddress()+"', contactNumber='"+customerData.getContactNumber()
				+"', CNIC='"+customerData.getCNIC()+"' where CID='"+customerData.getCID()+"'";
		
		System.out.println(query);
		connection.executeUpdate(query);
	}
	
	public void update(Customer customerData, boolean status)
	{
		query="UPDATE Customer SET stauts = " + (status?1:0) +" where CID='"+customerData.getCID()+"'";
		
		System.out.println(query);
		connection.executeUpdate(query);
	}	
	
	public boolean update(Account accountData)
	{
		try {
			query = "UPDATE Account SET password='"+accountData.getPassword()+" ' where userId="+accountData.getUserID();
		
			System.out.println(query);
			connection.executeUpdate(query);
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}

	public void update(Allowance allowanceData)
	{
		query="UPDATE Allowance SET description='"   +allowanceData.getDescription()+"', type='"+allowanceData.getType()
		+"',amount="+allowanceData.getAmount()+" where AllID="+allowanceData.getAllID();
		
		System.out.println(query);
		connection.executeUpdate(query);
	}
	
	public void update(RegisteredCourier courierData)
	{
		query = "update status = '" + courierData.getStatus() + "' where CID = '" + courierData.getCID() + "'";
		
		System.out.println(query);
		connection.executeUpdate(query);
	}
	
	public void update(UnRegisteredCourier courierData)
	{
		query = "update status = '" + courierData.getStatus() + "' where CID = '" + courierData.getCID() + "'";
		System.out.println(query);
		connection.executeUpdate(query);
	}	
	
	public void cancelCourier(String courierID) {
		query = "update status = 'Cancelled' where CID = '" + courierID + "'";
		System.out.println(query);
		connection.executeUpdate(query);		
	}
	
	public boolean updateFranchise(String fid, boolean status) {
		query = "update franchise set status = " + (status?1:0) + " where FID = '" + fid + "'";
		if (connection.executeUpdate(query) >= 1) 
			return true;
		else
			return false;
	}
	
	public boolean update(Rate rate) {
		query = "update Rate set price = " + rate.getPrice() + ", description = '" + rate.getDesc() +"', zone = '"
				+ rate.getZone() +"' where RID = '" + rate.getID() + "'";
		if (connection.executeUpdate(query) >= 1)
			return true;
		else
			return false;
	}
	
	public int fireManager(String MID)
	{
		query="update Manager Set status=0 where mid='"+MID+"'";
		return connection.executeUpdate(query);
		
	}
	
	public int fireEmployee(String ID)
	{
		query="update employee Set status=0 where eid='"+ID+"'";
		return connection.executeUpdate(query);
	}
	
	public boolean deleteAccount(String id)
	{
		query = "Delete from Account where userid='"+id+"'";
		if (connection.executeUpdate(query) >= 1)
			return true;
		else 
			return false;
	}
	
	public boolean updateAllStatus(ArrayList<String> list)
	{
		ArrayList<String> regList=new ArrayList<String>();
		ArrayList<String> UNregList=new ArrayList<String>();
		
		boolean foundRegRecord=false,foundUnRegRecord=false;
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).charAt(0)=='R')
			{
				foundRegRecord=true;
				regList.add(list.get(i));
			}
			else
			{
				foundUnRegRecord=true;
				UNregList.add(list.get(i));
			}
		}

		query="update regcourier Set status='Delivered' where cid in (";
		
		for(int i=0;i<regList.size();i++)
		{
			query+="'"+regList.get(i)+"'";
			if(i!=regList.size()-1)
			{
				query+=",";
			}
		}
		query+=")";
		if(foundRegRecord)
		{
			foundRegRecord=(connection.executeUpdate(query)>0);
		}
		else
		{
			foundRegRecord=true;
		}
		query="update unregcourier Set status='Delivered' where cid in (";
		
		for(int i=0;i<UNregList.size();i++)
		{
			query+="'"+UNregList.get(i)+"'";
			if(i!=UNregList.size()-1)
			{
				query+=",";
			}
		}
		query+=")";
		if(foundUnRegRecord)
		{
			foundUnRegRecord= (connection.executeUpdate(query)>0);
		}
		else
		{
			foundUnRegRecord=true;
		}
				
		return foundUnRegRecord  && foundRegRecord ;		
	}

}
