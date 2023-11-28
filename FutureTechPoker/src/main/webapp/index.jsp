<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.entity.User" %>
<%
  User logged = (User) session.getAttribute("User");
  if(logged!=null){
    response.sendRedirect("home.jsp");
  }
%>


<!DOCTYPE html>
<html>
<head>
  <title>FutureTech Poker</title>
  <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>
  <div class="banner">
    <h1><%= "FutureTech Poker" %></h1>
  </div>
  <div class="animated-background"></div>

  <br/>
  <img src="poker-chips.png" alt="Poker Chips" width="50" height="50" style="position: absolute; left: 44%;top: 220px">
  <img src="poker-chips.png" alt="Poker Chips" width="50" height="50" style="position: absolute; left: 53%;top: 220px">

  <img src="cards.png" alt="Cards" width="50" height="50" style="position: absolute; left: 43%;top: 140px">
  <img src="cards.png" alt="Cards" width="50" height="50" style="position: absolute; left: 54%;top: 140px">

  <div class="centered">
    <div class="table-buttons">
        <button style="background-color: #498ed5; color: #fff;" onclick="window.location.href='register.jsp'">Register</button>
    </div>
    <br>
    <br>
    <div class="table-buttons">
      <button style="background-color: #498ed5; color: #fff;" onclick="window.location.href='login.jsp'">Login</button>
    </div>
    <br>
    <br>
    <img src="images/poker_chip.png" alt="poker_chip" width="200" height="200">
    <h2>Welcome to FutureTech Poker!</h2>

  </div>

</body>
</html>