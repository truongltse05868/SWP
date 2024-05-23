package dao;

import entity.User;
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
        String query = "SELECT user_name, password FROM user WHERE user_name = ? AND phone = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, account);
            ps.setString(2, password);  
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

}
