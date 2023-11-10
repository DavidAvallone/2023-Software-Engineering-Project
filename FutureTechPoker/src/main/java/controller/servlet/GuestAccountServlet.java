package controller.servlet;

import controller.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.entity.User;

import java.io.IOException;

@WebServlet(name = "guestAccountServlet", value = "/guestAccountServlet")
public class GuestAccountServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User guestLogged = User.createGuest();
        guestLogged = UserService.registerUser(guestLogged);
        guestLogged.setUsername("Guest" + guestLogged.getID());
        guestLogged.setLogin("Guest" + guestLogged.getID());
        UserService.updateUsername(guestLogged);
        HttpSession session = request.getSession();
        session.setAttribute("User", guestLogged); // Adding user to session
        response.sendRedirect("home.jsp");
    }
}
