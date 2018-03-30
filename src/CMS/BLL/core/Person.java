package CMS.BLL.core;



public abstract class Person {
	private String FName;
	private String LName;
	private String address;
	private String contactNumber;
	private String CNIC;
	private String Date_Of_Birth;

	public String insertValue()
	{
		return FName+"', '"+LName+"', '"+address+"', '"+contactNumber+"', '"+CNIC+"', '"+Date_Of_Birth;
	}
	
	public String toString() {
		return (FName+","+LName+","+address+","+contactNumber+","+CNIC+","+Date_Of_Birth);
	}
	
	public void setAll(String FName, String LName,String address,
			String contactNumber,String CNIC, String Date_Of_Birth)
	{
		this.FName=FName;
		this.LName=LName;
		this.address=address;
		this.contactNumber=contactNumber;
		this.CNIC=CNIC;
		this.Date_Of_Birth=Date_Of_Birth;
	}
	public String getFName() {
		return FName;
	}
	public String getLName() {
		return LName;
	}
	public String getAddress() {
		return address;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getCNIC() {
		return CNIC;
	}
	public String getDate_Of_Birth() {
		return Date_Of_Birth;
	}
}
