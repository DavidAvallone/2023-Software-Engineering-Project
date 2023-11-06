package controller.servlet;

import model.entity.User;
import controller.service.UserService;
import util.PasswordUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.*;

@WebServlet(name = "resetPasswordServlet", value = "/resetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String hashedPass = PasswordUtil.hash(request.getParameter("txt_pass"));
        HttpSession session = request.getSession();
        User currUser = (User) session.getAttribute("User");
        currUser.setPassword(hashedPass);
        UserService.updatePassword(currUser);
        session.invalidate();
        response.sendRedirect("login.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }

}

