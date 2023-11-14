package controller.servlet;

import Poker.Round;
import controller.service.RoundService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "GameServlet", value = "/GameServlet")
public class GameServlet extends HttpServlet {

    public RoundService roundService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        roundService = (RoundService) session.getAttribute("roundService");

        String action = request.getParameter("action");

        Double bet = Double.parseDouble(request.getParameter("raiseAmount"));

        roundService.round.player_turn(0, action, bet);

        roundService.round.player_turn(1, "call", 0);

        roundService.round.player_turn(2, "call", 0);

        roundService.round.player_turn(3, "call", 0);

        roundService.round.update_round();

        session.setAttribute("roundService", roundService);
        response.sendRedirect("gametestpage.jsp");
    }
}
