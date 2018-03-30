package CMS.UIL.view.form.employee;

import CMS.BLL.core.Employee;
import CMS.BLL.core.Manager;
import CMS.UIL.model.CourierModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ViewCourierListController {

	@FXML private RadioButton regCustomer;
	@FXML private RadioButton unregCustomer;
	
	@FXML private ComboBox<String> courierType;
	@FXML private ComboBox<String> courierStatus;
	@FXML private DatePicker courierDate;
	
	@FXML private TableView<CourierModel> courierList;
	@FXML private TableColumn<CourierModel, String> cidCol;
	@FXML private TableColumn<CourierModel, String> snameCol;
	@FXML private TableColumn<CourierModel, String> rnameCol;
	@FXML private TableColumn<CourierModel,String> raddressCol;
	@FXML private TableColumn<CourierModel, String> dateCol;
	@FXML private TableColumn<CourierModel, String> typeCol;
	@FXML private TableColumn<CourierModel, String> weightCol;
	@FXML private TableColumn<CourierModel, String> priceCol;
	@FXML private TableColumn<CourierModel, String> statusCol;
	@FXML private TableColumn<CourierModel, String> empidCol;
	
	@FXML private Label totalCourier;
	@FXML private Label totalPrice;
	
	StringBuilder p = new StringBuilder();
	private ObservableList<CourierModel> list;
	private ObservableList<CourierModel> listcopy;
	
	Double price = 0.0;
	
	Employee employee = null;
	Manager manager = null;
	
	public void init(Employee emp, String fid) {
		employee = emp;
		
		list = employee.getCourierList(p, fid);
		listcopy = list;
		
		regCustomer.setSelected(false);
		unregCustomer.setSelected(false);
		
		// initialize the courier Type list
		courierType.getItems().addAll(employee.getList("type", "name"));
		
		// initialize the courier status list
		courierStatus.getItems().addAll("Delivered", "Pending", "Cancelled");
		
		// initialize the Courier List Table
        cidCol.setCellValueFactory(cellData -> cellData.getValue().CID);		
        snameCol.setCellValueFactory(cellData -> cellData.getValue().SName);
        rnameCol.setCellValueFactory(cellData -> cellData.getValue().rname);        
        raddressCol.setCellValueFactory(cellData -> cellData.getValue().raddr);
        dateCol.setCellValueFactory(cellDate -> cellDate.getValue().date);
        typeCol.setCellValueFactory(cellData -> cellData.getValue().type);
        weightCol.setCellValueFactory(cellData -> cellData.getValue().weight);        
        priceCol.setCellValueFactory(cellData -> cellData.getValue().price);
        statusCol.setCellValueFactory(cellData -> cellData.getValue().status);
        empidCol.setCellValueFactory(cellData -> cellData.getValue().registerby); 
    
        courierList.setItems(list);
        
		totalCourier.setText(list.size()+"");
		totalPrice.setText(p+"\tRupees");
	}
	
	public void init(Manager emp, String fid) {
		manager = emp;
		
		list = manager.getCourierList(p, fid);
		listcopy = list;
		
		regCustomer.setSelected(false);
		unregCustomer.setSelected(false);
		
		// initialize the courier Type list
		courierType.getItems().addAll(manager.getList("type", "name"));
		
		// initialize the courier status list
		courierStatus.getItems().addAll("Delivered", "Pending", "Cancel");
		
		// initialize the Courier List Table
        cidCol.setCellValueFactory(cellData -> cellData.getValue().CID);		
        snameCol.setCellValueFactory(cellData -> cellData.getValue().SName);
        rnameCol.setCellValueFactory(cellData -> cellData.getValue().rname);        
        raddressCol.setCellValueFactory(cellData -> cellData.getValue().raddr);
        dateCol.setCellValueFactory(cellDate -> cellDate.getValue().date);
        typeCol.setCellValueFactory(cellData -> cellData.getValue().type);
        weightCol.setCellValueFactory(cellData -> cellData.getValue().weight);        
        priceCol.setCellValueFactory(cellData -> cellData.getValue().price);
        statusCol.setCellValueFactory(cellData -> cellData.getValue().status);
        empidCol.setCellValueFactory(cellData -> cellData.getValue().registerby); 
    
        courierList.setItems(list);
        
		totalCourier.setText(list.size()+"");
		totalPrice.setText(p+"\tRupees");
	}	
	
	private void filterCourierType() {
		String type = courierType.getValue();
		listcopy = FXCollections.observableArrayList();
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).type.get().equals(type)) {
				listcopy.add(list.get(i));
			}
		}
	}
	
	private void filterCourierStatus() {
		String type = courierStatus.getValue();
		ObservableList<CourierModel> temp = listcopy;
		listcopy = FXCollections.observableArrayList();
		
		for (int i=0; i<temp.size(); i++) {
			if (temp.get(i).status.get().equals(type)) 
				listcopy.add(temp.get(i));
		}
	}
	
	private void filterCourierDate() {
		String date = courierDate.getValue().toString();
		ObservableList<CourierModel> temp = listcopy;
		listcopy = FXCollections.observableArrayList();
		for (int i=0; i<temp.size(); i++) {
			if (temp.get(i).date.get().equals(date)){
				listcopy.add(temp.get(i));
			}
		}
	}
	
	@FXML
	private void filterOpt(ActionEvent e) {
		if (courierType.getValue() != null) {
			filterCourierType();
		}
		
		if (courierStatus.getValue() != null) {
			filterCourierStatus();
		}
				
		if (courierDate.getValue() != null) {
			filterCourierDate();
		}
		

		for (int i=0; i<listcopy.size(); i++)
			price += Double.parseDouble(listcopy.get(i).price.get());
		
        courierList.setItems(listcopy);
        listcopy = list;
		totalCourier.setText(listcopy.size()+"");
		totalPrice.setText(price+"\tRupees");
	}
	
	@FXML
	private void showRegisterCustomerOpt(ActionEvent e) {
		if (regCustomer.isSelected() && unregCustomer.isSelected()) {
	        courierList.setItems(listcopy);
	        listcopy = list;
			totalCourier.setText(listcopy.size()+"");
			totalPrice.setText(price+"\tRupees");			
		}
		else {
			if (regCustomer.isSelected()) {
				ObservableList<CourierModel> temp = listcopy;
				listcopy = FXCollections.observableArrayList();	
				double price = 0.0;
				for (int i=0; i<temp.size(); i++) {
					if (temp.get(i).CID.get().charAt(0) == 'R') {
						listcopy.add(temp.get(i));
						price += Double.parseDouble(temp.get(i).price.get());
					}
				}
		        courierList.setItems(listcopy);
		        listcopy = list;
				totalCourier.setText(listcopy.size()+"");
				totalPrice.setText(price+"\tRupees");			
			}
		}
	}
	
	@FXML
	private void showUnregisterCustomerOpt(ActionEvent e) {
		if (regCustomer.isSelected() && unregCustomer.isSelected()) {
	        courierList.setItems(listcopy);
	        listcopy = list;
			totalCourier.setText(listcopy.size()+"");
			totalPrice.setText(price+"\tRupees");			
		}
		else {
			if (unregCustomer.isSelected()) {
				double price = 0.0;
				ObservableList<CourierModel> temp = listcopy;
				listcopy = FXCollections.observableArrayList();			
				for (int i=0; i<temp.size(); i++) {
					if (temp.get(i).CID.get().charAt(0) == 'U') {
						listcopy.add(temp.get(i));
						price += Double.parseDouble(temp.get(i).price.get());				
					}
				}
		        courierList.setItems(listcopy);
		        listcopy = list;
				totalCourier.setText(listcopy.size()+"");
				totalPrice.setText(price+"\tRupees");			
			}
		}
	}

	
	@FXML 
	private void resetOpt(ActionEvent e) {
	
		courierType.setValue(null);
		courierStatus.setValue(null);
		courierDate.setValue(null);
		
		regCustomer.setSelected(false);
		unregCustomer.setSelected(false);
		
		courierType.setPromptText("Select the courier Type");
		courierStatus.setPromptText("Select the Courier Status");
		courierDate.setPromptText("Select any Date");
		
        courierList.setItems(list);

		totalCourier.setText(list.size()+"");
		totalPrice.setText(p+"\tRupees");
		
		
	}
}
