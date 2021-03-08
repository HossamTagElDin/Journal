
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="classes.person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>home</title>
        <style>
            h1
            {
                color:white;
                margin-left: 550px;
                font-size: 50px;
                margin-top: 50px;

            }
            aside
            {
                margin-left: 400px;
                position: fixed;
            }
            aside a
            {
                width:250px;
                height: 40px;
                display:block;
                border:2px solid white;
                border-radius: 10px;
                text-decoration: none;
                color:white;
                font-size:40px;
                font-family: courier;
                padding-left: 20px;
                padding-top: 10px;
                margin-top: 20px;
            }
            div
            {
                background-color: white;
                float:right;
                width:500px;
                height: 300px;
                margin-right: 600px;
                margin-top: 60px;
            }
            div
            #post-content
            {
                width:500px;
                height:300px;
            }

        </style>
    </head>
    <body style="background-color: #0B3B72;">
        <h1>Legen- wait for it -dary news</h1>
        <h2 style="color:white;
            margin-left: 700px;
            font-size: 30px;
            margin-top: 50px;
            ">
            Welcome,<%=((person) request.getSession().getAttribute("user")).getF_name()%>

        </h2>
        <aside>
            <%
                person per = (person) request.getSession().getAttribute("user");
                if (per.getUsername().equals("samir")) {
                    out.println("<a href='home.jsp' >home</a>");
                }
            %>
            <% out.println("<a href='../logout' >logout</a>");%>
        </aside>
        
        <%
            Connection con = (Connection) getServletContext().getAttribute("con");
            

        %>
        
        <%
            
            try {
                PreparedStatement p_user = con.prepareStatement("select * from author order by n_articles desc");
                ResultSet r_user = p_user.executeQuery();
                while (r_user.next()) {
                    if (r_user.getString("username").equals(per.getUsername())) {
                        continue;
                    }
                    PreparedStatement p_post = con.prepareStatement("select * from articles where author=? and approved=?");
                    p_post.setString(1, r_user.getString("username"));
                    p_post.setBoolean(2, false);
                    ResultSet r_post = p_post.executeQuery();
                    String name = r_user.getString("f_name");
                    while (r_post.next()) {

        %>
        <div style="overflow:scroll">
            <% if (per.getUsername().equals("samir")) {
            %>
            <form action="../delete" method="post" >
                <input type="hidden" name="author" value="<%=r_post.getString("author")%>">
                <input type="hidden" name="title" value="<%=r_post.getString("title")%>">
                <input type="hidden" name="url" value="<%= request.getRequestURL()%>">
                <input type="submit" value="delete">
            </form>
                <form action="../approve" method="post" >
                <input type="hidden" name="author" value="<%=r_post.getString("author")%>">
                <input type="hidden" name="title" value="<%=r_post.getString("title")%>">
                <input type="hidden" name="url" value="<%= request.getRequestURL()%>">
                <input type="submit" value="approve">
            </form>
            
            <%
                }
            %>

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
