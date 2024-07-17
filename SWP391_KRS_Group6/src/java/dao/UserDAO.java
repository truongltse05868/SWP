package dao;

import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UserDAO handles all database operations related to the User entity.
 */
public class UserDAO extends DBConnect {

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

//    public UserDAO() {
//        super(); // Gọi constructor của superclass để khởi tạo kết nối
//    }
    /**
     * Fetches all users from the database.
     *
     * @param classId
     * @return a list of User objects
     */
    public List<User> getAllUsersInClass(int classId) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.user_id, u.user_name, u.password, u.email, u.full_name, u.phone, u.gender, u.status, u.role_id, u.otp "
                + "FROM class_user cu "
                + "JOIN user u ON cu.user_id = u.user_id "
                + "WHERE cu.class_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, classId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp")
                );
                // Populate user object
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting user in class by " + classId, e);
        }
        return users;
    }

    public List<User> searchAndSortUsersNotInClass(int classId, int page, int pageSize, String searchQuery, String sortField, String sortOrder) {
        List<User> users = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT u.user_id, u.user_name, u.password, u.email, u.full_name, u.phone, u.gender, u.status, u.role_id, u.otp ")
                .append("FROM user u ")
                .append("LEFT JOIN class_user cu ON u.user_id = cu.user_id AND cu.class_id = ? ")
                .append("WHERE (cu.class_id IS NULL OR cu.status = false) ")
                .append("AND u.role_id <> 1 ");

        if (searchQuery != null && !searchQuery.isEmpty()) {
            query.append("AND (u.user_name LIKE ? OR u.full_name LIKE ? OR u.email LIKE ?) ");
        }
        query.append("ORDER BY ").append(sortField).append(" ").append(sortOrder).append(" ");
        query.append("LIMIT ? OFFSET ?;");

        int offset = (page - 1) * pageSize;

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;
            ps.setInt(paramIndex++, classId);

            if (searchQuery != null && !searchQuery.isEmpty()) {
                String searchPattern = "%" + searchQuery + "%";
                ps.setString(paramIndex++, searchPattern);
                ps.setString(paramIndex++, searchPattern);
                ps.setString(paramIndex++, searchPattern);
            }

            ps.setInt(paramIndex++, pageSize);    // Limit
            ps.setInt(paramIndex++, offset);      // Offset

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("phone"),
                            rs.getString("gender"),
                            rs.getBoolean("status"),
                            rs.getInt("role_id"),
                            rs.getString("otp")
                    );
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error searching and sorting users not in class with ID " + classId, e);
        }

        return users;
    }

    public List<User> searchUsersNotInClass(int classId, int page, int pageSize, String searchQuery) {
        List<User> users = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT u.user_id, u.user_name, u.password, u.email, u.full_name, u.phone, u.gender, u.status, u.role_id, u.otp ")
                .append("FROM user u ")
                .append("LEFT JOIN class_user cu ON u.user_id = cu.user_id AND cu.class_id = ? ")
                .append("WHERE (cu.class_id IS NULL OR cu.status = false) ")
                .append("AND u.role_id <> 1 ");

        if (searchQuery != null && !searchQuery.isEmpty()) {
            query.append("AND (u.user_name LIKE ? OR u.full_name LIKE ? OR u.email LIKE ?) ");
        }
        query.append("LIMIT ? OFFSET ?;");

        int offset = (page - 1) * pageSize;

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;
            ps.setInt(paramIndex++, classId);

            if (searchQuery != null && !searchQuery.isEmpty()) {
                String searchPattern = "%" + searchQuery + "%";
                ps.setString(paramIndex++, searchPattern);
                ps.setString(paramIndex++, searchPattern);
                ps.setString(paramIndex++, searchPattern);
            }

            ps.setInt(paramIndex++, pageSize);    // Limit
            ps.setInt(paramIndex++, offset);      // Offset

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("phone"),
                            rs.getString("gender"),
                            rs.getBoolean("status"),
                            rs.getInt("role_id"),
                            rs.getString("otp")
                    );
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error searching users not in class with ID " + classId, e);
        }

        return users;
    }

    public List<User> sortUsersNotInClass(int classId, int page, int pageSize, String sortField, String sortOrder) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.user_id, u.user_name, u.password, u.email, u.full_name, u.phone, u.gender, u.status, u.role_id, u.otp "
                + "FROM user u "
                + "LEFT JOIN class_user cu ON u.user_id = cu.user_id AND cu.class_id = ? "
                + "WHERE (cu.class_id IS NULL OR cu.status = false) "
                + "AND u.role_id <> 1 "
                + "ORDER BY " + sortField + " " + sortOrder + " "
                + "LIMIT ? OFFSET ?;";

        int offset = (page - 1) * pageSize;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, classId);
            ps.setInt(2, pageSize);    // Limit
            ps.setInt(3, offset);      // Offset

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("phone"),
                            rs.getString("gender"),
                            rs.getBoolean("status"),
                            rs.getInt("role_id"),
                            rs.getString("otp")
                    );
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error sorting users not in class with ID " + classId, e);
        }

        return users;
    }

    public List<User> getAllUsersNotInClass(int classId, int page, int pageSize) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.user_id, u.user_name, u.password, u.email, u.full_name, u.phone, u.gender, u.status, u.role_id, u.otp "
                + "FROM user u "
                + "LEFT JOIN class_user cu ON u.user_id = cu.user_id AND cu.class_id = ? "
                + "WHERE (cu.class_id IS NULL OR cu.status = false) "
                + "AND u.role_id <> 1 "
                + "LIMIT ? OFFSET ?;";

        int offset = (page - 1) * pageSize;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, classId);
            ps.setInt(2, pageSize);    // Limit
            ps.setInt(3, offset);      // Offset

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("phone"),
                            rs.getString("gender"),
                            rs.getBoolean("status"),
                            rs.getInt("role_id"),
                            rs.getString("otp")
                    );
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting users not in class with ID " + classId, e);
        }

        return users;
    }

    public int countUsersNotInClass(int classId, String searchQuery) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) ")
                .append("FROM user u ")
                .append("LEFT JOIN class_user cu ON u.user_id = cu.user_id AND cu.class_id = ? ")
                .append("WHERE (cu.class_id IS NULL OR cu.status = false) ")
                .append("AND u.role_id <> 1 ");

        if (searchQuery != null && !searchQuery.isEmpty()) {
            query.append("AND (u.user_name LIKE ? OR u.full_name LIKE ? OR u.email LIKE ?) ");
        }

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;
            ps.setInt(paramIndex++, classId);

            if (searchQuery != null && !searchQuery.isEmpty()) {
                String searchPattern = "%" + searchQuery + "%";
                ps.setString(paramIndex++, searchPattern);
                ps.setString(paramIndex++, searchPattern);
                ps.setString(paramIndex++, searchPattern);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error counting users not in class with ID " + classId, e);
        }

        return 0;
    }

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
                        //                        rs.getInt("age"),
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

    public List<User> getAllUsersForDashboard(int status) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user where status =?"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        //                        rs.getInt("age"),
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
// Method to get users by username

    public List<User> searchUsersByUsername(String username) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE user_name LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + username + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        //                        rs.getInt("age"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp")
                );
                // Populate user object
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error searching users by username", e);
        }
        return users;
    }

    public List<User> getUsersSortedBy(String field) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user ORDER BY " + field;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        //                        rs.getInt("age"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp")
                );
                // Populate user object
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error sorting users by " + field, e);
        }
        return users;
    }

    public List<User> getUsersSortedSearchBy(String field, String search) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user where user_name like ? ORDER BY " + field;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        //                        rs.getInt("age"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp")
                );
                // Populate user object
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error sorting users by " + field, e);
        }
        return users;
    }

    public List<User> checkLogin(String identifier, String password) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE (user_name = ? OR email = ?) AND password = ? AND status = 1";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            ps.setString(3, password);
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
                            rs.getBoolean("status"),
                            rs.getInt("role_id"),
                            rs.getString("otp")
                    );
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Username or password wrong!", e);
        }
        return users;
    }

    public boolean checkEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM user WHERE email = ? and status = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setBoolean(2, true);
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
        String query = "SELECT COUNT(*) FROM user WHERE user_name = ? and status = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userName);
            ps.setBoolean(2, true);
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
                        //                        rs.getInt("age"),
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

    public List<User> getUsersByRole(int roleId) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user where role_id = ? and status = 1"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp")
                );
                // Populate user object
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all users", e);
        }
        return users;
    }
