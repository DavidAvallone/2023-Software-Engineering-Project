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
int otherUser = (int)request.getAttribute("otherUser");
%>

<html>
<head>
    <title></title>
</head>
<body>
    <h1>Messages with </h1>
<%
    List<Message> messages = (List<Message>) request.getAttribute("messages");
%>
    <%
        if(!messages.isEmpty() && messages != null){
            for(Message message: messages){

        %>
            <p><%=message.getSender()%>: <%= message.getMessage()%></p>
    <%
        }
        }
    %>
    <form action="AddMessageServlet" method="post">
        <input type="text" name="newMessage" id="newMessage" placeholder="New Message" required>
        <button type="submit" style="text-align: center"> Send Message</button>
    </form>
</body>
</html>
