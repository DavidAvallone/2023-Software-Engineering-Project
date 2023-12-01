package controller.servlet;
import controller.service.FriendsService;
import controller.service.UserService;
import jakarta.servlet.RequestDispatcher;
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

@WebServlet(name = "AddFriendsServlet", value = "/AddFriendsServlet")

public class AddFriendsServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String friendName = request.getParameter("newFriend");
        User owner = (User) session.getAttribute("User");
        if(UserService.findUserByName(friendName) != null && !friendName.equals(owner.getUsername())) {
            User newFriend = UserService.findUserByName(friendName);
            List<Friends> friendList = FriendsService.getFriendsList(owner);

            if(friendList.stream().anyMatch(friends -> friends.getFriend().getUsername().equals(friendName))){
                response.sendRedirect("user_friends.jsp?msg=1");
            }
            else {
                Friends friends = new Friends(owner, newFriend);
                FriendsService.addFriend(friends);
                response.sendRedirect("user_friends.jsp");
            }
        }
        else{
            response.sendRedirect("user_friends.jsp?msg=2");
        }

    }
}
