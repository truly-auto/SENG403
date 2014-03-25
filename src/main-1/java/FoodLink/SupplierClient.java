/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FoodLink;
// import package.

/**
 *
 * @author 
 */
public class SupplierClient {
    // note loggedInUser will probably be supplier info, probably change to a class with more details
    private String database;
    private String supplierID;
    
    public SupplierClient(String loggedInUser, String databaseLink)
    {
        // DatabaseLink, will figure out what needs to come in afterwords
        // Set up constructors and database link
        supplierID = "0"; // loggedInUser.getSupplierInfo() or the like
        database = databaseLink;
    }
    public static String getSupplierInfo()
    {
        return "nameOfSupplier"; // need to figgure out supplier/user class
    
    }
    private int addItem(String itemToAddKey, Integer quantity)    
    {
        // connect to database
        // check to see if adding is valid
        return 0;
        
    }
    // set and get??
    // should be same as addItem
    // item can be positive or negative and change accordingly?
    public int itemQuantity(String item, int quantity)
    {
        // 
        return 0;
    
    }
    
   
    
    
}
