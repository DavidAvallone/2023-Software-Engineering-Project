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
<%@ page import="model.entity.User" %>
<%@ page import="controller.servlet.m_gameServlet" %>
<%@ page import="controller.service.TableManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--    String table_id = request.getParameter("n");--%>

<%--    session = request.getSession();--%>
<%--    User u = (User) session.getAttribute("User");--%>
<%--    RoundService rs = (RoundService) session.getAttribute("roundService");--%>

<%--    if(rs == null) {--%>
<%--        rs = new RoundService(u, false);--%>
<%--        session.setAttribute("roundService", rs);--%>
<%--        rs.create_multiplayer_game();--%>
<%--        rs.start_multiplayer_game();--%>
<%--    }--%>
<%--%>--%>
<%
    String table_id = request.getParameter("n");

    TableManager tableManager = TableManager.getInstance();

    if (tableManager.getTable(table_id) == null) {
        tableManager.createTable(table_id);
    }

    RoundService rs = tableManager.getTable(table_id);


    if (session.getAttribute("playerCreationDone") == null) {
        User u = (User) session.getAttribute("User");
        Player player = new Player(u.getID(), u.getUsername(), u.getBalance());

        if (!rs.is_player_in(player))
            rs.addPlayer(player);

        int player_turn = rs.player_turn(player);

        session.setAttribute("my_player", player);
        session.setAttribute("playerCreationDone", true);
        session.setAttribute("player_turn", player_turn);
        session.setAttribute("table_id", table_id);
    }


    if (rs.round.getPlayers().size() >= 2 && !rs.game_started) {
        rs.start_multiplayer_game();
    }
    int my_int = (int) session.getAttribute("player_turn");

    int next1 = my_int + 1;
    if( next1 == 6)
        next1 = 0;

    int next2 = next1 + 1;
    if( next2 == 6)
        next2 = 0;

    int next3 = next2 + 1;
    if( next3 == 6)
        next3 = 0;

    int next4 = next3 + 1;
    if( next4 == 6)
        next4 = 0;

    int next5 = next4 + 1;
    if( next5 == 6)
        next5 = 0;

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
<h2>Current Turn: <%= current_player%> Current Round: <%=rs.round.getRound_num()%> Game Seed: <%=rs.round.getSeed()%></h2>

<div class="poker-buttons">
    <button style="background-color: #11e5d7;color: #fff;left: 5%;position: absolute" onclick="openPopup('imagePopup')">Open Cheat Sheet</button>
</div>

<div class="popup1" id="imagePopup">
    <span class="close-btn1" onclick="closePopup('imagePopup')">&times;</span>
    <img src="images/cheat_sheet.png" alt="Popup 1 Image">
</div>

<script>
    function openPopup(popupId) {
        document.getElementById(popupId).classList.add('active');
    }

    function closePopup(popupId) {
        document.getElementById(popupId).classList.remove('active');
    }
</script>

