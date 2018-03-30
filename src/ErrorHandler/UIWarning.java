package ErrorHandler;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UIWarning {
	
	public static void showWarning(String header, String msg) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(msg);
        
        alert.showAndWait();		
	}
	

}
