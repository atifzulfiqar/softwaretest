package CMS.UIL.view.form.admin;

import CMS.BLL.core.Admin;
import CMS.UIL.model.ManagerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ViewManagerController {

	@FXML private TextField managerId;
	
	@FXML private TableView<ManagerModel> ManagerList;
	@FXML private TableColumn<ManagerModel, String> id;
	@FXML private TableColumn<ManagerModel, String> name;
	@FXML private TableColumn<ManagerModel, String> address;
	@FXML private TableColumn<ManagerModel, String> contact;
	@FXML private TableColumn<ManagerModel, String> cnic;
	@FXML private TableColumn<ManagerModel, String> DOB;
	@FXML private TableColumn<ManagerModel, String> qualification;
	@FXML private TableColumn<ManagerModel, String> JD;
	@FXML private TableColumn<ManagerModel, String> salary;
	@FXML private TableColumn<ManagerModel, String> fa;	
	
	@FXML private Label totalManager;
	
	
    ObservableList<ManagerModel> list= null;	
    ObservableList<ManagerModel> listcopy = null;
    
    Admin admin = null;
	
	public void init(Admin a) {
		
		admin = a;
		list = admin.getManagerList();
		listcopy = list;
		
		// initialize the table
        id.setCellValueFactory(cellData -> cellData.getValue().MID);		
        name.setCellValueFactory(cellData -> cellData.getValue().Mname);
        address.setCellValueFactory(cellData -> cellData.getValue().Maddress);
        contact.setCellValueFactory(cellData -> cellData.getValue().Mcontact);
        cnic.setCellValueFactory(cellData -> cellData.getValue().MCNIC);
        DOB.setCellValueFactory(cellData -> cellData.getValue().MDOB);        
        qualification.setCellValueFactory(cellData -> cellData.getValue().Mqualification);
        JD.setCellValueFactory(cellData -> cellData.getValue().MJD);
        salary.setCellValueFactory(cellData -> cellData.getValue().Msalary);       
        fa.setCellValueFactory(cellData -> cellData.getValue().FranchiseName);
        
        ManagerList.setItems(list);
        totalManager.setText(list.size()+"");
	}
	
	@FXML
	private void filter() {
		if (managerId != null) {
			if (!(managerId.getText().equals(""))) {
				listcopy = FXCollections.observableArrayList();				
				String temp = managerId.getText();
				for (int i=0; i<list.size(); i++) {
					if (list.get(i).MID.get().equals(temp)) 
						listcopy.add(list.get(i));
				}
			}
		}
		else {
			listcopy = list;
		}
		
		ManagerList.setItems(listcopy);
		totalManager.setText(listcopy.size()+"");
	}
	
	@FXML
	private void reset() {
		ManagerList.setItems(list);
		totalManager.setText(list.size()+"");
		
		managerId.setPromptText("Enter the Manager ID....");

	}
}
