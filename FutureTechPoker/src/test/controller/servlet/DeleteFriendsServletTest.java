package controller.servlet;

import controller.service.FriendsService;
import controller.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Friends;
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

public class DeleteFriendsServletTest {

    @Test
    public void testDoPostDeleteSuccessful() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class);

        String friendName = "FriendName";
        User owner = new User(100, "Logged", "Login", "pword", User.NORMAL_PERMISSION);

        when(request.getParameter("deleteFriend")).thenReturn("103");
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("User")).thenReturn(owner);

        User friend = new User(101, "Logged1", "Login1", "pword", User.NORMAL_PERMISSION);
        User friend2 = new User(102, "Logged2", "Login2", "pword", User.NORMAL_PERMISSION);
        User friend3 = new User(103, "FriendName", "Login3", "pword", User.NORMAL_PERMISSION);

        Friends friends1 = new Friends(11, owner.getID(), friend.getID());
        Friends friends2 = new Friends(12, owner.getID(), friend2.getID());
        Friends friends3 = new Friends(13, owner.getID(), friend3.getID());
        List<Friends> friendsList = new ArrayList<>();
        friendsList.add(friends1);
        friendsList.add(friends2);
        friendsList.add(friends3);

        try (MockedStatic<FriendsService> service = mockStatic(FriendsService.class)) {
            try (MockedStatic<UserService> userService = mockStatic(UserService.class)) {
                service.when(() -> FriendsService.getFriendsList(any(User.class))).thenReturn(friendsList);
                service.when(() -> FriendsService.hasFriend(any(User.class), any(User.class))).thenReturn(true);
                service.when(() -> FriendsService.getFriends(any(int.class), any(int.class))).thenReturn(friends3);


                userService.when(() -> UserService.findUserByName(any(String.class))).thenReturn(friend3);
                userService.when(() -> UserService.findUserById(any(int.class))).thenReturn(friend3);

                DeleteFriendsServlet servlet = new DeleteFriendsServlet();
                servlet.doPost(request, response);

                assertAll(
                        () -> assertDoesNotThrow(
                                () -> verify(response).sendRedirect("FriendsListServlet"))
                );
            }
        }
    }


    @Test
    public void testDoPostDeleteUnsuccessful() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class);

        String friendName = "FriendName";
        User owner = new User(100, "Logged", "Login", "pword", User.NORMAL_PERMISSION);

        when(request.getParameter("deleteFriend")).thenReturn("103");
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("User")).thenReturn(owner);

        User friend = new User(101, "Logged1", "Login1", "pword", User.NORMAL_PERMISSION);
        User friend2 = new User(102, "Logged2", "Login2", "pword", User.NORMAL_PERMISSION);
        User friend3 = new User(103, "FriendName", "Login3", "pword", User.NORMAL_PERMISSION);

        Friends friends1 = new Friends(11, owner.getID(), friend.getID());
        Friends friends2 = new Friends(12, owner.getID(), friend2.getID());
        Friends friends3 = new Friends(13, owner.getID(), friend3.getID());
        List<Friends> friendsList = new ArrayList<>();
        friendsList.add(friends1);
        friendsList.add(friends2);


        try (MockedStatic<FriendsService> service = mockStatic(FriendsService.class)) {
            try (MockedStatic<UserService> userService = mockStatic(UserService.class)) {
                service.when(() -> FriendsService.addFriend(any(Friends.class))).thenReturn(new Friends());
                service.when(() -> FriendsService.getFriendsList(any(User.class))).thenReturn(friendsList);

                userService.when(() -> UserService.findUserByName(any(String.class))).thenReturn(friend3);
                userService.when(() -> UserService.findUserById(any(int.class))).thenReturn(friend3);


                DeleteFriendsServlet servlet = new DeleteFriendsServlet();
                servlet.doPost(request, response);

                assertAll(
                        () -> assertDoesNotThrow(
                                () -> verify(response).sendRedirect("user_friends.jsp?delmsg=1"))
                );
            }
        }
    }

}
