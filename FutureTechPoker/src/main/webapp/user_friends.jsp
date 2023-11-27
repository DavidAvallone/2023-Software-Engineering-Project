<%--
  Created by IntelliJ IDEA.
  User: alexkristian
  Date: 11/11/23
  Time: 8:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Friends" %>
<%@ page import="controller.service.FriendsService" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
 String code = request.getParameter("msg");
  String message = "";

  if(code != null) {
    switch (code) {
      case "1":
        message = "User is already your friend.";
        break;
      case "2":
        message = "Unable to find user.";
        break;
    }
  }
  String deleteCode = request.getParameter("delmsg");
  String deleteMessage = "";

  if(deleteCode != null) {
    switch (deleteCode) {
      case "1":
        deleteMessage = "User is not in your friends list.";
        break;
    }
  }
  User logged = (User) session.getAttribute("User");
%>
<html>
<head>
  <title>FutureTech Poker User Friends</title>
  <link rel="stylesheet" type="text/css" href="home.css">
</head>

<body>
    <div class="banner">
      <h1>FutureTech Poker</h1>
    </div>
    <div class="animated-background"></div>

    <div class="centered">
        <h2><%=logged.getUsername()%>'s Friends</h2>

        <%--<%--%>
        <%--  List<Friends> friendList = FriendsService.getFriendsList(logged);--%>
        <%--  for (Friends friend : friendList) {--%>
        <%--%>--%>
        <%--<p><%= friend.getFriend().getName() %></p>--%>
        <%--<%--%>
        <%--  }--%>
        <%--%>--%>
        <div>
            <h2>Add a Friend:</h2>
            <form action="AddFriendsServlet" method="post">
                <input type="text" name="newFriend" id="newFriend" placeholder="Enter friend's name" required>
                <button type="submit" style="text-align: center"> Add Friend</button>
            </form>
            <p><%=message%></p>
        </div>

        <h2>Delete a Friend:</h2>
        <form action="DeleteFriendsServlet" method="post">
            <input type="text" name="deleteFriend" id="deleteFriend" placeholder="Enter friend's name" required>
            <button type="submit"> Delete Friend</button>
        </form>

        <p><%=deleteMessage%></p>

        <div class="table-buttons">
            <button onclick="window.location.href='home.jsp'">Home</button>
        </div>
    </div>
</body>
</html>
