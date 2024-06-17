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
}
