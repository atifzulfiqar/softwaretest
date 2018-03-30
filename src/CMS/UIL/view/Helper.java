package CMS.UIL.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * @author Affan Ali
 *	purpose : provide the function use in the System controller
 */
public class Helper {
	
	public static boolean valid(String username, String password) {
		if (username == null || username.equals(""))
			return false;
		
		if (password == null || password.equals(""))
			return false;
		
		if (username.length() != 7)
			return false;
		
		if (password.length() < 4)
			return false;
		
		if (username.charAt(0)=='A' || username.charAt(0)=='E' || username.charAt(0)=='M') {
			for (int i=1; i<6; i++) {
				if (!Character.isDigit(username.charAt(i)))
					return false;
			}
		}
		
		return true;
	}
	
	public final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
	
	public static boolean isNumbericValue(String temp) {
		for (char c: temp.toCharArray()) {
			if (!(Character.isDigit(c))) {
				if (c != '.')	
					return false;				
			}
		}
		return true;
	}
	
	public static boolean chkCNIC(String temp) {
		if (temp.length() == 13) {
			for (char c: temp.toCharArray()) {
				if (!(Character.isDigit(c))) {
					return false;				
				}
			}
			return true;		
		}
		return false;
	}
	
	public static java.sql.Date getCurrentDateinSQL() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		return date;
	}
	
	
	public static boolean chkContactNo(String temp) {
		if (temp.length() == 11) {
			for (char c: temp.toCharArray()) {
				if (!(Character.isDigit(c))) {
					return false;				
				}
			}
			return true;		
		}
		return false;		
	}
	
	public static boolean chkID(String id) {
		if (id.length()==7) {
			if (id.charAt(0) == 'E' || id.charAt(0)=='M' || id.charAt(0)=='A') {
				String temp = id.substring(1);
				for (char c: temp.toCharArray()) {
					if (!Character.isDigit(c))
						return false;
				}
				return true;
			}
		}
		return false;
	}
	
	public static boolean isAlphaValue(String id) {
		for (char c: id.toCharArray()) {
			if (Character.isDigit(c)) {
				return false;				
			}
		}
		return true;		
	}
}

