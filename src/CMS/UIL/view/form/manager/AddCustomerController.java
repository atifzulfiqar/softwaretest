package CMS.UIL.view.form.manager;


import CMS.BLL.core.Customer;
import CMS.BLL.core.Manager;
import CMS.UIL.view.Helper;
import ErrorHandler.UIConfirmation;
import ErrorHandler.UIError;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddCustomerController {
	
	@FXML private Label formId;
	@FXML private TextField fname;
	@FXML private TextField lname;
	@FXML private TextField street;
	@FXML private TextField city;
	@FXML private TextField country;
	@FXML private TextField contact;
	@FXML private TextField cnic;
	@FXML private TextField organization;
	
	Manager manager;
	
	public void setFormID(Manager man) {
		manager = man;		
		formId.setText(Customer.generateID());
	}
	
	private boolean checkNull() {
		if (fname != null && lname != null) {
			if (street != null && city != null && country != null) {
				if (contact != null && cnic!=null) {
					if (organization!=null)
						return true;
				}
			}
		}
		return false;
	}
	
	private boolean validation() {
		if (checkNull()) {
			if (fname.getText().equals("")) {
				UIError.showError("Empty Field", "First Name Field must not be empty");
				fname.requestFocus();
				return false;
			}
			
			if (lname.getText().equals("")) {
				UIError.showError("Empty Field", "last Name Field must not be empty");
				lname.requestFocus();				
				return false;
			}
			
			if (street.getText().equals("")) {
				UIError.showError("Empty Field", "Street Field must not be empty");
				street.requestFocus();				
				return false;
			}
			
			if (city.getText().equals("")) {
				UIError.showError("Empty Field", "city Field must not be empty");
				city.requestFocus();				
				return false;
			}
			
			if (country.getText().equals("")) {
				UIError.showError("Empty Field", "country Field must not be empty");
				country.requestFocus();	
				return false;
			}
			
			if (contact.getText().equals("")) {
				UIError.showError("Empty Field", "Contact Number Field must not be empty");
				contact.requestFocus();	
				return false;
			}
			
			if (cnic.getText().equals("")) {
				UIError.showError("Empty Field", "CNIC Field must not be empty");
				cnic.requestFocus();	
				return false;
			}			
			
			if (organization.getText().equals("")) {
				UIError.showError("Empty Field", "Organization Field must not be empty");
				organization.requestFocus();
				return false;
			}
					
			return true;
		}
		else {
			UIError.showError("Invalid Argument", null);
			return false;
		}
	}
	
	public void  clear() {
		fname.setText("");
		lname.setText("");
		street.setText("");
		city.setText("");
		country.setText("");
		contact.setText("");
		cnic.setText("");
		organization.setText("");		
	}
	
	@FXML
	private void resetOpt() {
		if (checkNull()) {
			clear();
			UIConfirmation.showMsg("Form reset Successfully!", null);	
		}
	}
	
	private boolean validateForm() {
		if (Helper.chkContactNo(contact.getText())) {
			if (Helper.chkCNIC(cnic.getText())) {
				return true;
			}
			else {
				UIError.showError("Invalid CNIC", "Must be Numberic\nUp to 13 Digits\nDo not use '-'");
				cnic.requestFocus();
				return false;				
			}
		}
		else {
			UIError.showError("Invalid Contact Number", "Must be Numberic\nUp to 11 Digits\nDo not use '-'");
			contact.requestFocus();
			return false;
		}
	}
	
	@FXML
	private void saveOpt() {
		if (validation()) {
			if (validateForm()) {
				Customer customer = new Customer();
				customer.setAll(formId.getText(), fname.getText(), lname.getText(), (street.getText()+" "+city.getText()+" "+country.getText()), 
						contact.getText(), cnic.getText(), organization.getText());
				if (manager.addCustomer(customer)) {
					UIConfirmation.showMsg("Data Save Successfully!", null);
					clear();
				}
				else {
					UIError.showError("Customer Information did not saved!", "Please try later");
				}
			}
		}
	}
	
}
