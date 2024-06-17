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
                        getAllLessonList(request, response);
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/lesson/LessonList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting lesson list", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the user list.");
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
