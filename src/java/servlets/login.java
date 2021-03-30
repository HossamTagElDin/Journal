package servlets;

import classes.person;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hossam
 */
public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);

        try {
            Connection con = (Connection) getServletContext().getAttribute("con");

            PreparedStatement p = con.prepareStatement("select * from author where username=? and password=?");

            p.setString(1, username);
            p.setString(2, password);
            ResultSet r = p.executeQuery();

            if (r.next()) {

                person per = new person();
                per.setUsername(username);
                per.setN_articles(r.getInt("n_articles"));
                per.setF_name(r.getString("f_name"));
                request.getSession().setAttribute("user", per);

                Cookie c = new Cookie("name", per.getUsername());
                c.setMaxAge(60 * 60 * 60 * 60);
                response.setHeader("Expires", "");
                response.addCookie(c);
                response.sendRedirect("jsps/home.jsp");
            } else {
                response.sendRedirect("login error.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
