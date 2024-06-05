/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ClassDAO;
import dao.SubjectDAO;
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

/**
 *
 * @author HuyPC
 */
public class SubjectController extends HttpServlet {
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
            
            String action = request.getParameter("service");
            if(action == null){
                action = "ListAllSubject";
            }
            if (currentUser != null) {
                // Nếu là quản trị viên, cho phép truy cập vào các tính năng quản trị
                switch (action) {
                    case "ListAllSubject":
                        getAllSubject(request, response);
                        break;
                    case "updateSubject":
                         String submit = request.getParameter("submit");
                        int pid = Integer.parseInt(request.getParameter("pid"));
                        UpdateSubject(request, response, pid, submit);
                        break;
                    default:
                        break;
                }
            } else {
                // Nếu không phải là quản trị viên, chỉ cho phép truy cập vào trang thông tin cá nhân
                switch (action) {
                    case "ListAllSubject":
                        getAllSubject(request, response);
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
     void dispath(HttpServletRequest request,
            HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher dispath
                = request.getRequestDispatcher(page);
        //display
        dispath.forward(request, response);
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

    private void getAllSubject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
             
             SubjectDAO subjectDAO = new SubjectDAO();
            
            List<Subject> subjectList = subjectDAO.getAllSubjects();
            
            request.setAttribute("subjectList", subjectList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/SubjectList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get list Subject", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while get list subject.");
        }
    }
    private void UpdateSubject(HttpServletRequest request, HttpServletResponse response,int pid, String submit)
            throws ServletException, IOException {
    try {
        SubjectDAO dao = new SubjectDAO();
        if (submit == null) {
            Subject subject = dao.getSubjectById(pid);
            request.setAttribute("subject", subject);
            dispath(request, response, "WEB-INF/UpdateSubject.jsp");
        } else {
            String subjectId = request.getParameter("subject_id");
            String Code = request.getParameter("subject_code");
            String Name = request.getParameter("name");
            String description = request.getParameter("description");
            String statusParam = request.getParameter("status");
            boolean status = (statusParam != null && statusParam.equals("on"));

            // convert
            int subjectIdInt = Integer.parseInt(subjectId);

            //
            Subject subject = new Subject(subjectIdInt, Code, Name, description, status);
            dao.updateSubject(subject);
            
            // Redirect to a success page or display a success message
            String successMessage = "Subject updated successfully.";
            request.setAttribute("successMessage", successMessage);
            dispath(request, response, "success.jsp"); // Replace "success.jsp" with your success page
            
            // If you want to send a redirect to another page after success, uncomment the following line
            // response.sendRedirect("SubjectController");
        }
    } catch (NumberFormatException e) {
        logger.log(Level.SEVERE, "Invalid subject ID format", e);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid subject ID format");
    } catch (Exception e) {
        logger.log(Level.SEVERE, "Error updating subject", e);
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the subject.");
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
