<%@ page import="controller.servlet.GameServlet" %>
<%@ page import="controller.service.RoundService" %>
<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 11/5/2023
  Time: 7:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% RoundService rs = new RoundService(); %>
<% rs.game_create();%>
        <html>
<head>
    <title>Test Page</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <div class="banner">
        <h1>FutureTech Poker</h1>
    </div>
</head>
<body>

<p>Current Bet: $<%= rs.round.getCurrent_bet() %></p>
<p>Current Pot: $<%= rs.round.getCurrent_pot() %></p>
<p>Current Hand: <%= rs.round.getPlayers().get(0).getHand().toString() %></p>
<p>River: <% for(int i = 0; i < rs.round.getRiver().size(); i++){
        rs.round.getRiver().get(i);
        } %></p>

<!-- Add buttons and forms for player actions -->
<form action="GameServlet" method="post">
    <!-- Include buttons and form fields for raising, calling, checking, folding, all-in -->
    <!-- Example: -->
    <button type="submit" name="action" value="raise">Raise</button>
    <button type="submit" name="action" value="call">Call</button>
    <button type="submit" name="action" value="check">Check</button>
    <button type="submit" name="action" value="fold">Fold</button>
    <button type="submit" name="action" value="allin">All In</button>
    <!-- Add input fields for raising amount, if applicable -->
</form>

</body>
</html>
