package CMS.UIL.model;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FranchiseModel {

	public StringProperty fid;
    public StringProperty name;
    public StringProperty location;
    public StringProperty description;
    public StringProperty contact;    
    public StringProperty create;
    public BooleanProperty status;
    public ArrayList<StringProperty> allowances = new ArrayList<StringProperty>();
    public StringProperty managerID;
    
	
	public FranchiseModel(String n, String d, String loc, String c, String cre) {
		name = new SimpleStringProperty(n);
		location = new SimpleStringProperty(loc);
		description = new SimpleStringProperty(d);
		contact = new SimpleStringProperty(c);
		create = new SimpleStringProperty(cre);		
	}
	
	public void setManager(String s) {
		managerID = new SimpleStringProperty(s);
	}
	
	public void setStatus(boolean s) {
		status = new SimpleBooleanProperty(s);
	}	
	
	public void setID(String id) {
		fid = new SimpleStringProperty(id);
	}
	
	public void setAllowances (ArrayList<String> aid) {
		if (aid != null) {
			for (int i=0; i<aid.size(); i++) {
				allowances.add(new SimpleStringProperty(aid.get(i)));
			}
		}
	}	
}
