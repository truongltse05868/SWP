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
                    case "lessonListBySubject":
                        getAllLessonListBySubject(request, response);
                        break;
                    default:
                        break;
                }
            }else{
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
