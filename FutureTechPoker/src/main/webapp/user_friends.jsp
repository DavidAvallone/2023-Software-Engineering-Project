<%--
  Created by IntelliJ IDEA.
  User: alexkristian
  Date: 11/11/23
  Time: 8:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Friends" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>FutureTech Poker User Friends</title>
  <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="banner">
  <h1>FutureTech Poker</h1>
</div>
<h2>User Details:</h2>
<p>User ID: ${user.userId}</p>
<p>Username: ${user.username}</p>

<h2>Friends List:</h2>
  <ul>
    <%
      // Retrieve the friends attribute from the request scope
      List<String> friends = (List<String>) request.getAttribute("friends");

      // Iterate over the friends and display them
      for (String friend : friends) {
    %>
    <li><%= friend %></li>
    <% } %>
  </ul>
</body>
</html>
