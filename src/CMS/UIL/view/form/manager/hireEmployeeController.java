package CMS.UIL.view.form.manager;


import CMS.BLL.core.Employee;
import CMS.BLL.core.Manager;
import CMS.UIL.view.Helper;
import ErrorHandler.UIConfirmation;
import ErrorHandler.UIError;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class hireEmployeeController {
	
	Manager manager = null;
	Employee employee = new Employee();
	
	@FXML private Label empid;
	@FXML private TextField fname;
	@FXML private TextField lname;
	@FXML private TextField street;
	@FXML private TextField city;
	@FXML private TextField country;
	@FXML private TextField contactNo;
	@FXML private TextField cnic;
	@FXML private DatePicker dob;
	@FXML private ComboBox<String> designation;
	@FXML private TextField qualification;
	@FXML private TextField basicSalary;
	
		
	public void getFormID(Manager temp) {
		manager = temp;		
		
		employee.getNewID();
		empid.setText(employee.getEID());
		
		designation.getItems().addAll(employee.getList("Designation", "name"));
	}
	
	private void clear() {
		empid.setText("");
		fname.setText("");
		lname.setText("");
		street.setText("");
		city.setText("");
		country.setText("");
		contactNo.setText("");
		cnic.setText("");
		dob.setValue(null);
		designation.setValue(null);
		designation.setPromptText("Select any designation");
		qualification.setText("");
		basicSalary.setText("");
	}
	
	@FXML
	private void resetBtn() {
		clear();
		UIConfirmation.showMsg("Form reset successfully!", null);
	}
	
	private boolean validateForm() {
		if (Helper.chkContactNo(contactNo.getText())) {
			if (Helper.	chkCNIC(cnic.getText())) {
				if (Helper.isNumbericValue(basicSalary.getText())) {
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
					basicSalary.requestFocus();
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
			contactNo.requestFocus();
			return false;
		}
	}	
	
	@FXML
	private void saveBtn() {
		if (validate()) {
			if (validateForm()) {
				employee.setAll(empid.getText(), fname.getText(), lname.getText(), (street.getText()+" "+city.getText()+" "+country.getText()), 
								contactNo.getText(), cnic.getText(), dob.getValue().toString(), qualification.getText(), 
								designation.getValue().toString(), Double.parseDouble(basicSalary.getText())
								);
			
				employee.setFID(manager.getFID());
				manager.addEmployee(employee);
				UIConfirmation.showMsg("Employee Information saved successfully!", null);
				clear();
			}
		}
	}

	private boolean validate() {
		if (notNull()) {
			if(fname.getText().equals("")) {
				UIError.showError("Invalid Field", "First Name Field must not be empty!");
				return false;
			}
			
			if (fname.getText().contains(" ")) {
				UIError.showError("Spaces are not allow", null);
				fname.requestFocus();
				return false;				
			}			
			
			else if(lname.getText().equals("")) {
				UIError.showError("Invalid Field", "Last Name Field must not be empty!");
				return false;				
			}
	
			if (lname.getText().equals(" ")) {
				UIError.showError("Spaces are not allow", null);
				lname.requestFocus();
				return false;
			}				
						
			else if(street.getText().equals("")) {
				UIError.showError("Invalid Field", "Street Field must not be empty!");
				return false;				
			}
			
			else if(city.getText().equals("")) {
				UIError.showError("Invalid Field", "City Field must not be empty!");
				return false;				
			}
			
			else if(country.getText().equals("")) {
				UIError.showError("Invalid Field", "counry  Field must not be empty!");
				return false;				
			}
			
			else if(contactNo.getText().equals("")) {
				UIError.showError("Invalid Field", "Contact Number Field must not be empty!");
				return false;				
			}			

			else if(cnic.getText().equals("")) {
				UIError.showError("Invalid Field", "CNIC Field must not be empty!");
				return false;				
			}			
			
			else if(dob.getValue().equals("")) {
				UIError.showError("Invalid Field", "Date of Birth Field must not be empty!");
				return false;				
			}			
			
			else if(designation.getValue().toString().equals("")) {
				UIError.showError("Invalid Field", "Designation Field must not be empty!");
				return false;				
			}			
			
			else if(qualification.getText().equals("")) {
				UIError.showError("Invalid Field", "Qualification Field must not be empty!");
				return false;				
			}						

			else if(basicSalary.getText().equals("")) {
				UIError.showError("Invalid Field", "Basic Salary Field must not be empty!");
				return false;				
			}						
			return true;
		}
		else {
			UIError.showError("Invalid Argument", null);
			return false;
		}
	}
	
	private boolean notNull() {
		if (fname.getText() != null && lname.getText() != null) {
			if (street.getText() != null && city.getText() != null && country.getText() != null) {
				if (contactNo.getText() != null && cnic.getText() != null || dob.getValue() != null) {
					if (designation.getValue() != null && qualification.getText() != null && basicSalary.getText() != null) 
						return true;
				}
			}
		}
		
		return false;
	}
	
}
