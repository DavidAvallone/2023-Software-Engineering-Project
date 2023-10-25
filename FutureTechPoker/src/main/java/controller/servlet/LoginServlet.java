package controller.servlet;

import model.entity.User;
import controller.service.UserService;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Security Issue: passwords should never be sent via GET.");
        response.sendRedirect("index.jsp");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("txt_login");
        String unhashedPass = request.getParameter("txt_pass");

        User logged = UserService.loginUser(login, unhashedPass);
        if(logged!=null){
            System.out.println("Successfully logged in");
            HttpSession session = request.getSession();
            logged.setPassword(""); // Remove the password
            session.setAttribute("User",logged); // Adding user to session
            response.sendRedirect("home.jsp");
        } else {
            response.sendRedirect("login.jsp?msg=1");
        }
    }

}