package CMS.UIL.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ManagerModel {

    public StringProperty MID;
    public StringProperty Mname;
    public StringProperty Maddress;
    public StringProperty Mcontact;    
    public StringProperty MCNIC;
    public StringProperty MDOB;
    public StringProperty Mqualification;
    public StringProperty MJD; 	
    public StringProperty Msalary; 
    public StringProperty FranchiseName;
	public BooleanProperty status;    
	
	public void setStatus(boolean s) {
		status = new SimpleBooleanProperty(s);
	}    
    
	public ManagerModel(String id, String n, String a, String c, String cnic, String dob, String qual, String jd, String s)
	{
		MID = new SimpleStringProperty(id);
		Mname = new SimpleStringProperty(n);
		Maddress = new SimpleStringProperty(a);
		Mcontact = new SimpleStringProperty(c);
		MCNIC = new SimpleStringProperty(cnic);
		MDOB = new SimpleStringProperty(dob);
		Mqualification = new SimpleStringProperty(qual);
		MJD = new SimpleStringProperty(jd);
		Msalary = new SimpleStringProperty(s);
	}
	
	public void setFranchise(String name) {
		FranchiseName= new SimpleStringProperty(name);		
	}
	
}
