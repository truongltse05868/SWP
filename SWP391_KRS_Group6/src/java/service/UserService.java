/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UserDAO;
import entity.User;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.logging.Logger;

/**
 *
 * @author Ngs-MT305
 */
public class UserService extends BaseService {
    private final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+<>?";
//    private static final Logger logger = Logger.getLogger(userDAO.class.getName());
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }
    //validate

    public Map<String, String> validateClassData(String className) {
        Map<String, String> errors = new HashMap<>();
        errors.putAll(validateNotEmpty(className, "blankClassName", "Tên lớp học không được để trống"));
        return errors;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public boolean checkCurrentPassword(User user, String pass) {
        return userDAO.checkCurrentPassword(user, pass);
    }

    public boolean changePassword(User user, String currentPass) {
        return userDAO.changePassword(user, currentPass);
    }

    public boolean validateOTP(int userId, String otp) {
        return userDAO.validateOtp(userId, otp);
    }

    public void updateUserEmail(int userId, String newEmail, String fullname, String phone, String gender) {
        userDAO.updateUserEmail(userId, newEmail, fullname, phone, gender);
    }

    public void saveOtpForEmailChange(int userId, String otp, Timestamp expiry) {
        userDAO.saveOtpForEmailChange(userId, otp, expiry);
    }

    public boolean checkEmailExists(String email) {
        return userDAO.checkEmailExists(email);
    }

    public boolean checkUserNameExists(String userName) {
        return userDAO.checkUsernameExists(userName);
    }

    public boolean checkLogin(String account, String password) {
        return userDAO.loginAccount(account, password);
    }

    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    public List<User> getUsersSortedSearchBy(String field, String search) {
        return userDAO.getUsersSortedSearchBy(field, search);
    }

    public List<User> searchUsersByUsername(String username) {
        return userDAO.searchUsersByUsername(username);
    }

    public List<User> getUsersSortedBy(String field) {
        return userDAO.getUsersSortedBy(field);
    }

    public boolean confirmUser(String email, String otp) {
        return userDAO.confirmUser(email, otp);
    }

//    public boolean saveOtpToDatabase(String email, String otp) {
//        return userDAO.saveOtpToDatabase(email, otp);
//    }
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public boolean updatePassOTP(String password, String email, String otp) {
        return userDAO.updatePassOTP(password, email, otp);
    }

    public boolean saveOtpToDatabase(String email, String otp) {
        // Lưu OTP vào database cùng với email
        // Implement database save logic here
        boolean isSaveOTP = userDAO.saveOtpToDatabase(email, otp);
        return isSaveOTP;
    }

    public boolean addUserRegister(User user) {
        return userDAO.addUserRegister(user);
    }
    

    public String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
}
