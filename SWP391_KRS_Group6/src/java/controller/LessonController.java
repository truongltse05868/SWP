/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Lesson;
import entity.Setting;
import entity.Subject;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.LessonService;
import service.SettingService;
import service.SubjectService;
import service.UserService;

/**
 *
 * @author Ngs-MT305
 */
public class LessonController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LessonController.class.getName());
    SettingService settingService = new SettingService();
    UserService userService = new UserService();
    LessonService lessonService = new LessonService();
    SubjectService subjectService = new SubjectService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);

//            SettingDAO settingDAO = new SettingDAO();
//            SettingService settingService = new SettingService();
            User currentUser = (User) session.getAttribute("account");
            Setting setting = settingService.getSettingById(currentUser.getRole_id());

            if (currentUser == null) {
                // Nếu không có phiên đăng nhập, chuyển hướng đến trang đăng nhập
                response.sendRedirect("Home");
                return;
            }

            String action = request.getParameter("action");
            if (setting.getSettingName().toLowerCase().trim().equals("admin")) {
                switch (action) {
                    case "lessonList":
//                        getAllLessonList(request, response);
                        searchLessonByName2(request, response);
                        break;
                    case "addLessonPage":
                        addLessonPage(request, response);
                        break;
                    case "addLesson":
                        addLessonByAdmin(request, response);
                        break;
                    case "updateLessonPage":
                        updateLessonByAdminPage(request, response);
                        break;
                    case "updateLesson":
                        updateLessonByAdmin(request, response);
                        break;
                    case "lessonListBySubject":
                        getAllLessonListBySubject(request, response);
                        break;
                    default:
                        break;
                }
            } else {
                switch (action) {
                    case "lessonList":
                        getAllLessonList(request, response);
                        break;
                    case "lessonListBySubject":
                        getAllLessonListBySubject(request, response);
                        break;
                    default:
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
    
    private void addLessonPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Subject> subjects = subjectService.getAllSubjects();
            request.setAttribute("subject", subjects);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/lesson/AddLesson.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get add lesson page", e);
        }
    }
    
    private void addLessonByAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            List<Setting> roles = settingService.getAllRoles();
            List<Subject> subjects = subjectService.getAllSubjects();

            // Lấy thông tin từ form
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            String lessonName = request.getParameter("lessonName");
            boolean status = "1".equals(request.getParameter("status")); // Updated to get status from radio button

            // Validate các trường
            Map<String, String> errors = lessonService.validateLessonData(lessonName, 0);

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("title", lessonName);
                request.setAttribute("subject", subjects);
                request.setAttribute("status", status);
                request.setAttribute("successMessage", "Cập nhật không thành công.");
                request.getRequestDispatcher("WEB-INF/lesson/AddLesson.jsp").forward(request, response);
                return;
            }

            Lesson newLesson = new Lesson();
            newLesson.setTitle(lessonName);
            newLesson.setSubject_id(subjectId);
            newLesson.setStatus(status);

            boolean isSuccess = lessonService.addLesson(newLesson);
            String message = lessonService.getSuccessMessageAddLesson(isSuccess, lessonName);

            if (isSuccess) {
                request.setAttribute("successMessage", message);
//                List<Class> classes = classService.getAllClasses();
//                request.setAttribute("classes", classes);
//                request.setAttribute("subjectList", subjects);
//                request.getRequestDispatcher("WEB-INF/ClassListAdmin.jsp").forward(request, response);
                searchLessonByName2(request, response);
            } else {
//                request.setAttribute("roles", roles);
                    
                request.setAttribute("title", lessonName);
                request.setAttribute("subject", subjects);
                request.setAttribute("status", status);
                request.setAttribute("successMessage", message);
                request.getRequestDispatcher("WEB-INF/lesson/AddLesson.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            request.setAttribute("existsError", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/AddClass.jsp").forward(request, response);
        }
    }

    //update class
    private void updateLessonByAdminPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int lessonId = Integer.parseInt(request.getParameter("lessonId"));
            Lesson lessons = lessonService.getLessonById(lessonId);
            List<Subject> subjectList = subjectService.getAllSubjects();
            request.setAttribute("lessons", lessons);
            request.setAttribute("subject", subjectList);
            request.getRequestDispatcher("WEB-INF/lesson/UpdateLesson.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting lesson by lessonId", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting lesson.");
        }
    }

    private void updateLessonByAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Lesson lessonUpdate = new Lesson();

            int lessonId = Integer.parseInt(request.getParameter("lessonId"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            String lessonName = request.getParameter("lessonName");
            boolean status = "1".equals(request.getParameter("status")); // Updated to get status from radio button

            lessonUpdate.setLesson_id(lessonId);
            lessonUpdate.setTitle(lessonName);
            lessonUpdate.setSubject_id(subjectId);
            lessonUpdate.setStatus(status);

            Map<String, String> errors = lessonService.validateLessonData(lessonName, lessonId);
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                List<Subject> subjectList = subjectService.getAllSubjects();
                request.setAttribute("lessons", lessonUpdate);
                request.setAttribute("subject", subjectList);
                request.getRequestDispatcher("WEB-INF/lesson/UpdateLesson.jsp").forward(request, response);
                return;
            }
            boolean isUpdate = lessonService.updateLesson(lessonUpdate);
            if (isUpdate) {
//                List<Class> classList = classService.getAllClasses();
//                Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();
//                List<Subject> subjectList = subjectService.getAllSubjects();
//
//                request.setAttribute("classes", classList);
//                request.setAttribute("userCountMap", userCountMap);
//                request.setAttribute("subjectList", subjectList);
                request.setAttribute("successMessage", "Cập nhật bài thành công");
//                request.getRequestDispatcher("WEB-INF/ClassListAdmin.jsp").forward(request, response);
                searchLessonByName2(request, response);
            } else {
                request.setAttribute("successMessage", "Cập nhật bài không thành công");
                request.getRequestDispatcher("WEB-INF/lesson/UpdateLesson.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating class", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the class.");
        }
    }

    private void getAllLessonList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Subject> subjects = subjectService.getAllSubjects();
            List<Lesson> lessons = lessonService.getAllLesson();

            // Get search and sort parameters
            // Set the list of users as a request attribute
            request.setAttribute("lessons", lessons);
            request.setAttribute("subjectList", subjects);
            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/lesson/LessonListAdmin.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting lesson list", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the less list.");
        }
    }

    //search and phân trang
    private void searchLessonByName2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Lesson> lessons;
            List<Subject> subjects = subjectService.getAllSubjects();
