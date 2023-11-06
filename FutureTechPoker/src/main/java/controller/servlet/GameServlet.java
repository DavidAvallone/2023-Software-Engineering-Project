package controller.servlet;
import Poker.*;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "GameServlet", value = "/GameServlet")
public class GameServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/poker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Double bet = Double.parseDouble(request.getParameter("raiseAmount"));

        // call dao and do an update crud to the round
    }

}
