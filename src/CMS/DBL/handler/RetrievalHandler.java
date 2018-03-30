package CMS.DBL.handler;

import CMS.DBL.dbConnector.*;

import CMS.UIL.model.*;

import CMS.BLL.core.*;
import CMS.BLL.franchise.*;
import CMS.BLL.courier.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RetrievalHandler {
	String query;
	DBConnection connection;
	
	public RetrievalHandler() {
		query = "";
		connection = new DBConnection();
	}
	
	public String getLastId(String tableName,String primaryKey) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			query = "SELECT " + primaryKey + " From " + tableName + " ORDER BY "+ primaryKey + " DESC LIMIT 1";
			
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				String temp = rs.getString(1);
				rs.close();
				stmt.close();
				return temp;
			}
			else {
				rs.close();
				stmt.close();
				return null;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	public Object validateAccount(String ID, String Password)
	{	
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			query = "select * from account where userID='"+ID+"' and password='"+Password+"'";
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			
			if(!rs.next()) {
				rs.close();
				stmt.close();
				return null;
			}
			// close the previous statement
			stmt.close();
			rs.close();
			
			if((ID.charAt(0))=='A') {
				query = "select * from ADMIN where AID='"+ID+"'";
				stmt = connection.getConnection().createStatement();
				rs = stmt.executeQuery(query);

				if (rs.next()) {
					Admin admin=new Admin();
					admin.setAll(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4), 
								rs.getString(5),
								rs.getString(6),
								rs.getDate(7).toString(),
								rs.getDouble(8));
										
					rs.close();
					stmt.close();					
					return (Object)admin;
				}
			}
			
			else if((ID.charAt(0))=='E')
			{
				query = "select * from Employee where EID='"+ID+"'";

				stmt = connection.getConnection().createStatement();
				rs = stmt.executeQuery(query);
			
				if (rs.next()) {
					Employee employee=new Employee();
					employee.setAll(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4), 
									rs.getString(5),rs.getString(6), rs.getDate(7).toString(), rs.getString(9), 
									rs.getString(8),rs.getDouble(11));
						
					employee.setJoiningDate(rs.getDate(10));
					employee.setFID(rs.getString(12));
					
					rs.close();
					stmt.close();
					return (Object)employee;
				}
			}
	
			else if((ID.charAt(0))=='M')
			{
				query = "select * from Manager where MID='"+ID+"'";

				stmt = connection.getConnection().createStatement();
				rs = stmt.executeQuery(query);
				
				if (rs.next()) {
					Manager manager=new Manager();
					
//					String MID,String FName, String LName,String address,
//					String contactNumber,String CNIC, String Date_Of_Birth, 
//					double basicSalary, String Qualification, String fid)					
					manager.setAll(	rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4), 
									rs.getString(5),rs.getString(6), rs.getDate(7).toString(), rs.getDouble(10), 
									rs.getString(8), rs.getString(11));
				
					manager.setJoiningDate(rs.getDate(9));
							
					rs.close();
					stmt.close();
					return (Object)manager;
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
				rs.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public ObservableList<AllowanceModel> getFranchiseAllowancesList()
	{
	   ObservableList<AllowanceModel> FList = FXCollections.observableArrayList();
	   
		query="Select * from allowance";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				AllowanceModel f=new AllowanceModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				FList.add(f);				
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return FList;
	}	
	
	public ObservableList<Franchise> getFranchiseList()
	{
	    ObservableList<Franchise> FList = FXCollections.observableArrayList();
	    
		query = "Select * from Franchise where status = 1";
		try {
			ResultSet rs=connection.getConnection().createStatement().executeQuery(query);
			while(rs.next())
			{
				Franchise f=new Franchise();
				f.setAll(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getDate(6));

				FList.add(f);
			}
			
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return FList;
	}
	
	public FranchiseModel getFranchiseInfo(String fid) {
		query = "Select * from franchise where status = 1 && FID = '" + fid + "'";
		Statement stmt = null;
		ResultSet rs = null;
		FranchiseModel f = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				f = new FranchiseModel(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				f.setStatus(true);
				f.setID(rs.getString(1));
			}
			
			rs.close();
			stmt.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		} 
		return f;
	}
	
	public ObservableList<String> getEmployeeIDList(String fid) {
	   ObservableList<String> FList = FXCollections.observableArrayList();
	   
		query = "Select EID from employee where status = 1 && FID = '" + fid + "'";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
				FList.add(rs.getString(1));
			
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try { rs.close(); stmt.close(); } 
			catch (Exception ex ) { ex.printStackTrace(); }
		}
		return FList;			
	}
	
	public ObservableList<CourierModel> getRegCourierList(StringBuilder p) {
		Double price = 0.0;
		ObservableList<CourierModel> list = FXCollections.observableArrayList();	
		query = "Select * from regcourier";
		ResultSet rs = null;
		Statement stmt = null;
		Statement stat = null;
		ResultSet set = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				String t1 = rs.getString(1);
				String t2 = rs.getString(2);
				String t3 = rs.getString(3);
				String t4 = rs.getString(4);
				String t5 = rs.getString(5);
				String t6 = rs.getString(6);
				String t7 = rs.getString(7);
				String t8 = rs.getString(8);
				String t9 = rs.getString(9);
				String t10 = rs.getString(10);
				String t11 = new String() ,t12 = new String(), t13 = new String(), t14 = new String();
				
				String id = rs.getString(11);
				query = "Select * from customer where CID = '" + id + "'";
				stat = connection.getConnection().createStatement();
				set = stat.executeQuery(query);
				if (set.next()) {
					t11 = set.getString(2) + " " + set.getString(3);
					t12 = set.getString(4);
					t13 = set.getString(5);
					t14 = set.getString(6);
				}
				
				CourierModel f = new CourierModel(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14);
				list.add(f);
				price += Double.parseDouble(rs.getString(6));
			}
			set.close();
			rs.close();
			stmt.close();
			p.append(price+"");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	// P ==> simple store the total prices earned from the courier (Database)
	public ObservableList<CourierModel> getCourierList(StringBuilder p, String fid) {
		// first the get the list of employee who is working in the given Franchise ID
		ObservableList<String> employeeList = getList("Employee","EID","FID",fid);
		Double price = 0.0;
		ObservableList<CourierModel> list = FXCollections.observableArrayList();	
		query = "Select * from unregcourier";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				boolean con = false;
				for (int i=0; i<employeeList.size() && !con; i++) {
					if (employeeList.get(i).equals(rs.getString(10)))
						con = true;
				}
				if (con) {
					CourierModel f = new CourierModel(
														rs.getString(1), 
														rs.getString(2), 
														rs.getString(3), 
														rs.getString(4),
														rs.getString(5),
														rs.getString(6),
														rs.getString(7),
														rs.getString(8),
														rs.getString(9),
														rs.getString(10),
														rs.getString(11) + " " + rs.getString(12),
														rs.getString(13),
														rs.getString(14),
														rs.getString(15)
													);
					list.add(f);
					price += Double.parseDouble(rs.getString(6));
				}
			}
			query = "Select * from regcourier";		
			stmt.close();
			rs.close();
			Statement stat = null;
			ResultSet set = null;
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				boolean con = false;
				for (int i=0; i<employeeList.size() && !con; i++) {
					if (employeeList.get(i).equals(rs.getString(10)))
						con = true;
				}
				if (con) {
					query = "Select * from customer where CID = '" + rs.getString(11) + "'";
					stat = connection.getConnection().createStatement();
					set = stat.executeQuery(query);
					if (set.next()) {
						CourierModel f = new CourierModel(
															rs.getString(1), 
															rs.getString(2), 
															rs.getString(3), 
															rs.getString(4),
															rs.getString(5),
															rs.getString(6),
															rs.getString(7),
															rs.getString(8),
															rs.getString(9),
															rs.getString(10),
															set.getString(2) + " " + set.getString(3),
															set.getString(4),
															set.getString(5),
															set.getString(6)
														);
						list.add(f);
						price += Double.parseDouble(rs.getString(6));
					}
				}
			}
			
			rs.close();
			stmt.close();
			p.append(price+"");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;		
	}
	
	// P ==> simple store the total prices earned from the courier (Database)
	public ObservableList<CourierModel> getCourierList(StringBuilder p) {
		Double price = 0.0;
		ObservableList<CourierModel> list = FXCollections.observableArrayList();	
		query = "Select * from unregcourier";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				CourierModel f = new CourierModel(
													rs.getString(1), 
													rs.getString(2), 
													rs.getString(3), 
													rs.getString(4),
													rs.getString(5),
													rs.getString(6),
													rs.getString(7),
													rs.getString(8),
													rs.getString(9),
													rs.getString(10),
													rs.getString(11) + " " + rs.getString(12),
													rs.getString(13),
													rs.getString(14),
													rs.getString(15)
												);
				list.add(f);
				price += Double.parseDouble(rs.getString(6));
			}
			rs.close();
			stmt.close();
			p.append(price+"");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}
	
