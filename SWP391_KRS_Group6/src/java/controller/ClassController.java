/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ClassDAO;
import dao.SettingDAO;
import dao.SubjectDAO;
import dao.UserDAO;
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

/**
 *
 * @author Ngs-MT305
 */
public class ClassController extends HttpServlet {

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
            ClassDAO classDAO = new ClassDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            List<Class> classList = classDAO.getAllClass();
            List<Subject> subjectList = subjectDAO.getAllSubjects();
            request.setAttribute("classes", classList);
            request.setAttribute("subjectList", subjectList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ClassList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list Class", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get list class.");
        }
    }

    private void addClassPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SubjectDAO subjectDAO = new SubjectDAO();
            List<Subject> subjects = subjectDAO.getAllSubjects();
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
            ClassDAO classDAO = new ClassDAO();
            SubjectDAO subjectDAO = new SubjectDAO();

            List<Class> classList = classDAO.getAllClass();
            Map<Integer, Integer> userCountMap = classDAO.getUserCountForClasses();
            List<Subject> subjectList = subjectDAO.getAllSubjects();

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
            SettingDAO settingDAO = new SettingDAO();
            ClassDAO classDAO = new ClassDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            List<Setting> roles = settingDAO.getAllRole();
            List<Subject> subject = subjectDAO.getAllSubjects();
            // Danh sách các thông báo lỗi
            Map<String, String> errors = new HashMap<>();
            // Lấy thông tin từ form
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            String class_name = request.getParameter("className");
            boolean status = request.getParameter("status") != null;
            if (status == false) {
                status = true;
            }
            // Validate các trường
            if (class_name == null || class_name.isEmpty()) {
                errors.put("blankClassName", "Tên lớp học không được để trống");
            }
            if (!errors.isEmpty()) {

                request.setAttribute("errors", errors);
                request.setAttribute("class_name", class_name);
                request.setAttribute("subject", subject);
                request.setAttribute("status", status);
                request.setAttribute("successMessage", "Cập nhật không thành công.");
                request.getRequestDispatcher("WEB-INF/AddClass.jsp").forward(request, response);
                return;
            }
            Class newClass = new Class();
            newClass.setClass_name(class_name);
            newClass.setSubject_id(subjectId);
            newClass.setStatus(status);
            boolean isSuccess = classDAO.addClass(newClass);
            String message = isSuccess ? "Lưu thành công." : "Cập nhật không thành công.";
            if (isSuccess) {
                request.setAttribute("successMessage", message);
                List<Class> classes = classDAO.getAllClassUser();
                request.setAttribute("classes", classes);
                request.setAttribute("subject", subject);
                request.getRequestDispatcher("WEB-INF/ClassListAdmin.jsp").forward(request, response);
            } else {
                // Handle failure (optional)
                request.setAttribute("roles", roles);
                request.setAttribute("class_name", class_name);
                request.setAttribute("subject", subject);
                request.setAttribute("status", status);
                request.setAttribute("errorsMessage", "Cập nhật không thành công.");
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
            ClassDAO classDAO = new ClassDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            List<Subject> subjectList = subjectDAO.getAllSubjects();
            int classId = Integer.parseInt(request.getParameter("classId"));
            Class classes = classDAO.getClassById(classId);
            request.setAttribute("classes", classes);
            request.setAttribute("subject", subjectList);
            request.getRequestDispatcher("WEB-INF/UpdateClass.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get Class by class_id", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get class.");
        }
    }

    private void updateClassByAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ClassDAO classDAO = new ClassDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            Class classUpdate = new Class();

            int classId = Integer.parseInt(request.getParameter("classId"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            String className = request.getParameter("className");
            boolean status = request.getParameter("status") != null;

            classUpdate.setClass_id(classId);
            classUpdate.setClass_name(className);
            classUpdate.setSubject_id(subjectId);
            classUpdate.setStatus(status);
            boolean isUpdate = classDAO.updateClass(classUpdate);
            if (isUpdate) {
                List<Class> classList = classDAO.getAllClass();
                Map<Integer, Integer> userCountMap = classDAO.getUserCountForClasses();
                List<Subject> subjectList = subjectDAO.getAllSubjects();
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
            logger.log(Level.SEVERE, "Error get Class by class_id", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get class.");
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
