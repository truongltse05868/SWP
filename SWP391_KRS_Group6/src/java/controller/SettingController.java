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
import java.util.Vector;
import dao.SettingDAO;
import entity.User;
import java.util.List;
import service.SettingService;

@WebServlet(name = "SettingController", urlPatterns = {"/SettingController"})
public class SettingController extends HttpServlet {

    SettingService settingService = new SettingService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            SettingDAO dao = new SettingDAO();
            String service = request.getParameter("service");
            String search = request.getParameter("searchSetting");
            String message = request.getParameter("message");
            String sortColumn = request.getParameter("sortColumn");
            String sortOrder = request.getParameter("sortOrder");
            String pageParam = request.getParameter("page");
            String pageSizeParam = request.getParameter("pageSize");

            // Default values
            if (sortColumn == null) {
                sortColumn = "setting_id";
            }
            if (sortOrder == null) {
                sortOrder = "asc";
            }
            int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
            int pageSize = (pageSizeParam != null) ? Integer.parseInt(pageSizeParam) : 4;

            User currentUser = (User) session.getAttribute("account");
            Setting settingSer = settingService.getSettingById(currentUser.getRole_id());

            if (currentUser == null) {
                response.sendRedirect("Home");
            } else {
                if (settingSer.getSettingName().toLowerCase().equals("admin")) {
                    if (service == null) {
                        service = "listAllSetting";
                    }
                    if (service.equals("listAllSetting")) {
                        if (message == null) {
                            message = "";
                        }
                        Vector<Setting> settings;
                        List<Setting> subjec;
                        if (search != null && !search.isEmpty()) {
                            settings = dao.searchSettings(search, sortColumn, sortOrder, page, pageSize);
                            subjec = dao.getAllSettings();
                        } else {
                            settings = dao.getAllSettingsSortedBy(sortColumn, sortOrder, page, pageSize);
                            subjec = dao.getAllSettings();
                        }

                        int maxPage = (int) Math.ceil((double) subjec.size() / pageSize);
                        request.setAttribute("maxPage", maxPage);
                        request.setAttribute("settings", settings);
                        request.setAttribute("currentPage", page);
                        request.setAttribute("pageSize", pageSize);
                        request.setAttribute("sortColumn", sortColumn);
                        request.setAttribute("sortOrder", sortOrder);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Setting/SettingDisplay.jsp");
                        dispatcher.forward(request, response);
                    }
                    if (service.equals("insertSetting")) {
                        String submit = request.getParameter("submit");
                        if (submit == null) {
                            Vector<Setting> settings = dao.getAllSettingsSortedBy("setting_id", "ASC", page, pageSize);
                            request.setAttribute("settings", settings);
                            dispath(request, response, "WEB-INF/Setting/InsertSetting.jsp");
                        } else {
                            String settingName = request.getParameter("setting_name");
                            String type = request.getParameter("type");
                            String description = request.getParameter("description");
                            String statusParam = request.getParameter("status");
                            boolean status = (statusParam != null && statusParam.equals("on"));

                            Setting setting = new Setting();
                            setting.setSettingName(settingName);
                            setting.setType(type);
                            setting.setDescription(description);
                            setting.setStatus(status);

                            dao.addSetting(setting);
                            response.sendRedirect("SettingController");
                        }
                    }
                    if (service.equals("toggleStatus")) {
                        int settingId = Integer.parseInt(request.getParameter("settingId"));
                        Setting setting = dao.getSettingById(settingId);
                        if (setting != null) {
                            setting.setStatus(!setting.getStatus()); // Toggle status
                            dao.updateSetting(setting);
                        }
                        response.sendRedirect("SettingController?service=listAllSetting");
                    }

                    if (service.equals("updateSetting")) {
                        String submit = request.getParameter("submit");
                        if (submit == null) {
                            int pid = Integer.parseInt(request.getParameter("pid"));
                            Setting setting = dao.getSettingById(pid);
                            request.setAttribute("setting", setting);
                            dispath(request, response, "WEB-INF/Setting/SettingForm.jsp");
                        } else {
                            String settingId = request.getParameter("setting_id");
                            String settingName = request.getParameter("setting_name");
                            String type = request.getParameter("type");
                            String description = request.getParameter("description");
                            String statusParam = request.getParameter("status");
                            boolean status = (statusParam != null && statusParam.equals("on"));

                            int settingIdInt = Integer.parseInt(settingId);
                            Setting setting = new Setting(settingIdInt, settingName, type, description, status);
                            dao.updateSetting(setting);
                            response.sendRedirect("SettingController");
                        }
                    }
                }
            }
        }
    }

    void dispath(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher dispath = request.getRequestDispatcher(page);
        dispath.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
