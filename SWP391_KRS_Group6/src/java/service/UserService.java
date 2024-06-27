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

    public List<User> getAllUsersInClass(int classId) {
        return userDAO.getAllUserInClass(classId);
    }

//    public List<User> getUsersInClass(int classId, int page, int size, String searchQuery, String sortField, String sortOrder) {
//        return userDAO.getUsersInClass(classId, page, size, searchQuery, sortField, sortOrder);
//    }
//
//    public int getUserCountInClass(int classId, String searchQuery) {
//        return userDAO.getUserCountInClass(classId, searchQuery);
//    }
    // Method to search users in a specific class by userName and roleId
    public List<User> searchUsersInClass(int classId, String searchUserName, String searchRoleId, int page, int size) {
        return userDAO.searchUsersInClass(classId, searchUserName, searchRoleId, page, size);
    }

    // Method to count users in a specific class by userName and roleId
    public int countUsersInClass(int classId, String searchUserName, String searchRoleId) {
        return userDAO.countUsersInClass(classId, searchUserName, searchRoleId);
    }

    public List<User> sortUsersInClass(int classId, String sortField, String sortOrder, int page, int size) {
        return userDAO.sortUsersInClass(classId, sortField, sortOrder, page, size);
    }

    public List<User> getAllUserInClass(int classId, int page, int size) {
        return userDAO.getAllUserInClass(classId, page, size);
    }

    public List<User> searchAndSortUsersInClass(int classId, String searchUserName, String searchRoleId, String sortField, String sortOrder, int page, int size) {
        return userDAO.searchAndSortUsersInClass(classId, searchUserName, searchRoleId, sortField, sortOrder, page, size);
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

    public List<User> getUsersByRole(int role) {
        return userDAO.getUsersByRole(role);
    }

    public List<User> getAllUsersNotInClass(int classId, int page, int pageSize) {
        return userDAO.getAllUsersNotInClass(classId, page, pageSize);
    }

    public List<User> sortUsersNotInClass(int classId, int page, int pageSize, String sortField, String sortOrder) {
        return userDAO.sortUsersNotInClass(classId, page, pageSize, sortField, sortOrder);
    }

    public List<User> searchUsersNotInClass(int classId, int page, int pageSize, String searchQuery) {
        return userDAO.searchUsersNotInClass(classId, page, pageSize, searchQuery);
    }
    public List<User> searchAndSortUsersNotInClass(int classId, int page, int pageSize, String searchQuery, String sortField, String sortOrder) {
        return userDAO.searchAndSortUsersNotInClass(classId, page, pageSize,searchQuery, sortField, sortOrder);
    }

    public int countUsersNotInClass(int classId, String searchQuery) {
        return userDAO.countUsersNotInClass(classId, searchQuery);
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

    public boolean isUserNameExists(String userName, int userId) {
        return userDAO.isUserNameExists(userName, userId);
    }

    public boolean isEmailExists(String email, int userId) {
        return userDAO.isEmailExists(email, userId);
    }

    public User getUserByUserName(String userName) {
        return userDAO.getUserByUserName(userName);
    }
}
