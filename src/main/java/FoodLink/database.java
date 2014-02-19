/*this class connests to the database and runs queries search as select, insert, update etc*/


package FoodLink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
	private String dbURL = "jdbc:derby:derbyDB;";
    private String sqlDir = "src/main/resources/SQL/";
    private Connection conn = null;
    private Statement statement;
		   
	public database(){
		//create connection to database
		try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
            statement = conn.createStatement();
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
		
	}
	
	public void getSupplier(){
		String command = "select * from supplier";
		
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("supplier_id");
		         String name = rs.getString("name");
		         String tel = rs.getString("phoneNumber");
		         String add = rs.getString("address");
		         String city = rs.getString("city");
		         String email = rs.getString("email");
		         
		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(", name: " + name);
		         System.out.print(", tel: " + tel );
		         System.out.print(", address: " + add);
		         System.out.print(", city: " + city);
		         System.out.println(", email: " + email);
		      }
		      rs.close();
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		    }
		
		
	}
	
	public void addSupplier(String name, String phone, String address, String city, String email ){
		String command = "INSERT INTO supplier (name, phoneNumber, address, city, email) VALUES "
				+ "("+name+","+ phone+ ","+address+","+ city+ "," + email+")";
		

		try {
		     statement.execute(command);
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);
		     System.exit(0);
		    }
		System.out.println("Add Succesful");
		
	}
	
	public void addSupplierUser(int id, String username, String password ) throws SQLException{
	
		String command = "INSERT INTO supplier_users VALUES ("+id+","+ username+ ","+password+");";
		
		try {
		     statement.execute(command);
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		    }
		
	}
	
	public String [] getSpecSupplier(int supplier_id){
		String command = "select * from supplier where supplier_id = "+ Integer.toString(supplier_id);
		String [] supplier = new String [6];
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("supplier_id");
		         String name = rs.getString("name");
		         supplier[0]=name;
		         String tel = rs.getString("phoneNumber");
		         supplier[1]=tel;
		         String add = rs.getString("address");
		         supplier[2]=add;
		         String city = rs.getString("city");
		         supplier[3]=city;
		         String email = rs.getString("email");
		         supplier[4]=email;
		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(", name: " + name);
		         System.out.print(", tel: " + tel );
		         System.out.print(", address: " + add);
		         System.out.print(", city: " + city);
		         System.out.println(", email: " + email);
		      }
		      rs.close();
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		    }
		
		return supplier;
	}


}