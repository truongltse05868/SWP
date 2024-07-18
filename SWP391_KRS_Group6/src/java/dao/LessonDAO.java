/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Lesson;
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
 * @author T
 */
public class LessonDAO extends DBConnect {

    private static final Logger logger = Logger.getLogger(LessonDAO.class.getName());

    public List<Lesson> getAllLesson() {
        List<Lesson> lessones = new ArrayList<>();
        String query = "SELECT * FROM lesson"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Lesson lesson = new Lesson(
                        rs.getInt("lesson_id"),
                        rs.getInt("subject_id"),
                        rs.getString("title"),
                        rs.getBoolean("status")
                );
                lessones.add(lesson);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all class", e);
        }
        return lessones;
    }

    public int countClasses(String searchLesson, String searchSubjectId) {
        String query = "SELECT COUNT(*) FROM lesson WHERE 1=1";
        if (searchLesson != null && !searchLesson.isEmpty()) {
            query += " AND title LIKE ?";
        }
        if (searchSubjectId != null && !searchSubjectId.isEmpty()) {
            query += " AND subject_id = ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int paramIndex = 1;
            if (searchLesson != null && !searchLesson.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchLesson + "%");
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

    //search lesson
    public List<Lesson> searchAndSortClasses(String searchLesson, String searchSubjectId, String sortField, String sortOrder, int page, int size) {
        String query = "SELECT * FROM lesson WHERE 1=1";
        if (searchLesson != null && !searchLesson.isEmpty()) {
            query += " AND title LIKE ?";
        }
        if (searchSubjectId != null && !searchSubjectId.isEmpty()) {
            query += " AND subject_id = ?";
        }
        query += " ORDER BY " + sortField + " " + sortOrder + " LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int paramIndex = 1;
            if (searchLesson != null && !searchLesson.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchLesson + "%");
            }
            if (searchSubjectId != null && !searchSubjectId.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(searchSubjectId));
            }
            ps.setInt(paramIndex++, size);
            ps.setInt(paramIndex, (page - 1) * size);

            ResultSet rs = ps.executeQuery();
            List<Lesson> lessonList = new ArrayList<>();
            while (rs.next()) {
                Lesson lesson = new Lesson(
                        rs.getInt("lesson_id"),
                        rs.getInt("subject_id"),
                        rs.getString("title"),
                        rs.getBoolean("status")
                );
                lessonList.add(lesson);
            }
            return lessonList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Lesson> searchClasses(String searchLesson, String searchSubjectId, int page, int size) {
        String query = "SELECT * FROM lesson WHERE 1=1";

        if (searchLesson != null && !searchLesson.isEmpty()) {
            query += " AND title LIKE ?";
        }
        if (searchSubjectId != null && !searchSubjectId.isEmpty()) {
            query += " AND subject_id = ?";
        }

        query += " LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int paramIndex = 1;
            if (searchLesson != null && !searchLesson.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchLesson + "%");
            }
            if (searchSubjectId != null && !searchSubjectId.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(searchSubjectId));
            }
            ps.setInt(paramIndex++, size);
            ps.setInt(paramIndex++, (page - 1) * size);

            ResultSet rs = ps.executeQuery();
            List<Lesson> lessonList = new ArrayList<>();
            while (rs.next()) {
                Lesson lesson = new Lesson(rs.getInt("lesson_id"),
                        rs.getInt("subject_id"),
                        rs.getString("title"),
                        rs.getBoolean("status"));
                // Populate class object with data from result set
                lessonList.add(lesson);
            }
            return lessonList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Lesson> sortLesson(String sortField, String sortOrder, int page, int size) {
        // Calculate the offset
        int offset = (page - 1) * size;

        // Update the query to include pagination
        String query = "SELECT * FROM lesson ORDER BY " + sortField + " " + sortOrder + " LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Set the limit and offset parameters
            ps.setInt(1, size);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();
            List<Lesson> lessonList = new ArrayList<>();

            while (rs.next()) {
                Lesson lesson = new Lesson(rs.getInt("lesson_id"),
                        rs.getInt("subject_id"),
                        rs.getString("title"),
                        rs.getBoolean("status"));
                // Populate class object with data from result set
                lessonList.add(lesson);
            }

            return lessonList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public List<Lesson> getAllLesson(int page, int size) {
        String query = "SELECT * FROM lesson LIMIT ? OFFSET ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, size);
            ps.setInt(2, (page - 1) * size);
            ResultSet rs = ps.executeQuery();
            List<Lesson> lessonList = new ArrayList<>();
            while (rs.next()) {
                Lesson lesson = new Lesson(
                        rs.getInt("lesson_id"),
                        rs.getInt("subject_id"),
                        rs.getString("title"),
                        rs.getBoolean("status"));
                // Populate class object with data from result set
                lessonList.add(lesson);
            }
            return lessonList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Lesson getLessonById(int lessonId) {
        Lesson lesson = null;
        String query = "SELECT * FROM lesson WHERE lesson_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lessonId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lesson = new Lesson(
                            rs.getInt("lesson_id"),
                            rs.getInt("subject_id"),
                            rs.getString("title"),
                            rs.getBoolean("status")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user by ID", e);
        }
        return lesson;
    }

    public List<Lesson> getLessonBySubject(int subjectId) {
        Lesson lesson = null;
        List<Lesson> lessonList = new ArrayList<>();
        String query = "SELECT * FROM lesson WHERE subject_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, subjectId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lesson = new Lesson(
                            rs.getInt("lesson_id"),
                            rs.getInt("subject_id"),
                            rs.getString("title"),
                            rs.getBoolean("status")
                    );
                    lessonList.add(lesson);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user by ID", e);
        }
        return lessonList;
    }
}
