/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.SubjectDAO;
import entity.Subject;
import java.util.List;
import java.util.logging.Logger;

public class SubjectService extends BaseService {

    private static final Logger logger = Logger.getLogger(SubjectDAO.class.getName());
    private final SubjectDAO subjectDAO;

    public SubjectService() {
        this.subjectDAO = new SubjectDAO();
    }

    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }
}
