package controller.servlet;

import controller.service.FriendsService;
import controller.service.MessageService;
import controller.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Friends;
import model.entity.Message;
import model.entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddMessageServlet", value = "/AddMessageServlet")
public class AddMessageServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User owner = (User) session.getAttribute("User");
        int otherUserId = Integer.parseInt(request.getParameter("receiverId"));
        String newMessage = request.getParameter("newMessage");
        Message message = new Message(owner.getID(), otherUserId, newMessage);
        MessageService.addMessage(message);

        request.getRequestDispatcher("MessageListServlet?otherUser=" + otherUserId).forward(request, response);
    }
}

