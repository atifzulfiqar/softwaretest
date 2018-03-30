package CMS.UIL.view.form.manager;


import CMS.BLL.core.Manager;
import CMS.BLL.courier.Rate;
import CMS.UIL.view.Helper;
import ErrorHandler.UIConfirmation;
import ErrorHandler.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditRateController {
	
	Manager manager = null;

	@FXML private TextField  zone;
	@FXML private TextField price;
	@FXML private TextArea desc;
	
	public void init(Manager man) {
		manager = man;
	}
	
	private boolean chkNull () {
		if (zone != null && price!=null && desc!=null)
			return true;
		return false;
	}
	
	private boolean validate() {
		if (chkNull()) {
			if (zone.getText().equals("")) {
				UIError.showError("Empty Field", "Zone Field must not be empty!");
				zone.requestFocus();
				return false;
			}
			
			if (price.getText().equals("")) {
				UIError.showError("Empty Field ", "price Field must not be empty!");
				price.requestFocus();
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
		if (Helper.isNumbericValue(price.getText())) {
			return true;
		}
		else {
			UIError.showError("Invalid Price format", "Must be Numberic");
			price.requestFocus();
			return false;
		}
	}
	
	
	@FXML
	private void saveOpt(ActionEvent e) {
		if (validate()) {
			if (validateForm()) {
				Rate rate = new Rate();
				rate.setAll(Rate.generateID(), zone.getText(), Double.parseDouble(price.getText()), desc.getText());
				rate.setFID(manager.getFID());
				if (manager.addRate(rate)) {
					UIConfirmation.showMsg("Rate List updated Successfully!", null);
					zone.setText("");
					price.setText("");
					desc.setText("");
				}
				else {
					UIError.showError("List did not update successfully!", "Please try later");
				}
			}
		}
 	}
	
}
