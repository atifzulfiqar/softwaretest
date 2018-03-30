package CMS.UIL.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeeModel {

    public StringProperty id;
    public StringProperty fname;
    public StringProperty lname;
    public StringProperty address;    
    public StringProperty contact;
    public StringProperty cnic;
    public StringProperty dob;
    public StringProperty designation;
    public StringProperty qal;
    public StringProperty jd;
    public StringProperty salary;
    public StringProperty FID;
    public StringProperty FranchiseName;   // optional
    public BooleanProperty status;    
		
	public EmployeeModel(String i, String f, String l, String a, String c, String cn, String DOB, String des, String q, String j, String sal, String fid) 
	{	
		id = new SimpleStringProperty(i);
		fname = new SimpleStringProperty(f);
		lname = new SimpleStringProperty(l);
		address = new SimpleStringProperty(a);
		contact = new SimpleStringProperty(c);
		cnic = new SimpleStringProperty(cn);
		dob = new SimpleStringProperty(DOB);
		designation = new SimpleStringProperty(des);
		qal = new SimpleStringProperty(q);
		jd = new SimpleStringProperty(j);
		salary = new SimpleStringProperty(sal);
		FID = new SimpleStringProperty(fid);
	}

	public void setStatus(boolean s) {
		status = new SimpleBooleanProperty(s);
	}	
	
	public void setFranchiseName(String name) {
		FranchiseName = new SimpleStringProperty(name);
	}
	
	public void setAll(String fn, String ln, String a, String c, String cn, String job, String des, String q, String j,
						String sal ) {
		fname.set(fn);
		lname.set(ln);
		address.set(a);
		contact.set(c);
		cnic.set(cn);
		dob.set(job);
		designation.set(des);
		qal.set(q);
		jd.set(j);
		salary.set(sal);
	}

	public void updateDesignation(String des) {
		designation.set(des);	
	}
	
	public void updateSalary(String sla) {
		salary.set(sla);
	}
	
}

