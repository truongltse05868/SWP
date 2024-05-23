package controller;

import dao.UserDAO;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if (action != null && action.equals("update")) {
                updateUser(request, response);
            } else if (action != null && action.equals("loginPage")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            } else if (action != null && action.equals("login")) {

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if (loginAccount(username, password)) {
                    Cookie userCookie = new Cookie("username", username);
                    userCookie.setMaxAge(15); // 15s
                    response.addCookie(userCookie);
                    response.sendRedirect("index.html");
                } else {
                    response.sendRedirect("login.html?error=invalid_credentials");
                }
            }else {
                int userId = Integer.parseInt(request.getParameter("id"));
                UserDAO userDAO = new UserDAO();
                User user = userDAO.getUserById(userId);
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/userProfile.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid user ID format", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing user request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("id"));
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserById(userId);

            if (user != null) {
                user.setFull_name(fullname);
                user.setPhone(phone);
                userDAO.updateUser(user);
                response.sendRedirect(request.getContextPath() + "/userProfile?id=" + userId);

            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid user ID format", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating user.");
        }
    }

    private boolean loginAccount(String username, String password)
            throws ServletException, IOException {
        try {

            UserDAO userDAO = new UserDAO();
            boolean user = userDAO.loginAccount(username, password);

            return user != false;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user", e);
        }
        return false;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
