package servlets;

import classes.person;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hossam
 */
public class savepost extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = ((person) request.getSession().getAttribute("user")).getUsername();
        String desc = request.getParameter("desc");
        try {
            Connection con = (Connection) getServletContext().getAttribute("con");
            PreparedStatement p = con.prepareStatement("insert into articles values (?,?,?,?)");
            p.setString(1, author);
            p.setString(2, title);
            p.setString(3, desc);
            if (author.equals("samir")) {
                p.setBoolean(4, true);
                PreparedStatement pp = con.prepareStatement("update author set n_articles=n_articles+1 where username='samir'");
                pp.executeUpdate();
            } else {
                p.setBoolean(4, false);
            }

            p.executeUpdate();
            response.sendRedirect("jsps/home.jsp");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
