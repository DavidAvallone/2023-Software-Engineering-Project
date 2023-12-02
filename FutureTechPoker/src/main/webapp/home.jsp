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

    <div class="animated-background"></div>

    <div class="centered">
        <h2>Available Games</h2>
        <div class="button-container table-buttons">
            <button onclick="window.location.href='m_table.jsp?n=1'"> Table 1</button>
            <button onclick="window.location.href='m_table.jsp?n=2'"> Table 2</button>
            <button onclick="window.location.href='m_table.jsp?n=3'"> Table 3</button>
            <button onclick="window.location.href='m_table.jsp?n=4'"> Table 4</button>
            <button onclick="window.location.href='m_table.jsp?n=5'"> Table 5</button>
            <button onclick="window.location.href='m_table.jsp?n=6'"> Table 6</button>
            <button onclick="window.location.href='m_table.jsp?n=7'"> Table 7</button>
            <button onclick="window.location.href='m_table.jsp?n=8'"> Table 8</button>
            <button onclick="window.location.href='m_table.jsp?n=9'"> Table 9</button>
            <button onclick="window.location.href='m_table.jsp?n=10'"> Table 10</button>
            <button onclick="window.location.href='m_table.jsp?n=11'"> Table 11</button>
            <button onclick="window.location.href='m_table.jsp?n=12'"> Table 12</button>
        </div>

        <br>
        <div class="table-buttons">
            <button onclick="window.location.href='table.jsp?'">Single Player</button>
            <button onclick="window.location.href='tutorial.jsp?'">Tutorial</button>
        </div>
        <br>

        <form action="logoutServlet" method="post">
            <div class="table-buttons">
                <button type="submit" style="position: absolute; left: 85%; top: 125px;">Log Out</button>
            </div>
        </form>

        <div class="table-buttons">
            <button onclick="window.location.href='user_profile_information.jsp'">User Settings / Information</button>
        </div>
        <br>
        <% if(logged.getPermission()!=User.GUEST_PERMISSION){ %>
            <div class="table-buttons">
                <button onclick="window.location.href='FriendsListServlet'">Manage Friends</button>
            </div>
        <% } //end-else-if %>
        <br>
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