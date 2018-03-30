package CMS.UIL.view.form.manager;

import CMS.BLL.core.Manager;
import CMS.UIL.model.EmployeeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class UpdateEmployeeDesginationController {

	@FXML private Label desiLabel;
	@FXML private Label salaryLabel;
	
	@FXML private TextField empidField;
	@FXML private ComboBox<String> designationField;
	@FXML private TextField BasicSalaryField;
	@FXML private Button updateBtn;
	
	@FXML private Label errorLabel;
	
	EmployeeModel temp = null;
	
	
	Manager manager = null;
	
	public void init(Manager man) {
		manager = man;
		errorLabel.setText("");
		
		// initialize the Designation list
		designationField.getItems().addAll(manager.getList("designation","name"));
		
		desiLabel.setVisible(false);
		salaryLabel.setVisible(false);
		designationField.setVisible(false);
		BasicSalaryField.setVisible(false);
		updateBtn.setVisible(false);
	}

	@FXML
	private void searchOpt(ActionEvent e) {
		if (!(empidField.getText().equals(""))) {
			temp = manager.getEmployeeInfo(empidField.getText());
			if (temp != null) {
				designationField.setValue(temp.designation.get());
				BasicSalaryField.setText(temp.salary.get());

				desiLabel.setVisible(true);
				salaryLabel.setVisible(true);
				designationField.setVisible(true);
				BasicSalaryField.setVisible(true);
				updateBtn.setVisible(true);
			}
			else {
				temp = null;
				desiLabel.setVisible(false);
				salaryLabel.setVisible(false);
				designationField.setVisible(false);
				BasicSalaryField.setVisible(false);
				updateBtn.setVisible(false);				
				errorLabel.setText("There is no Such Employee exits in our database");
			}
		}
	}
	
	@FXML
	private void updateOpt(ActionEvent e) {
		if (temp != null) {
			temp.updateDesignation(designationField.getValue().toString());
			temp.updateSalary(BasicSalaryField.getText());			
			manager.updateEmployee(temp);
		}
	}
	
}
