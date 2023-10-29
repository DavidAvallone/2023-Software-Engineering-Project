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

<body>
<div class="banner">
    <h1><%= "FutureTech Poker" %></h1>
</div>
<br/>
<%--<h1>Welcome, <%=((User) session.getAttribute("User")).getLogin()%></h1>--%>

<div class="centered">
    <h2>Available Games</h2>
</div>
<div class="centered" style="column-count: 2;">
    <div class="centered">
        <a href="table.jsp">Table 1\nTest</a>
        <a>Table 3</a>
        <a>Table 5</a>
        <a>Table 7</a>
        <a>Table 9</a>
    </div>
    <div class="centered">
        <a>Table 2</a>
        <a>Table 4</a>
        <a>Table 6</a>
        <a>Table 8</a>
        <a>Table 10</a>
    </div>
</div>

<div class="centered">
    <form action="logoutServlet" method="post">
        <button type="submit">Log Out</button>
    </form>
    <form action="deleteUserServlet" method="post">
        <button type="submit">Delete Account</button>
    </form>
</div>
<img src="logo.jpeg" alt="FutureTech Logo" width="574" height="223" style="position: fixed; bottom: 0px; left: 50%; transform: translateX(-50%)">
</body>
</html>