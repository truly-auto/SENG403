package ScriptRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Runner
{
    private static String dbURL = "jdbc:derby:derbyDB;create=true";
    private static String sqlDir = "src/main/resources/SQL/";
    // jdbc Connection
    private static Connection conn = null;

    public static void main(String[] args)
    {
        createConnection();
        createDatabase();
        shutdown();
    }
    
    private static void createConnection()
    {
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
    
    private static void createDatabase() {
    	 
    	try {
    		
	    	ScriptRunner runner = new ScriptRunner(conn, true, false);
	    	File dir = new File(sqlDir);
	    	for (File child : dir.listFiles()) {
	    		String extension = getFileExtension(child.getName());
	    		
	    		if (extension.equals(".sql")) {
	    			System.out.println(child.getAbsolutePath());
	    			runner.runScript(new BufferedReader(new FileReader(child.getAbsolutePath())));
	    		}
	    	}
	    	conn.commit();
	    	//reader.close();
    	}
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    	catch (IOException ioExcept) {
    		ioExcept.printStackTrace();
    	}
    }
    
    public static String getFileExtension(String filename) {
    	String extension = "";
		int i = filename.lastIndexOf(".");
		if (i > 0) {
			extension = filename.substring(i);
		}
		return extension;
    }
    
    private static void shutdown()
    {
        try
        {
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            //Don't care as application is closing
        }

    }
}
