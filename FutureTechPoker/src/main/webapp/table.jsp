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


<div class="river-container">
    <h2>The River</h2>
    <img src="images/Playing Cards/PNG-cards-1.3/2_of_clubs.png" alt="Card 1" width="60" height="90" style="display: inline-block">
    <img src="images/Playing Cards/PNG-cards-1.3/3_of_clubs.png" alt="Card 2" width="60" height="90" style="display: inline-block">
    <img src="images/Playing Cards/PNG-cards-1.3/4_of_clubs.png" alt="Card 3" width="60" height="90" style="display: inline-block">
    <img src="images/Playing Cards/PNG-cards-1.3/5_of_clubs.png" alt="Card 4" width="60" height="90" style="display: inline-block">
    <img src="images/Playing Cards/PNG-cards-1.3/6_of_clubs.png" alt="Card 5" width="60" height="90" style="display: inline-block">
</div>



<form action="GameServlet" method="post">
    <div class="button-container poker-buttons">
        <button type="submit" name="action" value="raise">Raise</button>
        <input type="number" step="25.0" id="raiseAmount" name="raiseAmount" placeholder="Enter Raise" min="0.0" aria-placeholder="0.0" value="0.0">
        <button type="submit" name="action" value="call">Call</button>
        <button type="submit" name="action" value="check">Check</button>
        <button type="submit" name="action" value="fold">Fold</button>
        <button type="submit" name="action" value="all in">All In</button>
    </div>
</form>

</body>
</html>
