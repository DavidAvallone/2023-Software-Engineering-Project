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
import static org.mockito.Mockito.*;

public class ResetPasswordServletTest {
    @Test public void ResetPasswordSuccessful() throws IOException, ServletException {
        User user = new User();
        user.setID(101);
        user.setLogin("passwordresettest@passwordtesttest.com");
        user.setPermission(User.NORMAL_PERMISSION);
        String oldPassword = "oldPassword";
        user.setPassword(oldPassword);

        //Servlets always have two main parameters request & repost which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class); //In this case, also Session

        //Setup the mock "inputs" on request
        when(request.getParameter("txt_pass")).thenReturn("newPassword");
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("User")).thenReturn(user);

        //Also need to mock UserService, since it is static method, the mocking is different
        try (MockedStatic<UserService> service = mockStatic(UserService.class)) {
            //Inside try block, we replace static method calls to UserService
            ResetPasswordServlet servlet = new ResetPasswordServlet();
            servlet.doPost(request, response);

            assertAll("ResetPasswordServlet",
                    () -> assertDoesNotThrow(
                            () -> service.verify(() -> UserService.updatePassword(user)),
                            "ResetPasswordServlet should call UserService.updatePassword(" + user + ")"),
                    () -> assertDoesNotThrow(
                            () -> verify(response).sendRedirect("login.jsp"),
                            "ResetPasswordServlet should call sendRedirect(\"login.jsp\")"),
                    () -> assertNotEquals(oldPassword, user.getPassword(), "Password should have changed")
            );
            System.out.println(user.getPassword());
        }
    }
}
