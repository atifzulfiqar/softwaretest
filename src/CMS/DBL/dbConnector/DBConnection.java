package CMS.DBL.dbConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Affan Ali
 */

public class DBConnection {

	private String userName;
	private String password;
	private String serverName;
	private int portNumber;
	private String dbName ;
	private Connection con;
	private Statement stmt;

    
    public DBConnection() {
       userName="root";
       password="12345";
       serverName = "localhost";
       portNumber = 3306;
       dbName ="softwaretesting";
       Properties connectionProps = new Properties();
	   connectionProps.put("user", userName);
	   connectionProps.put("password", password);
       try {
		con = DriverManager.getConnection("jdbc:mysql://" + serverName
					+ ":" + portNumber + "/" + dbName, connectionProps);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public int executeUpdate(String query)
    {
    	try {		
    		System.out.println("query : " + query);
			stmt = con.createStatement();
	    	return stmt.executeUpdate(query);	
	    	//return 1;						
		} 
    	catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
    	finally {
    		try {
				stmt.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	
    }
    
    public Connection getConnection()
    {
    	return con;
    }
    
    
   
}
