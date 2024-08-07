package dao;

import entity.Class;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simon
 */
public class ClassDAO extends DBConnect {

    private static final Logger logger = Logger.getLogger(ClassDAO.class.getName());

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

    public List<Class> getAllClassForDash(int status) {
        List<Class> classList = new ArrayList<>();
        String query = "SELECT * FROM class where status = ?"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
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

    public List<Class> getClassesWithoutUser(int userId, int page, int pageSize) {
        List<Class> classList = new ArrayList<>();
        String query = "SELECT * FROM class WHERE class_id NOT IN (SELECT class_id FROM class_user WHERE user_id = ? AND status = 1) LIMIT ? OFFSET ? ";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, pageSize);
            ps.setInt(3, (page - 1) * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Class classs = new Class(
                            rs.getInt("class_id"),
                            rs.getInt("subject_id"),
                            rs.getString("class_name"),
                            rs.getBoolean("status")
                    );
                    classList.add(classs);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching classes without user", e);
        }
        return classList;
    }

    public int getTotalClassesWithoutUser(int userId) {
        String query = "SELECT COUNT(*) FROM class WHERE class_id NOT IN (SELECT class_id FROM class_user WHERE user_id = ? AND status = 1)";
        int totalClasses = 0;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalClasses = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error counting classes without user", e);
        }

