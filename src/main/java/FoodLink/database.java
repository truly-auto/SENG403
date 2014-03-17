/*this class connects to the database and runs queries search as select, insert, update etc*/


package FoodLink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public Object [] [] getInventory(int id){
		ArrayList<ArrayList<String>> inventory = new ArrayList<ArrayList<String>>();
		String command = "select * from items where supplier_id = " +id;
		
		//===
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     while(rs.next()){
		    	 ArrayList <String> currentItem = new ArrayList <String> (); 
		    	 //Retrieve by column name
		    	 int number = rs.getInt("item_number");
			      System.out.println(number);
			        currentItem.add(Integer.toString(number)); 
		    	 
		        String name = rs.getString("name");
		        System.out.println(name);
		        currentItem.add(name);  
		     	
		        String type = rs.getString("item_type");
		        System.out.println(type);
		        currentItem.add(type);  
		     	
		        int quantity = rs.getInt("quantity");
		        System.out.println(quantity);
		        currentItem.add(Integer.toString(quantity));  
		     	
		        String price = rs.getString("unit_price");
		        System.out.println(price);
		        currentItem.add(price);  
		     
		        inventory.add(currentItem);
		     	}
		      rs.close();
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		    }
		
		//===

		for (int i = 0; i< inventory.size(); i++){
			System.out.println(inventory.get(i));
			
		}
		
		Object [] [] inventoryArray =  new Object [inventory.size()] [];
		
		for (int i = 0; i< inventory.size(); i++){
			ArrayList <String> row =  inventory.get(i);
			inventoryArray[i]= row.toArray(new String [row.size()]);
			
			
		}
		return inventoryArray;
	}
	
	
	public String [] getSpecSupplier(int id){
		String command = "select * from supplier where supplier_id = " +id;
		String [] supplier = new String [5]; 
		//===
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     while(rs.next()){
		    	 ArrayList <String> currentItem = new ArrayList <String> (); 
		    	 //Retrieve by column name
		        String name = rs.getString("name");
		        System.out.println(name);
		        supplier[0]=name;  
		     	
		        String number = rs.getString("phonenumber");
		        System.out.println(number);
		        supplier[1]=number;  
		     	
		        String add = rs.getString("address");
		        System.out.println(add);
		        supplier[2]=add;  
		     	
		        String city = rs.getString("city");
		        System.out.println(city);
		        supplier[3]=city;  
		     	
		        String email = rs.getString("email");
		        System.out.println(email);
		        supplier[4]=email;  
		     	
		        }
		      rs.close();
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		    }
		
		//===
		return supplier;
	}
	
	public String [] getSupplierNames()
	{
		String command = "select name from supplier";
		String [] supplierNames = new String [5];
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     int counter = 0;
		     while(rs.next()&&counter<5)
		     	{
			         String name = rs.getString("name");
			         supplierNames[counter]=name;
			         counter++;
			         //Display values
			         System.out.print(", name: " + name);
			     }
			}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		
		
		}
		return supplierNames;

	}
	
	public String [] getSupplierItemsList(int id)
	{
		String command = "select name from items where supplier_id = " + id;
		String [] supplierItemsList = new String [10];
		
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     int counter = 0;
		     while(rs.next())
		     	{
			         String name = rs.getString("name");
			         supplierItemsList[counter]=name;
			         counter++;
			         //Display values
			         System.out.print(", name: " + name);
			     }
			}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		
		}
		
		
		return supplierItemsList;
	}
	
	
	public Object [][] getItemListForSupplier(int id)
	{
		String command = "select item_number, name, item_type, unit_price, unit from items where supplier_id = " + id;
		ArrayList<ArrayList<String>> inventory = new ArrayList<ArrayList<String>>();
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     while(rs.next())
		     	{
		    	 	ArrayList <String> itemList = new ArrayList <String> ();
			        //get item number
			       	String item_number = rs.getString("item_number");
			       	itemList.add(item_number);
			       	System.out.println("Item number: " + item_number);
			       	//get item name
			       	String name = rs.getString("name");
			    	itemList.add(name);
			       	System.out.println("Name: " + name);
			       	//get item type
			       	String item_type = rs.getString("item_type");
			    	itemList.add(item_type);
		        	System.out.println("Item Type: " + item_type);
			       	//get quantity
		        	String quantity = "";
		        	itemList.add("");
			       	//get unit price
		        	String unit_price = rs.getString("unit_price");
		        	itemList.add(unit_price);
		        	System.out.println("Unit Price: " + unit_price);
		           	//get price
		        	String unit = rs.getString("unit");
		        	itemList.add(unit);
		        	System.out.println("Unit: " + unit);
		        	//get price
		        	String total = "";
		        	itemList.add(total);
		        	System.out.println("Total: " + total);
			        inventory.add(itemList);
			     }
			 rs.close();
		    	 
		    	 
			}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		
		}
		
		for (int i = 0; i< inventory.size(); i++){
			System.out.println(inventory.get(i));
			
		}
		
		Object [] [] inventoryArray =  new Object [inventory.size()] [];
		
		for (int i = 0; i< inventory.size(); i++){
			ArrayList <String> row =  inventory.get(i);
			inventoryArray[i]= row.toArray(new String [row.size()]);
			
			
		}
		return inventoryArray;
		
	}
	
	public Object[][] getOrderList()
	{
		String command = "select invoice_number, total_cost, date_time_created, status from order_history";
		ArrayList<ArrayList<String>> orders = new ArrayList<ArrayList<String>>();
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     while(rs.next())
		     	{
		    	 	ArrayList <String> currOrder = new ArrayList <String> ();
			        //get item number
			       	String invoice_number = rs.getString("invoice_number");
			       	currOrder.add(invoice_number);
			       	System.out.println("Invoice number: " + invoice_number);
			       	//get item name
			       	String total_cost = rs.getString("total_cost");
			       	currOrder.add(total_cost);
			       	System.out.println("Total cost: " + total_cost);
			       	//get item type
			       	String date_time_created = rs.getString("date_time_created");
			       	currOrder.add(date_time_created);
		        	System.out.println("Date/Time Created: " + date_time_created);
			       	//get quantity
			       	String status = rs.getString("status");
			       	currOrder.add(status);
		        	System.out.println("Status: " + status);
			       
			        orders.add(currOrder);
			     }
			 rs.close();
		    	 
		    	 
			}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		
		}
		
		for (int i = 0; i< orders.size(); i++){
			System.out.println(orders.get(i));
			
		}
		
		Object [] [] orderList =  new Object [orders.size()] [];
		
		for (int i = 0; i< orders.size(); i++){
			ArrayList <String> row =  orders.get(i);
			orderList[i]= row.toArray(new String [row.size()]);
			
			
		}
		return orderList;
	}
	
	
	
	
	public void addItem(String[] item, int id) {
		String command = "INSERT INTO items (name, item_type, supplier_id, quantity, price) VALUES "
				+ "('"+item[0]+"', '"+ item[1]+"', "+ id +", " + Integer.parseInt(item[2])+", '"+ item[3]+"')";
		
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

	public void modifyItem(String[] item, int itemNum) {
	
		String command = "UPDATE items SET name = '" + item[0]+"', item_type = '"+item[1]+"', quantity = "+ Integer.parseInt(item[2])+", unit_price = '"+ item[3]+ "' + unit = '" +item[4]+"' "
				+ "WHERE item_number="+  itemNum;
		try {
		     statement.execute(command);
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);
		     System.exit(0);
		    }
		System.out.println("Mod Succesful");
	}

	public Object[] getUser(String username, boolean supplier) {
		String command = null;
		if(supplier){
			command = "select * from supplier_users where username = '" +username+"'";
			}
		else{
			command = "select * from store_users where username = '" +username+"'";
		}
		Object [] user = new Object [2]; 
		
		//===
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		    
		     while(rs.next()){
		    	 //Retrieve by column name
		        String password = rs.getString("password");
		        System.out.println(password);
		        user[0]=password;  
		     	if(supplier){
			        int sup_id = rs.getInt("supplier_id");
			        System.out.println(sup_id);
			        user[1]=sup_id;  }
		     	else{
		     		int str_id = rs.getInt("store_id");
			        System.out.println(str_id);
			        user[1]=str_id; 
		     	}
		     		
		        }
		      rs.close();
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		     return null;
		    }
		
		//===
		return user;
	}
	
		
	}

