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
public class delete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        String author=request.getParameter("author");
        int index = url.indexOf("home.jsp");
        try {
            Connection con = (Connection) getServletContext().getAttribute("con");
            PreparedStatement p = con.prepareStatement("delete from articles where title=?");
            PreparedStatement pp=con.prepareStatement("update author set n_articles=n_articles-1 where username=?");
            pp.setString(1, author);
            p.setString(1, title);
            p.executeUpdate();
            if(author.equals("samir"))
            pp.executeUpdate();
            
            if (index != -1) {
                response.sendRedirect("jsps/home.jsp");
            } else {
                response.sendRedirect("jsps/pending.jsp");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
