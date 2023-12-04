package controller.servlet;

import controller.service.FriendsService;
import controller.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Friends;
import model.entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "FriendsListServlet", value = "/FriendsListServlet")
public class FriendsListServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User owner = (User) session.getAttribute("User");
        List<User> userList = new ArrayList<>();
        if (owner != null) {
            List<Friends> friendsList = FriendsService.getFriendsList(owner);
            for (int i = 0; i < friendsList.size(); i++) {
                userList.add(UserService.findUserById(friendsList.get(i).getFriend()));
            }
        }
        String msg = request.getParameter("msg");
        request.setAttribute("friends", userList);
        if (msg == null) {
            request.getRequestDispatcher("user_friends.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("user_friends.jsp?msg=" + msg).forward(request, response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
