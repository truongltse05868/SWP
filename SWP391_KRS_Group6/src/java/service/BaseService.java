/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.HashMap;
import java.util.Map;

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
}
