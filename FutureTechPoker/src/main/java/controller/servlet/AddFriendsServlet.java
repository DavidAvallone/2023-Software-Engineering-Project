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
import model.dao.UserDAO;
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
           // List<Friends> friendList = FriendsService.getFriendsList(owner);
            if(FriendsService.hasFriend(owner, newFriend)){
            //if(friendList.stream().anyMatch(friends -> UserService.findUserById(friends.getFriend()).getUsername().equals(friendName))){
                response.sendRedirect("FriendsListServlet?msg=1");
            }
            else {
                Friends friends = new Friends(owner.getID(), newFriend.getID());
                FriendsService.addFriend(friends);
                response.sendRedirect("FriendsListServlet");
            }
        }
        else{
            response.sendRedirect("FriendsListServlet?msg=2");
        }

    }
}
