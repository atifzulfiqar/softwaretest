package CMS.UIL.view.form.manager;


import CMS.BLL.core.Manager;
import CMS.UIL.model.RateModel;
import ErrorHandler.UIError;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UpdateRateController {

	@FXML private TableView<RateModel> rateList;
	@FXML private TableColumn<RateModel, String> zoneCol;
	@FXML private TableColumn<RateModel, String> priceCol;
	@FXML private TableColumn<RateModel, String> descCol;
	
	
    ObservableList<RateModel> list = null;	
	
    Manager manager = null;
    
	public void init(Manager man) {
		manager = man;

        zoneCol.setCellValueFactory(cellData -> cellData.getValue().zone);
        priceCol.setCellValueFactory(cellData -> cellData.getValue().price);
        descCol.setCellValueFactory(cellData -> cellData.getValue().desc);
		
		
		list = manager.retrieveRateList();
        rateList.setItems(list);         
	}
	
	@FXML
	private void editOpt(ActionEvent e) {
        int i = rateList.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
        	RateModel temp = rateList.getSelectionModel().getSelectedItem();       	
        	// show the edit rate form 
            try {
                // Load person overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("UpdateRate.fxml"));
                AnchorPane personOverview = (AnchorPane) loader.load();
                
                UpdateRate controller = loader.getController();

                
                // create a separate stage for Add Employee form
                Stage stage = new Stage();
    	        stage.setScene(new Scene(personOverview));      
    	  
                controller.init(stage,manager, temp);
                
    	        // show Form
    	        stage.show();
            } 
            catch (Exception ex) {
            	ex.printStackTrace();
            }        	
        } 
        else {
        	UIError.showError("Nothing Selected", "Please select any record from the table");
        }		
	}
		
}
