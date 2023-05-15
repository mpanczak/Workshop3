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

        request.setAttribute("users", userList);

        getServletContext()
                .getRequestDispatcher("/users/list.jsp")
                .forward(request, response);
    }
}
