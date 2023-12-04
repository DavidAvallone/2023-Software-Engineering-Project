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
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>


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
    <link rel="stylesheet" type="text/css" href="home.css">
<head>
  <title>FutureTech Poker User Friends</title>

</head>

<body>


    <div class="banner">
      <h1>FutureTech Poker</h1>
    </div>
    <div class="animated-background"></div>
    <h2><%=logged.getUsername()%>'s Friends</h2>


    <div class="scrollContainer">
            <%

                List<User> friends = (List<User>) request.getAttribute("friends");
                %>

            <%
                if(friends != null && !friends.isEmpty()){
                    for(User user : friends)
                    {


            %>

        <div class="friendContainer">
            <div class= "friendNameItem"> <%=user.getName()%></div>
            <input class= "friendButtonItem" type="button" value="Message" onclick="window.location.href = 'MessageListServlet?otherUser=<%=user.getID()%>'" name="message">
            <input class="friendButtonItem" type="button" value ="Delete" onclick="window.location.href = 'DeleteFriendsServlet?deleteFriend=<%=user.getID()%>'" name="delete">
        </div>


            <%
                }
                }
            %>
    </div>
    <br>
        <div class = "centered">
            <form action="AddFriendsServlet" method="post">
                <input type="text" name="newFriend" id="newFriend" placeholder="Enter friend's name" required>
                <button type="submit" style="text-align: center"> Add Friend</button>
            </form>
            <p><%=message%></p>
        </div>

        <div class="table-buttons">
            <button onclick="window.location.href='home.jsp'">Home</button>
        </div>
</body>
</html>
