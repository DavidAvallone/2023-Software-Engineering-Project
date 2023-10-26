package controller.servlet;

import model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LogoutServletTest {

    @Test public void testDoGetLogout() throws IOException, ServletException {
        //Servlets always have two main parameters request & repost which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class); //In this case, also Session

        //Setup the mock "inputs" on request
        when(request.getSession()).thenReturn(sessionMock);

        //Setup the mocks on Session
        doNothing().when(sessionMock).invalidate();

        //Class and Method Under test
        LogoutServlet servlet = new LogoutServlet();
        servlet.doGet(request,response);

        assertAll("LogoutServlet Assertions",
                () -> assertDoesNotThrow(
                        () -> verify(response).sendRedirect("index.jsp"),
                        "LogoutServlet should called sendRedirect(\"index.jsp\")"),
                ()->assertDoesNotThrow(
                        ()->verify(sessionMock).invalidate(),
                        "LogoutServlet should callled session.invalidate()"
                )
        );
    }
}