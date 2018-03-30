package CMS.UIL.view.form.employee;

import CMS.BLL.core.Employee;
import CMS.BLL.core.Loan;
import CMS.UIL.view.Helper;
import ErrorHandler.UIConfirmation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TakeLoanController {

	@FXML private TextField amount;
	@FXML private TextArea desc;
	
	@FXML private Label errorMsg;
	
	Employee employee = null;
	
	public void init(Employee emp) {
		employee = emp;
		clear();	
		errorMsg.setText("");
	}
	
	private void clear() {
		errorMsg.setText("");
		amount.setText("");
		desc.setText("");
		amount.setPromptText("Enter Amount here.....");
		desc.setPromptText("Enter Description....... (optional)");
	}
	
	@FXML
	private void submitOpt(ActionEvent e) {
		if (!(amount.getText().equals(""))) {
			if (Helper.isNumbericValue(amount.getText())) {
				Loan loan = new Loan();
				loan.getNewID();
				loan.setAll(loan.getLID(), Double.parseDouble(amount.getText()), desc.getText());
				employee.requestLoan(loan);
				UIConfirmation.showMsg("Loan request successfully submitted", null);
				clear();
			}
			else {
				errorMsg.setText("Invalid Amount format");
			}
		}
		else {
			errorMsg.setText("Amount Field is empty");
			clear();
		}
	}	
}
