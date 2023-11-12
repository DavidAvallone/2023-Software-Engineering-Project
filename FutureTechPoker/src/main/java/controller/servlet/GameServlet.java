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
        HttpSession test = request.getSession();

        roundService = (RoundService) test.getAttribute("roundService");

        String action = request.getParameter("action");

        Double bet = Double.parseDouble(request.getParameter("raiseAmount"));

        roundService.round.player_turn(0, action, bet);

        roundService.round.update_round();
    }
}
