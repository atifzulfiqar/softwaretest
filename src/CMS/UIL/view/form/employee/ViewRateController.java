package CMS.UIL.view.form.employee;

import CMS.BLL.core.Employee;
import CMS.UIL.model.RateModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



public class ViewRateController {

	Employee employee = null;
	
	@FXML private TableView<RateModel> rateList;
	@FXML private TableColumn<RateModel, String> zoneCol;
	@FXML private TableColumn<RateModel, String> priceCol;
	@FXML private TableColumn<RateModel, String> descCol;
	
	
    ObservableList<RateModel> list = null;	
	
	
	
	public void init(Employee emp) {
		employee = emp;
		
		list = employee.retrieveRateList();
		
        zoneCol.setCellValueFactory(cellData -> cellData.getValue().zone);
        priceCol.setCellValueFactory(cellData -> cellData.getValue().price);
        descCol.setCellValueFactory(cellData -> cellData.getValue().desc);

        rateList.setItems(list);         		
		
	}
	
}
