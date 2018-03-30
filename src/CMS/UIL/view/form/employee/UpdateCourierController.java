package CMS.UIL.view.form.employee;


import CMS.BLL.core.Employee;
import CMS.BLL.courier.RegisteredCourier;
import CMS.BLL.courier.UnRegisteredCourier;
import ErrorHandler.UIConfirmation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateCourierController {

	@FXML private TextField courierID;
	@FXML private Label sname;
	@FXML private Label rname;
	@FXML private Label type;
	@FXML private Label weight;
	@FXML private Label registerOn;
	@FXML private ComboBox<String> status;
	
	@FXML private Label errorMsg;
	
	@FXML private Button updateBtn;
	
	RegisteredCourier obj1 = null;
	UnRegisteredCourier obj2 = null;
	
	Employee employee = null;
	
	public void init(Employee emp) {
		employee = emp;
		errorMsg.setText("");
		status.getItems().addAll("Pending", "Delivered", "Cancelled");
	}
	
	private void clear() {
		errorMsg.setText("");	
		courierID.setText("");
		courierID.setPromptText("Enter courier ID.....");
		sname.setText("");
		rname.setText("");
		type.setText("");
		weight.setText("");
		registerOn.setText("");
		status.setValue(null);
		status.setPromptText("Select Status .....");
		status.setVisible(false);
		updateBtn.setVisible(false);
	}
	
	private void show(RegisteredCourier obj) {
		sname.setText(obj.getCID());
		rname.setText(obj.getRName());
		type.setText(obj.getType());
		weight.setText(obj.getWeight()+"");
		registerOn.setText(obj.getRegDate());
		status.setVisible(true);
		status.setValue(obj.getStatus());
		updateBtn.setVisible(true);
	}
	
	private void show(UnRegisteredCourier obj) {
		sname.setText(obj.getSender());
		rname.setText(obj.getRName());
		type.setText(obj.getType());
		weight.setText(obj.getWeight()+"");
		registerOn.setText(obj.getRegDate());
		status.setVisible(true);;
		status.setValue(obj.getStatus());
		updateBtn.setVisible(false);
	}
	
	@FXML
	private void findOpt(ActionEvent e) {
		if (!(courierID.getText().equals(""))) {
			if (courierID.getText().charAt(0)=='R') {
				obj1 = employee.getRegCourier(courierID.getText());
				if (obj1 != null) {
					show(obj1);
				}
				else {
					clear();
					errorMsg.setText("There is no such courier exits \n Invalid Courier ID");
				}				
			}
			else {
				obj2 = employee.getUnRegCourier(courierID.getText());
				if (obj2 != null) {
					show(obj2);
				}
				else {
					clear();
					errorMsg.setText("There is no such courier exits \n Invalid Courier ID");
				}
			}
		}
		else {
			errorMsg.setText("Courier ID Field is empty");			
		}
	}
	
	@FXML
	private void updateOpt(ActionEvent e) {
		if (obj1 != null) {
			obj1.setStatus(status.getValue().toString());
			employee.updateCourier(obj1);
		}
		else if (obj2 != null) {
			obj2.setStatus(status.getValue().toString());			
			employee.updateCourier(obj2);
		}
		
		UIConfirmation.showMsg("Courier updated successfully!", null);
		
		obj1 = null;
		obj2 = null;
		clear();
	}
	

	
	
}
