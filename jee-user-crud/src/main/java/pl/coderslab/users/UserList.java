package pl.coderslab.users;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/user/list")
public class UserList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<User> userList = new ArrayList<>(Arrays.asList(userDAO.findAll()));

//        User user = new User();
//        user.setUserName("John Doe");
//        user.setEmail("john.doe.gmail.com");
//        user.setPassword("dupa1234");
//
//        User user2 = new User();
//        user2.setId(3);
//        user2.setUserName("Alice Smith");
//        user2.setEmail("a.smitch.yahoo.com");
//        user2.setPassword("coffe1&");
//
//        List<User> userList = new ArrayList<>();
//        userList.add(user);
//        userList.add(user2);

        request.setAttribute("users", userList);

        getServletContext()
                .getRequestDispatcher("/users/list.jsp")
                .forward(request, response);
    }
}
