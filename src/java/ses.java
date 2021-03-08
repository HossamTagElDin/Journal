
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

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
public class ses implements HttpSessionListener {
    HttpServletRequest req;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        if(se.getSession().isNew())
        {
                            System.out.println("hello boy111111111");

            try
            {
            Cookie c[]=req.getCookies();
            for(Cookie co:c)
            {
                               System.out.println(co.getValue());
 
            }
            }
            catch(Exception e)
            {
                System.out.println("hello boy");
            }
        }
            
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
