package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entities.User;

@WebServlet("/register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

  private final UserDAO userDAO = new UserDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        
        if (isValidRegistration(username, password)) {
            // Create a new user and save to the database
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            userDAO.saveUser(newUser);

          
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("error", "Invalid registration data");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    private boolean isValidRegistration(String username, String password) {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }
}
