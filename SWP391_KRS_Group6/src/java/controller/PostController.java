/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.PostDAO;
import dao.UserDAO;
import entity.Post;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
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
public class PostController extends HttpServlet {

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
            if (action == null) {
                action = "ListAllPost";
            }
            if (currentUser != null) {
                // Nếu là quản trị viên, cho phép truy cập vào các tính năng quản trị
                String submit = request.getParameter("submit");
                switch (action) {
                    case "ListAllPost":
                        getAllPost(request, response);
                        break;
                    case "updatePost":
                        int pid = Integer.parseInt(request.getParameter("pid"));
                        UpdatePost(request, response, pid, submit);
                        break;
                    case "insertPost":
                        int Author = currentUser.getUser_id();
                        InsertPost(request, response, submit, Author);
                        break;
                    default:
                        break;
                }
            } else {
                // Nếu không phải là quản trị viên, chỉ cho phép truy cập vào trang thông tin cá nhân
                switch (action) {
                    case "ListAllPost":
                        getAllPost(request, response);
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

    private void getAllPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PostDAO postDAO = new PostDAO();
            UserDAO userDAO = new UserDAO();
            List<Post> posts = postDAO.getAllPosts();
            List<User> user = userDAO.getAllUsers();

            request.setAttribute("posts", posts);
            request.setAttribute("user", user);// Changed attribute name to "posts"
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Post/PostList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting list of posts", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the list of posts.");
        }
    }

    private void UpdatePost(HttpServletRequest request, HttpServletResponse response, int pid, String submit)
            throws ServletException, IOException {
        try {
            PostDAO dao = new PostDAO();
            if (submit != null) {
                String postId = request.getParameter("post_id");
                String Title = request.getParameter("title");
                String Sum = request.getParameter("summary");
                String Thumbnail = request.getParameter("thumbnail_url");
                String Content = request.getParameter("content");
                String statusParam = request.getParameter("status");
                boolean status = (statusParam != null && statusParam.equals("on"));
                String UserId = request.getParameter("user_id");

                // convert
                int postIdInt = Integer.parseInt(postId);
                int userIdInt = Integer.parseInt(UserId);

                //
                Post post = new Post(postIdInt, Title, Sum, Thumbnail, Content, status, userIdInt);
                dao.updatePost(post);

                // Redirect to a success page or display a success message
                String successMessage = "Post updated successfully.";
                request.setAttribute("successMessage", successMessage);
                response.sendRedirect("PostController"); // Replace "success.jsp" with your success page

                // If you want to send a redirect to another page after success, uncomment the following line
                // response.sendRedirect("PostController");
            } else {
                Post post = dao.getPostById(pid);
                request.setAttribute("post", post);
                request.getRequestDispatcher("WEB-INF/Post/UpdatePost.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid post ID format", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID format");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating post", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the post.");
        }
    }

    private void InsertPost(HttpServletRequest request, HttpServletResponse response, String submit, int user_id)
            throws ServletException, IOException {
        try {
            PostDAO dao = new PostDAO();
            if (submit != null && submit.equals("insertPost")) {
                String Title = request.getParameter("title");
                String Sum = request.getParameter("summary");
                String Thumbnail = request.getParameter("thumbnail_url");
                String Content = request.getParameter("content");
                String statusParam = request.getParameter("status");
                boolean status = (statusParam != null && statusParam.equals("on"));
                int author = user_id;

                // Create and set Post object
                Post post = new Post();
                post.setTitle(Title);
                post.setSummary(Sum);
                post.setThumbnailUrl(Thumbnail);
                post.setContent(Content);
                post.setStatus(status);
                post.setUser_id(author);

                // Add post to the database
                dao.addPost(post);

                // Redirect to PostController
                response.sendRedirect("PostController");
            } else {
                List<Post> posts = dao.getAllPosts();
                request.setAttribute("postList", posts);
                request.getRequestDispatcher("WEB-INF/Post/insertPost.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid post ID format", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID format");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error inserting post", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while inserting the post.");
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
