package CMS.UIL.view.form.manager;

import CMS.BLL.core.Manager;
import CMS.UIL.model.EmployeeModel;
import ErrorHandler.UIConfirmation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditEmployeeController {

	@FXML private TextField empidField;
	@FXML private TextField fnameField;
	@FXML private TextField lnameField;
	@FXML private TextField addressField;
	@FXML private TextField contactField;
	@FXML private TextField cnicField;
	@FXML private ComboBox<String> designationList;
	@FXML private TextField qualificationField;
	@FXML private TextField salaryField;
	
	@FXML private Button updateBtn;
	@FXML private Button deleteBtn;
	
	@FXML private Label errorLabel;

	
	EmployeeModel employee = null;
	Manager manager = null;
	
	public void init(Manager man) {
		manager = man;
		show(false);
		errorLabel.setText("");
		
		// initialize the Designation list
		designationList.getItems().addAll(manager.getList("designation","name"));
	}
	
	private void show(boolean v) {
		empidField.setText("");
		empidField.setPromptText("Enter the Employee ID");
		empidField.requestFocus();
		fnameField.setVisible(v);
		lnameField.setVisible(v);
		addressField.setVisible(v);
		contactField.setVisible(v);
		cnicField.setVisible(v);
		designationList.setVisible(v);
		qualificationField.setVisible(v);
		salaryField.setVisible(v);
		updateBtn.setVisible(v);
		deleteBtn.setVisible(v);
	}
	
	@FXML
	private void findOpt(ActionEvent e) {
		if (!(empidField.getText().equals(""))) {
			employee = manager.getEmployeeInfo(empidField.getText());
			if (employee != null) {
				show(true);
				fnameField.setText(employee.fname.get());
				lnameField.setText(employee.lname.get());
				addressField.setText(employee.address.get());
				contactField.setText(employee.contact.get());
				cnicField.setText(employee.cnic.get());
				designationList.setValue(employee.designation.get());
				qualificationField.setText(employee.qal.get());
				salaryField.setText(employee.salary.get());
			}
			else {
				employee = null;
				show(false);
				errorLabel.setText("There is no such employee exists in our database");
			}
		}
	}
	
	@FXML void updateOpt(ActionEvent e) {
		if (employee != null) {
			employee.setAll(fnameField.getText(), lnameField.getText(), addressField.getText(), contactField.getText(), 
					cnicField.getText(), employee.dob.get(), designationList.getValue().toString(), qualificationField.getText(), 
					employee.jd.get(), salaryField.getText());
			manager.updateEmployee(employee);
		}
	}
	
	@FXML void deleteOpt(ActionEvent e) {
		if (employee != null) {
			if (manager.fireEmployee(employee.id.get())) {
				show(false);
				employee = null;				
				UIConfirmation.showMsg("Employee data delete successfully!", null);
			}
		}
	}
}
