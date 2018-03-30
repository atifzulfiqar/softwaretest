package CMS.UIL.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerModel {

    public StringProperty id;
    public StringProperty fname;
    public StringProperty lname;
    public StringProperty address;    
    public StringProperty contact;
    public StringProperty cnic;
    public StringProperty doj;
    public StringProperty organization;   // optional 
    public BooleanProperty status;
		
	public CustomerModel(String i, String f, String l, String a, String c, String cn, String DOj, String org) 
	{	
		id = new SimpleStringProperty(i);
		fname = new SimpleStringProperty(f);
		lname = new SimpleStringProperty(l);
		address = new SimpleStringProperty(a);
		contact = new SimpleStringProperty(c);
		cnic = new SimpleStringProperty(cn);
		doj = new SimpleStringProperty(DOj);
		organization = new SimpleStringProperty(org);	
	}
	
	public void setAll(String f, String l, String a, String c, String cn, String org) {	
			fname = new SimpleStringProperty(f);
			lname = new SimpleStringProperty(l);
			address = new SimpleStringProperty(a);
			contact = new SimpleStringProperty(c);
			cnic = new SimpleStringProperty(cn);
			organization = new SimpleStringProperty(org);
	}
	
	public void setStatus(boolean s) {
		status = new SimpleBooleanProperty(s);
	}
}
