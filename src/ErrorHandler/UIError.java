package ErrorHandler;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UIError {

	public static void showError(String header, String msg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(msg);
        
        alert.showAndWait();		
	}
	
	
}
