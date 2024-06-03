/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author Sunshine
 */
public class RegisterController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            String action = request.getParameter("action");

            if (action == null) {
                action = "default";
            }
            switch (action) {

                case "register":
                    register(request, response);
                    break;
                default:
                    showRegisterPage(request, response);
                    break;
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid user ID format", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing user request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
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
//        processRequest(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Register.jsp");
        dispatcher.forward(request, response);
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

    private void showRegisterPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserDAO userDAO = new UserDAO();
            User userRegister = new User();
            String userName = request.getParameter("username");
            String email = request.getParameter("email");
            String pass = request.getParameter("password");
            String repass = request.getParameter("repassword");
            String otp = generateOTP();
            // Danh sách các thông báo lỗi
            Map<String, String> errors = new HashMap<>();

            // Validate các trường
            if (!validateEmail(email)) {
                errors.put("emailError", "Email không hợp lệ.");
            }

            if (!validatePassword(pass)) {
                errors.put("passError", "Password phải có ít nhất 8 ký tự, chứa ít nhất một chữ cái viết hoa, một chữ cái viết thường và một chữ số.");
            }

            if (!validateUsername(userName)) {
                errors.put("usernameError", "Username phải có độ dài từ 3 đến 20 ký tự, chỉ chứa chữ cái và số, không chứa khoảng trắng.");
            }

            if (userDAO.checkEmailExists(email) || userDAO.checkUsernameExists(userName)) {
                errors.put("existsError", "Email hoặc Username đã tồn tại.");
            }

            if (!pass.equals(repass)) {
                errors.put("repassError", "Password và Repassword không trùng khớp.");
            }

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("username", userName);
                request.setAttribute("email", email);
                request.getRequestDispatcher("WEB-INF/Register.jsp").forward(request, response);
                return;
            }

            userRegister.setUser_name(userName);
            userRegister.setFull_name(userName);
            userRegister.setEmail(email);
            userRegister.setPassword(pass);
            userRegister.setStatus(false);
            userRegister.setRole_id(3);
            userRegister.setOtp(otp);
            boolean regisUser = userDAO.addUserRegister(userRegister);
            if (regisUser) {
                // Tạo liên kết xác nhận
                String confirmationLink = "http://localhost:8080/SWP391_KRS_Group6/EmailConfirm?email=" + email + "&otp=" + otp;

                // Gửi email xác nhận
                sendEmail(email, confirmationLink, userName);

                request.setAttribute("successMessage", "Đăng ký thành công. Vui lòng kiểm tra email để xác nhận.");
//                request.getRequestDispatcher("Login").forward(request, response);
                request.getRequestDispatcher("Login").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "lỗi gì đó chưa check được");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in register", e);
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/Register.jsp").forward(request, response);
        }
    }

    private String generateOTP() {
        // Tạo mã OTP ngẫu nhiên
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    private boolean saveOtpToDatabase(String email, String otp) {
        // Lưu OTP vào database cùng với email
        // Implement database save logic here
        UserDAO emails = new UserDAO();
        boolean isSaveOTP = emails.saveOtpToDatabase(email, otp);
        return isSaveOTP;
    }

    private void sendEmail(String recipientEmail, String confirmationLink, String email) {
        String host = "live.smtp.mailtrap.io";
        final String user = "api";
        final String password = "f89b8cfba9f3f07f3f9fc42aa068248a"; // thay thế bằng mật khẩu thực tế từ Mailtrap

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mailtrap@krsg6.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Email Confirmation");
            message.setText("Dear " + email + ""
                    + "\n\n Please click the following link to confirm your email address: "
                    + confirmationLink);

            Transport.send(message);
            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean validatePassword(String password) {
        // Password phải có ít nhất 8 ký tự, chứa ít nhất một chữ cái viết hoa, một chữ cái viết thường và một chữ số
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    private boolean validateUsername(String username) {
        // Username phải có độ dài từ 3 đến 20 ký tự, chỉ chứa chữ cái và số, không chứa khoảng trắng
        String usernameRegex = "^[a-zA-Z0-9]{3,20}$";
        return Pattern.matches(usernameRegex, username);
    }

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
