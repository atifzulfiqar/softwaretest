package CMS.BLL.core;

import java.util.ArrayList;

import CMS.BLL.franchise.Franchise;
import CMS.DBL.handler.*;
import CMS.UIL.model.*;
import javafx.collections.ObservableList;

public class Admin extends Person {
	private String AID;
	private Double basicSalary;
	
	public String getID() {
		return AID;
	}
	
	public Double getBasicSalary() {
		return basicSalary;
	}
	
	public void setAll(String AID,String FName, String LName,String address,
			String contactNumber,String CNIC, String Date_Of_Birth, double basicSalary)
	{
		this.AID=AID;
		super.setAll(FName, LName, address, contactNumber, CNIC, Date_Of_Birth);
		this.basicSalary=basicSalary;
	}

	public void addManager(Manager newManager) {
		InsertionHandler managerInsert=new InsertionHandler();
		// save the manager information
		managerInsert.insert(newManager);
		// create a new account
		Account acc = new Account();
		acc.setAll(newManager.getMID(), "1234567");
		managerInsert.insert(acc);
	}

	public ManagerModel getManager(String mid)
	{
		RetrievalHandler retrieveManager=new RetrievalHandler();
		return retrieveManager.getManager(mid);
	}	
	
	public void updateManager(Manager managerData)
	{
		UpdationHandler updateManager=new UpdationHandler();
		updateManager.update(managerData);
	}

	public  boolean fireManager(String mid) {
		 //update the status of  manager
		 UpdationHandler updateManager=new UpdationHandler();
		 
		 if((updateManager.fireManager(mid))>0)
		 {
			// remove the account from account table			 
			if(updateManager.deleteAccount(mid) )
			{
				return true;
			}
		 }
		 return false;
	}
	
	public ObservableList<ManagerModel> getManagerList() {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getManagerList();
	}
	
	public void addFranchise(Franchise newfranchise, ArrayList<String> ids ) {
		InsertionHandler insertFranchise = new InsertionHandler();
		// save the newFranchise information
		// save the allocated allowances in db		
		insertFranchise.insert(newfranchise, ids);
	}
	
	public FranchiseModel searchFranchise(String fid) {
		RetrievalHandler handler = new RetrievalHandler();
		
		return handler.getFranchiseInfo(fid);
	}

	public boolean dropFranchise(FranchiseModel f) {
		RetrievalHandler handler = new RetrievalHandler();
		UpdationHandler update = new UpdationHandler();
		
		ObservableList<String> employeeList = handler.getEmployeeIDList(f.fid.get());
		if (employeeList.size() == 0) {
			return update.updateFranchise(f.fid.get(), false);
		}
		else {
			return false;
		}
	}
	
	public  ObservableList<FranchiseModel> getFranchiseList() {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getFranchiseListView();
	}
	
	public ObservableList<AllowanceModel>  AllowancesList() {
		RetrievalHandler handler = new RetrievalHandler();		
		return handler.getFranchiseAllowancesList();
	}

	public ObservableList<FranchiseAllowancesModel>  getAllocatedAllowancesList() {
		RetrievalHandler handler = new RetrievalHandler();		
		return handler.getAllocatedAllowancesList();
	}
	
	
	public void dropFranchise() {
		// TODO Auto-generated method stub

	}
	
	public ObservableList<CourierModel> getRegCourierList (StringBuilder p) {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getRegCourierList(p);
	}

	public ObservableList<CourierModel> getCourierList (StringBuilder p) {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getCourierList(p);
	}	
	
	public ObservableList<Franchise> retreieveFranchiseList() {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getFranchiseList();
	}
	
	public ArrayList<String> getList(String tableName, String colName) {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.toArrayList(handler.getList(tableName, colName));		
	}
	
	public ObservableList<String> getEmployeeIDList(String id) {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getEmployeeIDList(id);
	}
	
	public  ObservableList<EmployeeModel> getRegEmployeeList(String fid) {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getRegEmployeeList(fid);
	}	
	
	public ObservableList<Franchise> retrieveFranchiseList() {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getFranchiseList(); 
	}
	
	// get the list of those franchise who does not have any franchise
	public ObservableList<Franchise> FranchiseList() {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.FranchiseList(); 
	}	
	
/*	public static void main(String []args)
	{
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		Manager m=new Manager();
		//m.setAll(FName, LName, address, contactNumber, CNIC, Date_Of_Birth, basicSalary, joiningDate, Qualifaction, FID);
		//m.setAll("M000000","Chandan", "Kumar", "POBOX          Kaloi", "03323881213", "98", date, 98.0, date, Qualifaction, FID);
		
	//	m.setAll("M000000","Omesh", "Kumar", "POBOX Kaloi", "03323881213", "98",date,89.0,date,"BA","F1234");
		
		
//		Admin a=new Admin();
//		a.updateManager(m);
		//a.addManager(m);
		
		
	}*/

}
