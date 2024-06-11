/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.HashMap;
import java.util.Map;
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
}
