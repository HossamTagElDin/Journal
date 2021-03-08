
package classes;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hossam
 */
public class dbconnection {
    public static Connection dbconnect(){
        try{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con =DriverManager.getConnection("jdbc:derby://localhost:1527/journal", "samir", "123");
        return con;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
}
