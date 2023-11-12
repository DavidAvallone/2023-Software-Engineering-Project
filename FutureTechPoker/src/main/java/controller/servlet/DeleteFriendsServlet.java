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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String deleteFriendName = request.getParameter("deleteFriend");
        User owner = (User) session.getAttribute("User");
        List<Friends> deleteFriendsList = FriendsService.getFriendsList(owner).stream().filter(friends -> friends.getFriend().getName().equals(deleteFriendName)).collect(Collectors.toList());
        if(!deleteFriendsList.isEmpty()){
            FriendsService.deleteFriends(deleteFriendsList.get(0).getID());
            response.sendRedirect("user_friends.jsp");
        }
        else{
            response.sendRedirect("user_friends.jsp?delmsg=1");
        }

    }
}