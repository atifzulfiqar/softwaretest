package CMS.UIL.view.form.employee;

import java.util.ArrayList;

import CMS.BLL.core.Employee;
import CMS.BLL.courier.Rate;
import CMS.BLL.courier.UnRegisteredCourier;
import CMS.UIL.view.Helper;
import ErrorHandler.UIConfirmation;
import ErrorHandler.UIError;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class UnregCourierController {
	
	Employee employee;

	@FXML private TextField sfname;
	@FXML private TextField slname;
	@FXML private TextField saddr;
	@FXML private TextField scnic;
	@FXML private TextField scontact;
	@FXML private TextField rname;
	@FXML private TextField raddr;
	@FXML private TextField rcontact;
	
	@FXML private ComboBox<String> type;
	@FXML private ComboBox<String> zoneBox;
	@FXML private TextField weight;

	private ObservableList<Rate> rateList;	
	
	private void clear() {
		sfname.setText("");
		slname.setText("");
		saddr.setText("");
		scnic.setText("");
		scontact.setText("");
		rname.setText("");
		raddr.setText("");
		rcontact.setText("");
		type.setValue(null);
		type.setPromptText("Select type.....");
		weight.setText("");
		zoneBox.setValue(null);
		zoneBox.setPromptText("Select Zone.....");
	}	
	
	
	public void init(Employee emp) {
		employee = emp;
		
		rateList = employee.getRateList();
				
		type.getItems().addAll(employee.getList("type","name"));
		
		ArrayList<String> list = new ArrayList<String>();
		for (int i=0; i<rateList.size(); i++) {
			list.add(rateList.get(i).getZone());
		}
		
		zoneBox.getItems().addAll(list);
	}
	
	private boolean chkNull() {
		if (sfname==null || slname == null || saddr == null || scnic == null || scontact==null || rname==null || raddr==null || rcontact == null
				|| weight==null)
			return false;
		return true;
	}
	
	private boolean validate() {
		if (chkNull()) {
			if (sfname.getText().equals("")) {
				UIError.showError("Empty Field", "Sender First Name Fied must not be empty");
				sfname.requestFocus();
				return false;
			}

			if (saddr.getText().equals("")) {
				UIError.showError("Empty Field", "Sender Address Fied must not be empty");
				saddr.requestFocus();
				return false;
			}

			if (scnic.getText().equals("")) {
				UIError.showError("Empty Field", "Sender CNIC Fied must not be empty");
				scnic.requestFocus();
				return false;
			}
			
			if (scontact.getText().equals("")) {
				UIError.showError("Empty Field", "Sender Contact Number Fied must not be empty");
				scontact.requestFocus();
				return false;
			}
			
			
			if (rname.getText().equals("")) {
				UIError.showError("Empty Field", "Receiver Name Field must not be Empty");
				rname.requestFocus();
				return false;
			}
			
			if (raddr.getText().equals("")) {
				UIError.showError("Empty Field", "Receiver Address Field must not be Empty");
				raddr.requestFocus();
				return false;
			}

			if (rcontact.getText().equals("")) {
				UIError.showError("Empty Field", "Receiver Contact Number Field must not be Empty");
				rcontact.requestFocus();
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
		else {
			UIError.showError("Invalid Argument", null);
			return false;
		}
	}
	
	private boolean validateForm() {
		if (Helper.chkCNIC(scnic.getText())) {
			if (Helper.chkContactNo(scontact.getText())) {
				if (Helper.chkContactNo(rcontact.getText())) {
					if (Helper.isNumbericValue(weight.getText())) {
						return true;
					}
					else {
						UIError.showError("Invalid Weight", "Must be Numberic");
						weight.requestFocus();
						return false;						
					}
				}
				else {
					UIError.showError("Invalid Receiver Contact Number", "Must be Numberic\nUp to 11 characters\nDo not use '-'");
					rcontact.requestFocus();
					return false;
				}
			}
			else {
				UIError.showError("Invalid Sender Contact Number", "Must be Numberic\nUp to 11 characters\nDo not use '-'");
				scontact.requestFocus();
				return false;
			}
		}
		else {
			UIError.showError("Invalid CNIC", "Must be Numberic\nUp to 13 characters\nDo not use '-'");
			scnic.requestFocus();
			return false;			
		}
	}
	
	
	@FXML
	private void regCourier(ActionEvent e) {
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
				UnRegisteredCourier courier = new UnRegisteredCourier();
				courier.setAll(	UnRegisteredCourier.generateID(), rname.getText(), raddr.getText(), rcontact.getText(), 
								sfname.getText(), slname.getText(), saddr.getText(), scontact.getText(), scnic.getText(), price,
								type.getValue().toString(), wei, employee.getEID());
				courier.setRegDate();
				employee.addCourier(courier);
				UIConfirmation.showMsg("Courier Register Successfully!", "Courier Bill is : " + price);
				clear();
			}
		}
	}
	
	@FXML
	private void reset(ActionEvent e) {
		sfname.setText("");
		slname.setText("");
		saddr.setText("");
		scnic.setText("");
		scontact.setText("");
		
		rname.setText("");
		raddr.setText("");
		rcontact.setText("");
		
		type.setValue(null);
		zoneBox.setValue(null);
		
		weight.setText("");
		
		UIConfirmation.showMsg("form reset", null);
	}
	
}
