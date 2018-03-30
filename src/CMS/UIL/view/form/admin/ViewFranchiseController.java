package CMS.UIL.view.form.admin;

import CMS.BLL.core.*;

import CMS.UIL.model.FranchiseAllowancesModel;
import CMS.UIL.model.FranchiseModel;
import ErrorHandler.UIError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ViewFranchiseController {

	Admin admin = null;
	
	@FXML private TableView<FranchiseModel> FranchiseList;
	
	@FXML private TableColumn<FranchiseModel, String> Fname;
	@FXML private TableColumn<FranchiseModel, String> Flocation;
	@FXML private TableColumn<FranchiseModel, String> Fcontact;
	@FXML private TableColumn<FranchiseModel, String> Fdesc;
	@FXML private TableColumn<FranchiseModel, String> Fcre;
	@FXML private TableColumn<FranchiseModel, String> managerID;	
	
	@FXML private TableView<FranchiseAllowancesModel> allowances;
	@FXML private TableColumn<FranchiseAllowancesModel, String> typeCol;
	@FXML private TableColumn<FranchiseAllowancesModel, String> amountCol;
	
	@FXML private Label total;

	
    ObservableList<FranchiseModel> list = null;	
    ObservableList<FranchiseAllowancesModel> allowanceList = null;
	
	
	public void init(Admin a) {
		// initialize the Administrator
		admin = a;    	
		// get the franchise list
		list = admin.getFranchiseList();	
		// get the list of allocated allowances
		allowanceList = admin.getAllocatedAllowancesList();
        // Initialize the Franchise table
        Fname.setCellValueFactory(cellData -> cellData.getValue().name);
        Flocation.setCellValueFactory(cellData -> cellData.getValue().location);
        Fcontact.setCellValueFactory(cellData -> cellData.getValue().contact);
        Fdesc.setCellValueFactory(cellData -> cellData.getValue().description);
        Fcre.setCellValueFactory(cellData -> cellData.getValue().create);
        managerID.setCellValueFactory(cellData -> cellData.getValue().managerID);

        typeCol.setCellValueFactory(cellData -> cellData.getValue().AIDName);
        amountCol.setCellValueFactory(cellData -> cellData.getValue().AAmount);
        
        showAllowancesList(null);
        
        // Listen for selection changes and show the person details when changed.
        FranchiseList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAllowancesList(newValue)
        );       
        
        // show the franchise list
        FranchiseList.setItems(list);
        // update the size of franchise list
		total.setText(list.size()+"");		
	}
	
	private void showAllowancesList(FranchiseModel temp) {
		ObservableList<FranchiseAllowancesModel> currentAllownacesList =  FXCollections.observableArrayList();;		
		if (temp != null) {
			for (int j=0; j<allowanceList.size(); j++) {
				if (temp.fid.get().equals(allowanceList.get(j).FID.get())) {
					currentAllownacesList.add(allowanceList.get(j));
				}
			}
			allowances.setItems(currentAllownacesList);
		}
		else {
			allowances.setItems(currentAllownacesList);			
		}
	}
	
	@FXML
	private void editOpt(ActionEvent e) {
		int  i = FranchiseList.getSelectionModel().getSelectedIndex();
		if (i >= 0) {
			FranchiseModel franchise = FranchiseList.getSelectionModel().getSelectedItem();
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("DropFranchiseLayout.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();

	            // create a separate stage for Add Employee form
	            Stage stage = new Stage();
		        stage.setScene(new Scene(personOverview));      
	            	            
	            DropFranchiseController contorller = loader.getController();
	            contorller.init(admin, franchise, stage);
	         
		        // show Form
		        stage.show();
	        } 
	        catch (Exception ex) {
	        	ex.printStackTrace();
	        }
		}
		else {
			UIError.showError("Selection Error", "You need to select Franchise from the table");
		}
	}
	
}
