/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Contact;
import java.io.IOException;
import java.io.PrintWriter;
import entity.Setting;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.ContactService;
import service.SettingService;
import service.SubjectService;
import service.UserService;

/**
 *
 * @author Sunshine
 */
public class ContactController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ContactController.class.getName());
    SettingService settingService = new SettingService();
    UserService userService = new UserService();
    SubjectService subjectService = new SubjectService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "":
                    getContactPage(request, response);
                    break;
                case "sentContact":
                    sentContact(request, response);
                    break;
                default:
                    getContactPage(request, response);
                    break;
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
        getContactPage(request, response);
       
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

    private void getContactPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//            SettingDAO settingDAO = new SettingDAO();

            List<Setting> contactType = settingService.getAllContactType();

            request.setAttribute("setting", contactType);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Contact/Contact.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get add user page", e);
        }
    }

    private void sentContact(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ContactService contactService = new ContactService();
            String userName = request.getParameter("name");
            String subject = request.getParameter("subject");
            String phone = request.getParameter("phone");
            String emailUser = request.getParameter("email");
            String message = request.getParameter("message");
            String emailAdmin = "ladykillah041098@gmail.com";
            int type = Integer.parseInt(request.getParameter("contactTypeId")) ;
            List<Setting> contactType = settingService.getAllContactType();
            
            Setting setting = settingService.getSettingById(type);
            String typeName = setting.getSettingName();
            Contact contact = new Contact( userName, emailUser, phone, subject, message, type, true);
            boolean isSaveContact = contactService.addContact(contact);
            //validate
            //sent email
            
            boolean isSentEmail = contactService.sendEmailContact(userName, phone, emailAdmin, emailUser, typeName, subject, message);
            if (isSentEmail) {
                request.setAttribute("successMessage", "Gửi thành công");
                request.setAttribute("setting", contactType);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Contact/Contact.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error get add user page", e);
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
