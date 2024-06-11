/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UserDAO;
import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.logging.Logger;

/**
 *
 * @author Ngs-MT305
 */
public class UserService extends BaseService {

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
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }
    public boolean updateUser(User user){
        return userDAO.updateUser(user);
    }
    public User getUserById(int userId){
        return userDAO.getUserById(userId);
    }
    public boolean checkCurrentPassword(User user, String pass){
        return userDAO.checkCurrentPassword(user, pass);
    }
    public boolean changePassword(User user, String currentPass){
        return userDAO.changePassword(user, currentPass);
    }

}
