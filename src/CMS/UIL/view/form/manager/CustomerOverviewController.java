package CMS.UIL.view.form.manager;


import java.util.Optional;

import CMS.BLL.core.Manager;
import CMS.UIL.model.CustomerModel;
import ErrorHandler.UIError;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerOverviewController {

	@FXML private TableView<CustomerModel> customerList;
	@FXML private TableColumn<CustomerModel, String> fname;
	@FXML private TableColumn<CustomerModel, String> lname;
	
	@FXML private Label cidLabel;	
	@FXML private Label firstNameLabel;
	@FXML private Label lastNameLabel;
	@FXML private Label addressLabel;
	@FXML private Label contactLabel;
	@FXML private Label cnicLabel;
	@FXML private Label dojLabel;
	@FXML private Label organizationLabel;
		
    ObservableList<CustomerModel> list;
    
    Manager manager = null;
    
	public void init(Manager man) {	
		manager = man;
		
		list = manager.getCustomerList();
		
        // Initialize the Customer table
        fname.setCellValueFactory(cellData -> cellData.getValue().fname);
        lname.setCellValueFactory(cellData -> cellData.getValue().lname);
        
        customerList.setItems(list);        
        
        // Clear person details.
        showCustomerDetails(null);

        // Listen for selection changes and show the person details when changed.
        customerList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCustomerDetails(newValue)
        );        
        
	}

	private void showCustomerDetails(CustomerModel customer) {
		if (customer != null) {
			cidLabel.setText(customer.id.get());
			firstNameLabel.setText(customer.fname.get());
			lastNameLabel.setText(customer.lname.get());
			addressLabel.setText(customer.address.get());
			contactLabel.setText(customer.contact.get());
			cnicLabel.setText(customer.cnic.get());
			dojLabel.setText(customer.doj.get());
			organizationLabel.setText(customer.organization.get());
		}
	}
	
	@FXML
	private void editOpt(ActionEvent e) {
		CustomerModel selectedPerson = customerList.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            try {
                // Load person overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("EditCustomerLayout.fxml"));
                AnchorPane personOverview = (AnchorPane) loader.load();
                
                EditCustomerController controller = loader.getController();
                controller.init(selectedPerson, manager);
                
                
                // create a separate stage for Add Employee form
                Stage stage = new Stage();
    	        stage.setScene(new Scene(personOverview));      
    	        // show Form
    	        stage.show();
            } 
            catch (Exception ex) {
            	ex.printStackTrace();
            }
        } 
        else {
        	UIError.showError("No Customer Selected", "Please select a Customer in the table");
        }
	}
	
	@FXML
	private void deleteOpt(ActionEvent e) {
		CustomerModel selectedPerson = customerList.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			try {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Delete Customer details");
				alert.setHeaderText("Are you sure you want to Delete Customer Details?");
				alert.setContentText("All Information will permanently delete");
		
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					manager.deleteCustomer(selectedPerson);
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
