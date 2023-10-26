<%--
  Created by IntelliJ IDEA.
  User: neilkeohane
  Date: 10/24/23
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.entity.User" %>
<%@ page import="controller.service.UserService" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FutureTech Poker User Home</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<h1>Welcome, <%=((User) session.getAttribute("User")).getLogin()%></h1>
<body>
<h1><%= "FutureTech Poker" %></h1>
<br/>
<form action="logoutServlet" method="post">
    <button type="submit" class="btn btn-success me-2">Log Out</button>
</form>
<form action="deleteUserServlet" method="post">
    <button type="submit" class="btn btn-success me-2">Delete Account</button>
</form>
</body>
</html>