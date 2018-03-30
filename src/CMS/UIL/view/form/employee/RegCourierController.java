package CMS.UIL.view.form.employee;

import java.util.ArrayList;

import CMS.BLL.core.Employee;
import CMS.BLL.courier.Rate;
import CMS.BLL.courier.RegisteredCourier;
import CMS.UIL.view.Helper;
import ErrorHandler.UIConfirmation;
import ErrorHandler.UIError;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegCourierController {

	Employee employee;
	
	@FXML private ComboBox<String> sender;
	@FXML private TextField recevier;
	@FXML private TextField Raddr;
	@FXML private TextField Rcontact;
	@FXML private ComboBox<String> type;
	@FXML private TextField weight;
	@FXML private ComboBox<String> zoneBox;
	
	private ObservableList<Rate> rateList;
	
	private void clear() {
		sender.setValue(null);
		sender.setPromptText("Select Sender....");
		recevier.setText("");
		Raddr.setText("");
		Rcontact.setText("");
		type.setValue(null);
		type.setPromptText("Select type.....");
		weight.setText("");
		zoneBox.setValue(null);
		zoneBox.setPromptText("Select Zone.....");
	}
	
	public void init(Employee emp) {
		employee = emp;
		
		rateList = employee.getRateList();
		
		ArrayList<String> temp = employee.getList("customer","CID");
		
		sender.getItems().addAll(temp);
		type.getItems().addAll(employee.getList("type","name"));
		
		ArrayList<String> list = new ArrayList<String>();
		for (int i=0; i<rateList.size(); i++) {
			list.add(rateList.get(i).getZone());
		}
		
		zoneBox.getItems().addAll(list);
	}
	
	private boolean chkNull() {
		if (recevier == null || Raddr == null || weight == null || Rcontact == null)
			return false;
		return true;
	}
	
	private boolean validate() {
		if (chkNull()) {
			if (sender.getValue() == null) {
				UIError.showError("Empty Field", "You must select sender for courier registration");
				sender.requestFocus();
				return false;
			}
			
			if (recevier.getText().equals("")) {
				UIError.showError("Empty Field", "Receiver Field must not be Empty");
				recevier.requestFocus();
				return false;
			}
			
			if (Raddr.getText().equals("")) {
				UIError.showError("Empty Field", "Receiver Address Field must not be Empty");
				Raddr.requestFocus();
				return false;
			}

			if (Rcontact.getText().equals("")) {
				UIError.showError("Empty Field", "Receiver Contact Number Field must not be Empty");
				Rcontact.requestFocus();
				return false;
			}			
			
			if (type.getValue() == null) {
				UIError.showError("Empty Field", "You must select type for courier registration");
				type.requestFocus();
				return false;
			}
			
			if (zoneBox.getValue() == null) {
				UIError.showError("Empty Field", "You must select Zone for courier registration");
				zoneBox.requestFocus();
				return false;
			}			
			
			if (weight.getText().equals("")) {
				UIError.showError("Empty Field", "weight Field must not be Empty");
				weight.requestFocus();
				return false;
			}
			return true;
		}
		else 
			return false;
	}
	
	private boolean validateForm() {
		
		if (Helper.isNumbericValue(weight.getText())) {
			if (Helper.chkContactNo(Rcontact.getText())) {
				return true;
			}
			else {
				UIError.showError("Invalid Contact Number", "Must be Numberic\nUp to 11 Digits\nDo not Enter -");
				Rcontact.requestFocus();
				return false;
			}
		}
		else {
			UIError.showError("Invalid Weight", "Must be numberic");
			weight.requestFocus();
			return false;
		}
	}
	
	@FXML
	private void regCourierOpt(ActionEvent e) {
		if (validate()) {
			if (validateForm()) {
				Double wei = Double.parseDouble(weight.getText());
				Double price = 0.0;
				String temp = zoneBox.getValue().toString();
				for (int i=0; i<rateList.size(); i++) {
					if (rateList.get(i).getZone().equals(temp)) {
						price = rateList.get(i).getPrice();
					}
				}
				price = price * wei;
				RegisteredCourier courier = new RegisteredCourier();
				courier.setAll(RegisteredCourier.generateID(), recevier.getText(), Raddr.getText(), Rcontact.getText(), 
							  type.getValue().toString(), wei, price, employee.getEID(), sender.getValue().toString());
				
				employee.addCourier(courier);
				UIConfirmation.showMsg("Courier Register Successfully!", "Courier Bill is : " + price);
				clear();
			}
		}
	}
	
	@FXML
	private void resetOpt(ActionEvent e) {
		sender.setValue(null);
		recevier.setText("");
		Raddr.setText("");
		type.setValue(null);
		zoneBox.setValue(null);
		weight.setText("");
		UIConfirmation.showMsg("Form Reset", null );
	}	
}
