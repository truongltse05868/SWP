/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.websocket.Session;
import dao.UserDAO;
import entity.User;
import java.io.PrintWriter;
//import java.net.PasswordAuthentication;
//import jdk.internal.net.http.websocket.Transport;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Ngs-MT305
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgotPasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPasswordController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("resetPassword.jsp");
            dispatcher.forward(request, response);
        } else {
            // Email không tồn tại
            request.setAttribute("message", "Email không tồn tại.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("resetPassword.jsp");
            dispatcher.forward(request, response);
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

    private void sendOtpEmail(String email, String otp) {
        String host = "live.smtp.mailtrap.io";
        final String user = "api";
        final String password = "your-password"; // thay thế bằng mật khẩu thực tế từ Mailtrap

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);

            try (Transport transport = session.getTransport("smtp")) {
                transport.connect(host, user, password);
                transport.sendMessage(message, message.getAllRecipients());
            }
            System.out.println("OTP email sent successfully...");

        } catch (MessagingException e) {
            e.printStackTrace();
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
