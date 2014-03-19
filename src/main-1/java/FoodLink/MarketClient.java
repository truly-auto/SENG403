/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FoodLink;
import java.util.*;

/**
 *
 * @author ts
 */
public class MarketClient {
    private String inventoryID;
    private String database;
    private String userID;
    private List<String> orders;    // won't be string, temporary until we figure out orders
                                    // orders should be sorted by date (which should be numerically sequential by order #
    
    public MarketClient(String loggedInUser, String databaseLink)
    {
        userID = loggedInUser;
        database = databaseLink;
        inventoryID = "0"; // = userID.getInventory();
    }
    
    private int selectItem(String itemNumber)
    {
        //select itemNumber;
        // connect to db and get() stuff
        return 0;
    }
    
    public String getMarketInfo()
    {
        // get marketInfo from loggedInUser and such
        return "nameOfMarket";
        
    }
    
    private int autoOrders()
    {
        // not sure
        return 0;
    }
    public int editOrder(String orderNumber)
    {
        // get order item
        // getorderNumber()
        // which should pull from database and make it editable
        return 0;
        
    
    }
    // should be a get class
    public String currentInventory()
    {
       // should be able to pull from Inventory from database
        return "inventory";
    }
    
    public static void main()
    {
        
    }
}
