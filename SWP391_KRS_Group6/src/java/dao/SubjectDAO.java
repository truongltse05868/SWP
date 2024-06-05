/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ngs-MT305
 */
public class SubjectDAO extends DBConnect {

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    //get all subject
    /**
     * Fetches all users from the database.
     *
     * @return a list of User objects
     */
    public List<Subject> getAllSubjects() {
        List<Subject> sbjectList = new ArrayList<>();
        String query = "SELECT * FROM subject"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Subject subjects = new Subject(
                        rs.getInt("subject_id"),
                        rs.getString("subject_code"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                );
                sbjectList.add(subjects);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all class", e);
        }
        return sbjectList;
    }

    public boolean updateSubject(Subject subject) {
        String sql = "UPDATE subject\n"
                + "SET subject_code = ?,\n"
                + "    name = ?,\n"
                + "    description = ?,\n"
                + "    status = ?\n"
                + "WHERE subject_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, subject.getSubject_code());
            ps.setString(2, subject.getSubject_name());
            ps.setString(3, subject.getDescription());
            ps.setBoolean(4, subject.isStatus());
            ps.setInt(5, subject.getSubject_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Subject getSubjectById(int subjectId) {
        String sql = "SELECT * FROM subject WHERE subject_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, subjectId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Subject(
                        rs.getInt("subject_id"),
                        rs.getString("subject_code"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
