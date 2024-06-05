package controller;

import dao.SettingDAO;
import dao.UserDAO;
import entity.Setting;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

//@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            User currentUser = (User) session.getAttribute("account");

            if (currentUser == null) {
                // Nếu không có phiên đăng nhập, chuyển hướng đến trang đăng nhập
                response.sendRedirect("Home");
                return;
            }

            String action = request.getParameter("action");

// Kiểm tra quyền truy cập của người dùng
            if (currentUser.getRole_id() == 1) {
                // Nếu là quản trị viên, cho phép truy cập vào các tính năng quản trị
                switch (action) {
                    case "addUserPage":
                        addUserPage(request, response);
                        break;
                    case "addUser":
                        addUser(request, response);
                        break;
                    case "update":
                        updateUser(request, response);
                        break;
                    case "userList":
                        getListUser2(request, response);
                        break;
                    case "changePassAdmin":
                        changePassAdmin(request, response);
                        break;
                    case "editUserProfile":
                        getUserProfle(request, response);
                        break;
                    case "searchUsername":
                        getListUser2(request, response);
                        break;
                    case "sortField":
                        getListUser2(request, response);
                        break;
                    case "profileUserPage":
                        getProfileUser(request, response);
                        break;
                    case "userChangePass":
                        changePassUser(request, response);
                        break;
                    default:
//                        getUserProfle(request, response);
                        break;
                }
            } else {
                // Nếu không phải là quản trị viên, chỉ cho phép truy cập vào trang thông tin cá nhân
                switch (action) {
                    case "profileUserPage":
                        getProfileUser(request, response);
                        break;
                    case "userChangePass":
                        changePassUser(request, response);
                        break;
                }
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

    private void getProfileUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            UserDAO userDAO = new UserDAO();
            SettingDAO settingDAO = new SettingDAO();
            User user = userDAO.getUserById(userId);
            List<Setting> roles = settingDAO.getAllRole();
            request.setAttribute("user", user);
            request.setAttribute("roles", roles);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ProfileUser.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list user", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get list user.");
        }
    }
//cái dưới là của admin xem.

    private void getUserProfle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("id"));
            UserDAO userDAO = new UserDAO();
            SettingDAO settingDAO = new SettingDAO();
            User user = userDAO.getUserById(userId);
            List<Setting> roles = settingDAO.getAllRole();
            request.setAttribute("user", user);
            request.setAttribute("roles", roles);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/userProfile.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list user", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get list user.");
        }
    }

    private void addUserPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SettingDAO settingDAO = new SettingDAO();

            List<Setting> roles = settingDAO.getAllRole();

            request.setAttribute("roles", roles);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/addUser.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get add user page", e);
        }
    }

    private void getListUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserDAO userDAO = new UserDAO();
            SettingDAO roleDAO = new SettingDAO();
//             List<UserList> users = userDAO.getAllUsersWithRole();
            List<User> users = userDAO.getAllUsers();
            List<Setting> roles = roleDAO.getAllRole();
            // Set the list of users as a request attribute
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/userList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list user", e);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("id"));
            String userName = request.getParameter("userName");
