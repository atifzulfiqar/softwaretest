package CMS.UIL.view.form.admin;

import java.util.ArrayList;

import CMS.BLL.core.Admin;
import CMS.BLL.franchise.Franchise;
import CMS.DBL.handler.RetrievalHandler;
import CMS.UIL.model.AllowanceModel;
import CMS.UIL.view.Helper;
import ErrorHandler.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddFranchiseController {

	Admin admin = null;
	
	@FXML private TextField nameF;
	@FXML private TextField locationF;
	@FXML private TextField contactF;
	@FXML private TextArea descF;
	
	@FXML private ListView<String> allowances;
	
	RetrievalHandler handler = new RetrievalHandler();
	
	ObservableList<AllowanceModel> list;
	ObservableList<String> listID = FXCollections.observableArrayList();
	
	public void init(Admin a) {
		admin = a;
		
		// return the list of all allowances
		list = admin.AllowancesList();
		// extract the type of allowances
		for (int i=0; i<list.size(); i++) {
			listID.add(list.get(i).type.get());
		}		
		allowances.getItems().addAll(listID);
		allowances.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	private boolean chkNull() {
		if (nameF == null || locationF == null || contactF == null || descF == null)
			return false;
		return true;
	}
	
	private boolean validate() {
		if (chkNull()) {
			if (nameF.getText().equals("")) {
				UIError.showError("Empty Field","Name Field must not be empty");
				nameF.requestFocus();
				return false;
			}
			
			if (locationF.getText().equals("")) {
				UIError.showError("Empty Field","Location Field must not be empty");
				locationF.requestFocus();
				return false;
			}
			
			if (contactF.getText().equals("")) {
				UIError.showError("Empty Field","Contact Number Field must not be empty");
				contactF.requestFocus();
				return false;
			}				
				
			return true;
		}
		else {
			UIError.showError("Invalid Argument", null);
			return false;
		}
	}
	
	private boolean validateForm() {
		if (!Helper.chkContactNo(contactF.getText())) {
			UIError.showError("Given Contact is invalid", "Must be numeric");
			contactF.requestFocus();
			return false;
		}
		return true;
	}
	
	@FXML
	private void addOpt(ActionEvent e) {
		if (validate()) {
			if (validateForm()) {
				Franchise franchise = new Franchise();
				franchise.setAll(Franchise.generateID(), nameF.getText(), descF.getText(), locationF.getText(), contactF.getText(), Helper.getCurrentDateinSQL());
				// get the list of selected Item (Allowances) from list of allowances
				ObservableList<String> selectedItems =  allowances.getSelectionModel().getSelectedItems();
				ArrayList<String> ids = new ArrayList<String>();
				// get the ID of each selected allowances type
				for (int i=0; i<selectedItems.size(); i++) {
					for (int j=0; j<list.size(); j++) {
						if (list.get(j).type.get().equals(selectedItems.get(i))) {
							ids.add(list.get(j).AID.get());
						}
					}
				}
				franchise.setAllowances(ids);
				// aids contains the list of allowances allocated to this franchise
				admin.addFranchise(franchise, ids);					
				UIConfirmation.showMsg("Franchise List updated successfully", null);
				clear();
			}
		}
	}
	
	private void clear() {
		nameF.setText("");
		locationF.setText("");
		contactF.setText("");
		descF.setText("");		
	}
	
	@FXML
	private void resetOpt(ActionEvent e) {
		clear();
		UIConfirmation.showMsg("reset form", null);
	}
}
