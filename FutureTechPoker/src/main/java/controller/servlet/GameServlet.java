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
import java.util.HashMap;

@WebServlet(name = "GameServlet", value = "/GameServlet")
public class GameServlet extends HttpServlet {

    public RoundService roundService = new RoundService();
    //public HashMap<String, RoundService> hsmRound = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        roundService = (RoundService) session.getAttribute("roundService");

        String action = request.getParameter("action");
        String exit = "no exit";
        try {
            exit = request.getParameter("exit");
            if (exit.equals("leave")){
                //roundService.update_player_db();
                //make a function in round that removes a player midround and takes care of that logic
                //action = "fold";
                roundService = null;
                session.setAttribute("roundService", roundService);

                response.sendRedirect("home.jsp");
                return;
            }
        }
        catch (Exception ex){
        }


        double bet = 0.0;

        try{
            bet = Double.parseDouble(request.getParameter("raiseAmount"));
        }
        catch (Exception ex){
        }

        if (bet == 0 && action.equals("raise"))
            bet = 25.0;
        roundService.round.player_turn(0, action, bet);

        roundService.round.player_turn(1, "call", 0);

        roundService.round.player_turn(2, "call", 0);

        roundService.round.player_turn(3, "call", 0);

        roundService.round.player_turn(4, "call", 0);

        roundService.round.player_turn(5, "call", 0);
        roundService.round.update_round();

        //roundService.update_player_db();

        session.setAttribute("roundService", roundService);
        response.sendRedirect("table.jsp");
    }
}
