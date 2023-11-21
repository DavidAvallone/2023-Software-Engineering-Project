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
    <link rel="stylesheet" type="text/css" href="poker.css">
</head>

<body background="images/table_background.png" style="background-size: cover; background-position: center; background-repeat: no-repeat; margin: 0; padding: 0;">

<div class="centered">
    <p style="position: absolute; top: 15px;">Player Name: Current Bet: Currency:</p>
    <div class="hand" style="position: absolute; top: 50px;">
        <img src="images/cardbacks.png" alt="Card 1">
        <img src="images/cardbacks.png" alt="Card 2">
    </div>
</div>


<p style="position: absolute; top: 165px; left: 10%">Player Name: Current Bet: Currency:</p>
<div class="hand" style="position: absolute; top: 200px; left: 15%">
    <img src="images/cardbacks.png" alt="Card 1">
    <img src="images/cardbacks.png" alt="Card 2">
</div>

<p style="position: absolute; top: 415px; left: 10%">Player Name: Current Bet: Currency:</p>
<div class="hand" style="position: absolute; top: 450px; left: 15%">
    <img src="images/cardbacks.png" alt="Card 1">
    <img src="images/cardbacks.png" alt="Card 2">
</div>

<p style="position: absolute; top: 165px; left: 70%">Player Name: Current Bet: Currency:</p>
<div class="hand" style="position: absolute; top: 200px; left: 75%">
    <img src="images/cardbacks.png" alt="Card 1">
    <img src="images/cardbacks.png" alt="Card 2">
</div>

<p style="position: absolute; top: 415px; left: 70%">Player Name: Current Bet: Currency:</p>
<div class="hand" style="position: absolute; top: 450px; left: 75%">
    <img src="images/cardbacks.png" alt="Card 1">
    <img src="images/cardbacks.png" alt="Card 2">
</div>

<div class="river-text-container">
    <h2>Current Pot:</h2>
    <h2>Current Bet:</h2>
</div>

<div class="river-container">
    <img src="images/cardbacks.png" alt="Card 1">
    <img src="images/cardbacks.png" alt="Card 2">
    <img src="images/cardbacks.png" alt="Card 3">
    <img src="images/cardbacks.png" alt="Card 4">
    <img src="images/cardbacks.png" alt="Card 5">
</div>

<%--This is my hand the rest should be card backs--%>
<div class="centered">
    <p style="position: absolute; top: 480px;">Player Name: Current Bet: Currency:</p>
    <div class="hand" style="position: absolute; top: 515px;">
        <img src="images/Playing Cards/PNG-cards-1.3/2_of_clubs.png" alt="Card 1">
        <img src="images/Playing Cards/PNG-cards-1.3/3_of_clubs.png" alt="Card 2">
    </div>
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
