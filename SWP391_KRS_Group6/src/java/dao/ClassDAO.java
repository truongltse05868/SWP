package dao;

import entity.Class;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simon
 */
public class ClassDAO extends DBConnect {

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    //get all class
    /**
     * Fetches all users from the database.
     *
     * @return a list of User objects
     */
    public List<Class> getAllClass() {
        List<Class> classList = new ArrayList<>();
        String query = "SELECT * FROM class"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Class classs = new Class(
                        rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status")
                );
                classList.add(classs);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all class", e);
        }
        return classList;
    }

    public List<Class> getAllClassUser() {
        List<Class> classList = new ArrayList<>();
        String query = "SELECT c.*, COUNT(cu.user_id) AS user_count "
                + "FROM class AS c "
                + "LEFT JOIN class_user AS cu ON c.class_id = cu.class_id "
                + "GROUP BY c.class_id, c.class_name;"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Class classs = new Class(
                        rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status")
                );
                classList.add(classs);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all class", e);
        }
        return classList;
    }

    public Map<Integer, Integer> getUserCountForClasses() {
        Map<Integer, Integer> userCountMap = new HashMap<>();
        String sql = "SELECT class_id, COUNT(user_id) AS user_count FROM class_user GROUP BY class_id";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                userCountMap.put(rs.getInt("class_id"), rs.getInt("user_count"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all class", e);
        }
        return userCountMap;
    }

    //add class
    public boolean addClass(Class newClass) {
        String sql = "INSERT INTO class (class_name, subject_id, status) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newClass.getClass_name());
            ps.setInt(2, newClass.getSubject_id());
            ps.setBoolean(3, newClass.isStatus());
//            statement.executeUpdate();
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                logger.log(Level.INFO, "class added successfully");
                return true;
            } else {
                logger.log(Level.WARNING, "No rows affected");
                return false;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all class", e);
            return false;
        }
    }

    public boolean updateClass(Class newClass) {
         String query = "UPDATE class SET subject_id = ?,  status = ?, class_name = ? WHERE class_id = ?";
         try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, newClass.getSubject_id());
            ps.setBoolean(2, newClass.isStatus());
            ps.setString(3, newClass.getClass_name());
            ps.setInt(4, newClass.getClass_id());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            return false;
        }
    }

    public Class getClassById(int classId) {
        Class classes = null;
        String query = "SELECT * FROM class WHERE class_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, classId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    classes = new Class(
                            rs.getInt("class_id"),
                            rs.getInt("subject_id"),
                            rs.getString("class_name"),
                            rs.getBoolean("status")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user by ID", e);
        }
        return classes;
    }

    public List<Class> getClassByUserId(int userId) {

        List<Class> classList = new ArrayList<>();
        String query = "SELECT c.class_id, c.subject_id, c.status "
                + "FROM class_user cu "
                + "JOIN class c "
                + "ON cu.class_id = c.class_id WHERE cu.user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Class classes = new Class(
                            rs.getInt("class_id"),
                            rs.getInt("subject_id"),
                            rs.getBoolean("status")
                    );
                    classList.add(classes);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Email or password wrong!", e);
        }
        return classList;
    }

}
