package CMS.UIL.view.form.manager;

import CMS.BLL.core.Customer;
import CMS.BLL.core.Manager;
import CMS.UIL.model.CustomerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditCustomerController {

	@FXML private TextField fname;
	@FXML private TextField lname;
	@FXML private TextField address;
	@FXML private TextField contact;
	@FXML private TextField cnic;
	
	CustomerModel customer = null;
	Manager manager = null;
	
	public void init(CustomerModel cus, Manager man) {
		customer = cus;
		manager = man;
		
		fname.setText(customer.fname.get());
		lname.setText(customer.lname.get());
		address.setText(customer.address.get());
		contact.setText(customer.contact.get());
		cnic.setText(customer.cnic.get());
	}
	
	@FXML
	private void updateOpt(ActionEvent e) {
		customer.setAll(fname.getText(), lname.getText(), address.getText(), contact.getText(), cnic.getText(), customer.organization.get());
		Customer cus = new Customer();
		cus.setAll(customer.fname.get(), customer.lname.get(), customer.address.get(), customer.contact.get(), customer.cnic.get(), customer.organization.get());
		manager.updateCutomer(cus);
	}
	
	@FXML
	private void deleteOpt(ActionEvent e) {
		
	}

}
