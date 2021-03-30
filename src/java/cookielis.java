
import classes.person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
public class cookielis implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        if (req.getSession().isNew()) {
            Cookie c[] = req.getCookies();

            try {
                for (Cookie co : c) {
                    if (co.getName().equals("name")) {
                        String username = co.getValue();

                        Connection con = (Connection) req.getServletContext().getAttribute("con");
                        PreparedStatement p = con.prepareStatement("select * from author where username=?");
                        p.setString(1, username);
                        ResultSet r = p.executeQuery();

                        if (r.next()) {
                            person per = new person();
                            per.setUsername(username);
                            per.setN_articles(r.getInt("n_articles"));
                            per.setF_name(r.getString("f_name"));
                            req.getSession().setAttribute("user", per);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }

        }
    }
}
