package pl.coderslab.users;

import pl.coderslab.entity.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/delete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("user"));

        UserDAO userDAO = new UserDAO();
        userDAO.delete(userID);

        response.sendRedirect("/user/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
