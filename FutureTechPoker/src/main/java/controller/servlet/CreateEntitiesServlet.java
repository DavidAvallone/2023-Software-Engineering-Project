package controller.servlet;

import java.io.IOException;

import controller.service.PlayerEntityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Player;
import model.entity.User;

@WebServlet(name = "CreateEntitiesServlet", value = "/CreateEntitiesServlet")
public class CreateEntitiesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User logged = (User) session.getAttribute("User");
        if (logged == null)
            response.sendRedirect("home.jsp");
        else {
            // Create a player entity
            Player player = PlayerEntityService.createPlayer(logged);
            session.setAttribute("Player",player); // Adding user to session

            // Set player attributes as needed

            // Redirect the user to the game test page
            response.sendRedirect("entitytest.jsp");
        }
    }
}