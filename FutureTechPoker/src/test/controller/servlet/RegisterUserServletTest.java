package controller.servlet;

import controller.service.UserService;
import model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import java.io.IOException;

import static org.mockito.Mockito.*; //Mock Framework
import static org.junit.jupiter.api.Assertions.*;

public class RegisterUserServletTest {

    @Test public void testDoPostSuccessfulRegister() throws IOException, ServletException {
        //Servlets always have two main parameters request & repost which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class); //In this case, also Session

        //Setup the mock "inputs" on request
        when(request.getParameter("txt_name")).thenReturn("TestName");
        when(request.getParameter("txt_login")).thenReturn("test@test.com");
        when(request.getParameter("txt_pass")).thenReturn("123456");
        when(request.getSession()).thenReturn(sessionMock);

        //Also need to mock UserService, since it is static method, the mocking is different
        try (MockedStatic<UserService> service = mockStatic(UserService.class)) {
            //Inside try block, we can replace static method calls to UserService
            service.when(() -> UserService.registerUser(any()) ).thenReturn(new User());

            RegisterUserServlet servlet = new RegisterUserServlet();
            servlet.doPost(request,response);

            assertAll("Register User Successfully assertions",
                    ()-> assertDoesNotThrow(
                            () -> verify(sessionMock).setAttribute(eq("User"),any(User.class)),
                            "RegisterUserServlet should called session.setAttribute(\"User\",userobject)"),
                    ()-> assertDoesNotThrow(
                            () -> verify(response).sendRedirect("login.jsp"),
                            "RegisterUserServlet should called sendRedirect(\"login.jsp\")")
            );
        }
    }

    @Test public void testDoPostNotRegister() throws IOException, ServletException {
        //Servlets always have two main parameters request & repost which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class); //In this case, also Session

        //Setup the mock "inputs" on request
        when(request.getParameter("txt_name")).thenReturn("TestName");
        when(request.getParameter("txt_login")).thenReturn("test@test.com");
        when(request.getParameter("txt_pass")).thenReturn("123456");
        when(request.getSession()).thenReturn(sessionMock);

        //Also need to mock UserService, since it is static method, the mocking is different
        try (MockedStatic<UserService> service = mockStatic(UserService.class)) {
            //Inside try block, we can replace static method calls to UserService
            service.when(() -> UserService.registerUser(any()) ).thenReturn(null);

            RegisterUserServlet servlet = new RegisterUserServlet();
            servlet.doPost(request,response);

            assertAll("Register user - User not register assertions",
                    ()-> assertDoesNotThrow(
                            () -> verify(sessionMock, never()).setAttribute(eq("User"),any(User.class)),
                            "RegisterUserServlet should never called session.setAttribute(...) when user is not register."),
                    ()-> assertDoesNotThrow(
                            () -> verify(response).sendRedirect("register.jsp?error=1"),
                            "RegisterUserServlet should called sendRedirect(\"register.jsp?error=1\")")
            );
        }
    }
}