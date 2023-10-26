package controller.servlet;

import controller.service.UserService;
import model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginServletTest {
    @Test
    public void testDoPostNormalPath() throws IOException, ServletException {
        User answer = new User();
        answer.setID(101);
        answer.setLogin("test@test.com");
        answer.setPassword("123456");

        //Servlets always have two main parameters request & repost which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class); //In this case, also Session

        //Setup the mock "inputs" on request
        when(request.getParameter("txt_login")).thenReturn("test@test.com");
        when(request.getParameter("txt_pass")).thenReturn("123456");
        when(request.getSession()).thenReturn(sessionMock);

        //Also need to mock UserService, since it is static method, the mocking is different
        try (MockedStatic<UserService> service = mockStatic(UserService.class)) {
            //Inside try block, we can replace static method calls to UserService
            service.when(() -> UserService.loginUser(anyString(), anyString())).thenReturn(answer);

            LoginServlet servlet = new LoginServlet();
            servlet.doPost(request, response);

            assertAll("LoginUserServlet - Logged User Path Assertions",
                    () -> assertDoesNotThrow(
                            () -> verify(response).sendRedirect("home.jsp"),
                            "LoginServlet should called sendRedirect(\"home.jsp\")"),
                    () -> assertDoesNotThrow(
                            () -> verify(sessionMock).setAttribute(eq("User"), any(User.class)),
                            "LoginServlet should called session.setAttribute(\"User\",logged); ")
            );
        }
    }


    @Test public void testDoPostLoginIncorrect() throws IOException, ServletException {
        //Servlets always have two main parameters request & repost which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class); //In this case, also Session

        //Setup the mock "inputs" on request
        when(request.getParameter("txt_login")).thenReturn("test@test.com");
        when(request.getParameter("txt_pass")).thenReturn("123456");
        when(request.getSession()).thenReturn(sessionMock);

        //Also need to mock UserService, since it is static method, the mocking is different
        try (MockedStatic<UserService> service = mockStatic(UserService.class)) {
            //Inside try block, we can replace static method calls to UserService
            service.when(() -> UserService.loginUser(anyString(), anyString())).thenReturn(null);

            LoginServlet servlet = new LoginServlet();
            servlet.doPost(request, response);

            assertAll("LoginUserServlet - Incorrect Login User Path Assertions",
                    () -> assertDoesNotThrow(
                            () -> verify(response).sendRedirect("login.jsp?msg=1"),
                            "LoginServlet should called sendRedirect(\"login.jsp?msg=1\")"),
                    () -> assertDoesNotThrow(
                            () -> verify(sessionMock, never()).setAttribute(eq("User"), any(User.class)),
                            "LoginServlet should never call session.setAttribute(...); ")
            );
        }
    }

    @Test public void testDoGet() throws IOException, ServletException{
        //Servlets always have two main parameters request & repost which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class); //In this case, also Session

        //Setup the mock "inputs" on request
        when(request.getParameter("txt_pass")).thenReturn("123456");
        when(request.getSession()).thenReturn(sessionMock);

        LoginServlet servlet = new LoginServlet();
        servlet.doGet(request, response);

        assertAll("LoginUserServlet - doGet Assertions",
                () -> assertDoesNotThrow(
                        () -> verify(response).sendRedirect("index.jsp"),
                        "LoginServlet.doGet should called sendRedirect(\"index.jsp\")"),
                () -> assertDoesNotThrow(
                        () -> verify(request,never()).getSession(),
                        "LoginServlet.doGet should never called request.getSession()"),
                () -> assertDoesNotThrow(
                        () -> verify(request,never()).getParameter(eq("txt_pass")),
                        "LoginServlet.doGet should never call request.getParameter(txt_pass); ")
        );

    }

}