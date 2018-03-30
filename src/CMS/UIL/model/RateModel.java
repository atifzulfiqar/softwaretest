package CMS.UIL.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RateModel {

    public StringProperty RID;
    public StringProperty zone;
    public StringProperty price;
    public StringProperty desc;
    public StringProperty FID;
	
	public RateModel(String id, String z, String p, String d) {
		RID = new SimpleStringProperty(id);
		zone = new SimpleStringProperty(z);
		price = new SimpleStringProperty(p);
		desc = new SimpleStringProperty(d);
	}
	
	public void setAll(String id, String z, String p, String d) {
		RID.set(id);
		zone.set(z);
		price.set(p);
		desc.set(d);
	}
	
	public void setFID(String fid) {
		FID = new SimpleStringProperty(fid);
	}

}
