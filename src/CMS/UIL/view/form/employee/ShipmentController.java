package CMS.UIL.view.form.employee;

import CMS.BLL.core.Employee;
import CMS.UIL.model.CourierModel;
import CMS.UIL.view.Helper;
import ErrorHandler.UIConfirmation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ShipmentController {

	@FXML private TableView<CourierModel> courierList;
	@FXML private TableColumn<CourierModel, String> sname;
	@FXML private TableColumn<CourierModel, String> rname;
	@FXML private TableColumn<CourierModel, String> addr;
	@FXML private TableColumn<CourierModel, String> typeCol;
	@FXML private TableColumn<CourierModel, String> weightCol;
	@FXML private TableColumn<CourierModel, String> priceCol;
	@FXML private TableColumn<CourierModel, String> statusCol;
	
	@FXML private TextField empid;
	@FXML private TextField vehicle;
	
	@FXML private Label empError;
	@FXML private Label vehError;
	
	@FXML private Button shipBtn;
	
	Employee employee = null;
	
    ObservableList<CourierModel> list = FXCollections.observableArrayList();
	
	
	public void init(Employee emp) {
		empError.setText("");
		vehError.setText("");
		
		employee = emp;
		list = employee.getPendingCourier();
		
		
		empid.setText(employee.getEID());
		
        // Initialize the Courier List table
        sname.setCellValueFactory(cellData -> cellData.getValue().SName);
        rname.setCellValueFactory(cellData -> cellData.getValue().rname);
        addr.setCellValueFactory(cellData -> cellData.getValue().raddr);
        typeCol.setCellValueFactory(cellData -> cellData.getValue().type);
        weightCol.setCellValueFactory(cellData -> cellData.getValue().weight);
        priceCol.setCellValueFactory(cellData -> cellData.getValue().price);	
        statusCol.setCellValueFactory(cellData -> cellData.getValue().status);	
        
        if (list != null && list.size()!=0) {
        	courierList.setItems(list);
            shipBtn.setVisible(true);
            empid.setVisible(true);
            empid.requestFocus();
            vehicle.setVisible(true);
        }
        else {
            shipBtn.setVisible(false);
            empid.setVisible(true);
            vehicle.setVisible(true); 
            empError.setText("");
            vehError.setText("");
            
        }

	}
	
	@FXML 
	private void shipOpt(ActionEvent e) {
		if (list != null) {
			if (!(empid.getText().equals(""))) {
				if (empid.getText().charAt(0)=='E' && empid.getText().length() == 7) {
					if (!(vehicle.getText().equals(""))) {
						if (vehicle.getText().length() == 7) {
							if (Helper.chkID(empid.getText())) {
								if (employee.makeShipment(empid.getText(), vehicle.getText(), list)) {
									UIConfirmation.showMsg("Courier Shipped", null);
									list = null;
						        	courierList.setItems(list);
						            shipBtn.setVisible(false);
						            empid.setVisible(false);
						            vehicle.setVisible(false);
						            empError.setText("");
						            vehError.setText("");
								}
							}
							else {
								empError.setText("Invalid Employee ID");
								empid.requestFocus();							
							}
						}
						else {
							vehError.setText("Invalid Vehicle Number");
							vehicle.requestFocus();						
						}
					}
					else {
						vehError.setText("Vehicle Number Field is Empty");
						vehicle.requestFocus();					
					}
				}
				else {
					empError.setText("Invalid Employee ID");
					empid.requestFocus();
				}
			}
			else {
				empError.setText("Delivered To field is empty");
				empid.requestFocus();
			}
		}
	}
	
	
}
