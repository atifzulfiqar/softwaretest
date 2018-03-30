package CMS.UIL.view.form.manager;


import CMS.BLL.core.Manager;
import CMS.BLL.franchise.Allowance;
import CMS.UIL.view.Helper;
import ErrorHandler.UIConfirmation;
import ErrorHandler.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddAllowanceController {

	@FXML private TextField type;
	@FXML private TextField amount;
	@FXML private TextArea desc;
	
	Manager manager = null;
	
	public void init(Manager man) {
		manager = man;
	}

	private boolean NotNULL() {
		if (type.getText() != null || amount.getText() != null || desc.getText() != null)
			return true;
		return false;
	}
	
	private boolean validate() {
		if (NotNULL()) {
			if (type.getText().equals("")) {
				UIError.showError("Invalid Parameter", "Type Field must not be empty!");
				type.requestFocus();
				return false;
			}
			
			else if (amount.getText().equals("")) {
				UIError.showError("Invalid Parameters", "Amount Field must not be empty");
				amount.requestFocus();
				return false;
			}
			return true;
		}
		else {
			UIError.showError("Invalid Argument", null);
			return false;
		}
	}
	
	private boolean validateForm() {
		if (Helper.isNumbericValue(amount.getText())) {
			return true;
		}
		else {
			UIError.showError("Invalid Amount Format", "Must be Numberic");
			amount.requestFocus();
			return false;
		}
	}
	
	@FXML
	private void saveInfoOpt(ActionEvent e) {
		if (validate()) {
			if (validateForm()) {
				Allowance allowance = new Allowance();
				allowance.setAll(Allowance.generateID(), desc.getText(), type.getText(), Double.parseDouble(amount.getText()));
				if (manager.addAllowance(allowance)) {
					UIConfirmation.showMsg("New Type of Allowances added Successfully", null);
					clear();
				}
				else {
					UIError.showError("Data did not saved!", "Please try Later");
				}
			}
		}
	}
	
	private void clear() {
		type.setText("");
		amount.setText("");
		desc.setText("");		
	}
	
	@FXML
	private void resetOpt(ActionEvent e) {
		clear();
		UIConfirmation.showMsg("Form Reset", null);
	}
	
}
