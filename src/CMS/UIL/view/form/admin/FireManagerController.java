package CMS.UIL.view.form.admin;

import CMS.BLL.core.Admin;
import CMS.UIL.model.ManagerModel;
import ErrorHandler.UIConfirmation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FireManagerController {

	@FXML private Label errormsg;
	
	@FXML private TextField ManagerID;
	
	@FXML private Label fname;
	@FXML private Label addr;
	@FXML private Label contact;
	@FXML private Label cnic;
	
	@FXML private Button deleteBtn;

	Admin admin;
	ManagerModel m = null;
	
	public void init(Admin a) {
		admin = a;
		hide();
		errormsg.setText("");
	}
	
	public void hide() {
		fname.setText("");
		addr.setText("");
		contact.setText("");
		cnic.setText("");
		
		deleteBtn.setVisible(false);
	}
	
	
	@FXML
	private void searchOpt(ActionEvent e) {
		if (!(ManagerID.getText().equals(""))) {
			m = admin.getManager(ManagerID.getText());
			if (m != null) {
				errormsg.setText("");
				fname.setText(m.Mname.get());
				addr.setText(m.Maddress.get());
				contact.setText(m.Mcontact.get());
				cnic.setText(m.MCNIC.get());
				
				deleteBtn.setVisible(true);
			}
			else {
				errormsg.setText("There is no such manager exists in our database!");
				hide();
			}
		}
	}
	
	
	@FXML
	private void deleteOpt(ActionEvent e) {
		if (m != null) {
			// update the status of manager
			// remove the account 
			if (admin.fireManager(m.MID.get())) {
				ManagerID.setText("");
				ManagerID.requestFocus();
				ManagerID.setPromptText("Enter Manager ID....");
				hide();
				UIConfirmation.showMsg("Manager Delete successfully!", null);
			}
		}
	}
	
	
}
