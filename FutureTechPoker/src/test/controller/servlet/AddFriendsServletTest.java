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
import static org.mockito.Mockito.verify;

public class AddFriendsServletTest {
    @Test
    public void testDoPostSuccessfulAdd() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class);

        //String friendName = request.getParameter("newFriend");
        User owner = new User(100, "Logged", "Login", "pword", User.NORMAL_PERMISSION);

        when(request.getParameter("newFriend")).thenReturn("FriendName");
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("User")).thenReturn(owner);

        User friend = new User(101, "Logged1", "Login1", "pword", User.NORMAL_PERMISSION);
        User friend2 = new User(102, "Logged2", "Login2", "pword", User.NORMAL_PERMISSION);
        User friend3 = new User(103, "FriendName", "Login3", "pword", User.NORMAL_PERMISSION);

        Friends friends1 = new Friends(11, owner, friend);
        Friends friends2 = new Friends(12, owner, friend2);
        Friends friends3 = new Friends(13, owner, friend3);
        List<Friends> friendsList = new ArrayList<>();
        friendsList.add(friends1);
        friendsList.add(friends2);

        try (MockedStatic<FriendsService> service = mockStatic(FriendsService.class)) {
            try (MockedStatic<UserService> userService = mockStatic(UserService.class)) {
                service.when(() -> FriendsService.addFriend(any(Friends.class))).thenReturn(new Friends());
                service.when(() -> FriendsService.getFriendsList(any(User.class))).thenReturn(friendsList);

                userService.when(() -> UserService.findUserByName(any(String.class))).thenReturn(friend3);

                AddFriendsServlet servlet = new AddFriendsServlet();
                servlet.doPost(request, response);

                assertAll(
                        () -> assertDoesNotThrow(
                                () -> verify(response).sendRedirect("user_friends.jsp"))
                );
            }
        }
    }


    @Test
    public void testDoPostUnknownUser() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class);

        String friendName = "FriendName";
        User owner = new User(100, "Logged", "Login", "pword", User.NORMAL_PERMISSION);

        when(request.getParameter("newFriend")).thenReturn(friendName);
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("User")).thenReturn(owner);

        User friend = new User(101, "Logged1", "Login1", "pword", User.NORMAL_PERMISSION);
        User friend2 = new User(102, "Logged2", "Login2", "pword", User.NORMAL_PERMISSION);
        User friend3 = new User(103, "FriendName", "Login3", "pword", User.NORMAL_PERMISSION);

        Friends friends1 = new Friends(11, owner, friend);
        Friends friends2 = new Friends(12, owner, friend2);
        Friends friends3 = new Friends(13, owner, friend3);
        List<Friends> friendsList = new ArrayList<>();
        friendsList.add(friends1);
        friendsList.add(friends2);
        friendsList.add(friends3);

        try (MockedStatic<FriendsService> service = mockStatic(FriendsService.class)) {
            try (MockedStatic<UserService> userService = mockStatic(UserService.class)) {
                service.when(() -> FriendsService.addFriend(any(Friends.class))).thenReturn(new Friends());
                service.when(() -> FriendsService.getFriendsList(any(User.class))).thenReturn(friendsList);

                userService.when(() -> UserService.findUserByName(any(String.class))).thenReturn(null);

                AddFriendsServlet servlet = new AddFriendsServlet();
                servlet.doPost(request, response);

                assertAll(
                        () -> assertDoesNotThrow(
                                () -> verify(response).sendRedirect("user_friends.jsp?msg=2"))
                );
            }
        }
    }

    @Test
    public void testDoPostAlreadyFriends() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class);

        String friendName = "FriendName";
        User owner = new User(100, "Logged", "Login", "pword", User.NORMAL_PERMISSION);

        when(request.getParameter("newFriend")).thenReturn(friendName);
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("User")).thenReturn(owner);

        User friend = new User(101, "Logged1", "Login1", "pword", User.NORMAL_PERMISSION);
        User friend2 = new User(102, "Logged2", "Login2", "pword", User.NORMAL_PERMISSION);
        User friend3 = new User(103, "FriendName", "Login3", "pword", User.NORMAL_PERMISSION);

        Friends friends1 = new Friends(11, owner, friend);
        Friends friends2 = new Friends(12, owner, friend2);
        Friends friends3 = new Friends(13, owner, friend3);
        List<Friends> friendsList = new ArrayList<>();
        friendsList.add(friends1);
        friendsList.add(friends2);
        friendsList.add(friends3);

        try (MockedStatic<FriendsService> service = mockStatic(FriendsService.class)) {
            try (MockedStatic<UserService> userService = mockStatic(UserService.class)) {
                service.when(() -> FriendsService.addFriend(any(Friends.class))).thenReturn(new Friends());
                service.when(() -> FriendsService.getFriendsList(any(User.class))).thenReturn(friendsList);

                userService.when(() -> UserService.findUserByName(any(String.class))).thenReturn(friend3);

                AddFriendsServlet servlet = new AddFriendsServlet();
                servlet.doPost(request, response);

                assertAll(
                        () -> assertDoesNotThrow(
                                () -> verify(response).sendRedirect("user_friends.jsp?msg=1"))
                );
            }
        }
    }

    @Test
    public void testDoPostFriendSelf() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class);

        String friendName = "Logged";
        User owner = new User(100, "Logged", "Login", "pword", User.NORMAL_PERMISSION);

        when(request.getParameter("newFriend")).thenReturn(friendName);
        when(request.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("User")).thenReturn(owner);

        User friend = new User(101, "Logged1", "Login1", "pword", User.NORMAL_PERMISSION);
        User friend2 = new User(102, "Logged2", "Login2", "pword", User.NORMAL_PERMISSION);
        User friend3 = new User(103, "FriendName", "Login3", "pword", User.NORMAL_PERMISSION);

        Friends friends1 = new Friends(11, owner, friend);
        Friends friends2 = new Friends(12, owner, friend2);
        Friends friends3 = new Friends(13, owner, friend3);
        List<Friends> friendsList = new ArrayList<>();
        friendsList.add(friends1);
        friendsList.add(friends2);
        friendsList.add(friends3);

        try (MockedStatic<FriendsService> service = mockStatic(FriendsService.class)) {
            try (MockedStatic<UserService> userService = mockStatic(UserService.class)) {
                service.when(() -> FriendsService.addFriend(any(Friends.class))).thenReturn(new Friends());
                service.when(() -> FriendsService.getFriendsList(any(User.class))).thenReturn(friendsList);

                userService.when(() -> UserService.findUserByName(any(String.class))).thenReturn(owner);

                AddFriendsServlet servlet = new AddFriendsServlet();
                servlet.doPost(request, response);

                assertAll(
                        () -> assertDoesNotThrow(
                                () -> verify(response).sendRedirect("user_friends.jsp?msg=2"))
                );
            }
        }
    }
}