public ObservableList<CourierModel> getPendingCourierList(String fid) {
		
		query="select * FROM regcourier  INNER JOIN  customer "
				+ "on regcourier.customerID=customer.CID  "
				+ "where regcourier.status='pending'";
		ObservableList<CourierModel> pendingCourierList = FXCollections.observableArrayList();	
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				

				
				CourierModel f = new CourierModel(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(13) + " " + rs.getString(14),
						rs.getString(15),
						rs.getString(16),
						rs.getString(17)
					);
				pendingCourierList.add(f);				
				
			}
			rs.close();
			stmt.close();
			
			query="select * FROM unregcourier";
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				

				
				CourierModel f = new CourierModel(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11) + " " + rs.getString(12),
						rs.getString(13),
						rs.getString(14),
						rs.getString(15)
					);
				pendingCourierList.add(f);				
				
			}
			rs.close();
			stmt.close();

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return pendingCourierList;
	}
		
	public ObservableList<Franchise> FranchiseList()
	{
	    ObservableList<Franchise> FList = FXCollections.observableArrayList();
	    
		query = "Select * from Franchise where status = 1";
		
		Statement stat = null;
		ResultSet set = null;
		try {
			stat = connection.getConnection().createStatement();
			ResultSet rs=connection.getConnection().createStatement().executeQuery(query);
			while(rs.next())
			{
				String q1 = "Select mid from manager where status = 1 and FID = '" + rs.getString(1) + "'";
				set = stat.executeQuery(q1);
				if (!set.next()) {
					Franchise f=new Franchise();					
					f.setAll(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getDate(6));
					FList.add(f);
				}
			}
			
			rs.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return FList;
	}
	
		
	public ObservableList<FranchiseModel> getFranchiseListView() {
		ObservableList<FranchiseModel> list = FXCollections.observableArrayList();	
		query = "Select * from franchise where status = 1";
		ResultSet rs = null;
		ResultSet set = null;
		Statement stmt = null;
		Statement stat = null;
		try {
			stmt = connection.getConnection().createStatement();
			stat = connection.getConnection().createStatement();			
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				FranchiseModel f = new FranchiseModel(
													rs.getString(2), 
													rs.getString(3), 
													rs.getString(4),
													rs.getString(5),
													rs.getDate(6).toString()
												);
				f.setID(rs.getString(1));
				String q1 = "Select MID from manager where status = 1 and FID = '" + rs.getString(1) + "'";
				set = stat.executeQuery(q1);
				if (set.next()) {
					f.setManager(set.getString(1));
				}
				list.add(f);
			}
			set.close();
			stat.close();
			rs.close();
			stmt.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				set.close();
				stat.close();				
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}	
	
	
	public ObservableList<ManagerModel> getManagerList() {
		ObservableList<ManagerModel> list = FXCollections.observableArrayList();	
		query = "Select * from manager where status = 1";
		ResultSet rs = null;
		Statement stmt = null;
		ResultSet set = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				ManagerModel f = new ManagerModel(
													rs.getString(1), 
													rs.getString(2)+" "+rs.getString(3), 
													rs.getString(4),
													rs.getString(5),
													rs.getString(6),
													rs.getDate(7).toString(),
													rs.getString(8),
													rs.getDate(9).toString(),
													rs.getString(10)
												);
				String q1 = "Select name from franchise where FID='"+rs.getString(11)+"'";
				stmt = connection.getConnection().createStatement();
				set = stmt.executeQuery(q1);
				if (set.next()) {
					f.setFranchise(set.getString(1));
				}
				list.add(f);
			}
			rs.close();
			stmt.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}	
	
	public ObservableList<RateModel> retrieveRateList(String fid) {
		ObservableList<RateModel> list = FXCollections.observableArrayList();
		query = "Select * from rate where FID = '" + fid + "'";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				RateModel f = new RateModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(f);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}
		
	
	public ObservableList<Rate> getRateList(String fid)
	{
		ObservableList<Rate> list = FXCollections.observableArrayList();
		query = "Select * from rate where fid = '" + fid + "'";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				Rate f = new Rate();
				f.setAll(rs.getString(1), rs.getString(2), Double.parseDouble(rs.getString(3)),rs.getString(4));
				list.add(f);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}	
	
	public EmployeeModel getEmployeeInfo(String id) {
			query = "Select * from employee where EID = '" + id + "'";
			
			EmployeeModel f = null;
			Statement stmt = null;
			ResultSet rs = null;		
			try {
				stmt = connection.getConnection().createStatement();
				
				rs = stmt.executeQuery(query);
				if(rs.next())
				{
					f = new EmployeeModel(
											rs.getString(1),
											rs.getString(2),
											rs.getString(3),
											rs.getString(4),
											rs.getString(5),
											rs.getString(6),
											rs.getDate(7).toString(),
											rs.getString(8),
											rs.getString(9),
											rs.getDate(10).toString(),
											rs.getString(11),
											rs.getString(12)
											);
				}
				rs.close();
				stmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
					stmt.close();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return f;
		}	
	
	public ObservableList<EmployeeModel> getRegEmployeeList(String fid)
	{
		ObservableList<EmployeeModel> list = FXCollections.observableArrayList();
		if (fid == null)
			query = "Select * from employee where status = 1";
		else 
			query = "Select * from employee where status = 1 and Fid = '" + fid + "'";
		
		Statement stmt = null;
		Statement stat = null;
		ResultSet rs = null;		
		ResultSet set = null;
		try {
			stmt = connection.getConnection().createStatement();
			
			String fname = "";
			String q1 = "Select name from franchise where FID = '" + fid + "'";
			rs = stmt.executeQuery(q1);
			if (rs.next())
				fname = rs.getString(1);

			rs.close();

			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				EmployeeModel f = new EmployeeModel(
													rs.getString(1),
													rs.getString(2),
													rs.getString(3),
													rs.getString(4),
													rs.getString(5),
													rs.getString(6),
													rs.getDate(7).toString(),
													rs.getString(8),
													rs.getString(9),
													rs.getDate(10).toString(),
													rs.getString(11),
													rs.getString(12)
													);
				if (fid == null) {
					q1 = "Select name from franchise where FID = '" + rs.getString(12) + "'";
					stat = connection.getConnection().createStatement();
					set = stat.executeQuery(q1);
					if (set.next()) {
						f.setFranchiseName(set.getString(1));
					}
				}
				else {
					f.setFranchiseName(fname);
				}
				list.add(f);
			}
			if (set!=null)
				set.close();
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (set != null)
					set.close();
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}	
	
	
	public ObservableList<AllowanceModel> getAllowanceList()
	{
		ObservableList<AllowanceModel> list = FXCollections.observableArrayList();
		query = "Select * from allowance";
		
		Statement stmt = null;
		ResultSet rs = null;		
		try {
			stmt = connection.getConnection().createStatement();
			
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				AllowanceModel f = new AllowanceModel(
													rs.getString(1),
													rs.getString(2),
													rs.getString(3),
													rs.getString(4)
													);
				list.add(f);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}		
	
	public ObservableList<CustomerModel> getCustomerList()
	{
		ObservableList<CustomerModel> list = FXCollections.observableArrayList();
		query = "Select * from customer where status = 1";
		
		Statement stmt = null;
		ResultSet rs = null;		
		try {
			stmt = connection.getConnection().createStatement();
			
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				CustomerModel f = new CustomerModel(
													rs.getString(1),
													rs.getString(2),
													rs.getString(3),
													rs.getString(4),
													rs.getString(5),
													rs.getString(6),
													rs.getDate(7).toString(),
													rs.getString(8)
													);
				list.add(f);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}	
	
	public ObservableList<String> getList(String tableName, String colname) {
		ObservableList<String> CusID = FXCollections.observableArrayList();
		query = "Select "+colname+" from "+ tableName;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				CusID.add(rs.getString(1));
			}
			
			rs.close();
			stmt.close();
			return CusID;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return CusID;
	} 

	public ObservableList<String> getList(String tableName, String colname, String searchCol, String Value) {
		ObservableList<String> CusID = FXCollections.observableArrayList();
		query = "Select "+colname+" from "+ tableName + " where " + searchCol + " = '" + Value + "'";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				CusID.add(rs.getString(1));
			}
			
			rs.close();
			stmt.close();
			return CusID;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return CusID;
	} 
	
	public ManagerModel getManager(String mid) {
		query="select  * from Manager where status=1 and MID='"+mid+"'";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			
			
			if (rs.next()) {
				return  new ManagerModel(
						rs.getString(1), 
						rs.getString(2)+" "+rs.getString(3), 
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getDate(7).toString(),
						rs.getString(8),
						rs.getDate(9).toString(),
						rs.getString(10)
					);	
			}
			
			rs.close();
			stmt.close();
			return null;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;

	}	
	
	
	public RegisteredCourier getRegisterCourier(String cid) {
		query = "select  * from regcourier where cid='"+cid+"'";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				RegisteredCourier temp = new RegisteredCourier();
				temp.setAll(
							rs.getString(1), 
							rs.getString(2), 
							rs.getString(3),
							rs.getString(4),
							rs.getDate(5).toString(),
							Double.parseDouble(rs.getString(6)),
							rs.getString(7),
							Double.parseDouble(rs.getString(8)),
							rs.getString(9),
							rs.getString(10),
							rs.getString(11)
						);	
				return temp;
			}
			
			rs.close();
			stmt.close();
			return null;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}		
	
	public UnRegisteredCourier getUnRegisteredCourier(String cid) {
		query = "select  * from unregcourier where cid='"+cid+"'";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				UnRegisteredCourier temp = new UnRegisteredCourier();
				temp.setAll(
							rs.getString(1), 
							rs.getString(2), 
							rs.getString(3),
							rs.getString(4),
							rs.getDate(5).toString(),
							Double.parseDouble(rs.getString(6)),
							rs.getString(7),
							Double.parseDouble(rs.getString(8)),
							rs.getString(9),
							rs.getString(10),
							rs.getString(11),
							rs.getString(12),
							rs.getString(13),
							rs.getString(14),
							rs.getString(15)
						);	
				return temp;
			}
			
			rs.close();
			stmt.close();
			return null;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	public ObservableList<FranchiseAllowancesModel > getAllocatedAllowancesList() {
		query = "select  * from allocated_allowances";
		ObservableList<FranchiseAllowancesModel> list = FXCollections.observableArrayList();	
	    
		ResultSet rs = null;
		Statement stmt = null;
		Statement stat = null;
		ResultSet set = null;
		try {
			stat = connection.getConnection().createStatement();			
			stmt = connection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				FranchiseAllowancesModel temp = new FranchiseAllowancesModel( rs.getString(1), rs.getString(2));
				String q1= "Select type, amount from allowance where allID = '" + rs.getString(2) + "'";
				set = stat.executeQuery(q1);
				if (set.next()) {
					temp.setAName(set.getString(1));
					temp.setAmount(set.getString(2));
				}
				list.add(temp);
			}
			set.close();
			stat.close();
			rs.close();
			stmt.close();
			return list;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				set.close();
				stat.close();				
				rs.close();
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<String> toArrayList(ObservableList<String> temp) {
		if (temp != null) {
			ArrayList<String> list = new ArrayList<String>();
			for (int i=0; i<temp.size(); i++) {
				list.add(temp.get(i));
			}
			return list;
		}
		return null;
	}
	
	
}
