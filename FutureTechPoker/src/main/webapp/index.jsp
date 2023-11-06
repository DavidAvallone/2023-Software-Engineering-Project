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
  <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="banner">
  <h1><%= "FutureTech Poker" %></h1>
</div>
<br/>
<div class="centered">
  <a href="home.jsp" class="home-button">Home Page</a>
  <a href="gametestpage.jsp" class="home-button">Game Test Page</a>


  <div class="image-container">
    <img src="poker-chips.png" alt="Poker Chips" class="poker-chips">
    <a href="register.jsp" class="home-button">Register Page</a>
  </div>

  <div class="image-container">
    <a href="login.jsp" class="home-button">Login Page</a>
    <img src="cards.png" alt="Cards" class="cards-image">
  </div>

</div>
<img src="logo.jpeg" alt="FutureTech Logo" width="574" height="223" style="position: absolute; bottom: 0px; left: 50%; transform: translateX(-50%)">
</body>
</html>