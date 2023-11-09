package controller.servlet;
import Poker.*;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.entity.PlayerEntity;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "GameServlet", value = "/GameServlet")
public class GameServlet extends HttpServlet {



    protected void processTurn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Double bet = Double.parseDouble(request.getParameter("raiseAmount"));

        // call round.playerturn(0, action, bet);

    }

}
