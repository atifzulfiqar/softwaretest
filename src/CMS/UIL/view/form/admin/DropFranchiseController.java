package CMS.UIL.view.form.admin;

import CMS.BLL.core.Admin;
import CMS.UIL.model.FranchiseModel;
import ErrorHandler.UIConfirmation;
import ErrorHandler.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class DropFranchiseController {
	
	Admin admin;
	
	@FXML private TextField FID;

	@FXML private Label floc;
	@FXML private Label fcontact;
	@FXML private Label fdesc;
	@FXML private Label fname;	
	@FXML private Label fcreateOn;
	
	Stage stage = null;
	
	FranchiseModel f = null;	
	
	public void init(Admin a, FranchiseModel fran, Stage s) {
		stage = s;
		
		admin  = a;
		f = fran;
		fname.setText(f.name.get());
		floc.setText(f.location.get());
		fcontact.setText(f.contact.get());
		String desc = f.description.get();
		if (desc != null && !(desc.equals("")))
			fdesc.setText(desc);
		else
			fdesc.setText("No description");
		fcreateOn.setText(f.create.get());
	}
	
//	private boolean validate() {
//		if (FID != null && fname !=null && floc!=null && fcontact!=null && fdesc!=null) 
//			return true;
//		return false;
//	}
//	
//	@FXML
//	private void searchOpt(ActionEvent e) {
//		if (validate()) {
//			if (!(FID.getText().equals(""))) {
//				f = admin.searchFranchise(FID.getText());
//				if (f != null) {
//					fname.setText(f.name.get());
//					floc.setText(f.location.get());
//					fcontact.setText(f.contact.get());
//					String desc = f.description.get();
//					if (desc != null && !(desc.equals("")))
//						fdesc.setText(desc);
//					else
//						fdesc.setText("No description");
//					fcreateOn.setText(f.create.get());
//					DropBtn.setVisible(true);
//				}
//				else {
//					errormsg.setText("Invalid Franchise ID");
//					FID.requestFocus();
//					fname.setText("");
//					floc.setText("");
//					fcontact.setText("");
//					fdesc.setText("");
//					fcreateOn.setText("");				
//					DropBtn.setVisible(false);
//				}
//			}
//		}
// 	}
	
	@FXML
	private void dropFranchise(ActionEvent  e) {
		if (f != null) {
			if (admin.dropFranchise(f)) {
				UIConfirmation.showMsg("Franchise Dropped successfully!", null);
				stage.close();
			}
			else {
				UIError.showError("You are not allowed to delete this Franchise", "Some Employees are still working in this employee");				
			}
		}
	}
	

}