        return totalClasses;
    }

    public List<Class> searchClassAdmin(String keyword, String column, String order, int page, int pageSize) {
        List<Class> classList = new ArrayList<>();
        String sql = "SELECT * FROM class WHERE class_name LIKE ? ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String query = "%" + keyword + "%";

            ps.setString(1, query);
            ps.setInt(2, pageSize);
            ps.setInt(3, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                classList.add(new Class(
                        rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error searching class", ex);
        }
        return classList;
    }

    // Get all subjects sorted by a specific column with pagination
    public List<Class> getAllClassSortedBy(String column, String order, int page, int pageSize) {
        List<Class> classList = new ArrayList<>();
        String sql = "SELECT * FROM class ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                classList.add(new Class(
                        rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching sorted subjects", ex);
        }
        return classList;
    }

    public boolean addUserToClass(User user, int classId) {
        String selectQuery = "SELECT COUNT(*) FROM class_user WHERE user_id = ? AND class_id = ?";
        String insertQuery = "INSERT INTO class_user (user_id, class_id, status) VALUES (?, ?, ?)";
        String updateQuery = "UPDATE class_user SET status = ? WHERE user_id = ? AND class_id = ?";

        try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
            selectStmt.setInt(1, user.getUser_id());
            selectStmt.setInt(2, classId);

            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    // Record exists, update the status
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                        updateStmt.setBoolean(1, true); // Set status to true
                        updateStmt.setInt(2, user.getUser_id());
                        updateStmt.setInt(3, classId);
                        int rowsAffected = updateStmt.executeUpdate();
                        if (rowsAffected > 0) {
                            logger.log(Level.INFO, "Updated user status in class successfully");
                            return true;
                        } else {
                            logger.log(Level.WARNING, "No rows affected during update");
                            return false;
                        }
                    }
                } else {
                    // Record does not exist, insert a new record
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, user.getUser_id());
                        insertStmt.setInt(2, classId);
                        insertStmt.setBoolean(3, true); // Set status to true
                        int rowsAffected = insertStmt.executeUpdate();
                        if (rowsAffected > 0) {
                            logger.log(Level.INFO, "Inserted user into class successfully");
                            return true;
                        } else {
                            logger.log(Level.WARNING, "No rows affected during insert");
                            return false;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding or updating user in class", e);
            return false;
        }
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
        String sql = "SELECT class_id, COUNT(user_id) AS user_count FROM class_user where status = 1 GROUP BY class_id";
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

    public boolean deleteUserInclass(int class_id, int user_id) {
        String query = "UPDATE class_user SET status = 0 WHERE class_id = ? AND user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, class_id);
            ps.setInt(2, user_id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating class_user", e);
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

    public boolean isClassNameExists(String className, int class_id) {
        String query = "SELECT COUNT(*) FROM class WHERE class_name = ? AND class_id <> ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, className);
            ps.setInt(2, class_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "loi roi!", e);
        }
        return false;
    }

    public List<Class> getClassSortedSearchBy(String field, String search) {
        List<Class> classes = new ArrayList<>();
        String query = "SELECT * FROM class where class_name like ? ORDER BY " + field;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Class classs = new Class(
                        rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status")
                );
                // Populate user object
                classes.add(classs);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error sorting users by " + field, e);
        }
        return classes;
    }
    // Method to get users by username

    public List<Class> searchUsersByClassName(String className) {
        List<Class> classes = new ArrayList<>();
        String query = "SELECT * FROM class WHERE class_name LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + className + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Class classe = new Class(
                        rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status")
                );
                // Populate user object
                classes.add(classe);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error searching users by username", e);
        }
        return classes;
    }

    public List<Class> getClassSortedBy(String field) {
        List<Class> classes = new ArrayList<>();
        String query = "SELECT * FROM class ORDER BY " + field;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Class classe = new Class(
                        rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status")
                );
                // Populate user object
                classes.add(classe);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error sorting users by " + field, e);
        }
        return classes;
    }

    //search class
    public List<Class> searchAndSortClasses(String searchClassName, String searchSubjectId, String sortField, String sortOrder, int page, int size) {
        String query = "SELECT * FROM class WHERE 1=1";
        if (searchClassName != null && !searchClassName.isEmpty()) {
            query += " AND class_name LIKE ?";
        }
        if (searchSubjectId != null && !searchSubjectId.isEmpty()) {
            query += " AND subject_id = ?";
        }
        query += " ORDER BY " + sortField + " " + sortOrder + " LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int paramIndex = 1;
            if (searchClassName != null && !searchClassName.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchClassName + "%");
            }
            if (searchSubjectId != null && !searchSubjectId.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(searchSubjectId));
            }
            ps.setInt(paramIndex++, size);
            ps.setInt(paramIndex, (page - 1) * size);

            ResultSet rs = ps.executeQuery();
            List<Class> classes = new ArrayList<>();
            while (rs.next()) {
                Class classs = new Class(
                        rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status")
                );
                classes.add(classs);
            }
            return classes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public int countClasses(String searchClassName, String searchSubjectId) {
        String query = "SELECT COUNT(*) FROM class WHERE 1=1";
        if (searchClassName != null && !searchClassName.isEmpty()) {
            query += " AND class_name LIKE ?";
        }
        if (searchSubjectId != null && !searchSubjectId.isEmpty()) {
            query += " AND subject_id = ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int paramIndex = 1;
            if (searchClassName != null && !searchClassName.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchClassName + "%");
            }
            if (searchSubjectId != null && !searchSubjectId.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(searchSubjectId));
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

    public List<Class> sortClasses(String sortField, String sortOrder, int page, int size) {
        // Calculate the offset
        int offset = (page - 1) * size;

        // Update the query to include pagination
        String query = "SELECT * FROM class ORDER BY " + sortField + " " + sortOrder + " LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Set the limit and offset parameters
            ps.setInt(1, size);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();
            List<Class> classes = new ArrayList<>();

            while (rs.next()) {
                Class classs = new Class(rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status"));
                // Populate class object with data from result set
                classes.add(classs);
            }

            return classes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public List<Class> searchClasses(String searchClassName, String searchSubjectId, int page, int size) {
        String query = "SELECT * FROM class WHERE 1=1";

        if (searchClassName != null && !searchClassName.isEmpty()) {
            query += " AND class_name LIKE ?";
        }
        if (searchSubjectId != null && !searchSubjectId.isEmpty()) {
            query += " AND subject_id = ?";
        }

        query += " LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int paramIndex = 1;
            if (searchClassName != null && !searchClassName.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchClassName + "%");
            }
            if (searchSubjectId != null && !searchSubjectId.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(searchSubjectId));
            }
            ps.setInt(paramIndex++, size);
            ps.setInt(paramIndex++, (page - 1) * size);

            ResultSet rs = ps.executeQuery();
            List<Class> classes = new ArrayList<>();
            while (rs.next()) {
                Class classs = new Class(rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status"));
                // Populate class object with data from result set
                classes.add(classs);
            }
            return classes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    //get all class wtih phan trang
    public List<Class> getAllClasses(int page, int size) {
        String query = "SELECT * FROM class LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, size);
            ps.setInt(2, (page - 1) * size);
            ResultSet rs = ps.executeQuery();
            List<Class> classes = new ArrayList<>();
            while (rs.next()) {
                Class classs = new Class(
                        rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status"));
                // Populate class object with data from result set
                classes.add(classs);
            }
            return classes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public int countClasses() {
        String query = "SELECT COUNT(*) FROM class";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
