package controller.servlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.PlayerDAO;
import model.dao.RoundDAO;
import model.dao.UserDAO;
import model.entity.PlayerEntity;
import model.entity.RoundEntity;
import Poker.*;
import model.entity.User;

@WebServlet("/CreateEntitiesServlet")
public class CreateEntitiesServlet extends HttpServlet {
    public static PlayerDAO pdao = new PlayerDAO();
    public static RoundDAO rdao = new RoundDAO();
    public static UserDAO udao = new UserDAO();
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User logged = (User) session.getAttribute("User");
        // Create a player entity
        PlayerEntity player = new PlayerEntity(logged);
        // Set player attributes as needed
        player.setName("PlayerName"); // Replace with actual player data

        long seed = 42;
        // Create a round entity
        Round round = new Round(50.0, seed);
        RoundEntity re = new RoundEntity(round);
        // Set round attributes as needed

        pdao.create(player);
        rdao.create(re);
        // Redirect the user to the game test page
        response.sendRedirect("gametestpage.jsp");
    }
}