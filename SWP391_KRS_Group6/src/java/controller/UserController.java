package controller;

import dao.UserDAO;
import entity.Role;
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
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//            HttpSession session = request.getSession();
            String action = request.getParameter("action");

            if (action != null && action.equals("update")) {
                updateUser(request, response);
//            } else if (action != null && action.equals("loginPage")) {
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
//                dispatcher.forward(request, response);
//            } else if (action != null && action.equals("login")) {
//
//                String username = request.getParameter("username");
//                String password = request.getParameter("password");
//
//                if (loginAccount(username, password)) {
//                    session.setAttribute("account", user);
//                    Cookie userCookie = new Cookie("username", username);
//                    userCookie.setMaxAge(15); // 15s
//                    response.addCookie(userCookie);
//                    response.sendRedirect("index.html");
//                } else {
//                    response.sendRedirect("login.html?error=invalid_credentials");
//                }
            } else if (action != null && action.equals("addUserPage")) {
                // Hiển thị trang để thêm người dùng mới
                // Truy vấn để lấy danh sách các role từ database
                UserDAO roleDAO = new UserDAO();
                List<Role> roles = roleDAO.getRoles();
                request.setAttribute("roles", roles);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/addUser.jsp");
                dispatcher.forward(request, response);

            } else if (action != null && action.equals("addUser")) {

                // Xử lý yêu cầu thêm người dùng mới
                addUser(request, response);
            } else {
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
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            int age = Integer.parseInt(request.getParameter("age"));
            boolean status = request.getParameter("status") != null;
            int roleId = Integer.parseInt(request.getParameter("roleId"));

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserById(userId);

            if (user != null) {
                user.setUser_name(userName);
                user.setPassword(password);
                user.setEmail(email);
                user.setFull_name(fullName);
                user.setPhone(phone);
                user.setGender(gender);
                user.setAge(age);
                user.setStatus(status);
                user.setRole_id(roleId);
                boolean isUpdated = userDAO.updateUser(user);

                if (isUpdated) {
                    request.setAttribute("successMessage", "Lưu thành công.");
                } else {
                    request.setAttribute("successMessage", "Lưu thành công.");
                }
                response.sendRedirect(request.getContextPath() + "/userController?id=" + userId);

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

    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy thông tin từ form
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            int age = Integer.parseInt(request.getParameter("age"));
            boolean status = request.getParameter("status") != null;
            int roleId = Integer.parseInt(request.getParameter("roleId"));

            // Tạo một đối tượng User
            User user = new User();
            user.setUser_name(userName);
            user.setPassword(password);
            user.setEmail(email);
            user.setFull_name(fullName);
            user.setPhone(phone);
            user.setGender(gender);
            user.setAge(age);
            user.setStatus(status);
            user.setRole_id(roleId);

            // Thêm người dùng vào cơ sở dữ liệu
            UserDAO userDAO = new UserDAO();
            boolean isSuccess = userDAO.addUser(user);

            // Chuyển hướng về trang addUser.jsp với thông báo kết quả
            if (isSuccess) {
                request.setAttribute("successMessage", "User added successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to add user.");
            }
            request.getRequestDispatcher("/addUser.jsp").forward(request, response);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/addUser.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
