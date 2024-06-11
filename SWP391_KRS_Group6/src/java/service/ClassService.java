/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ClassDAO;
import dao.SettingDAO;
import dao.SubjectDAO;
import entity.Setting;
import entity.Subject;
import entity.Class;
import java.lang.System.Logger.Level;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.sql.SQLException;

public class ClassService extends BaseService {
    private static final Logger logger = Logger.getLogger(ClassDAO.class.getName());
    private final ClassDAO classDAO;
    private final SettingDAO settingDAO;
    private final SubjectDAO subjectDAO;

    public ClassService() {
        this.classDAO = new ClassDAO();
        this.settingDAO = new SettingDAO();
        this.subjectDAO = new SubjectDAO();
    }
    //validate
//    public String checkClassNameExsist(String className){
//        return 
//    }
    public Map<String, String> validateClassData(String className) {
        Map<String, String> errors = new HashMap<>();
        errors.putAll(validateNotEmpty(className, "blankClassName", "Tên lớp học không được để trống"));
        
        if (isClassNameExists(className)) {
            errors.put("duplicateClassName", "Tên lớp học đã tồn tại");
        }

        return errors;
    }
     private boolean isClassNameExists(String className) {
         return classDAO.isClassNameExists(className);
    }
    //chọc vào DAO
    public boolean addClass(Class newClass) {
        return classDAO.addClass(newClass);
    }

    public List<Setting> getAllRoles() {
        return settingDAO.getAllRole();
    }

    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }

    public List<Class> getAllClasses() {
        return classDAO.getAllClassUser();
    }

    public Class getClassById(int classId){
        return classDAO.getClassById(classId);
    }
    public boolean updateClass(Class newClass){
        return classDAO.updateClass(newClass);
    }
    public Map<Integer, Integer> getUserCountForClasses(){
        return classDAO.getUserCountForClasses();
    }
    
}
