package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

       
        if (isValidLogin(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            request.setAttribute("error", "Invalid credentials");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private boolean isValidLogin(String username, String password) {
      
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }
}
