package CMS.UIL.view;

import CMS.BLL.core.*;
import CMS.BLL.franchise.Franchise;
import CMS.UIL.view.form.admin.*;
import CMS.UIL.view.form.employee.*;
import CMS.UIL.view.form.manager.*;
import ErrorHandler.UIError;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// interface to call all form 
public class FormController {
		
	private Stage ManagerPortal;
	public BorderPane MenuOverview;
	private Stage stage;
	
	public void setPrimaryStage (Stage ps) {
		ManagerPortal = ps;
	}

// *********************************************** MANAGER MENU ****************************************	
	
	
	// show the Manager Portal
	public void showManagerPortal() {
    	try {
	        // Load person overview.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("form/manager/ManagerLayout.fxml"));
	        MenuOverview = (BorderPane) loader.load();
	      //  ManagerPortal = new Stage();
	        ManagerPortal.setTitle("Manager Portal");
	        ManagerPortal.getIcons().add(new Image("file:resources/images/edit.png"));
	        ManagerPortal.setScene(new Scene(MenuOverview));
	        
	        ManagerPortal.setMaximized(true);
	        ManagerPortal.show();
	        
    	}
    	catch (Exception ex) {
    		ex.printStackTrace();
    	}		
	}
	
	// close the Manager Portal
	public void closeManagerPortal() {
		ManagerPortal.close();
	}

	public void showHireEmployeeForm(Manager manager) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/manager/HireEmployeeLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            hireEmployeeController controller = loader.getController();
            controller.getFormID(manager);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
            stage.setTitle("Employee Information Form");
            stage.getIcons().add(new Image("file:resources/images/AddEmployee.png"));
	        stage.setScene(new Scene(personOverview)); 
	        	        
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void closeHireEmployeeForm() {
		MenuOverview.setCenter(null);
	}

	public void showEmployeeList(Manager manager) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/manager/EmployeeOverviewLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            ViewEmployeeController controller = loader.getController();
            controller.init(manager);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void showEditEmployee(Manager manager) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/manager/EditEmployeeLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            EditEmployeeController controller = loader.getController();
            controller.init(manager);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}	
	
	public void showUpdateEmployee(Manager manager) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/manager/UpdateEmployeeDesginationLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            UpdateEmployeeDesginationController controller = loader.getController();
            controller.init(manager);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}		

	public void showAddCustomer(Manager manager) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/manager/AddCustomerLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            AddCustomerController controller = loader.getController();
            controller.setFormID(manager);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void showViewCustomer(Manager manager) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/manager/CustomerOverviewLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            CustomerOverviewController controller = loader.getController();
            controller.init(manager);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}	
	
//	public void showEditCustomerOpt() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("form/manager/EditCustomerLayout.fxml"));
//            AnchorPane personOverview = (AnchorPane) loader.load();
//            // create a separate stage for Add Employee form
//            stage = new Stage();
//	        stage.setScene(new Scene(personOverview));      
//	        // show Form
//	        stage.show();
//        } 
//        catch (Exception e) {
//        	e.printStackTrace();
//        }
//	}
	
	public void showManageAccountOpt(Manager manager) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/manager/ManageAccountLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            ManageAccountController controller = loader.getController();
            controller.init(manager);
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}		
		
	public void showAllowanceListOpt(Manager manager) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/manager/AddAllowancesLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            AddAllowanceController controller = loader.getController();
            controller.init(manager);
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void showviewAllowancesOpt(Manager manager) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/manager/EditAllowanceLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            EditAllowanceController controller = loader.getController();
            controller.init(manager);
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void showAddRateOpt(Manager manager) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/manager/EditRateLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            EditRateController controller = loader.getController();
            controller.init(manager);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}	
	
	public void showupdateRateOpt(Manager manager) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/manager/UpdateRateLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            UpdateRateController controller = loader.getController();
            controller.init(manager);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}	
	
