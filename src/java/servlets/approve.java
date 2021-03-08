/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
public class approve extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        String author = request.getParameter("author");
        try {
            Connection con = (Connection) getServletContext().getAttribute("con");
            PreparedStatement p = con.prepareStatement("update articles set approved=? where title=?");
            PreparedStatement pp = con.prepareStatement("update author set n_articles=n_articles+1 where username=?");
            pp.setString(1, author);
            p.setBoolean(1, true);
            p.setString(2, title);
            p.executeUpdate();
            pp.executeUpdate();
           response.sendRedirect("jsps/pending.jsp");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
