package FoodLink;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTestHelper {

	private String dbURL = "jdbc:derby:derbyDB;";
	private Connection conn = null;
	private Statement statement;

	public DatabaseTestHelper(){
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

	/**
	 * Method to nuke the database completely. Use before tests
	 */
	public void clearDatabase() {
		executeCommand("Delete From supplier_comments");
		executeCommand("Delete From automatic_orders");
		executeCommand("Delete From order_items_list");
		executeCommand("Delete From order_history");
		executeCommand("Delete From supplier_users");
		executeCommand("Delete From store_users");
		executeCommand("Delete From supermarket_inventory");
		executeCommand("Delete From items");
		executeCommand("Delete From Supplier");
		executeCommand("Delete From Supermarket");
	}

	public void executeCommand(String command) {
		try {
			statement.execute(command);
		}
		catch (SQLException e) {
			e.fillInStackTrace();
			System.out.println("Error executing: " + command);
			System.out.println(e);
			System.exit(0);
		}
	}

	public int insertAndReturnID(String command, String idColumn) {

		ResultSet rs;
		int id = -1;
		idColumn = idColumn.toUpperCase();

		try {
			PreparedStatement pstmt = conn.prepareStatement(command, new String[] {idColumn}); 

			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys(); // will return the ID in ID_COLUMN
			rs.next();
			id = rs.getInt(1);
		}
		catch (SQLException e) {
			e.fillInStackTrace();
			System.out.println("Error executing: " + command);
			System.out.println(e);
			System.exit(0);
		}

		return id;
	}

	public static void main(String[] args) {
		DatabaseTestHelper db = new DatabaseTestHelper();
		db.clearDatabase();
		String command = "INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('Jane Tops','403-989-3214','1 14 ST NW.','Calgary','www.yellowpages.com/janetops')";
		System.out.println(db.insertAndReturnID(command, "store_id"));

	}
}
