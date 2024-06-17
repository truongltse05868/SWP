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
                        rs.getInt("class_id"),
                        rs.getInt("subject_id"),
                        rs.getString("class_name"),
                        rs.getBoolean("status")
                );
                lessones.add(lesson);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all class", e);
        }
        return lessones;
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