// *********************************************** EMPLOYEE MENU ****************************************	
	
	// show the Employee Portal
	public void showEmployeePortal() {
    	try {
	        // Load person overview.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("form/employee/EmployeeMenuLayout.fxml"));
	        MenuOverview = (BorderPane) loader.load();
	      //  ManagerPortal = new Stage();
	        ManagerPortal.setTitle("Employee Portal");
	        ManagerPortal.getIcons().add(new Image("file:resources/images/edit.png"));
	        ManagerPortal.setScene(new Scene(MenuOverview));
	        
	        ManagerPortal.show();
	        
    	}
    	catch (Exception ex) {
    		ex.printStackTrace();
    	}		
	}	
	
	public void showRegCourierOpt(Employee employee) {
		if (!(employee.getDesignation().equals("Delivery boy"))) {
	         try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("form/employee/RegCourierLayout.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();
	            RegCourierController controller = loader.getController();
	            controller.init(employee);
	            // create a separate stage for Add Employee form
	            stage = new Stage();
		        stage.setScene(new Scene(personOverview));      
		        // show Form
		        stage.show();
	        } 
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
		}
		else {
			UIError.showError("You are not allow to register any courier", null);
		}
	}
	
	public void showUnregCourierOpt(Employee employee) {
		if (!(employee.getDesignation().equals("Delivery boy"))) {		
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("form/employee/UnregCourierLayout.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();
	            UnregCourierController controller = loader.getController();
	            controller.init(employee);
	            
	            // create a separate stage for Add Employee form
	            stage = new Stage();
		        stage.setScene(new Scene(personOverview));      
		        // show Form
		        stage.show();
	        } 
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
		}
		else {
			UIError.showError("You are not allow to register any courier", null);
		}
	}
	
	public void showUpdateCourierOpt(Employee employee) {
		if (!(employee.getDesignation().equals("Delivery boy"))) {				
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("form/employee/UpdateStatusLayout.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();
	            
	            UpdateCourierController controller = loader.getController();
	            controller.init(employee);
	            
	            // create a separate stage for Add Employee form
	            stage = new Stage();
		        stage.setScene(new Scene(personOverview));      
		        // show Form
		        stage.show();
	        } 
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
		}
		else {
			UIError.showError("You are not allow to make any Change in courier details", null);
		}
	}	
	
	public void showviewCourierOpt(Employee employee, String fid) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/employee/ViewCourierListLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            ViewCourierListController controller = loader.getController();
            controller.init(employee, fid);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));  

	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}	

	public void showviewCourierOpt(Manager employee, String fid) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/employee/ViewCourierListLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            ViewCourierListController controller = loader.getController();
            controller.init(employee, fid);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));  

	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}	
	
	
	public void showViewRateListOpt(Employee employee) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/employee/ViewRateLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            ViewRateController controller = loader.getController();
            controller.init(employee);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}	
	
	public void showCancelCourierOpt(Employee employee) {
		if (employee.getDesignation().equals("Delivery boy")) {
			UIError.showError("You are not allow to make any change in courier details", null);
		}
		else {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("form/employee/CancelCourier.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();
	            
	            CancelCourierController controller = loader.getController();
	            controller.init(employee);
	            
	            // create a separate stage for Add Employee form
	            stage = new Stage();
		        stage.setScene(new Scene(personOverview));      
		        // show Form
		        stage.show();
	        } 
	        catch (Exception e) {
	        	e.printStackTrace();
	        }		
		}
	}
	
	public void showShipmentOpt(Employee employee) {
		if (employee.getDesignation().equals("Delivery boy")) {
			UIError.showError("You are not allow to make any change in courier details", null);
		}
		else {		
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("form/employee/ShipmentLayout.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();
	            
	            ShipmentController controller = loader.getController();
	            controller.init(employee);
	            
	            // create a separate stage for Add Employee form
	            stage = new Stage();
		        stage.setScene(new Scene(personOverview));      
		        // show Form
		        stage.show();
	        } 
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
		}
	}		
	
	public void showLoanRequestOpt(Employee employee) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/employee/TakeLoanLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            TakeLoanController controller = loader.getController();
            controller.init(employee);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}		
	
// *********************************************** ADMIN MENU ****************************************		
	
	// show the ADMIN Portal
	public void showAdminPortal() {
    	try {
	        // Load person overview.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("form/admin/AdminMenuLayout.fxml"));
	        MenuOverview = (BorderPane) loader.load();
	      //  ManagerPortal = new Stage();
	        ManagerPortal.setTitle("Admin Portal");
	        ManagerPortal.getIcons().add(new Image("file:resources/images/edit.png"));
	        ManagerPortal.setScene(new Scene(MenuOverview));
	        
	        ManagerPortal.show();
	        
    	}
    	catch (Exception ex) {
    		ex.printStackTrace();
    	}		
	}
	
	public void showAddFanchiseOpt(Admin admin) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/admin/AddFranchiseLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            AddFranchiseController controller = loader.getController();
            controller.init(admin);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}	
	
//	public void showDropFranchiseOpt(Admin admin) {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("form/admin/DropFranchiseLayout.fxml"));
//            AnchorPane personOverview = (AnchorPane) loader.load();
//            
//            DropFranchiseController contorller = loader.getController();
//            contorller.init(admin);
//            
//            // create a separate stage for Add Employee form
//            stage = new Stage();
//	        stage.setScene(new Scene(personOverview));      
//	        // show Form
//	        stage.show();
//        } 
//        catch (Exception e) {
//        	e.printStackTrace();
//        }
//	}		
	
	public void showviewFranchiseOpt(Admin admin) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/admin/ViewFranchiseLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            ViewFranchiseController controller = loader.getController();
            controller.init(admin);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}	
	
	public void showAddManagerOpt(Admin admin) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/admin/AddManagerLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            AddManagerController controller = loader.getController();
        	ObservableList<Franchise> fl =  admin.FranchiseList();
    		if (fl == null || fl.size() == 0) {
    			UIError.showError("You are not allow to add any manager", "All Franchise have manager");
    		}
    		else {
    			controller.init(fl, admin);
                // create a separate stage for Add Employee form
                stage = new Stage();
    	        stage.setScene(new Scene(personOverview));
    	        
    	        // show Form
    	        stage.show(); 			
    		}

        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void showFireManagerOpt(Admin admin) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/admin/FireManagerLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            FireManagerController controller = loader.getController();
            controller.init(admin);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }		
	}
	
	public void showViewManagerOpt(Admin admin) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/admin/ViewManagerLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            ViewManagerController controller = loader.getController();
            controller.init(admin);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));      
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }		
	}

	public void showViewAllRegCourierOpt(Admin admin) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/admin/ViewAllRegCourier.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            ViewAllRegCourierController controller = loader.getController();
            controller.init(admin);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));  
	        stage.setMaximized(true);
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }		
	}	
	
	public void showViewAllUnRegCourierOpt(Admin admin) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/admin/ViewAllUnRegCourier.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            ViewAllUnRegCourierController controller = loader.getController();
            controller.init(admin);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview));    
	        stage.setMaximized(true);
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }		
	}		
	
	public void showViewAllEmployeeOpt(Admin admin) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("form/admin/ViewAllEmployee.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            ViewAllEmployeeController controller = loader.getController();
            controller.init(admin);
            
            // create a separate stage for Add Employee form
            stage = new Stage();
	        stage.setScene(new Scene(personOverview)); 
	        // show Form
	        stage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }		
	}	
	
	public void closeApp() {
		closeManagerPortal();
	}	
	
}
