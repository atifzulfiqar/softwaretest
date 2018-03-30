package CMS.UIL.view.form.admin;

import java.util.ArrayList;

import CMS.BLL.core.Admin;
import CMS.BLL.franchise.Franchise;
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



public class ViewAllEmployeeController {

	@FXML private ComboBox<String> franchiseList;
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
	@FXML private TableColumn<EmployeeModel, String> fid;		
	

	@FXML private Label totalEmployee;
	
	
    ObservableList<EmployeeModel> list= null;
    
    ObservableList<EmployeeModel> listcopy = null;

    ObservableList<Franchise> fl =	null;
    
    Admin admin = null;
    
	public void init(Admin a) {
		// initialize the administrator
		admin = a;
		// get the list of employee		
	    list= admin.getRegEmployeeList(null);	
	    // make a copy of employee list
	    listcopy = list;
	    // get the list of franchise 
	    fl = admin.retreieveFranchiseList();			
		
		ArrayList<String> franchisel = new ArrayList<String>();
		
		for (int i=0; i<fl.size(); i++) 
			franchisel.add(fl.get(i).getName());
		
		// initialize the franchise list
		franchiseList.getItems().addAll(franchisel);
		// initialize the Designation list

		desginationList.getItems().addAll(admin.getList("designation","name"));

        // Initialize the Courier table
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
        fid.setCellValueFactory(cellData -> cellData.getValue().FranchiseName);                
    
        employeeList.setItems(list);
        
		totalEmployee.setText(list.size()+"");
	}
		
	private void filterFranchise() {
		String fn = franchiseList.getValue();
		listcopy = FXCollections.observableArrayList();
		for (int i=0; i<list.size(); i++) {
			if ((list.get(i).FranchiseName.get()).equals(fn)) {
				listcopy.add(list.get(i));
			}
		}
	}
	
	private void Filterdesgination() {
		ObservableList<EmployeeModel> temp = listcopy;
		listcopy = FXCollections.observableArrayList();		
		String de = desginationList.getValue();
		for (int i=0; i<temp.size(); i++) {
			if (temp.get(i).designation.get().equals(de)) 
				listcopy.add(temp.get(i));
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
		if (franchiseList.getValue() != null) {
			filterFranchise();
		}
		
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
		
		franchiseList.setPromptText("Select the Franchise....");
		desginationList.setPromptText("Select the Desgination ....");
		salaryField.setPromptText("less & equal to given Salary");
		joiningDateField.setPromptText("Select any Joining Date");
		
		employeeList.setItems(list);
		totalEmployee.setText(list.size()+"");
	}
	
}
