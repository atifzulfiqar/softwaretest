package CMS.BLL.core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import CMS.BLL.courier.Rate;
import CMS.BLL.courier.RegisteredCourier;
import CMS.BLL.courier.UnRegisteredCourier;
import CMS.DBL.handler.*;
import CMS.UIL.model.CourierModel;
import CMS.UIL.model.RateModel;
import javafx.collections.ObservableList;

public class Employee extends Person {
	private String EID; 
	private String designation;
	private String Qualification;
	private Date joiningDate;
	private double basicSalary;
	private String FID;
	private boolean status = true;
	
	public String getEID() {
		return EID;
	}

	public String getDesignation() {
		return designation;
	}

	public String getQualification() {
		return Qualification;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}
	
	public  void setJoiningDate(Date date) {
		joiningDate=date;
	}
	
	public double getBasicSalary() {
		return basicSalary;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setFID(String fid) {
		FID = fid;
	}
	
	public String getFID() {
		return FID;
	}
	
	public void setStatus(boolean s) {
		status = s;
	}

	public void getNewID() {
		RetrievalHandler getLastId=new RetrievalHandler();
		String s = getLastId.getLastId("Employee", "EID");
		
		if(s!=null)
		{
			int lastID=Integer.parseInt(s.substring(1));
			lastID++;
			String newID=(lastID+"");
			for(int i=newID.length();i<6;i++)
			{
				newID = "0" + newID;
			}
			newID = s.charAt(0)+newID;
			EID = newID;			
		}
		else
			EID="E000001";
	}
	
	public void setAll(	String FName, String LName,String address,
						String contactNumber,String CNIC, String Date_Of_Birth, 
						String Qualifaction, String designation,double basicSalary)
	{
		RetrievalHandler getLastId=new RetrievalHandler();
		String s = getLastId.getLastId("Employee", "EID");
		
		if(s!=null)
		{
			int lastID=Integer.parseInt(s.substring(1));
			lastID++;
			String newID=(lastID+"");
			for(int i=newID.length();i<6;i++)
			{
				newID = "0" + newID;
			}
			newID = s.charAt(0)+newID;
			EID = newID;

			
		}
		else
			EID="E000001";	
		
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		
		super.setAll(FName, LName, address, contactNumber, CNIC, Date_Of_Birth);
		this.basicSalary=basicSalary;
		this.joiningDate=date;
		this.Qualification=Qualifaction;
		this.designation=designation;
	}
	
	public void setAll(	String EID,String FName, String LName,String address,
						String contactNumber,String CNIC, String Date_Of_Birth, 
						String Qualifaction, String designation,double basicSalary)
	{
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		this.EID=EID;
		super.setAll(FName, LName, address, contactNumber, CNIC, Date_Of_Birth);
		this.basicSalary=basicSalary;
		this.joiningDate=date;
		this.Qualification=Qualifaction;
		this.designation=designation;
	}

	public String insertValue()
	{
		return "('"+EID+"', '"+super.insertValue()
				+"', '"+designation+"', '"+Qualification+"', '"
				+joiningDate+"', "+basicSalary+",'"+FID+"',1)";
	}
	
	public String toString()
	{
		return EID+","+super.insertValue()
				+","+designation+","+Qualification+","
				+joiningDate+","+basicSalary+","+FID;
	}

	public void addCourier(RegisteredCourier newCourier) {
		InsertionHandler insertCourier=new InsertionHandler();
		insertCourier.insert(newCourier);
	}
	
	public void addCourier(UnRegisteredCourier newCourier    ) {
		// TODO Auto-generated method stub
		InsertionHandler insertCourier=new InsertionHandler();
		insertCourier.insert(newCourier);
	}
	
	public void updateCourier(RegisteredCourier newCourier) {
		UpdationHandler updateCourier=new UpdationHandler();
		updateCourier.update(newCourier);
	}
	
	public void updateCourier(UnRegisteredCourier newCourier) {
		UpdationHandler updateCourier=new UpdationHandler();
		updateCourier.update(newCourier);
	}	
	
	public void CancelCourier(CourierModel temp) {
		UpdationHandler update = new UpdationHandler();
		update.cancelCourier(temp.CID.get());
	}
	
	public ObservableList<CourierModel> getPendingCourier() {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getPendingCourierList(FID);
	}
	
	public boolean makeShipment(String empid, String vehicle, ObservableList<CourierModel> list) {
		InsertionHandler handler = new InsertionHandler();
		UpdationHandler update = new UpdationHandler();
		ArrayList<String> ids = new ArrayList<String>();
		for (int i=0; i<list.size(); i++) 
			ids.add(list.get(i).CID.get());
		
		if (update.updateAllStatus(ids)) {
			return handler.InsertShipment(empid, vehicle, ids);
		}
		return false;
	}
	
	public ObservableList<Rate> getRateList() {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getRateList(FID);
	}
	
	public ObservableList<CourierModel> getCourierList(StringBuilder p,String fid) {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.getCourierList(p, fid);
	}
	
	public ObservableList<RateModel> retrieveRateList() {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.retrieveRateList(FID);
	}
	
	public RegisteredCourier getRegCourier(String cid) {
		RetrievalHandler handler = new RetrievalHandler();
		RegisteredCourier obj = handler.getRegisterCourier(cid);
		return obj;
	}

	public UnRegisteredCourier getUnRegCourier(String cid) {
		RetrievalHandler handler = new RetrievalHandler();
		UnRegisteredCourier obj = handler.getUnRegisteredCourier(cid);
		return obj;
	}
	
	public boolean requestLoan(Loan loan) {
		InsertionHandler handler = new InsertionHandler();
		return handler.insert(loan);
	}
	
	public ArrayList<String> getList(String tableName , String colName) {
		RetrievalHandler handler = new RetrievalHandler();
		return handler.toArrayList(handler.getList(tableName,colName));		
	}
}
