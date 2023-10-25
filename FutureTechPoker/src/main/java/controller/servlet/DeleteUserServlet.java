package controller.servlet;

import controller.service.UserService;
import model.entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "deleteUserServlet", value = "/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{

        User logged = (User) request.getSession().getAttribute("User");
        UserService.deleteUser(logged.getID());
        HttpSession session = request.getSession(false); // Get the session without creating a new one if it doesn't exist
        if (session != null) {
            session.invalidate(); // Invalidate the session, which logs the user out
        }
        response.sendRedirect("index.jsp");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }

}
