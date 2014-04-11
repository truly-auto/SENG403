/*this class connects to the database and runs queries search as select, insert, update etc*/


package FoodLink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


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
	
	public void addComment(String comment, int store_id, int supplier_id){
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		String creationDate = sdf.format(Calendar.getInstance().getTime());
		
		String command = "INSERT INTO supplier_comments (comment, created, store_id, supplier_id) VALUES "
				+ "('"+comment+ "', '" + creationDate + "', " + store_id +", " + supplier_id +")";
		
		try{
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
		        
		        String units = rs.getString("unit");
		        System.out.println(units);
		        currentItem.add(units);  
		     
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
	
	public String [] getSpecSupermarket(int id){
		String command = "select * from supermarket where store_id = " +id;
		String [] supermarket = new String [5]; 
		//===
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     while(rs.next()){
		    	//ArrayList <String> currentItem = new ArrayList <String> (); 
		    	 //Retrieve by column name
		        String name = rs.getString("name");
		        System.out.println(name);
		        supermarket[0]=name;  
		     	
		        String number = rs.getString("phonenumber");
		        System.out.println(number);
		        supermarket[1]=number;  
		     	
		        String add = rs.getString("address");
		        System.out.println(add);
		        supermarket[2]=add;  
		     	
		        String city = rs.getString("city");
		        System.out.println(city);
		        supermarket[3]=city;  
		     	
		        String email = rs.getString("email");
		        System.out.println(email);
		        supermarket[4]=email;  
		     	
		        }
		      rs.close();
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		    }
		
		//===
		
		return supermarket;
	}
	
	//getting supplier and supermarket users from database (id, if supermarket)
	public Object [] [] getUser(int id, boolean supermarket){
		String command ="";
		if (supermarket)
			{command = "select * from store_users where store_id = " +id;
			}
		else{
			command = "select * from supplier_users where supplier_id = " +id;}
		ArrayList<ArrayList<String>> userArrayList = new ArrayList<ArrayList<String>>();
		//===
		try {
			statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     while(rs.next()){
		    	 ArrayList <String> currentUser = new ArrayList <String> ();  
		    	 //Retrieve by column name
		        String username = rs.getString("username");
		        currentUser.add(username);  
		     	
		        
		        String manager = rs.getString("manager");
		        if(manager.equals("true"))
		        		{manager="Administrative User";}
		        else
		        	{manager="Limited User";}
		        currentUser.add(manager);
		        
		     	userArrayList.add(currentUser);   }
		      rs.close();
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		    }
		
		//===
		
		for (int i = 0; i< userArrayList.size(); i++){
			System.out.println(userArrayList.get(i));
			
		}
		
		Object [] [] userArray =  new Object [userArrayList.size()] [];
		
		for (int i = 0; i< userArrayList.size(); i++){
			ArrayList <String> row =  userArrayList.get(i);
			userArray[i]= row.toArray(new String [row.size()]);
			
			
		}
		
		return userArray;
	}

	public String [] getSupplierNames()
	{
		String command = "select name from supplier";
		ArrayList<String> supplierNames = new ArrayList<String>();
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     
		     while(rs.next())
		     	{
			         String name = rs.getString("name");
			         supplierNames.add(name);
			         //Display values
			         System.out.print(", name: " + name);
			     }
			}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		
		
		}
		return supplierNames.toArray(new String [supplierNames.size()]);

	}
	
	public String [] getSupermarketName(int id)
	{
		String command = "select name from supermarket where store_id = " + id;
		ArrayList<String> storeNames = new ArrayList<String>();
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     
		     while(rs.next())
		     	{
			         String name = rs.getString("name");
			         storeNames.add(name);
			         //Display values
			         System.out.print(" Supermarket name: " + name);
			     }
			}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);
		
		
		}
		return storeNames.toArray(new String [storeNames.size()]);

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
		        	itemList.add(quantity);
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
	

	public Object[][] getSupplierInventory(int id)
	{
		String command = "select * from items where supplier_id = " + id;
		ArrayList<Object []> itemsList = new ArrayList<Object []>() ;
		
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     int counter = 0;
		     while(rs.next())
		     	{
		    	 	ArrayList<String> tempItems = new ArrayList<String>();
			        //get item number
			       	String item_number = rs.getString("item_number");
			       	tempItems.add(item_number);
			       	System.out.println("Item number: " + item_number);
			       	//get item name
			       	String name = rs.getString("name");
			       	tempItems.add(name);
			       	System.out.println("Name: " + name);
			       	//get item type
			       	String item_type = rs.getString("item_type");
			       	tempItems.add(item_type);
		        	System.out.println("Item Type: " + item_type);
			       	//get quantity
		        	String quantity = rs.getString("quantity");
		        	tempItems.add(quantity);
			       	//get price
		        	String unit_price = rs.getString("unit_price");
		        	tempItems.add(unit_price);
		        	System.out.println("Unit Price: " + unit_price);
		           	//get price
		        	String unit = rs.getString("unit");
		        	tempItems.add(unit);
		        	System.out.println("Unit: " + unit);
		        	
		        	itemsList.add(tempItems.toArray());
		     	}
		}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		
		}

		for (int i = 0; i< itemsList.size(); i++){
			System.out.println(itemsList.get(i));
			
		}

		Object[][] returnArray = new Object[0][0];
		if (itemsList.size() > 0) {
			returnArray = new Object[itemsList.size()] [itemsList.get(0).length];
			
			for (int i = 0; i < itemsList.size(); i++) {
				returnArray[i] = itemsList.get(i);
			}
		
		}
		
		return returnArray;
	}
		     
	//invoice list for supermarket
	public Object[][] getOrderList(int id)
	{
		String command = "select invoice_number, supplier, total_cost, date_time_created, status from order_history where store_id = " + id;
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
			       	//get supplier name
			       	String supplier = rs.getString("supplier");
			       	currOrder.add(supplier);
			       	System.out.println("Supplier: " + supplier);
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
	
	//get orders for supplier from different supermarkets
	public Object[][] getOrderListSupplier(int id)
	{
		String command = "select invoice_number, supermarket, total_cost, date_time_created, status, store_id from order_history where supplier_id = " + id;
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
			       	//get supplier name
			       	String supermarket = rs.getString("supermarket");
			       	currOrder.add(supermarket);
			       	System.out.println("Supermarket: " + supermarket);
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
		        	
		        	String store_id = rs.getString("store_id");
		        	currOrder.add(store_id);
		        	System.out.println("Store id: " + store_id);
			       
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
	
	
	public void manageSupplierUsers(String[] user, int id, boolean add) {
		String command;
		
		if (add==true)
			{command = "INSERT INTO supplier_users (username, password, supplier_id, manager) VALUES "
				+ "('"+user[0]+"', '"+ user[1]+"', "+ id +", '" + user[2] + "')";
			}
		else {command = "DELETE FROM supplier_users WHERE username = '"+ user[0]+"'";
			}
		
		try {
		     statement.execute(command);
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);
		     System.exit(0);
		    }
		System.out.println("Add or delete Succesful");
		
	}
	
	public void manageSupermarketUsers(String[] user, int id, boolean add) {
		String command;
		
		if (add==true)
			{command = "INSERT INTO store_users (username, password, store_id, manager) VALUES "
				+ "('"+user[0]+"', '"+ user[1]+"', "+ id +", '" + user[2] + "')";
			}
		else {command = "DELETE FROM store_users WHERE username = '"+ user[0]+"'";
			}
		
		try {
		     statement.execute(command);
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);
		     System.exit(0);
		    }
		System.out.println("Add or delete Succesful");
		
	}
	public void manageItems(String[] item, int id, boolean add) {
		String command;
		
		if (add==true)
			{command = "INSERT INTO items (name, item_type, supplier_id, quantity, unit_price , unit) VALUES "
				+ "('"+item[0]+"', '"+ item[1]+"', "+ id +", " + Integer.parseInt(item[2])+", "+Double.parseDouble(item[3])+" ,' "+ item[4]+"')";
			}
		else {command = "DELETE FROM items WHERE item_number = "+ id;
			}
		
		try {
		     statement.execute(command);
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);
		     System.exit(0);
		    }
		System.out.println("Add or delete Succesful");
		
	}


	public void modifyItem(String[] item, int itemNum) {
		System.out.println(item[0]);
		System.out.println(item[1]);
		System.out.println(item[2]);
		System.out.println(item[3]);
		System.out.println(item[4]);
		String command = "UPDATE items SET name = '" + item[0]+"', item_type = '"+item[1]+"', quantity = "+ Integer.parseInt(item[2])+", unit_price = "+ Integer.parseInt(item[3])+ ", unit = '" +item[4]+"' "
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
		Object [] user = new Object [3]; 
		
		//===
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		    
		     while(rs.next()){
		    	 //Retrieve by column name
		        String password = rs.getString("password");
		        System.out.println(password);
		        user[0]=password;  
		     	
		        String manager = rs.getString("manager");
		        user[2]=manager;
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
	
	public void addSupermarketItem(String[] item, int id) {
		
		String command = "INSERT INTO supermarket_inventory (name, inventory_type, supermarket_id, quantity, unit_price, unit, supplier_item_number) VALUES "
					+ "('"+item[0]+"', '"+ item[1]+"', "+ id +", " + Integer.parseInt(item[2])+", "+ Double.parseDouble(item[3]) +", '" + item[4] + "', " + item[5] +  ")";
		
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
	
	public void modifySupermarketItem(String[] item, int itemNum) {
		
		String command = "UPDATE supermarket_inventory SET name = '" + item[0]+"', inventory_type = '"+item[1]+"', quantity = "+ Integer.parseInt(item[2])+", unit_price = "+ Double.parseDouble(item[3])+ " , unit = '" +item[4]+"' "
				+ "WHERE inventory_number="+  itemNum;
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

	public void deleteInvetory(int id) {
		//delete any auto-orders first
		String deleteThreshold = "DELETE FROM automatic_orders WHERE supermarket_item = " + id;
		
		try {
		     statement.execute(deleteThreshold);
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + deleteThreshold);
		     System.out.println(e);
		     System.exit(0);
		    }
		
		String command = "DELETE FROM supermarket_inventory  WHERE inventory_number = "+ id;
			
		
		try {
		     statement.execute(command);
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);
		     System.exit(0);
		    }
		System.out.println("delete Succesful");
		
	}


	public Object[][] getSupermarketInventory(int id) {

		String command = "select * from supermarket_inventory where supermarket_id = " + id;
		ArrayList<Object []> itemsList = new ArrayList<Object []>() ;
		
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     
		     while(rs.next())
		     	{
		    	 	ArrayList<String> tempItems = new ArrayList<String>();
			        //get item number
			       	String item_number = rs.getString("inventory_number");
			       	tempItems.add(item_number);
			       	System.out.println("Item number: " + item_number);
			       	//get item name
			       	String name = rs.getString("name");
			       	tempItems.add(name);
			       	System.out.println("Name: " + name);
			       	//get item type
			       	String item_type = rs.getString("inventory_type");
			       	tempItems.add(item_type);
		        	System.out.println("Item Type: " + item_type);
			       	//get quantity
		        	String quantity = rs.getString("quantity");
		        	tempItems.add(quantity);
			       	//get price
		        	String unit_price = rs.getString("unit_price");
		        	tempItems.add(unit_price);
		        	System.out.println("Unit Price: " + unit_price);
		           	//get price
		        	String unit = rs.getString("unit");
		        	tempItems.add(unit);
		        	System.out.println("Unit: " + unit);
		        	
		        	itemsList.add(tempItems.toArray());
		     	}
		    	 
		    	 
			}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		
		}
		
		Object[][] returnArray = new Object[0][0];
		if (itemsList.size() > 0) {
			returnArray = new Object[itemsList.size()] [itemsList.get(0).length];
			
			for (int i = 0; i < itemsList.size(); i++) {
				returnArray[i] = itemsList.get(i);
			}
		
		}
		
		return returnArray;
	}
	
	public void addAutomaticOrder(String[] order, int id) {
		
		String command = "INSERT INTO automatic_orders (threshold, quantity, supermarket_item) VALUES "
				+ "(" + Integer.parseInt(order[0]) + ", "+ Integer.parseInt(order[1]) + ", " + id + ")";
		
		try {
		     statement.execute(command);
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);
		     System.exit(0);
		   }
		System.out.println("Add Successful");
	}
	
	public Integer[] getThreshold(int id) {
		String command = "select * from automatic_orders where supermarket_item = " + id;
		ArrayList<Integer> itemsList = new ArrayList<Integer>() ;
		
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     
		     //while(rs.next()) {
		    if (rs.next()) {
		    	 itemsList.add(Integer.parseInt(rs.getString("threshold")));
		    	 itemsList.add(Integer.parseInt(rs.getString("quantity")));
		     }
		}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);
		}
		
		return itemsList.toArray(new Integer[itemsList.size()]);
	}
	
	public boolean addOrderInformation(int invoice_number, String name, String item_type, double quantity, double unit_price, String unit, double total, double grandTotal){
		String command = "INSERT INTO order_items_list (invoice_number, name, item_type, quantity, unit_price, unit, total, grandTotal) VALUES "
				+ "("+ invoice_number + "," + "'" + name+ "'" + ","+ "'" + item_type + "'" + "," + quantity +","+ unit_price + "," + "'" + unit + "'" + "," + total + "," + grandTotal + ")";
		

		try {
		     statement.execute(command);
		    }
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);
		     System.exit(0);
		    }
		System.out.println("Add item informatio succesful");
		return true;
		
	}
	
	public boolean addToOrderHistory(String supplierName, String supermarketName, Double grandTotal, String orderStatus, int store_id, int supplier_id){
		java.util.Date date= new java.util.Date();
		
		//printed time may vary from the table time by a few milliseconds 
		System.out.println("Current timestamp added: " + new Timestamp(date.getTime()));
		
		
		String current_timestamp = "" +  new Timestamp(date.getTime());
		String command = "INSERT INTO order_history (supplier, supermarket, total_cost, date_time_created, status, store_id, supplier_id) VALUES "
				+ "(" + "'" + supplierName + "'" + "," +"'" + supermarketName + "'" + "," + grandTotal + "," + "'" +current_timestamp + "'" +"," + "'" +orderStatus + "'" + "," + store_id + "," +  supplier_id + ")";
		

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
		return true;
		
	}
	
	public int getLastElementInOrderHistory()
	{
		String command = "SELECT MAX(INVOICE_NUMBER) FROM ORDER_HISTORY";
		
		int invoiceNum = -1;
		
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     
		     while(rs.next())
		     	{
			         invoiceNum = rs.getInt(1);
			         System.out.println("The last INVOICE_NUMBER: " + invoiceNum);
			     }
		    
		     
			}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		
		
		}
		return invoiceNum;
	}
	
	//Get the list of comments for a supplier
	public Object[][] getSuplierComments (int supplier_id)
	{
		String command = "select id, comment, market.name, created from supplier_comments join supermarket as market on supplier_comments.store_id = market.store_id where supplier_id = " + supplier_id + "order by created desc";
		ArrayList<ArrayList<String>> comments = new ArrayList<ArrayList<String>>();
		try {
			statement.execute(command);
			ResultSet rs = statement.getResultSet();
			while(rs.next())
			{
				ArrayList <String> currComment = new ArrayList <String> ();
				
				//get supermarket name
				String name = rs.getString("name");
				currComment.add(name);
				System.out.println("Supermarket name: " + name);
				
				//get comment
				String comment = rs.getString("comment");
				currComment.add(comment);
				System.out.println("Comment: " + comment);
				
				String createdDate = rs.getString("created");
				currComment.add(createdDate);
				System.out.println("Date Created: " + createdDate);
				
				//get comment ID
				String id = rs.getString("id");
				currComment.add(id);
				System.out.println("ID: " + id);
				
				comments.add(currComment);
			}
			rs.close();



		}
		catch (SQLException e) {
			e.fillInStackTrace();
			System.out.println("Error executing: " + command);
			System.out.println(e);;

		}

		for (int i = 0; i< comments.size(); i++){
			System.out.println(comments.get(i));

		}

		Object [] [] commentsList =  new Object [comments.size()] [];

		for (int i = 0; i< comments.size(); i++){
			ArrayList <String> row =  comments.get(i);
			commentsList[i]= row.toArray(new String [row.size()]);


		}
		return commentsList;
	}
	
	//retrieve data for a specific invoice_number
	public Object[][] getOrderItems(int invoice_number)
	{
		String command = "select name, item_type, quantity, unit_price, unit, total from order_items_list where invoice_number = " + invoice_number;
		ArrayList<ArrayList<String>> orderItemsList = new ArrayList<ArrayList<String>>();
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     while(rs.next())
		     	{
		    	 	ArrayList <String> currOrder = new ArrayList <String> ();
			       	//get item name
			       	String name = rs.getString("name");
			       	currOrder.add(name);
			       	System.out.println("Supplier: " + name);
			       	//get item type
			       	String item_type = rs.getString("item_type");
			       	currOrder.add(item_type);
			       	System.out.println("Total cost: " + item_type);
			       	//get quantity
			       	String quantity = rs.getString("quantity");
			       	currOrder.add(quantity);
		        	System.out.println("Date/Time Created: " + quantity);
		        	//get unit price
			       	String unit_price = rs.getString("unit_price");
			       	currOrder.add(unit_price);
		        	System.out.println("Date/Time Created: " + unit_price);
		        	//get unit price
			       	String unit = rs.getString("unit");
			       	currOrder.add(unit);
		        	System.out.println("Date/Time Created: " + unit);
		        	//get total
			       	String total = rs.getString("total");
			       	currOrder.add(total);
		        	System.out.println("Date/Time Created: " + total);
			       
			        orderItemsList.add(currOrder);
			     }
			 rs.close();

		    	 
		    	 
			}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		
		}

		for (int i = 0; i< orderItemsList.size(); i++){
			System.out.println(orderItemsList.get(i));
			
		}
		
		Object [] [] orderList =  new Object [orderItemsList.size()] [];
		
		for (int i = 0; i< orderItemsList.size(); i++){
			ArrayList <String> row =  orderItemsList.get(i);
			orderList[i]= row.toArray(new String [row.size()]);
			
			
		}
		return orderList;

	}
	
	public boolean updateOrderStatus(String status, int invoice_number)
	{
		String command = "UPDATE order_history SET status = " + "'" + status + "'" + "WHERE invoice_number = " + invoice_number;
		
		try {
		     statement.execute(command);	    
		     return true;
			}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);;
		     return false;
		
		}
	}
	
	public String getStoreName(int id)
	{
		String command = "select name from supermarket where store_id = " + id;
		String name = null;
		try {
		     statement.execute(command);
		     ResultSet rs = statement.getResultSet();
		     
		     while(rs.next())
		     	{
			         name = rs.getString("name");
			         //Display values
			         System.out.print(" Supermarket name: " + name);
			     }
			}
		catch (SQLException e) {
		     e.fillInStackTrace();
		     System.out.println("Error executing: " + command);
		     System.out.println(e);
		
		
		}
		return name;

	}
	
}