<div class="centered">
    <%
        //Player 4
        String p4_string = "None"; // Default value
        String p4_card1 = "images/cardbacks.png";
        String p4_card2 = "images/cardbacks.png";
        try {
            Player p3 = rs.round.getPlayers().get(next3);
            p4_string = p3.getName() + " $" + p3.getCurrentBet() + " $" + p3.getCurrency();
        }
        catch (Exception ex){
        }
        if(rs.round.getGameOver()) {
            try {

                List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(next3).getHand().toString());
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
    <%
        boolean small4 = false;
        boolean big4 = false;
        try {
            Player p1 = rs.round.getPlayers().get(next3);
            small4 = p1.isSmallBlind();
            big4 = p1.isBigBlind();

            if (small4) { %>
    <img src="images/small_blind.png" alt="Small Blind" height="30" width="30" style="position: absolute; top: 50px;left: 55%">
    <% }
        if (big4) { %>
    <img src="images/big_blind.png" alt="Big Blind" height="35" width="35" style="position: absolute; top: 50px;left: 55%">
    <% }
    } catch (Exception ex) {
        ex.printStackTrace();
    } %>
</div>

<%
    //Player 2
    String p5_string = "None"; // Default value
    String p5_card1 = "images/cardbacks.png";
    String p5_card2 = "images/cardbacks.png";

    try {
        Player p3 = rs.round.getPlayers().get(next2);
        p5_string = p3.getName() + " $" + p3.getCurrentBet() + " $" + p3.getCurrency();
    }
    catch (Exception ex){
    }
    if(rs.round.getGameOver()) {
        try {

            List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(next2).getHand().toString());
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
    boolean small5 = false;
    boolean big5 = false;
    try {
        Player p1 = rs.round.getPlayers().get(next2);
        small5 = p1.isSmallBlind();
        big5 = p1.isBigBlind();

        if (small5) { %>
<img src="images/small_blind.png" alt="Small Blind" height="30" width="30" style="position: absolute; top: 200px;left: 25%">
<% }
    if (big5) { %>
<img src="images/big_blind.png" alt="Big Blind" height="35" width="35" style="position: absolute; top: 200px;left: 25%">
<% }
} catch (Exception ex) {
    ex.printStackTrace();
} %>

<%
    //Player 2
    String p6_string = "None"; // Default value
    String p6_card1 = "images/cardbacks.png";
    String p6_card2 = "images/cardbacks.png";

    try {
        Player p3 = rs.round.getPlayers().get(next1);
        p6_string = p3.getName() + " $" + p3.getCurrentBet() + " $" + p3.getCurrency();
    }
    catch (Exception ex){
    }
    if(rs.round.getGameOver()) {
        try {

            List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(next1).getHand().toString());
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
    boolean small6 = false;
    boolean big6 = false;
    try {
        Player p1 = rs.round.getPlayers().get(next1);
        small6 = p1.isSmallBlind();
        big6 = p1.isBigBlind();

        if (small6) { %>
<img src="images/small_blind.png" alt="Small Blind" height="30" width="30" style="position: absolute; top: 450px;left: 25%">
<% }
    if (big6) { %>
<img src="images/big_blind.png" alt="Big Blind" height="35" width="35" style="position: absolute; top: 450px;left: 25%">
<% }
} catch (Exception ex) {
    ex.printStackTrace();
} %>

<%
    //Player 5
    String p3_string = "None"; // Default value
    String p3_card1 = "images/cardbacks.png";
    String p3_card2 = "images/cardbacks.png";
    try {
        Player p3 = rs.round.getPlayers().get(next4);
        p3_string = p3.getName() + " $" + p3.getCurrentBet() + " $" + p3.getCurrency();
    }
    catch (Exception ex){
    }
    if(rs.round.getGameOver()) {
        try {

            List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(next4).getHand().toString());
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
    boolean small2 = false;
    boolean big2 = false;
    try {
        Player p1 = rs.round.getPlayers().get(next4);
        small2 = p1.isSmallBlind();
        big2 = p1.isBigBlind();

        if (small2) { %>
<img src="images/small_blind.png" alt="Small Blind" height="30" width="30" style="position: absolute; top: 200px;left: 85%">
<% }
    if (big2) { %>
<img src="images/big_blind.png" alt="Big Blind" height="35" width="35" style="position: absolute; top: 200px;left: 85%">
<% }
} catch (Exception ex) {
    ex.printStackTrace();
} %>

<%
    //Player 6
    String p2_string = "None"; // Default value
    String p2_card1 = "images/cardbacks.png";
    String p2_card2 = "images/cardbacks.png";
    try {
        Player p2 = rs.round.getPlayers().get(next5);
        p2_string = p2.getName() + " $" + p2.getCurrentBet() + " $" + p2.getCurrency();
    }
    catch (Exception ex){
    }
    if(rs.round.getGameOver()) {
        try {

            List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(next5).getHand().toString());
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
<%
    boolean small1 = false;
    boolean big1 = false;
    try {
        Player p1 = rs.round.getPlayers().get(next5);
        small1 = p1.isSmallBlind();
        big1 = p1.isBigBlind();

        if (small1) { %>
<img src="images/small_blind.png" alt="Small Blind" height="30" width="30" style="position: absolute; top: 450px;left: 85%">
<% }
    if (big1) { %>
<img src="images/big_blind.png" alt="Big Blind" height="35" width="35" style="position: absolute; top: 450px;left: 85%">
<% }
} catch (Exception ex) {
    ex.printStackTrace();
} %>

<div class="river-text-container">
    <h2>Current Pot: $<%= rs.round.getCurrent_pot() %></h2>
    <h2>Current Bet: $<%= rs.round.getCurrent_bet() %></h2>
</div>
<%
    List<String> river_pngs;
    String river1 = "images/cardbacks.png";
    String river2 = "images/cardbacks.png";
    String river3 = "images/cardbacks.png";
    String river4 = "images/cardbacks.png";
    String river5 = "images/cardbacks.png";
    List<Card> river = rs.round.getRiver();

    try {
        if (river.size() == 3) {
            river_pngs = rs.extractCardNames(rs.round.getRiver().toString());
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
        Player p1 = rs.round.getPlayers().get(my_int);
        p1_string = p1.getName() + " $" + p1.getCurrentBet() + " $" + p1.getCurrency();
        List<String> cards = rs.extractCardNames(rs.round.getPlayers().get(my_int).getHand().toString());
        card1 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(0);
        card2 = "images/Playing Cards/PNG-cards-1.3/" + cards.get(1);
    }
    catch (Exception ex){
    }
%>
<%--This is my hand the rest should be card backs 480 515 or 560 600--%>
<div class="centered">
    <p style="position: absolute; top: 480px;"><%=p1_string%></p>
    <div class="hand" style="position: absolute; top: 515px;">
        <img src="<%= card1 %>" alt="Card 1">
        <img src="<%= card2 %>" alt="Card 2">
    </div>
    <%
        boolean small0 = false;
        boolean big0 = false;
        try {
        Player p1 = rs.round.getPlayers().get(my_int);
        small0 = p1.isSmallBlind();
        big0 = p1.isBigBlind();

        if (small0) { %>
    <img src="images/small_blind.png" alt="Small Blind" height="30" width="30" style="position: absolute; top: 515px;left: 55%">
    <% }
        if (big0) { %>
    <img src="images/big_blind.png" alt="Big Blind" height="35" width="35" style="position: absolute; top: 515px;left: 55%">
    <% }
    } catch (Exception ex) {
        ex.printStackTrace();
    } %>
</div>

<form action="GameServlet" method="post">
    <div class="poker-buttons" style="position: absolute; top: 580px; left: 83%;">
        <button type="submit" name="exit" value="leave">Leave Table</button>
    </div>
</form>

<%
    String playerStatus = "";
    try {
        Player p1 = rs.round.getPlayers().get(my_int);
        playerStatus = p1.getStatus(); // Replace this with your actual logic to get the player's status
    }
    catch (Exception ex){
    }
    int current_turn = rs.round.getCurrent_player_turn();
    if (my_int == current_turn){
        if (!("fold".equals(playerStatus) || "all in".equals(playerStatus))) {
%>
<form action="m_GameServlet" method="post">
    <div class="button-container poker-buttons">
        <button type="submit" name="action" value="raise">Raise</button>
        <input type="number" step="25.0" id="raiseAmount" name="raiseAmount" placeholder="Enter Raise" min="0.0" aria-placeholder="0.0" value="0.0">
        <button type="submit" name="action" value="call">Call</button>
        <button type="submit" name="action" value="check">Check</button>
        <button type="submit" name="action" value="fold">Fold</button>
        <button type="submit" name="action" value="all in">All In</button>
    </div>
</form>
<%
        }
    }
%>

<% if (rs.round.getGameOver()) {
    User user = (User) session.getAttribute("User");
    Player current = new Player(user.getID(), user.getUsername(), user.getBalance(), 0);
    if (rs.round.who_won().getId() == current.getId())
        rs.update_player_outcome(user, true);
    else
        rs.update_player_outcome(user, false);
%>
    <script>
    function dragElement(elmnt) {
        var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
        if (document.getElementById(elmnt.id + "-header")) {
            // if present, the header is where you move the DIV from
            document.getElementById(elmnt.id + "-header").onmousedown = dragMouseDown;
        } else {
            // otherwise, move the DIV from anywhere inside the DIV
            elmnt.onmousedown = dragMouseDown;
        }

        function dragMouseDown(e) {
            e = e || window.event;
            e.preventDefault();
            // get the mouse cursor position at startup:
            pos3 = e.clientX;
            pos4 = e.clientY;
            document.onmouseup = closeDragElement;
            // call a function whenever the cursor moves:
            document.onmousemove = elementDrag;
        }

        function elementDrag(e) {
            e = e || window.event;
            e.preventDefault();
            // calculate the new cursor position:
            pos1 = pos3 - e.clientX;
            pos2 = pos4 - e.clientY;
            pos3 = e.clientX;
            pos4 = e.clientY;
            // set the element's new position:
            elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
            elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
        }

        function closeDragElement() {
            // stop moving when mouse button is released:
            document.onmouseup = null;
            document.onmousemove = null;
        }
    }


    // JavaScript function to open the popup
    function openPopup() {
        document.getElementById('overlay').style.display = 'flex';
    }

    // JavaScript function to close the popup
    function closePopup() {
        document.getElementById('overlay').style.display = 'none';
        location.reload(true); // This will force a page reload

    }

    // Call the dragElement function when the page loads
    window.onload = function () {
        var popup = document.getElementById('overlay');
        dragElement(popup);
        openPopup(); // Call openPopup function when the page loads (you can modify this based on your requirement)
    };

    // Call openPopup function when the page loads (you can modify this based on your requirement)
    // window.onload = openPopup;
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
