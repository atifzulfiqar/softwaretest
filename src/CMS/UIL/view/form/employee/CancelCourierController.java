package CMS.UIL.view.form.employee;

import CMS.BLL.core.Employee;
import CMS.BLL.courier.RegisteredCourier;
import CMS.BLL.courier.UnRegisteredCourier;
import CMS.UIL.model.CourierModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



public class CancelCourierController {
	
	@FXML private Label errorMsg;
	
	@FXML private TextField courierID;
	@FXML private TableView<CourierModel> courierList;
	@FXML private TableColumn<CourierModel, String> sname;
	@FXML private TableColumn<CourierModel,String> rname;
	@FXML private TableColumn<CourierModel, String> addr;
	@FXML private TableColumn<CourierModel, String> typeCol;
	@FXML private TableColumn<CourierModel, String> weightCol;
	@FXML private TableColumn<CourierModel, String> priceCol;
	
	@FXML private Button cancelShipBtn;
	
	Employee employee = null;
	CourierModel courier = null;
	
	RegisteredCourier temp = null;
	UnRegisteredCourier temp1 = null;
	
	public void init(Employee emp) {
		employee = emp;
		errorMsg.setText("");
		cancelShipBtn.setVisible(false);
		
        // Initialize the Courier List table
        sname.setCellValueFactory(cellData -> cellData.getValue().SName);
        rname.setCellValueFactory(cellData -> cellData.getValue().rname);
        addr.setCellValueFactory(cellData -> cellData.getValue().raddr);
        typeCol.setCellValueFactory(cellData -> cellData.getValue().type);
        weightCol.setCellValueFactory(cellData -> cellData.getValue().weight);
        priceCol.setCellValueFactory(cellData -> cellData.getValue().price);		
	}
	
	@FXML
	private void searchOpt(ActionEvent e) {

		if (!(courierID.getText().equals(""))) {
			if (courierID.getText().length() == 15) {
				if (courierID.getText().charAt(0)=='R') {
					temp = employee.getRegCourier(courierID.getText());
					courier = new CourierModel(temp.getCID(), temp.getRName(), temp.getRAddress(), temp.getRContactNumber(), 
											   temp.getRegDate(), temp.getPrice()+"", temp.getType(), temp.getWeight()+"",
											   temp.getStatus(), temp.getRegisterBy(), temp.getCustomerID(), "", "", "");
				    ObservableList<CourierModel> list = FXCollections.observableArrayList();
				    list.add(courier);
				    courierList.setItems(list);
					cancelShipBtn.setVisible(true);
				}
				else if (courierID.getText().charAt(0)=='U') {
					temp1 = employee.getUnRegCourier(courierID.getText());
					courier = new CourierModel(temp1.getCID(), temp1.getRName(), temp1.getRAddress(), temp1.getRContactNumber(), 
											   temp1.getRegDate(), temp1.getPrice()+"", temp1.getType(), temp1.getWeight()+"",
											   temp1.getStatus(), temp1.getRegisterBy(), temp1.getSender(), "", "", "");
				    ObservableList<CourierModel> list = FXCollections.observableArrayList();
				    list.add(courier);
				    courierList.setItems(list);
					cancelShipBtn.setVisible(true);					
				}
				else {
					errorMsg.setText("Invalid Courier ID");
					cancelShipBtn.setVisible(false);					
				}
			}
			else {
				errorMsg.setText("Invalid Courier ID");
				cancelShipBtn.setVisible(false);
			}
		}
		else {
			errorMsg.setText("Courier ID field is empty");
			cancelShipBtn.setVisible(false);			
		}
	}
	
	@FXML
	private void cancelOpt(ActionEvent e) {
		if (!(courierID.getText().equals(""))) {
			if (courier != null) {
				employee.CancelCourier(courier);
			}
		    ObservableList<CourierModel> list = null;
		    courierList.setItems(list);			
			courier = null;
			errorMsg.setText("");
			cancelShipBtn.setVisible(false);			
			courierID.setText("");
			courierID.requestFocus();
			courierID.setPromptText("Cxxxxxxxxxxxxxx");
			
		}
	}
	
}
