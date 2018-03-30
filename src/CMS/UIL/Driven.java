/**
 * 
 */
package CMS.UIL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import CMS.BLL.controller.SystemController;
/**
 * @author affan ali
 *
 */
public class Driven extends Application{

		public Stage primaryStage;
		public Stage LoginStage;
		private AnchorPane rootLayout;
		final private double height = 400;
		final private double width = 600;
		
	    @Override
	    public void start (Stage primaryStage) {
	    	try {
	    		// initialize the primary Stage
		        this.primaryStage = primaryStage;
		        //this.primaryStage.setTitle("");	        
		        // Set the application icon.
		        //this.primaryStage.getIcons().add(new Image("file:resources/images/iconConnect.png"));
	            // Load root layout from FXML file.
		        LoginStage = new Stage();
		        LoginStage.setTitle("Verification");
		        LoginStage.getIcons().add(new Image("file:resources/images/iconConnect.png"));	
		        
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("view/form/LoginLayout.fxml"));
	            rootLayout = (AnchorPane) loader.load();
	            LoginStage.initStyle(StageStyle.UTILITY);            
	            
	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout, width, height);
	            LoginStage.setScene(scene);
	            
	            // display the primary Stage
	            LoginStage.show();
	            
	            // link the controller class with the FXML file
	            SystemController rootController = loader.getController();
	            rootController.setApp(this);
	           
	    	}
	    	catch (Exception ex) {
	    		ex.printStackTrace();
	    	}
	    }	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