//update otp by userId

    public void saveOtpForEmailChange(int userId, String otp, Timestamp expiry) {
        String query = "UPDATE user SET otp = ?, otp_expiry = ? WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, otp);
            ps.setTimestamp(2, expiry);
            ps.setInt(3, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saving OTP for email change", e);
        }
    }

    public boolean validateOtp(int userId, String otp) {
        String query = "SELECT otp, otp_expiry FROM user WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String savedOtp = rs.getString("otp");
                Timestamp otpExpiry = rs.getTimestamp("otp_expiry");
                Timestamp now = new Timestamp(System.currentTimeMillis());

                if (otp.equals(savedOtp) && now.before(otpExpiry)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error validating OTP", e);
        }
        return false;
    }

    public void updateUserEmail(int userId, String newEmail, String fullname, String phone, String gender) {
        String query = "UPDATE user SET email = ?, full_name = ?, phone = ?, gender = ?, otp = NULL WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newEmail);
            ps.setString(2, fullname);
            ps.setString(3, phone);
            ps.setString(4, gender);
            ps.setInt(5, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user email", e);
        }
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
                            //                            rs.getInt("age"),
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

    public User getUserByUserName(String userName) {
        User user = null;
        String query = "SELECT * FROM user WHERE user_name = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userName);
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
                            //                            rs.getInt("age"),
                            rs.getBoolean("status"),
                            rs.getInt("role_id"),
                            rs.getString("otp")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user by user_name", e);
        }
        return user;
    }

    public boolean addUserRegister(User user) {
        String query = "INSERT INTO user (user_name, password,full_name, email, status, role_id, otp, otp_expiry) "
                + "VALUES (?, ?, ? , ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getUser_name());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFull_name());
            ps.setString(4, user.getEmail());
            ps.setBoolean(5, user.isStatus());
            ps.setInt(6, user.getRole_id()); //mặc định là student
            ps.setString(7, user.getOtp());
            long expiryTime = System.currentTimeMillis() + 5 * 60 * 1000;
            Timestamp otp_expiry = new Timestamp(expiryTime);
            ps.setTimestamp(8, otp_expiry);
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

    public boolean confirmUser(String email, String otp) {
        // Tạo một timestamp cho thời điểm hiện tại
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        String query = "UPDATE user SET status = ? WHERE email = ? and otp = ? and otp_expiry > ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, true);
            ps.setString(2, email);
            ps.setString(3, otp);
            ps.setTimestamp(4, currentTime);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                logger.log(Level.INFO, "User confirm successfully");
                return true;
            } else {
                logger.log(Level.WARNING, "No rows affected or OTP expired");
                return false;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error user confirm", e);
            return false;
        }
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
                            //                            rs.getInt("age"),
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

    public List<User> getUserByColumn(String column, String value) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE " + column + " = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, value);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("phone"),
                            rs.getString("gender"),
                            //                            rs.getInt("age"),
                            rs.getBoolean("status"),
                            rs.getInt("role_id"),
                            rs.getString("otp"),
                            rs.getTimestamp("otp_expiry")
                    );
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching users by column", e);
        }
        return users;
    }

    public boolean updateUser(User user) {
        String query = "UPDATE user SET user_name = ?, password = ?, email = ?, "
                + "full_name = ?, phone = ?, gender = ?, status = ?, role_id = ? WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getUser_name());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFull_name());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getGender());
//            ps.setInt(7, user.getAge());
            ps.setBoolean(7, user.isStatus());
            ps.setInt(8, user.getRole_id());
            ps.setInt(9, user.getUser_id());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            return false;
        }
    }

    public boolean changePassword(User user, String currentPassword) {
        String query = "UPDATE user SET password = ? WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, currentPassword);
            ps.setInt(2, user.getUser_id());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating password", e);
            return false;
        }
    }

    public boolean checkCurrentPassword(User user, String currentPass) {
        String query = "Select * from user WHERE user_id = ? and status = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, user.getUser_id());
            ps.setBoolean(2, user.isStatus());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String pass = rs.getString("password");
                    if (pass.equals(currentPass)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user by ID", e);
            return false;
        }
        return false;
    }

    public boolean loginAccount(String account, String password) {
        String query = "SELECT * FROM user WHERE `user_name` = ? and status = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, account);
            ps.setBoolean(2, true); // chỉ lấy accout active
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
        String query = "INSERT INTO user (user_name, password, email, full_name, phone, gender, status, role_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getUser_name());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFull_name());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getGender());
//            ps.setInt(7, user.getAge());
            ps.setBoolean(7, user.isStatus());
            ps.setInt(8, user.getRole_id());

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
        String query = "UPDATE user SET password = ?, status = ? WHERE email = ? AND otp = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, password);
            ps.setBoolean(2, true);
            ps.setString(3, email);
            ps.setString(4, otp);
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
        String query = "SELECT * FROM user WHERE `user_name` = ? and `password` = ?";
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
                            //                            rs.getInt("age"),
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
    // class 

    public List<User> getAllUserInClass(int class_id) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.* FROM class_user as cu "
                + "inner JOIN user as u "
                + "ON cu.user_id= u.user_id "
                + "where cu.class_id = ? and cu.status = 1 and u.status = 1;"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, class_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("phone"),
                            rs.getString("gender"),
                            rs.getBoolean("status"),
                            rs.getInt("role_id"),
                            rs.getString("otp")
                    );
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all ", e);
        }
        return users;
    }

    public List<User> getUsersInClass(int classId, int page, int size, String searchQuery, String sortField, String sortOrder) {
        int offset = (page - 1) * size;

        // Base query
        String query = "SELECT u.* FROM class_user as cu "
                + "INNER JOIN user as u ON cu.user_id = u.user_id "
                + "WHERE cu.class_id = ? AND cu.status = 1 AND u.status = 1 ";

        // Search filter
        if (searchQuery != null && !searchQuery.isEmpty()) {
            query += "AND (u.user_name LIKE ? OR u.email LIKE ?) ";
        }

        // Sorting
        query += "ORDER BY " + sortField + " " + sortOrder + " ";

        // Pagination
        query += "LIMIT ? OFFSET ?";

        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int paramIndex = 1;
            ps.setInt(paramIndex++, classId);

            if (searchQuery != null && !searchQuery.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchQuery + "%");
                ps.setString(paramIndex++, "%" + searchQuery + "%");
            }

            ps.setInt(paramIndex++, size);
            ps.setInt(paramIndex, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("full_name"),
                            rs.getString("phone"),
                            rs.getString("gender"),
                            rs.getBoolean("status"),
                            rs.getInt("role_id"),
                            rs.getString("otp")
                    );
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching users in class", e);
        }
        return users;
    }

    public int getUserCountInClass(int classId, String searchQuery) {
        String query = "SELECT COUNT(*) FROM class_user as cu "
                + "INNER JOIN user as u ON cu.user_id = u.user_id "
                + "WHERE cu.class_id = ? AND cu.status = 1 AND u.status = 1 ";

        if (searchQuery != null && !searchQuery.isEmpty()) {
            query += "AND (u.user_name LIKE ? OR u.email LIKE ?)";
        }

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int paramIndex = 1;
            ps.setInt(paramIndex++, classId);

            if (searchQuery != null && !searchQuery.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchQuery + "%");
                ps.setString(paramIndex, "%" + searchQuery + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user count in class", e);
        }

        return 0;
    }

    //end of class
    public boolean isUserNameExists(String userName, int userId) {
        String query = "SELECT COUNT(*) FROM user WHERE user_name = ? AND user_id <> ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userName);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error user name is exists", e);
        }
        return false;
    }

    public boolean isEmailExists(String email, int userId) {
        String query = "SELECT COUNT(*) FROM user WHERE email = ? AND user_id <> ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error email is exists", e);
        }
        return false;
    }

    public List<User> searchAndSortUsersInClass(int classId, String searchUserName, String searchRoleId, String sortField, String sortOrder, int page, int size) {
        String query = "SELECT u.user_id, u.user_name, u.password, u.email, u.full_name, u.phone, u.gender, u.age, u.status, u.role_id, u.otp, u.otp_expiry "
                + "FROM class_user cu "
                + "JOIN user u ON cu.user_id = u.user_id "
                + "WHERE cu.class_id = ? AND cu.status = 1 AND u.status = 1";

        if (searchUserName != null && !searchUserName.isEmpty()) {
            query += " AND u.user_name LIKE ?";
        }
        if (searchRoleId != null && !searchRoleId.isEmpty()) {
            query += " AND u.role_id = ?";
        }
        query += " ORDER BY " + sortField + " " + sortOrder + " LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int paramIndex = 1;
            ps.setInt(paramIndex++, classId);
            if (searchUserName != null && !searchUserName.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchUserName + "%");
            }
            ps.setInt(paramIndex++, size);
            ps.setInt(paramIndex, (page - 1) * size);

            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<User> getAllUserInClass(int classId, int page, int size) {
        String query = "SELECT u.user_id, u.user_name, u.password, u.email, u.full_name, u.phone, u.gender, u.status, u.role_id, u.otp "
                + "FROM class_user cu JOIN user u ON cu.user_id = u.user_id "
                + "WHERE cu.class_id = ? AND cu.status = 1 AND u.status = 1 LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, classId);
            ps.setInt(2, size);
            ps.setInt(3, (page - 1) * size);
            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public int countUsersInClass(int classId, String searchUserName, String searchRoleId) {
        String query = "SELECT COUNT(*) FROM class_user cu "
                + "JOIN user u ON cu.user_id = u.user_id "
                + "WHERE cu.class_id = ? AND cu.status = 1 AND u.status = 1";

        List<Object> params = new ArrayList<>();
        params.add(classId);

        if (searchUserName != null && !searchUserName.isEmpty()) {
            query += " AND u.user_name LIKE ?";
            params.add("%" + searchUserName + "%");
        }

        if (searchRoleId != null && !searchRoleId.isEmpty()) {
            query += " AND u.role_id = ?";
            params.add(searchRoleId);
        }

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Set parameters
            int paramIndex = 1;
            for (Object param : params) {
                if (param instanceof String) {
                    ps.setString(paramIndex++, (String) param);
                } else if (param instanceof Integer) {
                    ps.setInt(paramIndex++, (Integer) param);
                }
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<User> sortUsersInClass(int classId, String sortField, String sortOrder, int page, int size) {
        // Calculate the offset
        int offset = (page - 1) * size;

        // Update the query to include pagination
        String query = "SELECT u.user_id, u.user_name, u.password, u.email, u.full_name, u.phone, u.gender, u.age, u.status, u.role_id, u.otp, u.otp_expiry "
                + "FROM class_user cu "
                + "JOIN user u ON cu.user_id = u.user_id "
                + "WHERE cu.class_id = ? AND cu.status = 1 AND u.status = 1 "
                + "ORDER BY " + sortField + " " + sortOrder + " LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Set the parameters
            ps.setInt(1, classId);
            ps.setInt(2, size);
            ps.setInt(3, offset);

            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList<>();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp")
                );
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public List<User> searchUsersInClass(int classId, String searchUserName, String searchRoleId, int page, int size) {
        String query = "SELECT u.user_id, u.user_name, u.password, u.email, u.full_name, u.phone, u.gender, u.age, u.status, u.role_id, u.otp, u.otp_expiry "
                + "FROM class_user cu "
                + "JOIN user u ON cu.user_id = u.user_id "
                + "WHERE cu.class_id = ? AND cu.status = 1 AND u.status = 1";

        List<Object> params = new ArrayList<>();
        params.add(classId);

        if (searchUserName != null && !searchUserName.isEmpty()) {
            query += " AND u.user_name LIKE ?";
            params.add("%" + searchUserName + "%");
        }

        if (searchRoleId != null && !searchRoleId.isEmpty()) {
            query += " AND u.role_id = ?";
            params.add(searchRoleId);
        }

        query += " LIMIT ? OFFSET ?";
        params.add(size);
        params.add((page - 1) * size);

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Set parameters
            int paramIndex = 1;
            for (Object param : params) {
                if (param instanceof String) {
                    ps.setString(paramIndex++, (String) param);
                } else if (param instanceof Integer) {
                    ps.setInt(paramIndex++, (Integer) param);
                }
            }

            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp")
                );
                // Populate user object with data from result set
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public int countUsers(String searchUsername) {
        String query = "SELECT COUNT(*) FROM user";
        if (searchUsername != null && !searchUsername.isEmpty()) {
            query += " WHERE username LIKE ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            if (searchUsername != null && !searchUsername.isEmpty()) {
                ps.setString(1, "%" + searchUsername + "%");
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error counting users", e);
        }
        return 0;
    }

    public List<User> getUsersSortedSearchBy(String sortField, String sortOrder, String searchUsername, int page, int size) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE username LIKE ? ORDER BY " + sortField + " " + sortOrder + " LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + searchUsername + "%");
            ps.setInt(2, size);
            ps.setInt(3, (page - 1) * size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting sorted and searched users", e);
        }
        return users;
    }

    public List<User> searchUsersByUsername(String searchUsername, int page, int size) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE username LIKE ? LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + searchUsername + "%");
            ps.setInt(2, size);
            ps.setInt(3, (page - 1) * size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error searching users by username", e);
        }
        return users;
    }

    public List<User> getUsersSortedBy(String sortField, String sortOrder, int page, int size) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user ORDER BY " + sortField + " " + sortOrder + " LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, size);
            ps.setInt(2, (page - 1) * size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting sorted users", e);
        }
        return users;
    }

    public List<User> getAllUsers(int page, int size) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, size);
            ps.setInt(2, (page - 1) * size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getBoolean("status"),
                        rs.getInt("role_id"),
                        rs.getString("otp"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting all users with pagination", e);
        }
        return users;
    }

}
