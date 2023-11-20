<%--
  Created by IntelliJ IDEA.
  User: bhblades
  Date: 10/29/2023
  Time: 2:41 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FutureTech Poker Table</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="banner">
    <h1><%= "FutureTech Poker" %></h1>
</div>

<form action="GameServlet" method="post" >

    <div class="button-container poker-buttons">
        <button type="submit" name="action" value="raise">Raise</button>
        <button type="submit" name="action" value="call">Call</button>
        <button type="submit" name="action" value="check">Check</button>
        <button type="submit" name="action" value="fold">Fold</button>
        <button type="submit" name="action" value="all in">All In</button>
    </div>
</form>
</body>
</html>
