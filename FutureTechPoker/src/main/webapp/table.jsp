<%--
  Created by IntelliJ IDEA.
  User: bhblades
  Date: 10/29/2023
  Time: 2:41 PM
--%>
<%@ page import="controller.servlet.GameServlet" %>
<%@ page import="controller.service.RoundService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page import="Poker.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session = request.getSession();
    RoundService rs = (RoundService) session.getAttribute("roundService");

    if(rs == null) {
        rs = new RoundService();
        session.setAttribute("roundService", rs);
        rs.game_create_test();
    }
%>
<html>
<head>
    <title>FutureTech Poker Table</title>
    <link rel="stylesheet" type="text/css" href="poker.css">
</head>

<body background="images/table_background.png" style="background-size: cover; background-position: center; background-repeat: no-repeat; margin: 0; padding: 0;">
<%
    int curr = rs.round.getCurrent_player();
    String current_player = rs.round.getPlayers().get(curr).getName();
%>
<h2>Current Turn: <%= current_player%> </h2>
<div class="centered">
    <%
        //Player 4
        String p4_string = "None"; // Default value
        String p4_card1 = "images/cardbacks.png";
        String p4_card2 = "images/cardbacks.png";
        try {
            Player p3 = rs.round.getPlayers().get(3);
            p4_string = p3.getName() + " $" + p3.getCurrentBet() + " $" + p3.getCurrency();
        }
        catch (Exception ex){
        }
        if(rs.round.getGameOver()) {
            try {

                List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(3).getHand().toString());
                p4_card1 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(0);
                p4_card2 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(1);
            } catch (Exception ex) {
            }
        }
    %>
    <p style="position: absolute; top: 15px;"><%=p4_string%></p>
    <div class="hand" style="position: absolute; top: 50px;">
        <img src="<%=p4_card1%>" alt="Card 1">
        <img src="<%=p4_card2%>" alt="Card 2">
    </div>
</div>

<%
    //Player 5
    String p5_string = "None"; // Default value
    String p5_card1 = "images/cardbacks.png";
    String p5_card2 = "images/cardbacks.png";
    try {
        Player p3 = rs.round.getPlayers().get(4);
        p5_string = p3.getName() + " $" + p3.getCurrentBet() + " $" + p3.getCurrency();
    }
    catch (Exception ex){
    }
    if(rs.round.getGameOver()) {
        try {

            List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(4).getHand().toString());
            p5_card1 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(0);
            p5_card2 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(1);
        } catch (Exception ex) {
        }
    }
%>
<p style="position: absolute; top: 165px; left: 10%"><%=p5_string%></p>
<div class="hand" style="position: absolute; top: 200px; left: 15%">
    <img src="<%=p5_card1%>" alt="Card 1">
    <img src="<%=p5_card2%>" alt="Card 2">
</div>
<%
    //Player 6
    String p6_string = "None"; // Default value
    String p6_card1 = "images/cardbacks.png";
    String p6_card2 = "images/cardbacks.png";
    try {
        Player p3 = rs.round.getPlayers().get(5);
        p6_string = p3.getName() + " $" + p3.getCurrentBet() + " $" + p3.getCurrency();
    }
    catch (Exception ex){
    }
    if(rs.round.getGameOver()) {
        try {

            List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(5).getHand().toString());
            p6_card1 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(0);
            p6_card2 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(1);
        } catch (Exception ex) {
        }
    }
%>
<p style="position: absolute; top: 415px; left: 10%"><%=p6_string%></p>
<div class="hand" style="position: absolute; top: 450px; left: 15%">
    <img src="<%=p6_card1%>" alt="Card 1">
    <img src="<%=p6_card2%>" alt="Card 2">
</div>

<%
    //Player 3
    String p3_string = "None"; // Default value
    String p3_card1 = "images/cardbacks.png";
    String p3_card2 = "images/cardbacks.png";
    try {
        Player p3 = rs.round.getPlayers().get(2);
        p3_string = p3.getName() + " $" + p3.getCurrentBet() + " $" + p3.getCurrency();
    }
    catch (Exception ex){
    }
    if(rs.round.getGameOver()) {
        try {

            List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(2).getHand().toString());
            p3_card1 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(0);
            p3_card2 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(1);
        } catch (Exception ex) {
        }
    }
%>
<p style="position: absolute; top: 165px; left: 70%"><%=p3_string%></p>
<div class="hand" style="position: absolute; top: 200px; left: 75%">
    <img src="<%=p3_card1%>" alt="Card 1">
    <img src="<%=p3_card2%>" alt="Card 2">
