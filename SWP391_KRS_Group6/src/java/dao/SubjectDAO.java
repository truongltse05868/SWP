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
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean addSubject(Subject subject) {
        String sql = "INSERT INTO subject (subject_code, name, description, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, subject.getSubject_code());
            ps.setString(2, subject.getSubject_name());
            ps.setString(3, subject.getDescription());
            ps.setBoolean(4, subject.isStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Search subjects by keyword in title or summary
    public List<Subject> searchCourse(String keyword, String column, String order, int page, int pageSize) {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subject WHERE (title LIKE ? OR summary LIKE ?) AND status =1 "
                + "ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String query = "%" + keyword + "%";
            ps.setString(1, query);
            ps.setString(2, query);
            ps.setInt(3, pageSize);
            ps.setInt(4, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getInt("subject_id"),
                        rs.getString("subject_code"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error searching subjects", ex);
        }
        return subjects;
    }

    // Get all subjects sorted by a specific column with pagination
    public List<Subject> getAllCourseSortedBy(String column, String order, int page, int pageSize) {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subject WHERE status =1 ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getInt("subject_id"),
                        rs.getString("subject_code"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching sorted subjects", ex);
        }
        return subjects;
    }

    public List<Subject> searchSubjects(String keyword, String column, String order, int page, int pageSize) {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subject WHERE title LIKE ? OR summary LIKE ? ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String query = "%" + keyword + "%";
            ps.setString(1, query);
            ps.setString(2, query);
            ps.setInt(3, pageSize);
            ps.setInt(4, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getInt("subject_id"),
                        rs.getString("subject_code"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error searching subjects", ex);
        }
        return subjects;
    }

    // Get all subjects sorted by a specific column with pagination
    public List<Subject> getAllSubjectsSortedBy(String column, String order, int page, int pageSize) {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subject ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getInt("subject_id"),
                        rs.getString("subject_code"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching sorted subjects", ex);
        }
        return subjects;
    }

}
