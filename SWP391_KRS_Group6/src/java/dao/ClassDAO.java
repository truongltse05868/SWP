
package dao;

import entity.Class;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simon
 */
public class ClassDAO extends DBConnect{
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
