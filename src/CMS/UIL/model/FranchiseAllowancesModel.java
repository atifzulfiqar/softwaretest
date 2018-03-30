package CMS.UIL.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FranchiseAllowancesModel {

    public StringProperty FID;
    public StringProperty AID;	
    public StringProperty AIDName;
    public StringProperty AAmount;
	
    public FranchiseAllowancesModel(String fid, String aid) {
    	FID = new SimpleStringProperty(fid);
    	AID = new SimpleStringProperty(aid);
    }
    
    public void setAName(String name) {
    	AIDName = new SimpleStringProperty(name);
    }
    
    public void setAmount(String amount) {
    	AAmount = new SimpleStringProperty(amount);
    }
       
}
