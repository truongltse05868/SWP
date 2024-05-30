package dao;

import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
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
                        rs.getInt("role_id"),
                        rs.getString("otp")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all users", e);
        }
        return users;
    }

    public List<User> checkLogin(String account, String password) {

        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE `user_name` = ? and `password` = ? and status = 1";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, account);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
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
                            rs.getInt("role_id"),
                            rs.getString("otp")
                    );
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Email or password wrong!", e);
        }
        return users;
    }

    public boolean checkEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching email", e);
        }
        return false;
    }

    public boolean checkUsernameExists(String userName) {
        String query = "SELECT COUNT(*) FROM user WHERE user_name = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user_name", e);
        }
        return false;
    }

    public List<User> getAllUserActive() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user where status = 1"; // Ensure table name matches the one in your database
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
                        rs.getInt("role_id"),
                        rs.getString("otp")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all users", e);
        }
        return users;
    }
//update otp by email

    public boolean saveOtpToDatabase(String email, String otp) {
        String query = "UPDATE user SET otp = ?, otp_expiry = ? WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, otp);
            long expiryTime = System.currentTimeMillis() + 1 * 60 * 1000;
            Timestamp otp_expiry = new Timestamp(expiryTime);
            ps.setTimestamp(2, otp_expiry);
            ps.setString(3, email);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            return false;
        }
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
                            rs.getInt("role_id"),
                            rs.getString("otp")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user by ID", e);
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
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
                            rs.getInt("role_id"),
                            rs.getString("otp"),
                            rs.getTimestamp("otp_expiry")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user by ID", e);
        }
        return user;
    }

    public boolean updateUser(User user) {
        String query = "UPDATE user SET user_name = ?, password = ?, email = ?, "
                + "full_name = ?, phone = ?, gender = ?, age = ?, status = ?, role_id = ? WHERE user_id = ?";
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
            ps.setInt(10, user.getUser_id());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            return false;
        }
    }

    public boolean loginAccount(String account, String password) {
        String query = "SELECT * FROM user WHERE `user_name` = ?";
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

    public boolean updatePassOTP(String password, String email, String otp) {
        String query = "UPDATE user SET password = ? WHERE email = ? AND otp = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, password);
            ps.setString(2, email);
            ps.setString(3, otp);
            int rowsAffected = ps.executeUpdate();
            // Kiểm tra số hàng bị ảnh hưởng
            if (rowsAffected > 0) {
                logger.log(Level.INFO, "Password updated successfully for email: {0}", email);
            } else {
                logger.log(Level.WARNING, "No rows updated. Check if email: {0} and otp: {1} are correct.", new Object[]{email, otp});
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error otp", e);
            return false;
        }
    }

    public User checkRoleLogin(String account, String password) {
        User user = null;
        String query = "SELECT * FROM user WHERE `user_name` = ? and `password` = ? and status = 1";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, account);
            ps.setString(2, password);
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
                            rs.getInt("role_id"),
                            rs.getString("otp")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error logging in user", e);
        }
        return user;
    }

}
