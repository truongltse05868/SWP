/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Setting;
import entity.User;
import entity.Class;
import entity.Subject;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.ClassService;
import service.SubjectService;
import service.SettingService;
import service.UserService;

/**
 *
 * @author Ngs-MT305
 */
public class ClassController extends HttpServlet {

    ClassService classService = new ClassService();
    UserService userService = new UserService();
    SubjectService subjectService = new SubjectService();
    SettingService settingService = new SettingService();
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            User currentUser = (User) session.getAttribute("account");

            if (currentUser == null) {
                // Nếu không có phiên đăng nhập, chuyển hướng đến trang đăng nhập
                response.sendRedirect("Home");
                return;
            }

            String action = request.getParameter("action");
            if (action == null) {
                action = "ListAllClass";
            }
            if (currentUser.getRole_id() == 1) {
                // Nếu là quản trị viên, cho phép truy cập vào các tính năng quản trị
                switch (action) {
                    case "classList":
                        getAllClassesAdmin(request, response);
                        break;
                    case "addClassPage":
                        addClassPage(request, response);
                        break;
                    case "addClass":
                        addClassByAdmin(request, response);
                        break;
                    case "updateClassPage":
                        updateClassByAdminPage(request, response);
                        break;
                    case "updateClass":
                        updateClassByAdmin(request, response);
                        break;
                    case "searchClass":
                        searchClassByName(request, response);
                        break;
                    case "sort":
                        searchClassByName(request, response);
                        break;
                    case "classDetail":
                        getClassDetail(request, response);
                        break;
                    case "addUserToClassPage":
                        addUserToClassPage(request, response);
                        break;
                    case "addUserToClass":
                        addUserToClass(request, response);
                        break;
                    case "deteteUserInclass":
                        deteteUserInclass(request, response);
                        break;
                    default:

                        break;
                }
            } else {
                // Nếu không phải là quản trị viên, chỉ cho phép truy cập vào trang thông tin cá nhân
                switch (action) {
                    case "ListAllClass":
                        getAllClasses(request, response);
                        break;
                    default:
//                        getUserProfle(request, response);
                        break;
                }
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid user ID format", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing user request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void getAllClasses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Class> classList = classService.getAllClass();
            List<Subject> subjectList = subjectService.getAllSubjects();
            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
            request.setAttribute("classes", classList);
            request.setAttribute("userCountMap", userCountMap);
            request.setAttribute("subjectList", subjectList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ClassList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list Class", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get list class.");
        }
    }

    private void deteteUserInclass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int classId = Integer.parseInt(request.getParameter("classId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            boolean isDelete = classService.deleteUserInclass(classId, userId);
            List<Setting> role = settingService.getAllRoles();
            List<User> users = userService.getAllUsersInClass(classId);
            Class classs = classService.getClassById(classId);
            if(isDelete){
                request.setAttribute("successMessage", "Xoá thành công");
            }else{
                request.setAttribute("successMessage", "Xoá không thành công");
            }
            request.setAttribute("users", users);
            request.setAttribute("roles", role);
            request.setAttribute("classs", classs);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Class/ClassDetailAdmin.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list Class", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get list class.");
        }
    }

    private void getClassDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int classId = Integer.parseInt(request.getParameter("classId"));
            Class classs = classService.getClassById(classId);
            List<User> users = userService.getAllUsersInClass(classId);
            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
            List<Setting> roles = settingService.getAllRoles();
            request.setAttribute("classs", classs);
            request.setAttribute("userCountMap", userCountMap);
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Class/ClassDetailAdmin.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list Class", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get list class.");
        }
    }

    private void addUserToClassPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//            List<User> user = userService.getUsersByRole(3);
            int class_id = Integer.parseInt(request.getParameter("classId"));
            Class classs = classService.getClassById(class_id);
            List<User> user = userService.getAllUsersNotInClass(class_id);
            List<Setting> role = settingService.getAllRoles();
            request.setAttribute("users", user);
            request.setAttribute("roles", role);
            request.setAttribute("classs", classs);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Class/AddUserToClass.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get add class page", e);
        }
    }

    private void addUserToClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//            List<User> user = userService.getUsersByRole(3);
            int class_id = Integer.parseInt(request.getParameter("classId"));
            int UserId = Integer.parseInt(request.getParameter("UserId"));
            Class classs = classService.getClassById(class_id);
            
            User user = userService.getUserById(UserId);
            List<Setting> role = settingService.getAllRoles();
            boolean isAddSuccess = classService.addUserToClass(user, class_id);
            List<User> users = userService.getAllUsersNotInClass(class_id);
            if (isAddSuccess) {
                request.setAttribute("successMessage", "Thêm thành công");
            } else {
                request.setAttribute("successMessage", "Thêm thất bại");
            }

            request.setAttribute("users", users);
            request.setAttribute("roles", role);
            request.setAttribute("classs", classs);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Class/AddUserToClass.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get add class page", e);
        }
    }

    private void addClassPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Subject> subjects = subjectService.getAllSubjects();
            request.setAttribute("subject", subjects);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/AddClass.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get add class page", e);
        }
    }

    private void getAllClassesAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Class> classList = classService.getAllClass();
            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
            List<Subject> subjectList = subjectService.getAllSubjects();
            request.setAttribute("classes", classList);
            request.setAttribute("userCountMap", userCountMap);
            request.setAttribute("subjectList", subjectList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ClassListAdmin.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list Class", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get list class.");
        }
    }

    private void addClassByAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            List<Setting> roles = settingService.getAllRoles();
            List<Subject> subjects = subjectService.getAllSubjects();

            // Lấy thông tin từ form
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            String className = request.getParameter("className");
            boolean status = request.getParameter("status") != null;

            // Validate các trường
            Map<String, String> errors = classService.validateClassData(className, 0);

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("class_name", className);
                request.setAttribute("subjectList", subjects);
                request.setAttribute("status", status);
                request.setAttribute("successMessage", "Cập nhật không thành công.");
                request.getRequestDispatcher("WEB-INF/AddClass.jsp").forward(request, response);
                return;
            }

            Class newClass = new Class();
            newClass.setClass_name(className);
            newClass.setSubject_id(subjectId);
            newClass.setStatus(status);

            boolean isSuccess = classService.addClass(newClass);
            String message = classService.getSuccessMessage(isSuccess);

            if (isSuccess) {
                request.setAttribute("successMessage", message);
                List<Class> classes = classService.getAllClasses();
                request.setAttribute("classes", classes);
                request.setAttribute("subjectList", subjects);
                request.getRequestDispatcher("WEB-INF/ClassListAdmin.jsp").forward(request, response);
            } else {
                request.setAttribute("roles", roles);
                request.setAttribute("class_name", className);
                request.setAttribute("subjectList", subjects);
                request.setAttribute("status", status);
                request.setAttribute("successMessage", message);
                request.getRequestDispatcher("WEB-INF/AddClass.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            request.setAttribute("existsError", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/AddClass.jsp").forward(request, response);
        }
    }

    //update class
    private void updateClassByAdminPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int classId = Integer.parseInt(request.getParameter("classId"));
            Class classes = classService.getClassById(classId);
            List<Subject> subjectList = subjectService.getAllSubjects();
            request.setAttribute("classes", classes);
            request.setAttribute("subject", subjectList);
            request.getRequestDispatcher("WEB-INF/UpdateClass.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting class by class_id", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting class.");
        }
    }

    private void updateClassByAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class classUpdate = new Class();

            int classId = Integer.parseInt(request.getParameter("classId"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            String className = request.getParameter("className");
            boolean status = request.getParameter("status") != null;

            classUpdate.setClass_id(classId);
            classUpdate.setClass_name(className);
            classUpdate.setSubject_id(subjectId);
            classUpdate.setStatus(status);

            Map<String, String> errors = classService.validateClassData(className, classId);
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                List<Subject> subjectList = subjectService.getAllSubjects();
                request.setAttribute("classes", classUpdate);
                request.setAttribute("subject", subjectList);
                request.getRequestDispatcher("WEB-INF/UpdateClass.jsp").forward(request, response);
                return;
            }
            boolean isUpdate = classService.updateClass(classUpdate);
            if (isUpdate) {
                List<Class> classList = classService.getAllClasses();
                Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
                List<Subject> subjectList = subjectService.getAllSubjects();

                request.setAttribute("classes", classList);
                request.setAttribute("userCountMap", userCountMap);
                request.setAttribute("subjectList", subjectList);
                request.setAttribute("successMessage", "Cập nhật lớp thành công");
                request.getRequestDispatcher("WEB-INF/ClassListAdmin.jsp").forward(request, response);
            } else {
                request.setAttribute("successMessage", "Cập nhật lớp không thành công");
                request.getRequestDispatcher("WEB-INF/UpdateAdmin.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating class", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the class.");
        }
    }

    //sort and search class
    private void searchClassByName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Class> classes;
            List<Subject> subjects = subjectService.getAllSubjects();
            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
            // Get search and sort parameters
            String searchClassName = request.getParameter("searchClassname");
            String sortField = request.getParameter("sortField");

            if (searchClassName != null && !searchClassName.isEmpty() && sortField != null && !sortField.isEmpty()) {
                // Sort and search
                classes = classService.searchByClassNameAndSort(sortField, searchClassName);
            } else if (searchClassName != null && !searchClassName.isEmpty()) {
                // Search only
                classes = classService.searchClassByClassName(searchClassName);
            } else if (sortField != null && !sortField.isEmpty()) {
                // Sort only
                classes = classService.sortClass(sortField);
            } else {
                // Default: get all users
                classes = classService.getAllClass();
            }

            // Set the list of users as a request attribute
            request.setAttribute("userCountMap", userCountMap);
            request.setAttribute("classes", classes);
            request.setAttribute("subjectList", subjects);
            request.setAttribute("searchClassname", searchClassName);
            request.setAttribute("sortField", sortField);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ClassListAdmin.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting user list", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the user list.");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
