package CMS.UIL.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CourierModel {
    public StringProperty rname;
    public StringProperty raddr;
    public StringProperty rcontact;
    public StringProperty date;    
    public StringProperty price;
    public StringProperty type;
    public StringProperty weight;
    public StringProperty status;
    public StringProperty registerby;
    
    public StringProperty CID;
	public StringProperty SName;
	public StringProperty Saddress;
	public StringProperty ScontactNumber;
	public StringProperty SCNIC;
	    
	
	public CourierModel(String cid, String rn, String ra, String rc, String d, String p, String t, String w, String s, String register,
						String sn, String sa, String scontact, String scnic)
	{
		CID = new SimpleStringProperty(cid);
		rname = new SimpleStringProperty(rn);
		raddr = new SimpleStringProperty(ra);
		rcontact = new SimpleStringProperty(rc);
		
		date = new SimpleStringProperty(d);
		price = new SimpleStringProperty(p);
		type = new SimpleStringProperty(t);
		weight = new SimpleStringProperty(w);
		status = new SimpleStringProperty(s);
		registerby = new SimpleStringProperty(register);
		
		SName = new SimpleStringProperty(sn);
		Saddress = new SimpleStringProperty(sa);
		ScontactNumber = new SimpleStringProperty(scontact);
		SCNIC = new SimpleStringProperty(scnic);
	}
	
    
}