//            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            int age = Integer.parseInt(request.getParameter("age"));
            String statusParam = request.getParameter("status");
            boolean status = (statusParam != null && statusParam.equals("on"));
            int roleId = Integer.parseInt(request.getParameter("roleId"));

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserById(userId);

            if (user != null) {

                user.setUser_name(userName);
//                user.setPassword(password);
                user.setEmail(email);
                user.setFull_name(fullName);
                user.setPhone(phone);
                user.setGender(gender);
                user.setAge(age);
                user.setStatus(status);
                user.setRole_id(roleId);
                boolean isUpdated = userDAO.updateUser(user);

                String message = isUpdated ? "Lưu thành công." : "Cập nhật không thành công.";
                request.setAttribute("successMessage", message);
//                response.sendRedirect(request.getContextPath() + "/userController?id=" + userId);
                // save xong lấy các thông tin vừa lưu để đẩy về jsp(vẫn giữ nguyên data của page không có mất hết data khi call jsp)
                // call cách khác thì truyền id vào không bảo mật tí nào
                SettingDAO settingDAO = new SettingDAO();
                List<Setting> roles = settingDAO.getAllRole();
                request.setAttribute("user", user);
                request.setAttribute("roles", roles);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/userProfile.jsp");
                dispatcher.forward(request, response);

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

    private void changePassUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SettingDAO settingDAO = new SettingDAO();
            List<Setting> roles = settingDAO.getAllRole();
            Map<String, String> errors = new HashMap<>();
            int userId = Integer.parseInt(request.getParameter("userid"));
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String reNewPassword = request.getParameter("renewPassword");
            String message = "";

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserById(userId);

            if (user != null) {
                boolean isCurrentPass = userDAO.checkCurrentPassword(user, currentPassword);
                if (isCurrentPass) {
                    if (!newPassword.equals(reNewPassword) || !validatePassword(newPassword) || !validatePassword(reNewPassword)) {
                        message = "New password does not meet requirements.";
                        errors.put("errorsSamePass", message);
                    } else {
//                        user.setPassword(newPassword);
                        boolean isUpdated = userDAO.changePassword(user, newPassword);
                        message = isUpdated ? "Password updated successfully." : "Password update failed.";
                        errors.put("errorsUpdate", message);
                    }
                } else {
                    message = "Mật khẩu cũ không đúng";
                    errors.put("errosOldPass", message);
                }

            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                return;
            }
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("user", user);
                request.setAttribute("roles", roles);
                request.setAttribute("tab", "change-password");
                request.getRequestDispatcher("WEB-INF/ProfileUser.jsp").forward(request, response);
                return;
            }
