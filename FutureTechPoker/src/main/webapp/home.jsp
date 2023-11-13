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
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
<div class="banner">
    <h1>FutureTech Poker</h1>
</div>
<br/>

<div class="centered">
    <h2>Available Games</h2>
</div>
<div style="column-gap: 10px; column-count: 4; align-content: center">
    <a href="table.jsp" style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 1</a>
    <a style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 5</a>
    <a style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 9</a>
    <a style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 2</a>
    <a style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 6</a>
    <a style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 10</a>
    <a style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 3</a>
    <a style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 7</a>
    <a style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 11</a>
    <a style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 4</a>
    <a style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 8</a>
    <a style="background-color: #3498db; color: #000; padding: 10px; border-radius: 10px; border: 2px solid #000; cursor: pointer; margin: 10px; text-decoration: none;">Table 12</a>
</div>

<div class="centered">
    <form action="logoutServlet" method="post">
        <button type="submit">Log Out</button>
    </form>
    <a href="user_profile_information.jsp" class="home-button">User Settings / Information</a>
    <% if(logged.getPermission()!=User.GUEST_PERMISSION){ %>
    <a href="user_friends.jsp" class="home-button">Manage Friends</a>
    <% } //end-else-if %>

    <% if(logged.getPermission()==User.ADMIN_PERMISSION){ %>
    <a href="admin.jsp" class="home-button">Admin Settings</a>
    <% } //end-else-if %>

</div>
<a href="entitytest.jsp" class="home-button">Entity Test Page</a>

<img src="logo.jpeg" alt="FutureTech Logo" width="574" height="223" style="position: fixed; bottom: 0px; left: 50%; transform: translateX(-50%)">
</body>
<% } else { %>
<%
    // If access is denied, send a redirect to another page
    response.sendRedirect("index.jsp");
%>
<% } //end-else-if %>
</html>