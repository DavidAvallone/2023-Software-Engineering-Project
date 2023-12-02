package controller.servlet;
import controller.service.FriendsService;
import controller.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Friends;
import model.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "DeleteFriendsServlet", value = "/DeleteFriendsServlet")

public class DeleteFriendsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int deleteFriend = Integer.parseInt(request.getParameter("deleteFriend"));
        User owner = (User) session.getAttribute("User");
        User deleteUser = UserService.findUserById(deleteFriend);

        if (deleteUser != null && FriendsService.hasFriend(owner, deleteUser)) {
            Friends deleteFriends = FriendsService.getFriends(owner.getID(), deleteUser.getID());
            FriendsService.deleteFriends(deleteFriends.getID());
            response.sendRedirect("FriendsListServlet");
        } else {
            response.sendRedirect("user_friends.jsp?delmsg=1");
        }
    }
}