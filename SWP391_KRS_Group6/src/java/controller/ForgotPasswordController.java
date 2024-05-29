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

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
/**
 *
 * @author Simon
 * @author
 */
public class ForgotPasswordController extends HttpServlet {

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
                default:
//                    getUserProfle(request, response);
                    break;
            }
        } catch (Exception e) {

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

            String email = request.getParameter("email");

            // Kiểm tra email có tồn tại trong database
            boolean emailExists = checkEmailExists(email);

            if (emailExists) {
                // Tạo mã OTP và lưu vào database
                String otp = generateOTP();
                saveOtpToDatabase(email, otp);

                // Gửi email OTP
                sendOtpEmail(email, otp);

                // Chuyển hướng đến trang resetPassword.jsp với email
                request.setAttribute("email", email);
                request.setAttribute("message", "OTP đã được gửi đến email của bạn.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ResetPassword.jsp");
                dispatcher.forward(request, response);
            } else {
                // Email không tồn tại
                request.setAttribute("message", "Email không tồn tại.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ResetPassword.jsp");
                dispatcher.forward(request, response);
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
            String email = request.getParameter("email");
            String pass = request.getParameter("newPassword");
            String repass = request.getParameter("reNewPassword");
            if (pass.equals(repass)) {
                String otp = request.getParameter("otp");
                UserDAO password = new UserDAO();
                boolean isUpdated = password.updatePassOTP(repass, email, otp);

                String message = "";
                if (isUpdated != true) {
                    request.setAttribute("successMessage", "OTP không đúng");
                    request.getRequestDispatcher("WEB-INF/ResetPassword.jsp").forward(request, response);
                } else {
                    request.setAttribute("successMessage", "Cập nhật mật khẩu thành công");
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("successMessage", "Mật khẩu không khớp");
                request.getRequestDispatcher("WEB-INF/ResetPassword.jsp").forward(request, response);
            }

//            isUpdated ? "Cập nhật mật khẩu thành công." : "Cập nhật mật khẩu không thành công.";
//            request.setAttribute("successMessage", message);
//            request.getRequestDispatcher("WEB-INF/ResetPassword.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user", e);
        }

    }

    private boolean checkEmailExists(String email) {
        // Kiểm tra email trong database
        // Trả về true nếu tồn tại, false nếu không tồn tại
        // Implement database check logic here
        UserDAO users = new UserDAO();
        List<User> emails = users.getAllUserActive();
        for (User user : emails) {
            if (user.getEmail().equals(email)) {
                return true; // Email tồn tại
            }
        }
        return true; // Giả sử email tồn tại
    }

    private String generateOTP() {
        // Tạo mã OTP ngẫu nhiên
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    private void saveOtpToDatabase(String email, String otp) {
        // Lưu OTP vào database cùng với email
        // Implement database save logic here
        UserDAO emails = new UserDAO();
        emails.saveOtpToDatabase(email, otp);

    }

    private boolean sendOtpEmail(String email, String otp) {
        String host = "live.smtp.mailtrap.io";
        final String user = "api";
        final String password = "7e7cde194e73d40d2d11cf7e36786209"; // thay thế bằng mật khẩu thực tế từ Mailtrap

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
            message.setFrom(new InternetAddress("mailtrap@demomailtrap.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);
            Transport.send(message);
//            try (Transport transport = session.getTransport("smtp")) {
//                transport.connect(host, user, password);
//                transport.sendMessage(message, message.getAllRecipients());
//            }
            System.out.println("OTP email sent successfully...");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

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
