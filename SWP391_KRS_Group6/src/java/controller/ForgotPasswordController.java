package controller;

import dao.UserDAO;
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
import java.util.List;
//import java.util.Properties;
//import java.util.UUID;
//import jakarta.mail.*;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
//import service.SettingService;
//import service.SubjectService;
import service.UserService;

/**
 *
 * @author Simon
 * @author
 */
public class ForgotPasswordController extends HttpServlet {
//    SettingService settingService = new SettingService();

    UserService userService = new UserService();
//    SubjectService subjectService = new SubjectService();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                action = "default";
            }
            switch (action) {
                case "forgotPassPage":
                    forgotPassPage(request, response);
                    break;
                case "checkEmail":
                    checkMail(request, response);
                    break;
                case "renewpass":
                    updatePassOTP(request, response);
                    break;
                case "reSentOTP":
                    reSentOTP(request, response);
                    break;
                default:
//                    getUserProfle(request, response);
                    break;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error action", e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void checkMail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            String userName = request.getParameter("email");
            String subjectEmail = "KRS_G6 Reset Password";
            // Tạo mã OTP và lưu vào database
            String otp = userService.generateOTP();
            // Kiểm tra email có tồn tại trong database
            boolean emailExists = userService.checkEmailExists(email);
            boolean userNameExists = userService.checkUserNameExists(email);

            if (emailExists) {

                boolean saveSuccess = userService.saveOtpToDatabase(email, otp);
                if (saveSuccess) {

                    // Gửi email OTP
                    userService.sendOtpToEmail(email, otp, subjectEmail);
                    // Chuyển hướng đến trang resetPassword.jsp với email
//                    request.setAttribute("email", email);
                    session.setAttribute("email", email);
                    request.setAttribute("successMessage", "OTP đã được gửi đến email của bạn.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("message", "Lỗi hệ thống OTP");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ResetPassword.jsp");
                    dispatcher.forward(request, response);
                }

            } else if (userNameExists) {
                User user = userService.getUserByUserName(userName);
                boolean saveSuccess = userService.saveOtpToDatabase(user.getEmail(), otp);
                if (saveSuccess) {
                    // Gửi email OTP
                    userService.sendOtpToEmail(user.getEmail(), otp, subjectEmail);
                    // Chuyển hướng đến trang resetPassword.jsp với email
//                    request.setAttribute("email", user.getEmail());
                    session.setAttribute("email", user.getEmail());
                    request.setAttribute("SuccessMessage", "OTP đã được gửi đến email của bạn.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp");
                    dispatcher.forward(request, response);

                } else {
                    request.setAttribute("message", "Lỗi hệ thống OTP");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ResetPassword.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                // Email không tồn tại
                request.setAttribute("message", "Email hoặc UserName không tồn tại.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ResetPassword.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user", e);
        }

    }

    private void reSentOTP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String email = request.getParameter("email");
            String subjectEmail = "KRS_G6 Reset Password";
            // Kiểm tra email có tồn tại trong database

            // Tạo mã OTP và lưu vào database
            String otp = userService.generateOTP();

            boolean saveSuccess = userService.saveOtpToDatabase(email, otp);
            if (saveSuccess) {

                // Chuyển hướng đến trang resetPassword.jsp với email
                request.setAttribute("email", email);
                request.setAttribute("SuccessMessage", "OTP đã được gửi đến email của bạn.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp");
                dispatcher.forward(request, response);
                // Gửi email OTP
                userService.sendOtpToEmail(email, otp, subjectEmail);

            } else {
                request.setAttribute("message", "Lỗi hệ thống OTP");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ResetPassword.jsp");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user", e);
        }

    }

    private void forgotPassPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            request.getRequestDispatcher("WEB-INF/ResetPassword.jsp").forward(request, response);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user", e);
        }

    }

    private void updatePassOTP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            String pass = request.getParameter("newPassword");
            String repass = request.getParameter("reNewPassword");
            String otp = request.getParameter("otp");
            User user = userService.getUserByEmail(email);
            if (pass.equals(repass)) {
                if (user != null && user.getOtp().equals(otp)) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime <= user.getOtp_expiry().getTime()) {
                        boolean isUpdated = userService.updatePassOTP(repass, email, otp);
                        if (isUpdated) {
                            request.setAttribute("successMessage", "Cập nhật mật khẩu thành công");
                            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                        } else {
                            request.setAttribute("message", "Cập nhật mật khẩu không thành công");
                            request.setAttribute("otp", otp);
//                            request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("message", "OTP đã hết hạn");
                        request.setAttribute("otp", otp);
//                        request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("message", "OTP không đúng");
                    request.setAttribute("otp", otp);
//                    request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Mật khẩu không khớp");
                request.setAttribute("otp", otp);
                request.setAttribute("newPassword", pass);
                request.setAttribute("reNewPassword", repass);
//                request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            request.setAttribute("message", "An error occurred: " + e.getMessage());
//            request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp").forward(request, response);
        }
        // No need to forward if already on the correct page
        request.getRequestDispatcher("WEB-INF/ConfirmOTP.jsp").include(request, response);
    }

//    private boolean checkEmailExists(String email) {
//        // Kiểm tra email trong database
//        // Trả về true nếu tồn tại, false nếu không tồn tại
//        // Implement database check logic here
//        UserDAO users = new UserDAO();
//        List<User> emails = users.getAllUserActive();
//        for (User user : emails) {
//            if (user.getEmail().equals(email)) {
//                return true; // Email tồn tại
//            }
//        }
//        return false; // email không tồn tại
//    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