</div>

<%
    //Player 2
    String p2_string = "None"; // Default value
    String p2_card1 = "images/cardbacks.png";
    String p2_card2 = "images/cardbacks.png";
    try {
        Player p2 = rs.round.getPlayers().get(1);
        p2_string = p2.getName() + " $" + p2.getCurrentBet() + " $" + p2.getCurrency();
    }
    catch (Exception ex){
    }
    if(rs.round.getGameOver()) {
        try {

            List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(1).getHand().toString());
            p2_card1 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(0);
            p2_card2 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(1);
        } catch (Exception ex) {
        }
    }
%>
<p style="position: absolute; top: 415px; left: 70%"><%=p2_string%></p>
<div class="hand" style="position: absolute; top: 450px; left: 75%">
    <img src="<%=p2_card1%>" alt="Card 1">
    <img src="<%=p2_card2%>" alt="Card 2">
</div>

<div class="river-text-container">
    <h2>Current Pot: $<%= rs.round.getCurrent_bet() %></h2>
    <h2>Current Bet: $<%= rs.round.getCurrent_pot() %></h2>
</div>
<%
    List<String> river_pngs;
    String river1 = "images/cardbacks.png";
    String river2 = "images/cardbacks.png";
    String river3 = "images/cardbacks.png";
    String river4 = "images/cardbacks.png";
    String river5 = "images/cardbacks.png";
    List<Card> river = rs.round.getRiver();
    int size = 0;
    try {
        if (river.size() == 3) {
            river_pngs = rs.extractCardNames(rs.round.getRiver().toString());
            size = river_pngs.size();
            river1 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(0);
            river2 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(1);
            river3 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(2);
            river4 = "images/cardbacks.png";
            river5 = "images/cardbacks.png";
        } else if (river.size() == 4) {
            river_pngs = rs.extractCardNames(rs.round.river_string());
            river1 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(0);
            river2 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(1);
            river3 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(2);
            river4 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(3);
            river5 = "images/cardbacks.png";
        } else if (river.size() == 5) {
            river_pngs = rs.extractCardNames(rs.round.river_string());
            river1 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(0);
            river2 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(1);
            river3 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(2);
            river4 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(3);
            river5 = "images/Playing Cards/PNG-cards-1.3/" + river_pngs.get(4);
        }
    }
    catch (Exception ex){

    }
%>

<div class="river-container">
    <img src="<%= river1 %>" alt="Card 1">
    <img src="<%= river2 %>" alt="Card 2">
    <img src="<%= river3 %>" alt="Card 3">
    <img src="<%= river4 %>" alt="Card 4">
    <img src="<%= river5 %>" alt="Card 5">
</div>

<%
    //Player 1
    String p1_string = "None"; // Default value
    String card1 = "images/cardbacks.png";
    String card2 = "images/cardbacks.png";
    try {
        Player p3 = rs.round.getPlayers().get(0);
        p1_string = p3.getName() + " $" + p3.getCurrentBet() + " $" + p3.getCurrency();
        List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(0).getHand().toString());
        card1 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(0);
        card2 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(1);
    }
    catch (Exception ex){
    }
%>
<%--This is my hand the rest should be card backs--%>
<div class="centered">
    <p style="position: absolute; top: 480px;"><%=p1_string%></p>
    <div class="hand" style="position: absolute; top: 515px;">
        <img src="<%= card1 %>" alt="Card 1">
        <img src="<%= card2 %>" alt="Card 2">
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

<% if (rs.round.getGameOver()) { %>
    <script>
    // JavaScript function to open the popup
    function openPopup() {
        document.getElementById('overlay').style.display = 'flex';
    }

    // JavaScript function to close the popup
    function closePopup() {
        document.getElementById('overlay').style.display = 'none';
        location.reload(true); // This will force a page reload

    }

    // Call openPopup function when the page loads (you can modify this based on your requirement)
    window.onload = openPopup;
    </script>

    <!-- The overlay and pop-up -->
    <div class="overlay" id="overlay">
        <div class="popup">
            <span class="close-btn" onclick="closePopup()">&times;</span>
            <h2>Game Over!</h2>
            <br>
            <p>Winner: <%=rs.round.getWinning_player().getName()%></p>
            <br>
            <p>Winnings: $<%=rs.round.getCurrent_pot()%></p>
        </div>
    </div>
<% } %>

<%
    if(rs.round.getGameOver()){
        rs.new_game();
    }
%>
</body>
</html>
