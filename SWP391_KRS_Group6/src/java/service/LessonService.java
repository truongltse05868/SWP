/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.LessonDAO;
import entity.Lesson;
import java.util.List;
import java.util.logging.Logger;


public class LessonService extends BaseService{
    
    private static final Logger logger = Logger.getLogger(LessonDAO.class.getName());
    private final LessonDAO lessonDAO;

    public LessonService() {
        this.lessonDAO = new LessonDAO();
    }
    public List<Lesson> getAllLesson(){
        return lessonDAO.getAllLesson();
    }
    public List<Lesson> getAllLessonBySubject(int subjectId){
        return lessonDAO.getLessonBySubject(subjectId);
    }
    public Lesson getLessonById(int lessonId){
        return lessonDAO.getLessonById(lessonId);
    }
    public int countLessones(String searchLesson, String searchSubjectId) {
        return lessonDAO.countClasses(searchLesson, searchSubjectId);
    }
    public List<Lesson> searchAndSortLesson(String searchLesson, String searchSubjectId, String sortField, String sortOrder, int page, int size) {
        return lessonDAO.searchAndSortClasses(searchLesson, searchSubjectId, sortField, sortOrder, page, size);
    }
    public List<Lesson> searchLesson(String searchLesson, String searchSubjectId, int page, int size) {
        return lessonDAO.searchClasses(searchLesson, searchSubjectId, page, size);
    }
    public List<Lesson> sortLesson(String sortField, String sortOrder, int page, int size) {
        return lessonDAO.sortLesson(sortField, sortOrder, page, size);
    }
    public List<Lesson> getAllLesson(int page, int size) {
        return lessonDAO.getAllLesson(page, size);
    }
}
