
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>reader page</title>
        <style>
            body
            {
                background-color: #0B3B72;
            }
            h1
            {
                color:white;
                margin-left: 550px;
                font-size: 50px;
                margin-top: 50px;  
            }
            div
            {
                background-color: white;
                margin:auto;
                width:500px;
                height: 300px;
                margin-bottom: 20px;
                overflow: scroll;

            }
        </style>
    </head>
    <body>
        <h1>Legen- wait for it -dary news</h1>
        <%
            Connection con = (Connection) getServletContext().getAttribute("con");
            try {
                PreparedStatement p_user = con.prepareStatement("select * from author order by n_articles desc");
                ResultSet r_user = p_user.executeQuery();
                while (r_user.next()) {
                    PreparedStatement p_post = con.prepareStatement("select * from articles where author=? and approved=?");
                    p_post.setString(1, r_user.getString("username"));
                    p_post.setBoolean(2,true);
                    ResultSet r_post = p_post.executeQuery();
                    String name = r_user.getString("f_name");
                    while (r_post.next()) {
        %>
        <div>
            <h2><%=r_post.getString("title")%> </h2>
            <h4 style='margin-top:-20px'>Author: "<%=name%></h4>
            <p><%= r_post.getString("article")%></p>
        </div>
        <%
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        %>
    </body>
</html>
