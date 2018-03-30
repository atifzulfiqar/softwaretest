package CMS.UIL.view.form.manager;

import CMS.BLL.core.Manager;
import CMS.BLL.courier.Rate;
import CMS.UIL.model.RateModel;
import ErrorHandler.UIConfirmation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateRate {

	@FXML private TextField zoneField;
	@FXML private TextField amountField;
	@FXML private TextArea descField;
	
	RateModel rate = null;
	Manager manager = null;
	
	Stage stage = null;
	
	public void init(Stage s, Manager man, RateModel r) {
		stage = s;
		rate = r;
		manager = man;
		
		zoneField.setText(rate.zone.get());
		amountField.setText(rate.price.get());
		descField.setText(rate.desc.get());
		
	}
	
	@FXML
	private void updateOpt(ActionEvent e) {
		Rate R = new Rate();
    	R.setAll(rate.RID.get(), zoneField.getText(), Double.parseDouble(amountField.getText()), descField.getText());
    	if (manager.updateRate(R)) {
    		UIConfirmation.showMsg("Rate Update successfully!", null);
    		stage.close();
    	}
	}
}
