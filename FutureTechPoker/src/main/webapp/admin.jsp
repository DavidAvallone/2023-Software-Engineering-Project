<%@ page import="model.entity.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User logged = (User) session.getAttribute("User");
%>

<html>
<body>
<div class="container mt-2">
    <% if(logged!=null && logged.getPermission()==User.ADMIN_PERMISSION){ %>
    <div class="banner">
        <h1><%= "FutureTech Poker" %></h1>
    </div>
    <div class="col-12 col-md-6">
        <label class="col-form-label col-md-3 col-lg-1" for="ban_email"> Ban Player: </label>
        <input class="form-control" type="email" name="ban_email" id="ban_email" required/>
        <button class="btn btn-success me-2"> Ban </button>
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