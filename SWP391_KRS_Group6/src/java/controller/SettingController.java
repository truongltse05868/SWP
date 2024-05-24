/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Setting;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.Vector;
import dao.SettingDAO;

@WebServlet(name = "SettingController", urlPatterns = {"/SettingURL"})
public class SettingController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            SettingDAO dao = new SettingDAO();
            String service = request.getParameter("service");
            String search = request.getParameter("searchSetting");
            String message = request.getParameter("message");
//SEARCH
//        if (search != null) {
//            Vector<Setting> vector = dao.searchSettingByName(search);
//            if (vector.isEmpty()) {
//               if (message == null) {
//                message = "product not exists";
//            }
//            request.setAttribute("message", message);
//            } else {
//                vector = dao.getData("select * from products where product_name like '%" + search + "%'");
//                request.setAttribute("data", vector);
//                RequestDispatcher dispath
//                        = request.getRequestDispatcher("/jsp/Menu.jsp");
//                //display
//                dispath.forward(request, response);
//            }
//        }

            if (service == null) {
                service = "listAllSetting";
            }
            if (service.equals("listAllSetting")) {
                //get message
//                String message = request.getParameter("message");
                if (message == null) {
                    message = "";
                }
                // Fetch list of users from the database
                Vector<Setting> setting = dao.getAllSettingsSortedBy("1", "asc");

                // Set the list of users as a request attribute
                request.setAttribute("setting", setting);

                // Forward the request to the JSP page
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin/SettingDisplay.jsp");
                dispatcher.forward(request, response);

            }

            if (service.equals("insertSetting")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // request view form
                    Vector<Setting> vector = dao.getAllSettingsSortedBy("1", "ASC");
                    request.setAttribute("data", vector);
                    dispath(request, response, "/admin/InsertSetting.jsp");
                } else { // submit <> null --> request insert
                    String settingId = request.getParameter("setting_id");
                    String settingName = request.getParameter("setting_name");
                    String type = request.getParameter("type");
                    String description = request.getParameter("description");
                    Boolean status = Boolean.parseBoolean(request.getParameter("status"));

                    // convert
                    int settingIdInt = Integer.parseInt(settingId);

                    Setting setting = new Setting(settingIdInt, settingName, type, description, status);
                    dao.addSetting(setting);
                    String st = "";
                    //send message
                    response.sendRedirect("SettingController");
                }
            }
            if (service.equals("updateSetting")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int pid = Integer.parseInt(request.getParameter("pid"));
                    Setting setting = dao.getSettingById(pid);
                    request.setAttribute("setting", setting);

                    dispath(request, response, "/admin/SettingForm.jsp");
                } else {
                    String settingId = request.getParameter("setting_id");
                    String settingName = request.getParameter("setting_name");
                    String type = request.getParameter("type");
                    String description = request.getParameter("description");
                    String statusParam = request.getParameter("status");
                    boolean status = (statusParam != null && statusParam.equals("on"));

                    // convert
                    int settingIdInt = Integer.parseInt(settingId);

                    //
                    Setting setting = new Setting(settingIdInt, settingName, type, description, status);
                    dao.updateSetting(setting);
                    String st = "";
                    //send message
                    response.sendRedirect("SettingController");
                }
            }
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
