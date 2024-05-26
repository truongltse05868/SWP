package dao;

import entity.Role;
import entity.User;
import entity.UserList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UserDAO handles all database operations related to the User entity.
 */
public class UserDAO extends DBConnect {

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public UserDAO() {
        super(); // Gọi constructor của superclass để khởi tạo kết nối
    }

    /**
     * Fetches all users from the database.
     *
     * @return a list of User objects
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all users", e);
        }
        return users;
    }

    public List<UserList> getAllUsersWithRole() {
        List<UserList> usersl = new ArrayList<>();
        String query = "SELECT u.user_id, u.user_name,u.password , u.email, u.full_name, u.phone, u.gender, u.age, u.status, s.setting_name\n"
                + "FROM user u\n"
                + "JOIN setting s ON s.setting_id = u.role_id;"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UserList userl = new UserList(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getBoolean("status"),
                        rs.getString("setting_name")
                );
//                User user = new User(
//                        rs.getInt("user_id"),
//                        rs.getString("user_name"),
//                        rs.getString("password"),
//                        rs.getString("email"),
//                        rs.getString("full_name"),
//                        rs.getString("phone"),
//                        rs.getString("gender"),
//                        rs.getInt("age"),
//                        rs.getBoolean("status"),
//                        rs.getInt("role_id")
//                );
                usersl.add(userl);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all users", e);
        }
        return usersl;
    }

    /**
     * Fetches a user by ID from the database.
     *
     * @param userId the ID of the user to fetch
     * @return a User object, or null if not found
     */
    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM user WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("phone"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getBoolean("status"),
                            rs.getInt("role_id")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user by ID", e);
        }
        return user;
    }

    public boolean updateUser(User user) {
        String query = "UPDATE user SET full_name=?, phone=? WHERE user_id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getFull_name());
            ps.setString(2, user.getPhone());
            ps.setInt(3, user.getUser_id());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            return false;
        }
    }

    public boolean loginAccount(String account, String password) {
        String query = "SELECT user_name, password FROM user WHERE user_name = ? ";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, account);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String checkAccount = rs.getString("user_name");
                    String checkPass = rs.getString("password");
                    if (checkPass.equals(password) && checkAccount.equals(account)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error logging in user", e);
        }
        return false;
    }

    public boolean addUser(User user) {
        String query = "INSERT INTO user (user_name, password, email, full_name, phone, gender, age, status, role_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getUser_name());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFull_name());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getGender());
            ps.setInt(7, user.getAge());
            ps.setBoolean(8, user.isStatus());
            ps.setInt(9, user.getRole_id());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                logger.log(Level.INFO, "User added successfully");
                return true;
            } else {
                logger.log(Level.WARNING, "No rows affected");
                return false;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding user", e);
            return false;
        }
    }

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT setting_id, setting_name FROM setting WHERE type = 'User role'";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Role role = new Role(
                        rs.getInt("setting_id"),
                        rs.getString("setting_name")
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching roles", e);
        }
        return roles;
    }

}
