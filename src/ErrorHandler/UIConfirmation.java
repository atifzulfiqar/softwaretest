package ErrorHandler;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UIConfirmation {

	public static void showMsg(String header, String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(header);
        alert.setContentText(msg);
        
        alert.showAndWait();		
	}
	
}
