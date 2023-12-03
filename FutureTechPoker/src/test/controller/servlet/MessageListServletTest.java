package controller.servlet;

import controller.service.MessageService;
import controller.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
import static org.mockito.Mockito.verify;

public class MessageListServletTest {
    @Test
    public void testDoPostSuccessfulAdd() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class);
        RequestDispatcher requestDispatcherMock = mock(RequestDispatcher.class);

        User currentUser = new User(100, "Logged", "Login", "pword", User.NORMAL_PERMISSION);
        User receiver = new User(101, "Logged1", "Login1", "pword1", User.NORMAL_PERMISSION);

        Message m1 = new Message(1, currentUser.getID(), receiver.getID(), "1");
        Message m2 = new Message(2, receiver.getID(), currentUser.getID(), "2");
        Message m3 = new Message(3, currentUser.getID(), receiver.getID(), "3");
        Message m4 = new Message(4, receiver.getID(), currentUser.getID(), "4");
        List<Message> messageList = new ArrayList<>();
        messageList.add(m1);
        messageList.add(m2);
        messageList.add(m3);
        messageList.add(m4);

        when(request.getParameter("otherUser")).thenReturn("101");
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("User")).thenReturn(currentUser);
        when(request.getRequestDispatcher("messages.jsp")).thenReturn(requestDispatcherMock);


        try (MockedStatic<MessageService> messageService = mockStatic(MessageService.class)) {
            try (MockedStatic<UserService> userService = mockStatic(UserService.class)) {
                messageService.when(() -> MessageService.getToAndFromMessages(any(int.class), any(int.class))).thenReturn(messageList);

                userService.when(() -> UserService.findUserById(any(int.class))).thenReturn(receiver);


                MessageListServlet servlet = new MessageListServlet();
                servlet.doPost(request, response);

                assertAll(
                        () -> assertDoesNotThrow(
                                () -> verify(requestDispatcherMock).forward(request, response))
                );
            }
        }
    }
}
