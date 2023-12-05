<%--
  Created by IntelliJ IDEA.
  User: alexkristian
  Date: 12/3/23
  Time: 1:34 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.util.List" %>
<%@ page import="model.entity.Message" %>
<%@ page import="model.entity.User" %>
<%@ page import="controller.service.UserService" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User otherUser = (User)request.getAttribute("otherUser");
    User user = (User)session.getAttribute("User");
%>

<html>
<head>
    <title>Messages with <%= otherUser.getName()%></title>
    <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>
    <div class="banner">
        <h1>FutureTech Poker</h1>
    </div>
    <div class="animated-background"></div>
    <h2>Messages with <%=otherUser.getUsername()%></h2>
<%
    List<Message> messages = (List<Message>) request.getAttribute("messages");
%>
    <div class="scrollContainer">
    <%
        if(!messages.isEmpty() && messages != null){
            for(int i = 0; i< messages.size(); i++){
                User sender = UserService.findUserById(messages.get(i).getSender());



        %>
            <div class="messageContainer">
                <%if(sender.getID().equals(otherUser.getID())){
                    %>
                    <div class="messageFriendItem">
                        <%=messages.get(i).getMessage()%>
                    </div>
    <%
                        }else{

    %>
                <div class="messageUserItem">
                    <%=messages.get(i).getMessage()%>
                </div>
                    <%
                }
                %>
            </div>
        <%
        }
        }
    %>
    </div>

    <div class = "centered">
        <form action="AddMessageServlet" method="post">
            <input type="text" name="newMessage" id="newMessage" placeholder="New Message" required>
            <input type="hidden" name="receiverId" value="<%= otherUser.getID() %>">
            <button type="submit" style="text-align: center"> Send Message</button>
        </form>
        </div>
    <div class="table-buttons">
        <button onclick="window.location.href='FriendsListServlet'">Back</button>
    </div>
</body>
</html>
