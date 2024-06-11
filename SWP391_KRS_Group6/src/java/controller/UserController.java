package controller;

import dao.SettingDAO;
import dao.UserDAO;
import entity.Setting;
import entity.User;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;
import service.SettingService;
import service.UserService;

//@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            SettingDAO settingDAO = new SettingDAO();
            User currentUser = (User) session.getAttribute("account");
            Setting setting = settingDAO.getSettingById(currentUser.getRole_id());
            

            if (currentUser == null) {
                // Nếu không có phiên đăng nhập, chuyển hướng đến trang đăng nhập
                response.sendRedirect("Home");
                return;
            }

            String action = request.getParameter("action");
// Kiểm tra quyền truy cập của người dùng
            if (setting.getSettingName().toLowerCase().equals("admin")) {
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
                    case "sort":
                        getListUser2(request, response);
                        break;
                    case "profileUserPage":
                        getProfileUser(request, response);
                        break;
                    case "userChangePass":
                        changePassUser(request, response);
                        break;
                    case "userChangeInfo":
                        updateUserInfo(request, response);
                        break;
                    case "confirmEmailChange":
                        confirmEmailChange(request, response);
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
                    case "userChangeInfo":
                        updateUserInfo(request, response);
                        break;
                    case "confirmEmailChange":
                        confirmEmailChange(request, response);
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/UserProfile.jsp");
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/AddUser.jsp");
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/UserList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list user", e);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserService userService = new UserService();
            SettingService settingService = new SettingService();
            
            int userId = Integer.parseInt(request.getParameter("id"));
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            String statusParam = request.getParameter("status");
            boolean status = (statusParam != null && statusParam.equals("on"));
            int roleId = Integer.parseInt(request.getParameter("roleId"));

            User user = userService.getUserById(userId);

            if (user != null) {

                user.setUser_name(userName);
                user.setEmail(email);
                user.setFull_name(fullName);
                user.setPhone(phone);
                user.setGender(gender);
                user.setStatus(status);
                user.setRole_id(roleId);
                boolean isUpdated = userService.updateUser(user);

                String message = isUpdated ? "Lưu thành công." : "Cập nhật không thành công.";
                request.setAttribute("successMessage", message);
//                response.sendRedirect(request.getContextPath() + "/userController?id=" + userId);
                // save xong lấy các thông tin vừa lưu để đẩy về jsp(vẫn giữ nguyên data của page không có mất hết data khi call jsp)
                // call cách khác thì truyền id vào không bảo mật tí nào
                List<Setting> roles = settingService.getAllRoles();
                List<User> users = userService.getAllUsers();
                request.setAttribute("users", users);
                request.setAttribute("roles", roles);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/UserList.jsp");
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
            Map<String, String> errors = new HashMap<>();
            UserService userService = new UserService();
            SettingService settingService = new SettingService();
            List<Setting> roles = settingService.getAllRoles();
            int userId = Integer.parseInt(request.getParameter("userid"));
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String reNewPassword = request.getParameter("renewPassword");
            String message;
            User user = userService.getUserById(userId);
//            validatePassword = userService.validatePassword(newPassword);
            if (user != null) {
                boolean isCurrentPass = userService.checkCurrentPassword(user, currentPassword);
                if (isCurrentPass) {
                    if (!newPassword.equals(reNewPassword) || !userService.validatePassword(newPassword) || !userService.validatePassword(reNewPassword)) {
                        message = "Mật khẩu không khớp và phải có ít nhất 8 ký tự, chứa ít nhất một chữ cái viết hoa, một chữ cái viết thường và một chữ số";
                        errors.put("errorsSamePass", message);
                    } else {
//                        user.setPassword(newPassword);
                        boolean isUpdated = userService.changePassword(user, newPassword);
                        message = isUpdated ? "Cập nhật mật khẩu thành công" : "Cập nhật mật khẩu thất bại";
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
            UserService userService = new UserService();
            int userId = Integer.parseInt(request.getParameter("id"));
            String newPassword = request.getParameter("newPassword");
            String reNewPassword = request.getParameter("reNewPassword");
            String message = "";

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserById(userId);

            if (user != null) {
                if (!newPassword.equals(reNewPassword) || !userService.validatePassword(newPassword)) {
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/UserProfile.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid user ID format", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating user.");
        }
    }

    //user update info by user
    private void confirmEmailChange(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String newEmail = request.getParameter("newEmail");
        String otp = request.getParameter("otp");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String ageParam = request.getParameter("age");

        int age;
        if (ageParam == null || ageParam.trim().isEmpty()) {
            age = 1;
        } else {
            age = Integer.parseInt(ageParam);
        }
        Map<String, String> errors = new HashMap<>();
        UserDAO userDAO = new UserDAO();
        SettingDAO settingDAO = new SettingDAO();
        List<Setting> roles = settingDAO.getAllRole();

        if (userDAO.validateOtp(userId, otp)) {
            userDAO.updateUserEmail(userId, newEmail, fullname, phone, gender, age);
            User user = userDAO.getUserById(userId);
            errors.put("Success", "Update thành công");
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
            request.setAttribute("roles", roles);
            request.setAttribute("tab", "edit-profile");
            request.getRequestDispatcher("WEB-INF/ProfileUser.jsp").forward(request, response);
        } else {
            errors.put("Errors", "Hết hạn OTP");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("WEB-INF/ConfirmEmailChange.jsp").forward(request, response);
        }
    }

    private String generateOTP() {
        // Tạo mã OTP ngẫu nhiên
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    private boolean sendOtpToEmail(String email, String otp) {
        String host = "live.smtp.mailtrap.io";
        final String user = "api";
        final String password = "f89b8cfba9f3f07f3f9fc42aa068248a"; // thay thế bằng mật khẩu thực tế từ Mailtrap

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
//            mailtrap@krsg6.com
            message.setFrom(new InternetAddress("mailtrap@krsg6.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("KRS_G6 Change Email");
            message.setText("Your OTP code is: " + otp);
            Transport.send(message);
            System.out.println("OTP email sent successfully...");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void updateUserInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Danh sách các thông báo lỗi
            Map<String, String> errors = new HashMap<>();
            SettingDAO settingDAO = new SettingDAO();
            UserService userService = new UserService();
            List<Setting> roles = settingDAO.getAllRole();
            int userId = Integer.parseInt(request.getParameter("userId"));
            String phone = request.getParameter("phone");
            String fullName = request.getParameter("fullname");
            String newEmail = request.getParameter("email");
            String gender = request.getParameter("gender");
//            String ageParam = request.getParameter("age");
//
//            int age;
//            if (ageParam == null || ageParam.trim().isEmpty()) {
//                age = 1;
//            } else {
//                age = Integer.parseInt(ageParam);
//            }

            UserDAO userDAO = new UserDAO();
            User currentUser = userDAO.getUserById(userId);

            boolean emailChanged = !newEmail.equals(currentUser.getEmail());

            User user = new User(userId, currentUser.getUser_name(), currentUser.getPassword(), newEmail, fullName, phone, gender, currentUser.isStatus(), currentUser.getRole_id(), null);
            //

//            boolean usernameExists = userDAO.checkUsernameExists(userName);
            // Validate các trường
            if (newEmail == null || newEmail.trim().isEmpty() || !userService.validateEmail(newEmail)) {
                errors.put("emailError", "Email không hợp lệ hoặc không được bỏ trống");
            }
            if (!userService.isValidPhone(phone)) {
                errors.put("phoneError", "Số điện thoại có 10 chữ số và bắt đầu bằng số 0");
            }
            if (!userService.isValidFullName(fullName)) {
                errors.put("fullnameError", "Độ dài không quá 100");
            }
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("user", user);
                request.setAttribute("roles", roles);
                request.setAttribute("tab", "edit-profile");
                request.getRequestDispatcher("WEB-INF/ProfileUser.jsp").forward(request, response);
                return;
            }

            if (emailChanged) {
                boolean emailExists = userDAO.checkEmailExists(newEmail);
                if (emailExists) {
                    errors.put("emailDup", "Email đã tồn tại");
                    request.setAttribute("errors", errors);
                    request.setAttribute("user", user);
                    request.setAttribute("roles", roles);
                    request.setAttribute("tab", "edit-profile");
                    request.getRequestDispatcher("WEB-INF/ProfileUser.jsp").forward(request, response);
                } else {
                    // Generate OTP and save it with expiry
                    String otp = generateOTP();
                    Timestamp otpExpiry = new Timestamp(System.currentTimeMillis() + 1 * 60 * 1000); // 1 minutes expiry
                    userDAO.saveOtpForEmailChange(userId, otp, otpExpiry);

                    // Send OTP to the new email
                    sendOtpToEmail(newEmail, otp);

                    // Redirect to an OTP confirmation page
                    request.getSession().setAttribute("userId", userId);
                    request.getSession().setAttribute("newEmail", newEmail);
                    request.getSession().setAttribute("fullname", fullName);
                    request.getSession().setAttribute("phone", phone);
                    request.getSession().setAttribute("gender", gender);
//                    request.getSession().setAttribute("age", age);
//                    response.sendRedirect("WEB-INF/ConfirmEmailChange.jsp");
                    request.getRequestDispatcher("WEB-INF/ConfirmEmailChange.jsp").forward(request, response);
                }
            } else {
                boolean updateSuccessful = userDAO.updateUser(user);
                if (updateSuccessful) {
                    errors.put("Success", "Update thành công");
                    request.setAttribute("errors", errors);
                    request.setAttribute("user", user);
                    request.setAttribute("roles", roles);
                    request.setAttribute("tab", "edit-profile");
                    request.getRequestDispatcher("WEB-INF/ProfileUser.jsp").forward(request, response);
                } else {
                    errors.put("errors", "Update không thành công");
                    request.setAttribute("errors", errors);
                    request.setAttribute("user", user);
                    request.setAttribute("roles", roles);
                    request.setAttribute("tab", "edit-profile");
                    request.getRequestDispatcher("WEB-INF/ProfileUser.jsp").forward(request, response);
                }
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
            SettingDAO settingDAO = new SettingDAO();
            List<Setting> roles = settingDAO.getAllRole();
            UserService userService = new UserService();
            // Danh sách các thông báo lỗi
            Map<String, String> errors = new HashMap<>();
            // Lấy thông tin từ form
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            boolean status = request.getParameter("status") != null;
            String roleIdParam = request.getParameter("roleId");
            int roleId;
            if (roleIdParam == null || roleIdParam.trim().isEmpty()) {
                roleId = 3;
            } else {
                roleId = Integer.parseInt(roleIdParam);
            }
//            String gender = "";
            if (gender == null || gender.isEmpty()) {
                gender = "Other";
            }
            if (status == false) {
                status = true;
            }
            // Tạo một đối tượng User
            User user = new User();
            user.setUser_name(userName);
            user.setPassword(password);
            user.setEmail(email);
            user.setFull_name(fullName);
            user.setPhone(phone);
            user.setGender(gender);
            user.setStatus(status);
            user.setRole_id(roleId);

            UserDAO userDAO = new UserDAO();
            //check email and tên account
            // Kiểm tra email và tên người dùng đã tồn tại hay chưa
            boolean emailExists = userDAO.checkEmailExists(email);
            boolean usernameExists = userDAO.checkUsernameExists(userName);

            // Validate các trường
            if (email == null || email.trim().isEmpty() || !userService.validateEmail(email)) {
                errors.put("emailError", "Email không hợp lệ hoặc không được bỏ trống");
            }

            if (password == null || password.trim().isEmpty() || !userService.validatePassword(password)) {
                errors.put("passError", "Password phải có ít nhất 8 ký tự, chứa ít nhất một chữ cái viết hoa, một chữ cái viết thường và một chữ số.");
            }

            if (userName == null || userName.trim().isEmpty() || !userService.validateUsername(userName)) {
                errors.put("usernameError", "Username phải có độ dài từ 3 đến 20 ký tự, chỉ chứa chữ cái và số, không chứa khoảng trắng.");
            }
            if (emailExists) {
                errors.put("existsErrorEmail", "Email đã tồn tại.");
            }
            if (usernameExists) {
                errors.put("existsErrorUserName", "Username đã tồn tại.");
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
                request.setAttribute("status", status);
                request.setAttribute("roleId", roleId);
                request.getRequestDispatcher("WEB-INF/AddUser.jsp").forward(request, response);
                return;
            }

            // Thêm người dùng vào cơ sở dữ liệu
            boolean isSuccess = userDAO.addUser(user);
            String message = isSuccess ? "Lưu thành công." : "Cập nhật không thành công.";
            if (isSuccess) {
                // Redirect to userList with success message
//                response.sendRedirect(request.getContextPath() + "/userList?successMessage=" + URLEncoder.encode(message, "UTF-8"));
                request.setAttribute("successMessage", message);
                List<User> users = userDAO.getAllUsers();
                request.setAttribute("users", users);
                request.setAttribute("roles", roles);
                request.getRequestDispatcher("WEB-INF/UserList.jsp").forward(request, response);
            } else {
                // Handle failure (optional)
                request.setAttribute("roles", roles);
                request.setAttribute("user_name", userName);
                request.setAttribute("password", password);
                request.setAttribute("email", email);
                request.setAttribute("full_name", fullName);
                request.setAttribute("phone", phone);
                request.setAttribute("gender", gender);
                request.setAttribute("status", status);
                request.setAttribute("roleId", roleId);
                request.setAttribute("successMessage", "Cập nhật không thành công.");
                request.getRequestDispatcher("WEB-INF/AddUser.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            request.setAttribute("existsError", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/AddUser.jsp").forward(request, response);
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

            // Get search and sort parameters
            String searchUsername = request.getParameter("searchUsername");
            String sortField = request.getParameter("sortField");

            if (searchUsername != null && !searchUsername.isEmpty() && sortField != null && !sortField.isEmpty()) {
                // Sort and search
                users = userDAO.getUsersSortedSearchBy(sortField, searchUsername);
            } else if (searchUsername != null && !searchUsername.isEmpty()) {
                // Search only
                users = userDAO.searchUsersByUsername(searchUsername);
            } else if (sortField != null && !sortField.isEmpty()) {
                // Sort only
                users = userDAO.getUsersSortedBy(sortField);
            } else {
                // Default: get all users
                users = userDAO.getAllUsers();
            }

            // Set the list of users as a request attribute
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
            request.setAttribute("searchUsername", searchUsername);
            request.setAttribute("sortField", sortField);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/UserList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting user list", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the user list.");
        }
    }



//    private boolean isValidAge(int age) {
//        // Kiểm tra xem age có nằm trong khoảng từ 1 đến 150 không (giả sử đây là giới hạn hợp lệ cho tuổi)
//        return age >= 2 && age <= 150;
//    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
