package CMS.UIL.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AllowanceModel {

    public StringProperty AID;
    public StringProperty desc;	
    public StringProperty type;
    public StringProperty amount;
	
    public AllowanceModel(String id, String d, String t, String a) {
    	AID = new SimpleStringProperty(id);
    	desc = new SimpleStringProperty(d);
    	type = new SimpleStringProperty(t);
    	amount = new SimpleStringProperty(a);
    }
   	
	
}
