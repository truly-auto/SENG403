/*this class connests to the database and runs queries search as select, insert, update etc*/


package FoodLink;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
	private static String dbURL = "jdbc:derby:derbyDB;";
    private static String sqlDir = "src/main/resources/SQL/";
    private static Connection conn = null;

		   
	public database(){
		//create connection to database
		try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
		
	}
	
	private void getFrom(String table){
		//query = select * from table
		//result = Array to be returned
		
	}
	
	private void addSupplierUser(int supplier_id, String Username, String password ){
		//insert (supplier_id, username, password)	
	}

}
