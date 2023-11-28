<%@ page import="model.entity.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User logged = (User) session.getAttribute("User");
%>

<html>
<link rel="stylesheet" type="text/css" href="home.css">
<% if(logged!=null && logged.getPermission()==User.ADMIN_PERMISSION){ %>

<body>
    <div class="banner">
        <h1>FutureTech Poker</h1>
    </div>
    <div class="animated-background"></div>


    <h2>ADMIN PAGE</h2>
    <div class="centered">
        <div class="col-12 col-md-6">
            <label class="col-form-label col-md-3 col-lg-1" for="ban_email" style="font-family: Copperplate Gothic Light, fantasy;"> Ban Player: </label>
            <input class="form-control" type="email" name="ban_email" id="ban_email" required/>
        </div>

            <br>
            <div class="table-buttons">
                <button class="btn btn-success me-2"> Ban </button>
            </div>
            <br>
            <div class="table-buttons">
                <button onclick="window.location.href='home.jsp'">Home</button>
            </div>

        <% } else { %>
        <%
            // If access is denied, send a redirect to another page
            response.sendRedirect("home.jsp");
        %>
        <% } %>
    </div>
</body>
</html>