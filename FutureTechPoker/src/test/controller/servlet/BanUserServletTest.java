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

public class BanUserServletTest {

    @Test public void BanUserSuccessful() throws IOException, ServletException {
        User admin = new User();
        admin.setPermission(User.ADMIN_PERMISSION);
        admin.setBanned(false);

        User banUser = new User();
        banUser.setUsername("banUser");
        banUser.setBanned(false);

        // Mocking the static method call
        try (MockedStatic<UserService> mockedStatic = mockStatic(UserService.class)) {
            mockedStatic.when(() -> UserService.findUserByLogin(anyString())).thenReturn(banUser);

            // Mock HttpServletRequest and HttpServletResponse
            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);
            HttpSession session = mock(HttpSession.class);

            // Mocking session to simulate admin user
            User adminUser = new User(); // Create an admin user
            adminUser.setPermission(2);
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute("User")).thenReturn(adminUser);

            // Mocking other request parameters as needed
            when(request.getParameter("ban_username")).thenReturn("banUser");

            BanUserServlet servlet = new BanUserServlet();
            servlet.doPost(request, response);

            assertTrue(banUser.getBanned(), "User's banned status should be changed");
        }

    }

    @Test
    public void BanUserUnsuccessful() throws IOException, ServletException {
        User admin = new User();
        admin.setPermission(User.ADMIN_PERMISSION);
        admin.setBanned(false);

        User banUser = new User();
        banUser.setUsername("banUser");
        banUser.setBanned(false);

        // Mocking the static method call
        try (MockedStatic<UserService> mockedStatic = mockStatic(UserService.class)) {
            mockedStatic.when(() -> UserService.findUserByLogin(anyString())).thenReturn(banUser);

            // Mock HttpServletRequest and HttpServletResponse
            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);
            HttpSession session = mock(HttpSession.class);

            // Mocking session to simulate admin user
            User NonAdminUser = new User(); // Create an admin user
            NonAdminUser.setPermission(1);
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute("User")).thenReturn(NonAdminUser);

            // Mocking other request parameters as needed
            when(request.getParameter("ban_username")).thenReturn("banUser");

            BanUserServlet servlet = new BanUserServlet();
            servlet.doPost(request, response);

            assertFalse(banUser.getBanned(), "User's banned status should not have changed");
        }

    }
}