<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Player" %>
<%@ page import="controller.service.PlayerEntityService" %><%--
  Created by IntelliJ IDEA.
  User: David
  Date: 11/12/2023
  Time: 1:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    User logged = (User) session.getAttribute("User");
    Player player = new Player(logged);
    PlayerEntityService.createPlayer(logged);
%>

<p><%= logged.getEmail()%> <%= player.getPlayerName()%></p>

<%--<form action="CreateEntitiesServlet" method="post">--%>
<%--    <button type="submit">Create Player Entity</button>--%>
<%--</form>--%>
<%--<%--%>
<%--    player = (PlayerEntity) session.getAttribute("Player");--%>
<%--    if(player == null){--%>
<%--        player = new PlayerEntity();--%>
<%--        player.setName("did not work");--%>
<%--    }--%>
<%--%>--%>

<%--<p><%= player.getPlayerName()%>--%>
<%--</p>--%>

</body>
</html>
