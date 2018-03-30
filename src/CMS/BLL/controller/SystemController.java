/**
 * 
 */
package CMS.BLL.controller;

import CMS.BLL.core.*;
import CMS.DBL.handler.RetrievalHandler;
import CMS.UIL.Driven;
import CMS.UIL.view.*;
import ErrorHandler.UIConfirmation;
import ErrorHandler.UIError;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * @author affan ali
 *
 */
public class SystemController {
	
	public static Manager manager = null;
	public static Employee employee = null;
	public static Admin admin = null;
	
	public Driven mainApp;
	public FormController form = new FormController();
	
	// Login Form variable
	@FXML private TextField username;
	@FXML private TextField password;
	@FXML private Label status;
	
	@FXML
	public void initalize() {
	}
		
	public void setApp(Driven d) {
		mainApp = d;
	}
	
	@FXML
	private void closeOpt(ActionEvent e) {
		form.closeApp();
	}
	
	/*
	 * Login Function
	 * button in LoginLayout Form
	 */
	
	@FXML
	private void LoginOpt(ActionEvent e) {
		try {
			RetrievalHandler handler = new RetrievalHandler();
			
			String user = username.getText();
			String pswd = password.getText();
			if (Helper.valid(user,pswd)) {
				if (user.charAt(0) =='M') {
					manager = (Manager)handler.validateAccount(user,pswd);
					if (manager != null) {
						UIConfirmation.showMsg("Login Succesfully!", manager.getFName() + " " + manager.getLName());
						// close the Login form
						form.setPrimaryStage(mainApp.primaryStage);
						mainApp.LoginStage.close();
						// load the ManagerLayout form
						form.showManagerPortal();
					}
					else {
						UIError.showError("Invalid user ID / Password", "No Account found against against Parameters");
					}
				}
				else if (user.charAt(0) == 'E') {
					employee = (Employee)handler.validateAccount(user,pswd);
					if (employee != null) {
						UIConfirmation.showMsg("Login Succesfully!", employee.getFName() + " " + employee.getLName());
						// close the Login form
						form.setPrimaryStage(mainApp.primaryStage);
						mainApp.LoginStage.close();
						// load the ManagerLayout form
						form.showEmployeePortal();	
					}
					else {
						UIError.showError("Invalid user ID / Password", "No Account found against against Parameters");
					}					
				}
				else if (user.charAt(0) == 'A') {
					admin = (Admin)handler.validateAccount(user,pswd);
					if (admin != null) {
						UIConfirmation.showMsg("Login Succesfully!", admin.getFName() + " " + admin.getLName());
						// close the Login form
						form.setPrimaryStage(mainApp.primaryStage);
						mainApp.LoginStage.close();
						// load the ManagerLayout form
						form.showAdminPortal();										
					}
					else {
						UIError.showError("Invalid user ID / Password", "No Account found against against Parameters");
					}					
				}
			}
			else {
				UIError.showError("Login Failed", "Invalid username / password");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}


	// *********************************************** MANAGER MENU ****************************************		
	
	@FXML
	private void AddEmployeeOpt(ActionEvent e) {
		form.showHireEmployeeForm(manager);
	}
	
	@FXML
	private void viewEmployeeOpt(ActionEvent e) {
		form.showEmployeeList(manager);
	}
	
	@FXML 
	private void editEmployeeOpt(ActionEvent e) {
		form.showEditEmployee(manager);
	}
	
	@FXML 
	private void upgradeOpt(ActionEvent e) {
		form.showUpdateEmployee(manager);
	}
		
	@FXML
	private void AddCustomerOpt(ActionEvent e) {
		form.showAddCustomer(manager);
	}
	
	@FXML
	private void viewCustomerOpt(ActionEvent e) {
		form.showViewCustomer(manager);
	}
	
//	@FXML
//	private void EditCustomerOpt(ActionEvent e) {
//		form.showEditCustomerOpt();
//	}
	
	@FXML
	private void ManageAccountOpt(ActionEvent e) {
		form.showManageAccountOpt(manager);
	}
	
	@FXML
	private void AddAllowanceListOpt(ActionEvent e) {
		form.showAllowanceListOpt(manager);
	}
	
	@FXML
	private void viewAllowancesOpt(ActionEvent e) {
		form.showviewAllowancesOpt(manager);
	}
	
	@FXML
	private void AddRateOpt(ActionEvent e) {
		form.showAddRateOpt(manager);		
	}
	
	@FXML
	private void updateRateOpt(ActionEvent e) {
		form.showupdateRateOpt(manager);
	}
	
//	@FXML
//	private void viewCourierOpt(ActionEvent e) {
//		form.showviewCourierOpt();
//	}
	
// *********************************************** EMPLOYEE MENU ****************************************	
	
	@FXML
	private void RegCourierOpt(ActionEvent e) {
		form.showRegCourierOpt(employee);
	}
	
	@FXML
	private void UnregCourierOpt(ActionEvent e) {
		form.showUnregCourierOpt(employee);
	}
	
	@FXML
	private void updateCourierOpt(ActionEvent e) {
		form.showUpdateCourierOpt(employee);
	}
	
	@FXML
	private void CancelCourierOpt(ActionEvent e) {
		form.showCancelCourierOpt(employee);
	}
	
	@FXML
	private void viewCourierOpt(ActionEvent e) {
		String fid;
		if (employee != null) {
			fid = employee.getFID();
			form.showviewCourierOpt(employee, fid);			
		}
		else {
			fid = manager.getFID();
			form.showviewCourierOpt(manager, fid);			
		}

	}
	
	@FXML
	private void shipmentOpt(ActionEvent e) {
		form.showShipmentOpt(employee);
	}
	
	@FXML
	private void viewRateListOpt(ActionEvent e) {
		form.showViewRateListOpt(employee);
	}
	
	@FXML
	private void LoanRequestOpt(ActionEvent e) {
		form.showLoanRequestOpt(employee);
	}
	
// *********************************************** ADMIN MENU ****************************************	
	
	@FXML
	private void AddFanchiseOpt(ActionEvent e) {
		form.showAddFanchiseOpt(admin);
	}
	
//	@FXML
//	private void DropFranchiseOpt(ActionEvent e) {
//		form.showDropFranchiseOpt(admin);
//	}
	
	@FXML
	private void viewFranchiseOpt(ActionEvent e) {
		form.showviewFranchiseOpt(admin);
	}
	
	@FXML
	private void AddManagerOpt(ActionEvent e){
		form.showAddManagerOpt(admin);
	}
	
	@FXML
	private void FireManagerOpt(ActionEvent e) {
		form.showFireManagerOpt(admin);
	}
	
	@FXML
	private void ViewManagerOpt(ActionEvent e) {
		form.showViewManagerOpt(admin);
	}
		
	@FXML
	private void ViewRegCourierOpt(ActionEvent e) {
		form.showViewAllRegCourierOpt(admin);
	}
	
	@FXML
	private void ViewUnRegCourierOpt(ActionEvent e) {
		form.showViewAllUnRegCourierOpt(admin);
	}	
	
	@FXML
	private void ViewAllEmployeeOpt(ActionEvent e) {
		form.showViewAllEmployeeOpt(admin);
	}
}