//            Map<Integer, Integer> userCountMap = classService.getUserCountForClasses();

            // Get search, sort, and pagination parameters
            String searchTitle = request.getParameter("searchLesson");
            String searchSubjectId = request.getParameter("subjectId");
            String sortField = request.getParameter("sortField");
            String sortOrder = request.getParameter("sortOrder");
            int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
            int size = Integer.parseInt(request.getParameter("size") != null ? request.getParameter("size") : "3");

            int totalClasses = lessonService.countLessones(searchTitle, searchSubjectId);

            if ((searchTitle != null && !searchTitle.isEmpty()) || (searchSubjectId != null && !searchSubjectId.isEmpty())) {
                if (sortField != null && !sortField.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
                    // Search, sort, and paginate
                    lessons = lessonService.searchAndSortLesson(searchTitle, searchSubjectId, sortField, sortOrder, page, size);
                } else {
                    // Search and paginate
                    lessons = lessonService.searchLesson(searchTitle, searchSubjectId, page, size);
                }
            } else if (sortField != null && !sortField.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
                // Sort and paginate
                lessons = lessonService.sortLesson(sortField, sortOrder, page, size);
            } else {
                // Default: get all classes with pagination
                lessons = lessonService.getAllLesson(page, size);
            }

            int totalPages = (int) Math.ceil((double) totalClasses / size);

            // Set the list of classes as a request attribute
//            request.setAttribute("userCountMap", userCountMap);
            request.setAttribute("lessons", lessons);
            request.setAttribute("subjectList", subjects);
            request.setAttribute("searchLesson", searchTitle);
            request.setAttribute("searchSubjectId", searchSubjectId);
            request.setAttribute("sortField", sortField);
            request.setAttribute("sortOrder", sortOrder);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/lesson/LessonListAdmin.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting lesson list", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the lesson list.");
        }
    }

    private void getAllLessonListBySubject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Subject> subjects = subjectService.getAllSubjects();
            int subject = Integer.parseInt(request.getParameter("subjectId"));
            List<Lesson> lessons = lessonService.getAllLessonBySubject(subject);
            // Set the list of users as a request attribute
            request.setAttribute("lessons", lessons);
            request.setAttribute("subjectList", subjects);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/lesson/LessonList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting lesson list", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the lesson list.");
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
