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
		     System.out.println(e);;
		
		
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
		String command = "INSERT INTO items (name, item_type, supplier_id, quantity, unit_price , unit) VALUES "
				+ "('"+item[0]+"', '"+ item[1]+"', "+ id +", " + Integer.parseInt(item[2])+", "+Double.parseDouble(item[3])+" ,' "+ item[4]+"')";
		
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
	
	public void addSupermarketItem(String[] item, int id, boolean customItem) {
		
		String command = "";
		if (customItem) {
			//Custom additions don't need the supplier's item number
			command = "INSERT INTO supermarket_inventory (name, inventory_type, supermarket_id, quantity, unit_price, unit) VALUES "
					+ "('"+item[0]+"', '"+ item[1]+"', "+ id +", " + Integer.parseInt(item[2])+", "+ Double.parseDouble(item[3]) +", '" + item[4] + "')";
		}else{
			command = "INSERT INTO supermarket_inventory (name, inventory_type, supermarket_id, quantity, unit_price, unit, supplier_item_number) VALUES "
					+ "('"+item[0]+"', '"+ item[1]+"', "+ id +", " + Integer.parseInt(item[2])+", "+ Double.parseDouble(item[3]) +", '" + item[4] + "', " + item[5] +  ")";
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
}
