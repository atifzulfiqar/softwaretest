package CMS.UIL.view.form.manager;


import CMS.BLL.core.*;
import CMS.UIL.model.EmployeeModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



public class ViewEmployeeController {

	Manager manager;
	
	@FXML private ComboBox<String> desginationList;
	@FXML private TextField salaryField;
	@FXML private DatePicker joiningDateField;
	
	@FXML private TableView<EmployeeModel> employeeList;

	@FXML private TableColumn<EmployeeModel, String> empid;	
	@FXML private TableColumn<EmployeeModel, String> fname;
	@FXML private TableColumn<EmployeeModel, String> lname;
	@FXML private TableColumn<EmployeeModel, String> addr;
	@FXML private TableColumn<EmployeeModel, String> contact;	
	@FXML private TableColumn<EmployeeModel, String> cnic;
	@FXML private TableColumn<EmployeeModel, String> dob;
	@FXML private TableColumn<EmployeeModel, String> designation;
	@FXML private TableColumn<EmployeeModel, String> qualification;
	@FXML private TableColumn<EmployeeModel, String> jd;	
	@FXML private TableColumn<EmployeeModel, String> salary;	
	
	@FXML private Label totalEmployee;	
	
    ObservableList<EmployeeModel> list;
    ObservableList<EmployeeModel> listcopy;
    
	public void init(Manager man) {
		// initialize the manager 
		manager = man;
		// get the list of employee
		list = manager.getRegEmployeeList(manager.getFID());
		// make a copy of employee list
		listcopy = list;		
		// initialize the Designation list
		desginationList.getItems().addAll(manager.getList("designation","name"));

        // Initialize the Employee table
        empid.setCellValueFactory(cellData -> cellData.getValue().id);		
        fname.setCellValueFactory(cellData -> cellData.getValue().fname);
        lname.setCellValueFactory(cellData -> cellData.getValue().lname);
        addr.setCellValueFactory(cellData -> cellData.getValue().address);
        contact.setCellValueFactory(cellData -> cellData.getValue().contact);
        cnic.setCellValueFactory(cellData -> cellData.getValue().cnic);        
        dob.setCellValueFactory(cellData -> cellData.getValue().dob);
        designation.setCellValueFactory(cellData -> cellData.getValue().designation);
        qualification.setCellValueFactory(cellData -> cellData.getValue().qal); 
        jd.setCellValueFactory(cellData -> cellData.getValue().jd);
        salary.setCellValueFactory(cellData -> cellData.getValue().salary);                 
    
        employeeList.setItems(list);
        
		totalEmployee.setText(list.size()+"");
	}
		
	
	private void Filterdesgination() {
		listcopy = FXCollections.observableArrayList();		
		String de = desginationList.getValue();
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).designation.get().equals(de)) 
				listcopy.add(list.get(i));
		}
	}
	
	private void FilterDate() {
		String j = joiningDateField.getValue().toString();
		ObservableList<EmployeeModel> temp = listcopy;
		listcopy = FXCollections.observableArrayList();		
		for (int i=0; i<temp.size(); i++) {
			if (temp.get(i).jd.get().equals(j)) 
				listcopy.add(temp.get(i));
		}		
	}
	
	private void FilterSalary() {
		String j = salaryField.getText();
		ObservableList<EmployeeModel> temp = listcopy;
		listcopy = FXCollections.observableArrayList();		
		for (int i=0; i<temp.size(); i++) {
			if (temp.get(i).salary.get().equals(j)) 
				listcopy.add(temp.get(i));
		}
	}
	
	@FXML
	private void filterOpt(ActionEvent e) {
		if (desginationList.getValue() != null) {
			Filterdesgination();
		}
		
		if (joiningDateField.getValue() != null) {
			FilterDate();
		}
		
		if (!(salaryField.getText().equals(""))) {
			FilterSalary();
		}
		
		employeeList.setItems(listcopy);		
		totalEmployee.setText(listcopy.size()+"");
		
	}
	
	@FXML
	private void resetOpt(ActionEvent e) {
		
		salaryField.setText("");
		joiningDateField.setValue(null);
		desginationList.setValue(null);
		
		desginationList.setPromptText("Select the Desgination ....");
		salaryField.setPromptText("less & equal to given Salary");
		joiningDateField.setPromptText("Select any Joining Date");
		
		employeeList.setItems(list);
		totalEmployee.setText(list.size()+"");
	}
	
}
