<%--
  Created by IntelliJ IDEA.
  User: neilkeohane
  Date: 11/4/23
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User logged = (User) session.getAttribute("User");
%>

<html>
<link rel="stylesheet" type="text/css" href="styles.css">
<body>
<div class="banner">
    <h1><%= "FutureTech Poker" %></h1>
</div>
<% if(logged != null && logged.getPermission() != User.GUEST_PERMISSION){ %>
<title><%= ((User) session.getAttribute("User")).getEmail()%>'s Profile</title>
<div id="userInformation">
    <h2><%= ((User) session.getAttribute("User")).getEmail()%>'s Profile Settings & Statistics</h2>
    <p><%= ((User) session.getAttribute("User")).getWins()%>W-<%= ((User) session.getAttribute("User")).getLosses()%>L</p>
    <p>Balance: <%= ((User) session.getAttribute("User")).getBalance()%></p>
    <form action="resetPasswordServlet" method="post">
        <div class="row mb-2">
            <label class="col-form-label col-md-3 col-lg-1" for="txt_pass"> Password: </label>
            <div class="col-12 col-md-6">
                <input class="form-control" type="password" name="txt_pass" id="txt_pass" autocomplete="off"
                       minlength="6" required/>
            </div>
        </div>
        <div class="row mb-2">
            <label class="col-form-label col-md-3 col-lg-1" for="txt_conf"> Confirm Password: </label>
            <div class="col-12 col-md-6">
                <input class="form-control" type="password" name="txt_conf" id="txt_conf" autocomplete="off"
                       minlength="6" required/>
            </div>
        </div>
        <button type="submit" onclick="return checkRegisterForm()">Reset Password</button>

    </form>

    <form action="deleteUserServlet" method="post">
        <button type="submit">Delete Account</button>
    </form>
    <a href="home.jsp" class="home-button">Home Page</a>
</div>
<%} else if(logged != null && logged.getPermission() == User.GUEST_PERMISSION) {%>
    <div class="banner">
        <h1><%= "FutureTech Poker" %></h1>
    </div>
    <h2>Guests do not have profile information or settings.</h2>
<%} else {%>
<%
    response.sendRedirect("index.jsp");
%>
<%}%>

<script type="text/javascript">
    function checkRegisterForm(){
        let pass = document.getElementById("txt_pass").value;
        let conf = document.getElementById("txt_conf").value;
        if(pass === conf){
            //Typed text in Password and Confirm Password are the same
            return true;
        } else {
            //Typed text in Password and Confirm Password do not match.
            window.alert("Passwords do not match.");
            return false;
        }
    }
</script>

</body>
</html>
