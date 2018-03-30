package CMS.UIL.view.form.admin;

import java.util.ArrayList;
import java.util.Date;

import CMS.BLL.core.Admin;
import CMS.BLL.core.Manager;
import CMS.BLL.franchise.Franchise;
import CMS.UIL.view.Helper;
import ErrorHandler.UIConfirmation;
import ErrorHandler.UIError;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddManagerController {
	
	Admin admin;
	
	@FXML private Label time;
	@FXML private Label id;
	@FXML private TextField fname;
	@FXML private TextField lname;
	@FXML private TextField street;
	@FXML private TextField city;
	@FXML private TextField country;
	@FXML private TextField contact;
	@FXML private TextField cnic;
	@FXML private DatePicker dob;
	@FXML private TextField qualification;
	@FXML private TextField salary;
	@FXML private ComboBox<String> franchise;
	
	private ObservableList<Franchise> franchiseList;
	
	
	public void init(ObservableList<Franchise> fl, Admin a) {
		admin = a;
		
		franchiseList = fl;
		
		time.setText(new Date().toString());
		
		id.setText(Manager.generateMID());

		ArrayList<String> list = new ArrayList<String>();
		for (int i=0; i<franchiseList.size(); i++) {
			list.add(franchiseList.get(i).getName());
		}

		franchise.getItems().addAll(list);
		
	}

	private boolean chkNull() {
		if (fname == null || lname==null || street==null || city==null || country==null || contact==null || cnic==null
				|| qualification==null || salary==null ) 
			return false;
		return true;
	}
	
	private boolean validate() {
		if (chkNull()) {
			if (fname.getText().equals("")) {
				UIError.showError("Empty Field", "First Name Field must not be empty");
				fname.requestFocus();
				return false;
			}

			if (fname.getText().contains(" ")) {
				UIError.showError("Spaces are not allow", null);
				fname.requestFocus();
				return false;				
			}
			
			if (lname.getText().equals("")) {
				UIError.showError("Empty Field", "last Name Field must not be empty");
				lname.requestFocus();
				return false;
			}	

			if (lname.getText().equals(" ")) {
				UIError.showError("Spaces are not allow", null);
				lname.requestFocus();
				return false;
			}				
			
			if (street.getText().equals("")) {
				UIError.showError("Empty Field", "Street Field must not be empty");
				street.requestFocus();
				return false;
			}			
			
			if (city.getText().equals("")) {
				UIError.showError("Empty Field", "City Field must not be empty");
				city.requestFocus();
				return false;
			}			
			
			if (country.getText().equals("")) {
				UIError.showError("Empty Field", "Country Field must not be empty");
				country.requestFocus();
				return false;
			}			
			
			if (contact.getText().equals("")) {
				UIError.showError("Empty Field", "contact Number Field must not be empty");
				contact.requestFocus();
				return false;
			}			
			
			if (cnic.getText().equals("")) {
				UIError.showError("Empty Field", "CNIC Field must not be empty");
				cnic.requestFocus();
				return false;
			}	
			
			if (dob.getValue() == null) {
				UIError.showError("Empty Field", "Date of Birth Field must not be empty");
				dob.requestFocus();
				return false;
				
			}
						
			if (qualification.getText().equals("")) {
				UIError.showError("Empty Field", "Qualification Field must not be empty");
				qualification.requestFocus();
				return false;
			}	
			
			if (salary.getText().equals("")) {
				UIError.showError("Empty Field", "Salary Field must not be empty");
				salary.requestFocus();
				return false;
			}			
			
			if (franchise.getValue() == null) {
				UIError.showError("Empty Field", "You must assign a Manager to some Franchise");
				franchise.requestFocus();
				return false;
			}			
			return true;
		}
		else {
			UIError.showError("Invalid Arugment", null);
			return false;
		}
	}
	
	private boolean validateForm() {
		if (Helper.chkContactNo(contact.getText())) {
			if (Helper.	chkCNIC(cnic.getText())) {
				if (Helper.isNumbericValue(salary.getText())) {
					if (Helper.isAlphaValue(qualification.getText())) {
						return true;
					}
					else {
						UIError.showError("Invalid Qualificatoin", null);
						qualification.requestFocus();
						return false;					
					}
				}
				else {
					UIError.showError("Invalid Salary Format", " > Must be Numberic");
					salary.requestFocus();
					return false;
				}
			}
			else {
				UIError.showError("Invalid CNIC", " > Must be Numberic \n > up to 13 characters \n > no dash");
				cnic.requestFocus();
				return false;
			}
		}
		else {
			UIError.showError("Invalid CONTACT", " > Must be Numberic \n > Up to 11 Characters \n > No dash");
			contact.requestFocus();
			return false;
		}
	}
	
	@FXML
	private void saveOpt(ActionEvent e) {
		try {
			if (validate()) {
				if (validateForm()) {
					String fid = null;
					String franchisename = franchise.getValue().toString();
					for (int i=0; i<franchiseList.size(); i++) {
						if (franchiseList.get(i).getName().equals(franchisename)) {
							fid = franchiseList.get(i).getFID();
						}
					}
					
					Manager manager = new Manager();
					manager.setAll( id.getText(), fname.getText() , lname.getText(), (street.getText()+" "+city.getText()+" "+country.getText()), 
									contact.getText(), cnic.getText(), dob.getValue().toString(), Double.parseDouble(salary.getText()), 
									qualification.getText(), fid);
					
					admin.addManager(manager);
					UIConfirmation.showMsg("Manager information saved successfully!", null);
					clear();
				}
			}
		}
		catch (Exception ex) {
			System.out.println("Error in storing Manager Information");
			ex.printStackTrace();
		}
	}
	
	private void clear() {
		fname.setText("");
		lname.setText("");
		street.setText("");
		city.setText("");
		country.setText("");
		contact.setText("");
		cnic.setText("");
		dob.setValue(null);
		qualification.setText("");
		salary.setText("");
		franchise.setValue(null);		
	}
	
	@FXML
	private void resetOpt(ActionEvent e) {
		clear();
		UIConfirmation.showMsg("Form Reset successfully!", null);
	}
	

}
