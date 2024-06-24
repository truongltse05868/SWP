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
            int userId = currentUser.getUser_id();
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
//                        getAllClassesAdmin(request, response);
                        searchClassByName2(request, response);
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
                        searchClassByName2(request, response);
                        break;
                    case "sort":
                        searchClassByName2(request, response);
                        break;
                    case "classDetail":
                        getClassDetailAdmin2(request, response);
                        break;
                    case "searchUserInClass":
                        getClassDetailAdmin2(request, response);
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
                        getClassesWithoutUser(request, response, userId);
                        break;
                    case "enrollClass":
                        enrollToClass(request, response, userId);
                        break;
                    case "classDetail":
                        getClassDetail2(request, response);
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

    private void getClassesWithoutUser(HttpServletRequest request, HttpServletResponse response, int userId)
            throws ServletException, IOException {
        try {
            List<Class> classList = classService.getClassesWithoutUser(userId);
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
            if (isDelete) {
                request.setAttribute("successMessage", "Xoá thành công");
            } else {
                request.setAttribute("successMessage", "Xoá không thành công");
            }
            request.setAttribute("users", users);
            request.setAttribute("roles", role);
            request.setAttribute("classs", classs);
            getClassDetailAdmin2(request, response);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Class/ClassDetailAdmin.jsp");
//            dispatcher.forward(request, response);
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
            List<Subject> subjectList = subjectService.getAllSubjects();
            List<User> users = userService.getAllUsersInClass(classId);
            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
            List<Setting> roles = settingService.getAllRoles();
            request.setAttribute("classs", classs);
            request.setAttribute("userCountMap", userCountMap);
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
            request.setAttribute("subjectList", subjectList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Class/ClassDetailAdmin.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list Class", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get list class.");
        }
    }

    //get class with paging
//    private void getClassDetailAdmin(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            int classId = Integer.parseInt(request.getParameter("classId"));
//
//            // Pagination parameters
//            int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
//            int size = Integer.parseInt(request.getParameter("size") != null ? request.getParameter("size") : "3");
//
//            // Search parameter
//            String searchQuery = request.getParameter("searchUsername");
//
//            // Sorting parameters
//            String sortField = request.getParameter("sortField") != null ? request.getParameter("sortField") : "user_id";
//            String sortOrder = request.getParameter("sortOrder") != null ? request.getParameter("sortOrder") : "ASC";
//
//            // Get class details
//            Class classs = classService.getClassById(classId);
//
//            // Get subjects
//            List<Subject> subjectList = subjectService.getAllSubjects();
//
//            // Get users with pagination, search, and sorting
//            List<User> users = userService.getUsersInClass(classId, page, size, searchQuery, sortField, sortOrder);
//
//            // Get user count for classes
//            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
//
//            // Get roles
//            List<Setting> roles = settingService.getAllRoles();
//
//            // Get total count of users in the class
//            int totalUserCount = userService.getUserCountInClass(classId, searchQuery);
//
//            // Calculate total pages
//            int totalPages = (int) Math.ceil((double) totalUserCount / size);
//
//            // Set attributes for the request
//            request.setAttribute("classs", classs);
//            request.setAttribute("userCountMap", userCountMap);
//            request.setAttribute("users", users);
//            request.setAttribute("roles", roles);
//            request.setAttribute("subjectList", subjectList);
//
//            // Pagination and search details
//            request.setAttribute("currentPage", page);
//            request.setAttribute("pageSize", size);
//            request.setAttribute("totalPages", totalPages);
//            request.setAttribute("searchQuery", searchQuery);
//            request.setAttribute("sortField", sortField);
//            request.setAttribute("sortOrder", sortOrder);
//
//            // Forward to JSP
//            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Class/ClassDetailAdmin.jsp");
//            dispatcher.forward(request, response);
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Error getting class details", e);
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting class details.");
//        }
//    }
    private void getClassDetailAdmin2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<User> useres;
            List<Subject> subjects = subjectService.getAllSubjects();
            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
            List<Setting> roles = settingService.getAllRoles();
            // Get search, sort, and pagination parameters
            int classId = Integer.parseInt(request.getParameter("classId"));
            String searchUserName = request.getParameter("searchUsername");
            String searchRoleId = request.getParameter("roleId");
            String sortField = request.getParameter("sortField");
            String sortOrder = request.getParameter("sortOrder");
            int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
            int size = Integer.parseInt(request.getParameter("size") != null ? request.getParameter("size") : "3");
            Class classs = classService.getClassById(classId);
