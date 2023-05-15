package pl.coderslab.users;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("user"));

        UserDAO userDAO = new UserDAO();
        User user = userDAO.read(userID);

        request.setAttribute("user", user);

        getServletContext()
                .getRequestDispatcher("/users/edit.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userID"));
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setId(userId);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);

        UserDAO userDAO = new UserDAO();
        userDAO.update(user);

        response.sendRedirect("/user/list");
    }
}
