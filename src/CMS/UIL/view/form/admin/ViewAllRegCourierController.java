package CMS.UIL.view.form.admin;

import java.util.ArrayList;

import CMS.BLL.core.Admin;
import CMS.BLL.franchise.Franchise;
import CMS.UIL.model.CourierModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ViewAllRegCourierController {

	@FXML private ComboBox<String> franchise;
	@FXML private ComboBox<String> typeList;
	@FXML private DatePicker courierDate;
	
	@FXML private TableView<CourierModel> courierList;
	
	@FXML private TableColumn<CourierModel, String> sname;
	@FXML private TableColumn<CourierModel, String> saddr;

	@FXML private TableColumn<CourierModel, String> rname;
	@FXML private TableColumn<CourierModel, String> raddr;
	
	@FXML private TableColumn<CourierModel, String> date;
	@FXML private TableColumn<CourierModel, String> type;
	@FXML private TableColumn<CourierModel, String> weight;
	@FXML private TableColumn<CourierModel, String> price;
	@FXML private TableColumn<CourierModel, String> empid;	

	@FXML private Label totalCourier;
	@FXML private Label totalPrice;
	
	
    StringBuilder  p =  new StringBuilder();	
    ObservableList<CourierModel> list = null;	
    
    ObservableList<CourierModel> listcopy = null;

    ObservableList<Franchise> fl = null;
    
    Admin admin = null;
	
	public void init(Admin a) {
		// initialize the administrator
		admin = a;
		// retrieve the courier list
		list= admin.getRegCourierList(p);
		// make a copy of list
		listcopy = list;
		// retrieve the franchise list
		fl = admin.retreieveFranchiseList();
		
		ArrayList<String> franchiselist = new ArrayList<String>();
		
		for (int i=0; i<fl.size(); i++) 
			franchiselist.add(fl.get(i).getName());
		
		// initialize the franchise list
		franchise.getItems().addAll(franchiselist);
		// initialize the type list
		typeList.getItems().addAll(admin.getList("type","name"));
		
		
        // Initialize the Courier table
        sname.setCellValueFactory(cellData -> cellData.getValue().SName);
        saddr.setCellValueFactory(cellData -> cellData.getValue().Saddress);
        rname.setCellValueFactory(cellData -> cellData.getValue().rname);
        raddr.setCellValueFactory(cellData -> cellData.getValue().raddr);
        date.setCellValueFactory(cellData -> cellData.getValue().date);        
        type.setCellValueFactory(cellData -> cellData.getValue().type);
        weight.setCellValueFactory(cellData -> cellData.getValue().weight);
        price.setCellValueFactory(cellData -> cellData.getValue().price); 
        empid.setCellValueFactory(cellData -> cellData.getValue().registerby);        
    
        courierList.setItems(list);
        
		totalCourier.setText(list.size()+"");
		totalPrice.setText(p+"\tRupees");
	}
	
	private String getFranchiseID(String fname) {
		if (fl != null || fl.size() <= 0) {
			for (int i=0; i<fl.size(); i++) {
				if (fl.get(i).getName().equals(fname))
					return fl.get(i).getFID();
			}
		}
		return null;
	}
	
	private void filterFranchise(String fname) {
		String id = getFranchiseID(fname);
		if (id != null) {
			ObservableList<String> employeeList = admin.getEmployeeIDList(id);
			if (employeeList != null) {
				if (employeeList.size() <= 0) {
					listcopy = null;
				}
				else {
					listcopy = FXCollections.observableArrayList();
					for (int i=0; i<list.size(); i++) {
						for (int j=0; j<employeeList.size(); j++) {
							if (list.get(i).registerby.get().equals(employeeList.get(j))) {
								listcopy.add(list.get(i));
							}
						}
					}
				}
			}
		}
	}
	
	
	private void filterType(String t) {
		ObservableList<CourierModel> temp = listcopy;
		listcopy = FXCollections.observableArrayList();		
		for (int i=0; i<temp.size(); i++) {
			if (temp.get(i).type.get().equals(t)) {
				listcopy.add(temp.get(i));
			}
		}
	}
	
	private void filterDate(String t) {
		ObservableList<CourierModel> temp = listcopy;
		listcopy = FXCollections.observableArrayList();
		for (int i=0; i<temp.size(); i++) {
			if (temp.get(i).date.get().equals(t)) {
				listcopy.add(temp.get(i));
			}
		}
	}
	
	@FXML
	public void filterOpt(ActionEvent e) {
		if (franchise.getValue() != null) {
			filterFranchise(franchise.getValue());
		}
		
		if (typeList.getValue() != null) {
			filterType(typeList.getValue());
		}
		
		if (courierDate.getValue() != null) {
			filterDate(courierDate.getValue().toString());
		}
		
        courierList.setItems(listcopy);
        
        double price = 0.0;
        for (int i=0; i<listcopy.size(); i++) {
        	price += Double.parseDouble(listcopy.get(i).price.get());
        }
       
		totalCourier.setText(listcopy.size()+"");
		totalPrice.setText(price+"\tRupees");		
		
	}
	
	@FXML
	private void resetOpt(ActionEvent e) {
		
		franchise.setPromptText("Select Franchise");		
		typeList.setPromptText("Select Type...");	
		courierDate.setPromptText("Select Date...");		
		
        courierList.setItems(list);	
		totalCourier.setText(list.size()+"");
		totalPrice.setText(p+"\tRupees");        
	}
	
}