//            int totalClasses = classService.countClasses(searchUserName, searchSubjectId);
// Get total count of users in the class
            int totalUserCount = userService.countUsersInClass(classId, searchUserName, searchRoleId);
            // Calculate total pages
            int totalPages = (int) Math.ceil((double) totalUserCount / size);
            if ((searchUserName != null && !searchUserName.isEmpty()) || (searchRoleId != null && !searchRoleId.isEmpty())) {
                if (sortField != null && !sortField.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
                    // Search, sort, and paginate
                    useres = userService.searchAndSortUsersInClass(classId, searchUserName,searchRoleId, sortField, sortOrder, page, size);
                } else {
                    // Search and paginate
                    useres = userService.searchUsersInClass(classId, searchUserName, searchRoleId, page, size);
                }
            } else if (sortField != null && !sortField.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
                // Sort and paginate
                useres = userService.sortUsersInClass(classId, sortField, sortOrder, page, size);
            } else {
                // Default: get all classes with pagination
                useres = userService.getAllUserInClass(classId, page, size);
            }

            // Set the list of classes as a request attribute
            request.setAttribute("roles", roles);
            request.setAttribute("userCountMap", userCountMap);
            request.setAttribute("classs", classs);
            request.setAttribute("users", useres);
            request.setAttribute("subjectList", subjects);
            request.setAttribute("searchUserName", searchUserName);
            request.setAttribute("searchRoleId", searchRoleId);
            request.setAttribute("sortField", sortField);
            request.setAttribute("sortOrder", sortOrder);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Class/ClassDetailAdmin.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting class list", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the class list.");
        }
    }

    private void getClassDetail2(HttpServletRequest request, HttpServletResponse response)
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Class/ClassDetail.jsp");
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

    private void enrollToClass(HttpServletRequest request, HttpServletResponse response, int userId)
            throws ServletException, IOException {
        try {
//            List<User> user = userService.getUsersByRole(3);
            int class_id = Integer.parseInt(request.getParameter("classId"));
//            int UserId = Integer.parseInt(request.getParameter("UserId"));
            Class classs = classService.getClassById(class_id);

            User user = userService.getUserById(userId);
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
            List<Class> classList = classService.getClassesWithoutUser(userId);
            List<Subject> subjectList = subjectService.getAllSubjects();
            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
            request.setAttribute("classes", classList);
            request.setAttribute("userCountMap", userCountMap);
            request.setAttribute("subjectList", subjectList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ClassList.jsp");
            dispatcher.forward(request, response);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ClassList.jsp");
//            dispatcher.forward(request, response);

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

    private void getAllClassesAdmin2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
            int pageSize = Integer.parseInt(request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "6");
            String sortBy = request.getParameter("sortBy") != null ? request.getParameter("sortBy") : "subject_id";
            String sortOrder = request.getParameter("sortOrder") != null ? request.getParameter("sortOrder") : "ASC";
            String keyword = request.getParameter("keyword");

            List<Class> ClassList;
            if (keyword != null && !keyword.trim().isEmpty()) {
                ClassList = classService.getAllClassAdmin(keyword, sortBy, sortOrder, page, pageSize);
            } else {
                ClassList = classService.getAllClassSortedBy(sortBy, sortOrder, page, pageSize);
            }

            request.setAttribute("subjectList", ClassList);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("sortBy", sortBy);
            request.setAttribute("sortOrder", sortOrder);
            request.setAttribute("keyword", keyword);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Subject/SubjectList.jsp");
            dispatcher.forward(request, response);
//            List<Class> classList = classService.getAllClass();
//            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
//            List<Subject> subjectList = subjectService.getAllSubjects();
//            request.setAttribute("classes", classList);
//            request.setAttribute("userCountMap", userCountMap);
//            request.setAttribute("subjectList", subjectList);
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ClassListAdmin.jsp");
//            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list Class", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get list class.");
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
//    private void searchClassByName(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            List<Class> classes;
//            List<Subject> subjects = subjectService.getAllSubjects();
//            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
//            // Get search and sort parameters
//            String searchClassName = request.getParameter("searchClassname");
//            String sortField = request.getParameter("sortField");
//
//            if (searchClassName != null && !searchClassName.isEmpty() && sortField != null && !sortField.isEmpty()) {
//                // Sort and search
//                classes = classService.searchByClassNameAndSort(sortField, searchClassName);
//            } else if (searchClassName != null && !searchClassName.isEmpty()) {
//                // Search only
//                classes = classService.searchClassByClassName(searchClassName);
//            } else if (sortField != null && !sortField.isEmpty()) {
//                // Sort only
//                classes = classService.sortClass(sortField);
//            } else {
//                // Default: get all users
//                classes = classService.getAllClass();
//            }
//
//            // Set the list of users as a request attribute
//            request.setAttribute("userCountMap", userCountMap);
//            request.setAttribute("classes", classes);
//            request.setAttribute("subjectList", subjects);
//            request.setAttribute("searchClassname", searchClassName);
//            request.setAttribute("sortField", sortField);
//
//            // Forward the request to the JSP page
//            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ClassListAdmin.jsp");
//            dispatcher.forward(request, response);
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Error getting user list", e);
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the user list.");
//        }
//    }
    //search and phân trang
    private void searchClassByName2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Class> classes;
            List<Subject> subjects = subjectService.getAllSubjects();
            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();

            // Get search, sort, and pagination parameters
            String searchClassName = request.getParameter("searchClassname");
            String searchSubjectId = request.getParameter("subjectId");
            String sortField = request.getParameter("sortField");
            String sortOrder = request.getParameter("sortOrder");
            int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
            int size = Integer.parseInt(request.getParameter("size") != null ? request.getParameter("size") : "3");

            int totalClasses = classService.countClasses(searchClassName, searchSubjectId);

            if ((searchClassName != null && !searchClassName.isEmpty()) || (searchSubjectId != null && !searchSubjectId.isEmpty())) {
                if (sortField != null && !sortField.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
                    // Search, sort, and paginate
                    classes = classService.searchAndSortClasses(searchClassName, searchSubjectId, sortField, sortOrder, page, size);
                } else {
                    // Search and paginate
                    classes = classService.searchClasses(searchClassName, searchSubjectId, page, size);
                }
            } else if (sortField != null && !sortField.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
                // Sort and paginate
                classes = classService.sortClasses(sortField, sortOrder, page, size);
            } else {
                // Default: get all classes with pagination
                classes = classService.getAllClasses(page, size);
            }

            int totalPages = (int) Math.ceil((double) totalClasses / size);

            // Set the list of classes as a request attribute
            request.setAttribute("userCountMap", userCountMap);
            request.setAttribute("classes", classes);
            request.setAttribute("subjectList", subjects);
            request.setAttribute("searchClassname", searchClassName);
            request.setAttribute("searchSubjectId", searchSubjectId);
            request.setAttribute("sortField", sortField);
            request.setAttribute("sortOrder", sortOrder);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ClassListAdmin.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting class list", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the class list.");
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
