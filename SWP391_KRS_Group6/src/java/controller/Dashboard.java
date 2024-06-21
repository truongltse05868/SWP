/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import static com.mysql.cj.conf.PropertyKey.logger;
import dao.ClassDAO;
import dao.PostDAO;
import dao.SubjectDAO;
import dao.UserDAO;
import entity.Post;
import entity.Subject;
import entity.User;
import entity.Class;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HuyPC
 */
public class Dashboard extends HttpServlet {

    private static final Logger logger = Logger.getLogger(PostController.class.getName());

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

        response.setContentType("text/html;charset=UTF-8");
        try {

            PostDAO postDAO = new PostDAO();
            UserDAO userDAO = new UserDAO();
            SubjectDAO SubjectDAO = new SubjectDAO();
            ClassDAO ClassDAO = new ClassDAO();

            int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
            int pageSize = Integer.parseInt(request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "6");
            String sortBy = request.getParameter("sortBy") != null ? request.getParameter("sortBy") : "post_id";
            String sortOrder = request.getParameter("sortOrder") != null ? request.getParameter("sortOrder") : "DESC";
            String keyword = request.getParameter("keyword");

            List<Post> posts;
            if (keyword != null && !keyword.trim().isEmpty()) {
                posts = postDAO.searchPosts(keyword, sortBy, sortOrder, page, pageSize);
            } else {
                posts = postDAO.getAllPostsSortedBy(sortBy, sortOrder, page, pageSize);
            }
            List<User> users = userDAO.getAllUsers();
            List<Subject> subject = SubjectDAO.getAllSubjects();
            List<Class> classes = ClassDAO.getAllClass();

            request.setAttribute("posts", posts);
            request.setAttribute("users", users);
            request.setAttribute("subject", subject);
            request.setAttribute("classes", classes);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("sortBy", sortBy);
            request.setAttribute("sortOrder", sortOrder);
            request.setAttribute("keyword", keyword);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Dashboard.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing post request", e);
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
