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

public class GuestAccountServletTest {
    @Test
    public void SignInAsGuest() throws IOException, ServletException {
        // Servlets always have two main parameters request & response which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class); // In this case, also Session

        // Setup the mock "inputs" on request
        when(request.getSession()).thenReturn(sessionMock);

        GuestAccountServlet servlet = new GuestAccountServlet();
        servlet.doPost(request, response);

        // Verify that the session attribute "User" is set with a guest user and that the redirect is called
        assertAll("GuestAccountServlet - doPost Assertions",
                () -> {
                    verify(sessionMock).setAttribute(eq("User"), argThat((User user) -> user.getPermission() == User.GUEST_PERMISSION));
                    verify(response).sendRedirect("home.jsp");
                }
        );
    }
}
