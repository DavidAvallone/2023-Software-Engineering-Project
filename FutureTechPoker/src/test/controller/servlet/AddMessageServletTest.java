package controller.servlet;

import controller.service.FriendsService;
import controller.service.MessageService;
import controller.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Friends;
import model.entity.Message;
import model.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddMessageServletTest {
    @Test
    public void testDoPostSuccessfulAdd() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class);
        RequestDispatcher requestDispatcherMock = mock(RequestDispatcher.class);

        User currentUser = new User(100, "Logged", "Login", "pword", User.NORMAL_PERMISSION);
        User receiver = new User(101, "Logged1", "Login1", "pword1", User.NORMAL_PERMISSION);

        when(request.getParameter("receiverId")).thenReturn("101");
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("User")).thenReturn(currentUser);
        when(request.getRequestDispatcher("MessageListServlet?otherUser=" + "101")).thenReturn(requestDispatcherMock);


        try (MockedStatic<MessageService> messageService = mockStatic(MessageService.class)) {
            messageService.when(() -> MessageService.addMessage(any(Message.class))).thenReturn(new Message());

            AddMessageServlet servlet = new AddMessageServlet();
            servlet.doPost(request, response);

            assertAll(
                    () -> assertDoesNotThrow(
                            () -> verify(requestDispatcherMock).forward(request, response))
            );
        }
    }
}
