<%--
  Created by IntelliJ IDEA.
  User: neilkeohane
  Date: 10/24/23
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User logged = (User) session.getAttribute("User");
%>
<html>
<% if(logged!=null){ %>
<head>
    <title>FutureTech Poker User Home</title>
    <link rel="stylesheet" type="text/css" href="home.css">
</head>

<body>
    <div class="banner">
        <h1>FutureTech Poker</h1>
    </div>
    <br/>

    <div class="centered">
        <h2>Available Games</h2>

        <div class="button-container table-buttons">
            <button onclick="window.location.href='table.jsp'"> Table 1</button>
            <button onclick="window.location.href='table.jsp'"> Table 2</button>
            <button onclick="window.location.href='table.jsp'"> Table 3</button>
            <button onclick="window.location.href='table.jsp'"> Table 4</button>
            <button onclick="window.location.href='table.jsp'"> Table 5</button>
            <button onclick="window.location.href='table.jsp'"> Table 6</button>
            <button onclick="window.location.href='table.jsp'"> Table 7</button>
            <button onclick="window.location.href='table.jsp'"> Table 8</button>
            <button onclick="window.location.href='table.jsp'"> Table 9</button>
            <button onclick="window.location.href='table.jsp'"> Table 10</button>
            <button onclick="window.location.href='table.jsp'"> Table 11</button>
            <button onclick="window.location.href='table.jsp'"> Table 12</button>
        </div>

        <br>

        <form action="logoutServlet" method="post">
            <div class="table-buttons">
                <button type="submit">Log Out</button>
            </div>
        </form>

        <div class="table-buttons">
            <button onclick="window.location.href='user_profile_information.jsp'">User Settings / Information</button>
        </div>
        <% if(logged.getPermission()!=User.GUEST_PERMISSION){ %>
            <div class="table-buttons">
                <button onclick="window.location.href='user_friends.jsp'">Manage Friends</button>
            </div>
        <% } //end-else-if %>

        <% if(logged.getPermission()==User.ADMIN_PERMISSION){ %>
            <div class="table-buttons">
                <button onclick="window.location.href='admin.jsp'">Admin Settings</button>
            </div>
        <% } //end-else-if %>
    </div>

<%--<img src="logo.jpeg" alt="FutureTech Logo" width="574" height="223" style="position: fixed; bottom: 0px; left: 50%; transform: translateX(-50%)">--%>
</body>
<% } else { %>
<%
    // If access is denied, send a redirect to another page
    response.sendRedirect("index.jsp");
%>
<% } //end-else-if %>
</html>