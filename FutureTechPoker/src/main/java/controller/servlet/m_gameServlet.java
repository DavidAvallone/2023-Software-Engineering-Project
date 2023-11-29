package controller.servlet;

import Poker.Player;
import controller.service.RoundService;
import controller.service.TableManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.User;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "m_GameServlet", value = "/m_GameServlet")
public class m_gameServlet extends HttpServlet {
    private TableManager tableManager;

    public void init() throws ServletException {
        super.init();
        tableManager = TableManager.getInstance();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String table_id = (String) session.getAttribute("table_id");
        User user = (User) session.getAttribute("User");
        Player player = (Player) session.getAttribute("my_player");
        RoundService roundService = tableManager.getTable(table_id);
        String action = request.getParameter("action");
        String exit = "no exit";
        try {
            exit = request.getParameter("exit");
            if (exit.equals("leave")){
                roundService.update_player_db(user,player);
                roundService.update_player_outcome(user,false);
                roundService.round.remove_player(player);
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

        roundService.round.player_turn(player.getTurnOrder(), action, bet);
        roundService.round.update_round();
        player = roundService.round.getPlayers().get(player.getTurnOrder());
        roundService.update_player_db(user, player);
        session.setAttribute("my_player", player);
        request.setAttribute("n", table_id);
        response.sendRedirect("m_table.jsp");
    }
}
