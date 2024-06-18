/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

public abstract class BaseService {

    // Phương thức kiểm tra xem một chuỗi có null hoặc rỗng không
    public Map<String, String> validateNotEmpty(String value, String fieldName, String errorMessage) {
        Map<String, String> errors = new HashMap<>();
        if (value == null || value.trim().isEmpty()) {
            errors.put(fieldName, errorMessage);
        }
        return errors;
    }

    // Phương thức trả về thông báo thành công hay thất bại
    public String getSuccessMessage(boolean isSuccess) {
        return isSuccess ? "Lưu thành công." : "Cập nhật không thành công.";
    }

    public boolean isValidFullName(String fullName) {
        // Kiểm tra độ dài của fullName (không quá 100 ký tự)
        if (fullName.length() > 100) {
            return false;
        }
        return true;
    }

    public boolean validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        return Pattern.matches(emailRegex, email);
    }

    public boolean validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        // Password phải có ít nhất 8 ký tự, chứa ít nhất một chữ cái viết hoa, một chữ cái viết thường và một chữ số
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    public boolean validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        // Username phải có độ dài từ 3 đến 20 ký tự, chỉ chứa chữ cái và số, không chứa khoảng trắng
        String usernameRegex = "^[a-zA-Z0-9]{3,20}$";
        return Pattern.matches(usernameRegex, username);
    }

    public boolean isValidPhone(String phone) {
        // Check if the phone number is empty or null
        if (phone == null || phone.isEmpty()) {
            return true;
        }
        // Validate the phone number format
        // Example: "^0\\d{9}$" for a 10-digit number starting with 0

        return phone.matches("^0\\d{9}$");
    }

    public String generateOTP() {
        // Tạo mã OTP ngẫu nhiên
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
//
//    public boolean sendOtpToEmail(String email, String otp, String subjectEmail) {
//        String host = "live.smtp.mailtrap.io";
//        final String user = "api";
//        final String password = "f89b8cfba9f3f07f3f9fc42aa068248a"; // thay thế bằng mật khẩu thực tế từ Mailtrap
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(user, password);
//            }
//        });
//
//        try {
//            MimeMessage message = new MimeMessage(session);
////            mailtrap@krsg6.com
//            message.setFrom(new InternetAddress("mailtrap@krsg6.com"));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
//            message.setSubject(subjectEmail);
//            message.setText("Your OTP code is: " + otp);
//            Transport.send(message);
//            System.out.println("OTP email sent successfully...");
//            return true;
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//    }
//
//    public boolean sendPassToEmail(String email, String pass, String subjectEmail) {
//        String host = "live.smtp.mailtrap.io";
//        final String user = "api";
//        final String password = "f89b8cfba9f3f07f3f9fc42aa068248a"; // thay thế bằng mật khẩu thực tế từ Mailtrap
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(user, password);
//            }
//        });
//
//        try {
//            MimeMessage message = new MimeMessage(session);
////            mailtrap@krsg6.com
//            message.setFrom(new InternetAddress("mailtrap@krsg6.com"));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
//            message.setSubject(subjectEmail);
//            message.setText("Your password: " + pass);
//            Transport.send(message);
//            System.out.println("OTP email sent successfully...");
//            return true;
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//    }
//
//    public void sendEmailVerifyLink(String recipientEmail, String confirmationLink, String email) {
//        String host = "live.smtp.mailtrap.io";
//        final String user = "api";
//        final String password = "f89b8cfba9f3f07f3f9fc42aa068248a"; // thay thế bằng mật khẩu thực tế từ Mailtrap
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(props,
//                new jakarta.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(user, password);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("mailtrap@krsg6.com"));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
//            message.setSubject("Email Confirmation");
//            message.setText("Dear " + email + ""
//                    + "\n\n Please click the following link to confirm your email address: "
//                    + confirmationLink);
//
//            Transport.send(message);
//            System.out.println("Email sent successfully");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static boolean sendEmail(String recipientEmail, String subject, String content) {
        final String host = "live.smtp.mailtrap.io";
        final String user = "api";
        final String password = "f89b8cfba9f3f07f3f9fc42aa068248a"; // Thay thế bằng mật khẩu thực tế từ Mailtrap

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
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mailtrap@krsg6.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("Email sent successfully");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendOtpToEmail(String email, String otp, String subjectEmail) {
        String content = "Your OTP code is: " + otp;
        return sendEmail(email, subjectEmail, content);
    }

    public boolean sendPassToEmail(String email, String userName, String pass, String subjectEmail) {
        String content = "Dear " + userName + ",\n\n"
                + "Welcome to KRS Learning System!\n\n"
                + "Your account has been successfully created by the administrator.\n\n"
                + "Below are your login credentials:\n\n"
                + "Username: " + userName + "\n"
                + "Password: " + pass + "\n\n"
                + "Please use these credentials to login to your account and start using our platform.\n\n"
                + "We recommend changing your password after your first login for security purposes.\n\n"
                + "If you have any questions or need assistance, feel free to contact our support team.\n\n"
                + "Best regards,\n"
                + "KRS Learning System Team";
        return sendEmail(email, subjectEmail, content);
    }

    public void sendEmailVerifyLink(String recipientEmail, String userName, String confirmationLink, String email) {
        String content = "Dear " + userName + "\n\n Please click the following link to confirm your email address: " + confirmationLink;
        sendEmail(recipientEmail, "Email Confirmation", content);
    }

}
