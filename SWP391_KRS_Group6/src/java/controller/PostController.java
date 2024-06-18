package controller;

import dao.PostDAO;
import dao.UserDAO;
import entity.Post;
import entity.User;
import service.BaseService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HuyPC
 */
public class PostController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(PostController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            User currentUser = (User) session.getAttribute("account");
            String action = request.getParameter("service");
            if (action == null) {
                action = "ListAllPost";
            }
            String submit = request.getParameter("submit");

            if (currentUser == null) {
                switch (action) {
                    case "BlogList" ->
                        getAllBlog(request, response);
                    case "BlogDetail" -> {
                        int bid = Integer.parseInt(request.getParameter("pid"));
                        getBlogDetail(request, response, bid);
                    }
                    default ->
                        response.sendRedirect("Home");
                }
                return;
            }
            if (currentUser != null && currentUser.getRole_id() == 1) {
                switch (action) {
                    case "ListAllPost" ->
                        getAllPost(request, response);
                    case "updatePost" -> {
                        int pid = Integer.parseInt(request.getParameter("pid"));
                        UpdatePost(request, response, pid, submit);
                    }
                    case "insertPost" -> {
                        int Author = currentUser.getUser_id();
                        InsertPost(request, response, submit, Author);
                    }
                    case "toggleStatus" -> {
                        PostDAO dao = new PostDAO();
                        int pid = Integer.parseInt(request.getParameter("postId"));
                        Post post = dao.getPostById(pid);
                        if (post != null) {
                            post.setStatus(!post.isStatus()); // Toggle status
                            dao.updatePost(post);
                        }
                        response.sendRedirect("PostController?service=ListAllPost");
                    }
                    case "BlogList" ->
                        getAllBlog(request, response);
                    case "BlogDetail" -> {
                        int bid = Integer.parseInt(request.getParameter("pid"));
                        getBlogDetail(request, response, bid);
                    }
                    default -> {
                        response.sendRedirect("Home");
                    }
                }
            } else {
                switch (action) {
                    case "insertPost" -> {
                        int Author = currentUser.getUser_id();
                        InsertPost(request, response, submit, Author);
                    }
                    case "BlogList" ->
                        getAllBlog(request, response);
                    case "BlogDetail" -> {
                        int bid = Integer.parseInt(request.getParameter("pid"));
                        getBlogDetail(request, response, bid);
                    }
                    default -> {
                        response.sendRedirect("Home");
                    }
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

    void dispatch(HttpServletRequest request,
            HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher dispatch
                = request.getRequestDispatcher(page);
        dispatch.forward(request, response);
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

    private void getAllPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PostDAO postDAO = new PostDAO();
            UserDAO userDAO = new UserDAO();

            int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
            int pageSize = Integer.parseInt(request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "3");
            String sortBy = request.getParameter("sortBy") != null ? request.getParameter("sortBy") : "post_id";
            String sortOrder = request.getParameter("sortOrder") != null ? request.getParameter("sortOrder") : "ASC";
            String keyword = request.getParameter("keyword");

            List<Post> posts;
            if (keyword != null && !keyword.trim().isEmpty()) {
                posts = postDAO.searchPosts(keyword, sortBy, sortOrder, page, pageSize);
            } else {
                posts = postDAO.getAllPostsSortedBy(sortBy, sortOrder, page, pageSize);
            }
            List<User> users = userDAO.getAllUsers();

            request.setAttribute("posts", posts);
            request.setAttribute("users", users);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("sortBy", sortBy);
            request.setAttribute("sortOrder", sortOrder);
            request.setAttribute("keyword", keyword);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Post/PostList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting list of posts", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the list of posts.");
        }
    }

    private void getAllBlog(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PostDAO postDAO = new PostDAO();
            UserDAO userDAO = new UserDAO();

            int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
            int pageSize = Integer.parseInt(request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "2");
            String sortBy = request.getParameter("sortBy") != null ? request.getParameter("sortBy") : "post_id";
            String sortOrder = request.getParameter("sortOrder") != null ? request.getParameter("sortOrder") : "ASC";
            String keyword = request.getParameter("keyword");

            List<Post> posts;
            if (keyword != null && !keyword.trim().isEmpty()) {
                posts = postDAO.searchBlog(keyword, sortBy, sortOrder, page, pageSize);
            } else {
                posts = postDAO.getAllBlogsSortedBy(sortBy, sortOrder, page, pageSize);
            }
            List<User> users = userDAO.getAllUsers();

            request.setAttribute("posts", posts);
            request.setAttribute("user", users);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("sortBy", sortBy);
            request.setAttribute("sortOrder", sortOrder);
            request.setAttribute("keyword", keyword);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Post/BlogDisplay.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting list of posts", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while getting the list of posts.");
        }
    }

    private void getBlogDetail(HttpServletRequest request, HttpServletResponse response, int pid)
            throws ServletException, IOException {
        try {
            PostDAO dao = new PostDAO();
            Post post = dao.getPostById(pid);
            UserDAO userDAO = new UserDAO();
            List<User> user = userDAO.getAllUsers();
            request.setAttribute("user", user);
            request.setAttribute("post", post);

            request.getRequestDispatcher("WEB-INF/Post/BlogDetail.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid post ID format", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID format");
        } catch (ServletException | IOException e) {
            logger.log(Level.SEVERE, "Error view detail of blog", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while view detail of blog.");
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
                int author = user_id;
                String title = request.getParameter("title");
                String summary = request.getParameter("summary");
                String thumbnail = request.getParameter("thumbnail_url");
                String content = request.getParameter("content");

                // Validate inputs
                if (title == null || title.isEmpty() || summary == null || summary.isEmpty()
                        || thumbnail == null || thumbnail.isEmpty() || content == null || content.isEmpty()) {
                    request.setAttribute("errorMessage", "All fields are required.");
                    request.getRequestDispatcher("WEB-INF/Post/insertPost.jsp").forward(request, response);
                    return;
                }

                boolean status = false; // Default status for non-admin users
                if (author == 1) {
                    String statusParam = request.getParameter("status");
                    status = (statusParam != null && statusParam.equals("on"));
                }

                Post post = new Post(0, title, summary, thumbnail, content, status, author);
                dao.addPost(post);

                String successMessage = (author == 1) ? "Post added successfully." : "Post added successfully! The admin will publish it soon.";
                request.setAttribute("successMessage", successMessage);

                // Fetch and set the list of posts
                List<Post> posts = dao.getAllPosts();
                request.setAttribute("postList", posts);

                if (author == 1) {
                    request.getRequestDispatcher("WEB-INF/Post/PostList.jsp").forward(request, response);
                } else {
                    UserDAO udao = new UserDAO();
                    List<User> adminUsers = udao.getUserByColumn("role_id", "1"); // Assuming this method returns a list of users with role_id 1

                    if (adminUsers != null && !adminUsers.isEmpty()) {
                        for (User admin : adminUsers) {
                            String email = admin.getEmail();
                            boolean emailExists = udao.checkEmailExists(email);

                            if (emailExists) {
                                User au = udao.getUserById(author);
                                String subject = "REQUEST Publish New Post";
                                String emailContent = String.format(
                                        "Dear publisher %s,\n\n"
                                        + "A new post has been submitted for publication on EduChamp.\n"
                                        + "Post title: %s by %s\n\n"
                                        + "We kindly request that you review the post and consider it for publication. "
                                        + "If any revisions are required, please let us know, and we will inform the author accordingly.\n"
                                        + "\n"
                                        + "Thank you for your attention to this request. We look forward to your feedback and the potential publication of this user-submitted content.\n"
                                        + "\n"
                                        + "Best regards,\n"
                                        + "EduChamp",
                                        admin.getFull_name(), post.getTitle(), au.getFull_name());

                                BaseService.sendEmail(email, subject, emailContent);
                            } else {
                                request.setAttribute("errorMessage", "Admin email address does not exist.");
                            }
                        }
                    } else {
                        request.setAttribute("errorMessage", "Admin user not found.");
                    }

                    request.getRequestDispatcher("WEB-INF/Post/BlogDisplay.jsp").forward(request, response);
                }
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
