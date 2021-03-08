
import classes.dbconnection;
import java.sql.Connection;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author hossam
 */
public class contextlis implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection con =dbconnection.dbconnect();
        sce.getServletContext().setAttribute("con", con);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
           try{
            Connection con =(Connection)sce.getServletContext().getAttribute("con");
            con.close();
           }
           catch(Exception e)
           {
               System.out.println("close error");
           }
    }
}
