package controller;

import dao.UserDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import entity.User;

public class Login extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Login.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession session = request.getSession();
            String identifier = request.getParameter("identifier"); // Username or Email
            String password = request.getParameter("password");
            UserDAO udao = new UserDAO();
            List<User> users = udao.checkLogin(identifier, password);
            String mess = "Username or password wrong! please try again";

            if (!users.isEmpty()) {
                User user = users.get(0);
                if (user.isStatus()) {
                    session.setAttribute("account", user);
                    if (user.getRole_id() == 1) { // role_id = 1 is admin
                        response.sendRedirect("Home"); // Redirect to admin home page
                    } else {
                        response.sendRedirect("Home"); // Redirect to regular user home page
                    }
                } else {
                    request.setAttribute("errorMessage", mess);
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", mess);
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during login process", e);
            request.setAttribute("mess", "An error occurred during login. Please try again.");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