//            request.setAttribute("successMessage", message);
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
            request.setAttribute("roles", roles);
            request.setAttribute("tab", "change-password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ProfileUser.jsp?tab=change-password");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid user ID format", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating user.");
        }
    }

    private void changePassAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SettingDAO settingDAO = new SettingDAO();
            List<Setting> roles = settingDAO.getAllRole();

            int userId = Integer.parseInt(request.getParameter("id"));
            String newPassword = request.getParameter("newPassword");
            String reNewPassword = request.getParameter("reNewPassword");
            String message = "";

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserById(userId);

            if (user != null) {
                if (!newPassword.equals(reNewPassword) || !validatePassword(newPassword)) {
                    message = "New password does not meet requirements.";
                } else {
                    user.setPassword(newPassword);
                    boolean isUpdated = userDAO.updateUser(user);
                    message = isUpdated ? "Password updated successfully." : "Password update failed.";
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                return;
            }

            request.setAttribute("successMessage", message);
            request.setAttribute("user", user);
            request.setAttribute("roles", roles);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/userProfile.jsp");
            dispatcher.forward(request, response);
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
            SettingDAO settingDAO = new SettingDAO();
            List<Setting> roles = settingDAO.getAllRole();
            // Danh sách các thông báo lỗi
            Map<String, String> errors = new HashMap<>();
            // Lấy thông tin từ form
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            String ageParam = request.getParameter("age");

            int age;
            if (ageParam == null || ageParam.trim().isEmpty()) {
                age = 1;
            } else {
                age = Integer.parseInt(ageParam);
            }
            boolean status = request.getParameter("status") != null;
            String roleIdParam = request.getParameter("roleId");
            int roleId;
            if (roleIdParam == null || roleIdParam.trim().isEmpty()) {
                roleId = 3;
            } else {
                roleId = Integer.parseInt(roleIdParam);
            }

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

            UserDAO userDAO = new UserDAO();
            //check email and tên account
            // Kiểm tra email và tên người dùng đã tồn tại hay chưa
            boolean emailExists = userDAO.checkEmailExists(email);
            boolean usernameExists = userDAO.checkUsernameExists(userName);

            // Validate các trường
            if (email == null || email.trim().isEmpty() || !validateEmail(email)) {
                errors.put("emailError", "Email không hợp lệ hoặc không được bỏ trống");
            }

            if (password == null || password.trim().isEmpty() || !validatePassword(password)) {
                errors.put("passError", "Password phải có ít nhất 8 ký tự, chứa ít nhất một chữ cái viết hoa, một chữ cái viết thường và một chữ số.");
            }

            if (userName == null || userName.trim().isEmpty() || !validateUsername(userName)) {
                errors.put("usernameError", "Username phải có độ dài từ 3 đến 20 ký tự, chỉ chứa chữ cái và số, không chứa khoảng trắng.");
            }

//            if (!isValidPhone(phone)) {
//                errors.put("phoneError", "Số điện thoại có 10 chữ số và bắt đầu bằng số 0");
//            }
//            if (!isValidAge(age)) {
//                errors.put("ageError", "Độ tuổi phù hợp như là học sinh sinh viên");
//            }
//            if (!isValidFullName(fullName)) {
//                errors.put("fullnameError", "Độ dài không quá 100");
//            }
            if (emailExists || usernameExists) {
                errors.put("existsError", "Email hoặc Username đã tồn tại.");
            }

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("roles", roles);
                request.setAttribute("user_name", userName);
                request.setAttribute("password", password);
                request.setAttribute("email", email);
                request.setAttribute("full_name", fullName);
                request.setAttribute("phone", phone);
                request.setAttribute("gender", gender);
                request.setAttribute("age", age);
                request.setAttribute("status", status);
                request.setAttribute("roleId", roleId);
                request.getRequestDispatcher("WEB-INF/addUser.jsp").forward(request, response);
                return;
            }

            // Thêm người dùng vào cơ sở dữ liệu
            boolean isSuccess = userDAO.addUser(user);
            String message = isSuccess ? "Lưu thành công." : "Cập nhật không thành công.";
            request.setAttribute("successMessage", message);

            // Chuyển hướng về trang addUser.jsp với thông báo kết quả
            request.setAttribute("roles", roles);
            request.setAttribute("user_name", userName);
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            request.setAttribute("full_name", fullName);
            request.setAttribute("phone", phone);
            request.setAttribute("gender", gender);
            request.setAttribute("age", age);
            request.setAttribute("status", status);
            request.setAttribute("roleId", roleId);
            //
            request.getRequestDispatcher("WEB-INF/addUser.jsp").forward(request, response);

        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            request.setAttribute("existsError", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/addUser.jsp").forward(request, response);
        }
    }

    //sort and search
    private void getListUser2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserDAO userDAO = new UserDAO();
            SettingDAO roleDAO = new SettingDAO();
            List<User> users;
            List<Setting> roles = roleDAO.getAllRole();

            // Lấy tham số tìm kiếm và sắp xếp
            String searchUsername = request.getParameter("searchUsername");
            String sortField = request.getParameter("sortField");

            if (searchUsername != null && !searchUsername.isEmpty()) {
                users = userDAO.searchUsersByUsername(searchUsername);
            } else if (sortField != null && !sortField.isEmpty()) {
                users = userDAO.getUsersSortedBy(sortField);
            } else {
                users = userDAO.getAllUsers();
            }

            // Set the list of users as a request attribute
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
            request.setAttribute("searchUsername", searchUsername);
            request.setAttribute("sortField", sortField);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/userList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list user", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the user list.");
        }
    }

    private boolean isValidFullName(String fullName) {
        // Kiểm tra độ dài của fullName (không quá 100 ký tự)
        if (fullName.length() > 100) {
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        // Password phải có ít nhất 8 ký tự, chứa ít nhất một chữ cái viết hoa, một chữ cái viết thường và một chữ số
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    private boolean validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        // Username phải có độ dài từ 3 đến 20 ký tự, chỉ chứa chữ cái và số, không chứa khoảng trắng
        String usernameRegex = "^[a-zA-Z0-9]{3,20}$";
        return Pattern.matches(usernameRegex, username);
    }

    private boolean isValidPhone(String phone) {
        // Kiểm tra định dạng của số điện thoại bằng biểu thức chính quy
        // ở đây bạn có thể thực hiện kiểm tra theo định dạng cụ thể của số điện thoại trong quốc gia của bạn
        // Ví dụ: "^\\d{10}$" cho 10 chữ số, hoặc kiểm tra có chứa ký tự đặc biệt nào khác không
        if (!phone.matches("^0\\d{9}$")) {
            return false;
        }
        return true;
    }

    private boolean isValidAge(int age) {
        // Kiểm tra xem age có nằm trong khoảng từ 1 đến 150 không (giả sử đây là giới hạn hợp lệ cho tuổi)
        return age >= 2 && age <= 150;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
