<%@ page import="controller.servlet.GameServlet" %>
<%@ page import="controller.service.RoundService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 11/5/2023
  Time: 7:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session = request.getSession();
    RoundService rs = new RoundService();
    rs.game_create_test();
    session.setAttribute("roundService", rs);
    rs = (RoundService) session.getAttribute("roundService");

%>

<html>
<head>
    <title>Test Page</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <div class="banner">
        <h1>FutureTech Poker</h1>
    </div>
</head>
<body>
<h2>Player Info: </h2>
<p>Player: <%= rs.round.getPlayers().get(0).getName() %></p>
<p>Player Bet: $<%= rs.round.getPlayers().get(0).getCurrentBet()%></p>
<p>Current Balance : $<%= rs.round.getPlayers().get(0).getCurrency() %></p>
<%
    List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(0).getHand().toString());
    String card1 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(0);
    String card2 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(1);
%>

<p>Current Hand: </p>
<div class="hand">
    <img src="<%= card1 %>" width="60" height="90">
    <img src="<%= card2 %>" width="60" height="90">
</div>

<h2>Game Info: </h2>
<p>Current Bet: $<%= rs.round.getCurrent_bet() %></p>
<p>Current Pot: $<%= rs.round.getCurrent_pot() %></p>

<%--<%--%>
<%--    List<String> rivers = rs.extractCardNames(rs.round.getRiver().toString());--%>
<%--    String river1 = "images/Playing Cards/PNG-cards-1.3/" + rivers.get(0);--%>
<%--    String river2 = "images/Playing Cards/PNG-cards-1.3/" + rivers.get(1);--%>
<%--    String river3 = "images/Playing Cards/PNG-cards-1.3/" + rivers.get(2);--%>
<%--%>--%>
<%--<p>River: </p>--%>
<%--<div class="hand">--%>
<%--    <img src="<%= river1 %>" width="60" height="90">--%>
<%--    <img src="<%= river2 %>" width="60" height="90">--%>
<%--    <img src="<%= river3 %>" width="60" height="90">--%>
<%--</div>--%>
<%--<br>--%>

<!-- Add buttons and forms for player actions -->
<form action="GameServlet" method="post" >
    <!-- Include buttons and form fields for raising, calling, checking, folding, all-in -->
    <!-- Example: -->
    <label for="raiseAmount">Enter Raise Amount:</label>
    <input type="number" step="25.0" id="raiseAmount" name="raiseAmount" placeholder="Enter amount" min="0.0" aria-placeholder="0.0" value="0.0">
    <button type="submit" name="action" value="raise">Raise</button>
    <button type="submit" name="action" value="call">Call</button>
    <button type="submit" name="action" value="check">Check</button>
    <button type="submit" name="action" value="fold">Fold</button>
    <button type="submit" name="action" value="all in">All In</button>
    <!-- Add input fields for raising amount, if applicable -->
</form>
</body>
</html>
