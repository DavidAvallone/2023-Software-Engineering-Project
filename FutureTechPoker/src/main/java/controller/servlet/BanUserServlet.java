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

@WebServlet(name = "banUserServlet", value = "/banUserServlet")
public class BanUserServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        String bannedUser = request.getParameter("ban_username");
        if(user.getPermission() == 2){ // If the User is Admin
            User userToBeBanned = UserService.findUserByName(bannedUser); // Get the banned user by username
            userToBeBanned.setBanned(!user.getBanned()); // Reverse previous ban status
        }
        else{
            response.sendRedirect("home.jsp"); // User does not have permissions to ban user
        }

    }
}
