package CMS.UIL.view.form.manager;


import java.util.ArrayList;

import CMS.BLL.core.Manager;
import CMS.BLL.franchise.Allowance;
import CMS.UIL.model.AllowanceModel;
import ErrorHandler.UIConfirmation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditAllowanceController {

	@FXML private Label errorMsg;
	
	@FXML private ComboBox<String> typeList;
	@FXML private TextField typeField;
	@FXML private TextField amountField;
	@FXML private TextArea descField;
	
	String id = null;
	Manager manager = null;
	
	ObservableList<AllowanceModel> list = null;
	
	public void init(Manager man) {
		manager = man;
		hide();
		errorMsg.setText("");
		list = manager.getAllowanceList();
		
		ArrayList<String> L = new ArrayList<String>();
		for (int i=0; i<list.size(); i++) {
			L.add(list.get(i).type.get());
		}
		
		typeList.getItems().addAll(L);
	}
	
	private void hide() {
		id = null;
		typeList.setValue(null);
		typeList.setPromptText("Select the Allowance type");
		typeField.setText("");
		amountField.setText("");
		descField.setText("");
	}
	
	@FXML
	private void searchOpt(ActionEvent e) {
		if (typeList.getValue() != null) {
			for (int i=0; i<list.size(); i++) {
				if (list.get(i).type.get().equals(typeList.getValue().toString())) {
					id = list.get(i).AID.get();
					typeField.setText(list.get(i).type.get());
					amountField.setText(list.get(i).amount.get());
					descField.setText(list.get(i).desc.get());					
				}
			}
		}
	}
	
	@FXML
	private void updateOpt(ActionEvent e) {
		if (typeList.getValue() != null) {
			Allowance allowance = new Allowance();
			allowance.setAll(id, descField.getText(), typeField.getText(), Double.parseDouble(amountField.getText()));
			if (manager.updateAllowance(allowance)) {
				UIConfirmation.showMsg("Allowance Updated Successfully!", null);
				hide();
			}
		}
	}	
}
