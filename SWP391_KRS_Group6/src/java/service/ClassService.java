/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ClassDAO;
import entity.Class;
import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ClassService extends BaseService {

    private static final Logger logger = Logger.getLogger(ClassDAO.class.getName());
    private final ClassDAO classDAO;

    public ClassService() {
        this.classDAO = new ClassDAO();
    }

    //validate
    public Map<String, String> validateClassData(String className, int class_id) {
        Map<String, String> errors = new HashMap<>();
        errors.putAll(validateNotEmpty(className, "blankClassName", "Tên lớp học không được để trống"));

        if (isClassNameExists(className, class_id)) {
            errors.put("duplicateClassName", "Tên lớp học đã tồn tại");
        }

        return errors;
    }
    public String getSuccessMessageAddClass(boolean isSuccess, String className) {
        return isSuccess ? "Thêm lớp "+ className + " thành công." : "Thêm lớp " + className + " không thành công.";
    }

    private boolean isClassNameExists(String className, int classId) {
        return classDAO.isClassNameExists(className, classId);
    }

    //chọc vào DAO
    public boolean addClass(Class newClass) {
        return classDAO.addClass(newClass);
    }

    public boolean addUserToClass(User user, int classId) {
        return classDAO.addUserToClass(user, classId);
    }

    public List<Class> getAllClasses() {
        return classDAO.getAllClassUser();
    }

    public Class getClassById(int classId) {
        return classDAO.getClassById(classId);
    }

    public boolean updateClass(Class newClass) {
        return classDAO.updateClass(newClass);
    }

    public Map<Integer, Integer> getUserCountForClasses() {
        return classDAO.getUserCountForClasses();
    }

    public List<Class> getAllClass() {
        return classDAO.getAllClass();
    }

    public List<Class> getClassesWithoutUser(int userId, int page, int pageSize) {
        return classDAO.getClassesWithoutUser(userId, page, pageSize);
    }
    public int getTotalClassesWithoutUser(int userId) {
        return classDAO.getTotalClassesWithoutUser(userId);
    }

    public List<Class> getAllClassAdmin(String keyword, String column, String order, int page, int pageSize) {
        return classDAO.searchClassAdmin(keyword, column, order, 0, 0);
    }

    public List<Class> getAllClassSortedBy(String column, String order, int page, int pageSize) {
        return classDAO.getAllClassSortedBy(column, order, page, pageSize);
    }

    public List<Class> searchByClassNameAndSort(String field, String search) {
        return classDAO.getClassSortedSearchBy(field, search);
    }

    public List<Class> searchClassByClassName(String search) {
        return classDAO.searchUsersByClassName(search);
    }

    public List<Class> sortClass(String search) {
        return classDAO.getClassSortedBy(search);
    }

    public boolean deleteUserInclass(int classId, int userId) {
        return classDAO.deleteUserInclass(classId, userId);
    }

    public List<Class> searchAndSortClasses(String searchClassName, String searchSubjectId, String sortField, String sortOrder, int page, int size) {
        return classDAO.searchAndSortClasses(searchClassName, searchSubjectId, sortField, sortOrder, page, size);
    }
    public int countClasses(String searchClassName, String searchSubjectId) {
        return classDAO.countClasses(searchClassName, searchSubjectId);
    }
    public List<Class> sortClasses(String sortField, String sortOrder, int page, int size) {
        return classDAO.sortClasses(sortField, sortOrder, page, size);
    }
    public List<Class> searchClasses(String searchClassName, String searchSubjectId, int page, int size) {
        return classDAO.searchClasses(searchClassName, searchSubjectId, page, size);
    }
    public List<Class> getAllClasses(int page, int size) {
        return classDAO.getAllClasses(page, size);
    }
}
