package CMS.UIL.view.form.manager;

import CMS.BLL.core.Account;
import CMS.BLL.core.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ManageAccountController {

	@FXML private TextField userID;
	@FXML private Label erroMsg;
	
	Manager manager;
	
	public void init(Manager man) {
		manager = man;
		erroMsg.setText("");
	}
	
	@FXML
	private void resetAccount(ActionEvent e) {
		if (userID != null) {
			if (!(userID.equals(""))) {
				if (userID.getText().length() != 7) {
					Account acc = new Account();
					acc.setAll(userID.getText(), "1234567");
					manager.updateAccount(acc);
				}
			}
		}
	}
	
}
