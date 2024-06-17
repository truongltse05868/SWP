package dao;

import entity.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ngs-MT305
 */
public class PostDAO extends DBConnect {

    private static final Logger logger = Logger.getLogger(PostDAO.class.getName());

    public List<Post> getAllPosts() {
        List<Post> postList = new ArrayList<>();
        String query = "SELECT * FROM post WHERE status = 1";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("summary"),
                        rs.getString("thumbnail_url"),
                        rs.getString("content"),
                        rs.getBoolean("status"),
                        rs.getInt("user_id")
                );
                postList.add(post);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all posts", e);
        }
        return postList;
    }

    public boolean updatePost(Post post) {
        String sql = "UPDATE post\n"
                + "SET title = ?,\n"
                + "    summary = ?,\n"
                + "    thumbnail_url = ?,\n"
                + "    content = ?,\n"
                + "    status = ?,\n"
                + "    user_id = ?\n"
                + "WHERE post_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getSummary());
            ps.setString(3, post.getThumbnailUrl());
            ps.setString(4, post.getContent());
            ps.setBoolean(5, post.isStatus());
            ps.setInt(6, post.getUser_id());
            ps.setInt(7, post.getPostId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating post", ex);
        }
        return false;
    }

    public boolean addPost(Post post) {
        String sql = "INSERT INTO post (title, summary, thumbnail_url, content, status, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getSummary());
            ps.setString(3, post.getThumbnailUrl());
            ps.setString(4, post.getContent());
            ps.setBoolean(5, post.isStatus());
            ps.setInt(6, post.getUser_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error adding post", ex);
        }
        return false;
    }

    public Post getPostById(int postId) {
        String sql = "SELECT * FROM post WHERE post_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Post(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("summary"),
                        rs.getString("thumbnail_url"),
                        rs.getString("content"),
                        rs.getBoolean("status"),
                        rs.getInt("user_id")
                );
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching post by ID", ex);
        }
        return null;
    }

    // Search posts by keyword in title or summary
    public List<Post> searchBlog(String keyword, String column, String order, int page, int pageSize) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post WHERE (title LIKE ? OR summary LIKE ?) AND status =1 "
                + "ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String query = "%" + keyword + "%";
            ps.setString(1, query);
            ps.setString(2, query);
            ps.setInt(3, pageSize);
            ps.setInt(4, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                posts.add(new Post(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("summary"),
                        rs.getString("thumbnail_url"),
                        rs.getString("content"),
                        rs.getBoolean("status"),
                        rs.getInt("user_id")
                ));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error searching posts", ex);
        }
        return posts;
    }

    // Get all posts sorted by a specific column with pagination
    public List<Post> getAllBlogsSortedBy(String column, String order, int page, int pageSize) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post WHERE status =1 ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                posts.add(new Post(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("summary"),
                        rs.getString("thumbnail_url"),
                        rs.getString("content"),
                        rs.getBoolean("status"),
                        rs.getInt("user_id")
                ));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching sorted posts", ex);
        }
        return posts;
    }

    public List<Post> searchPosts(String keyword, String column, String order, int page, int pageSize) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post WHERE title LIKE ? OR summary LIKE ? ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String query = "%" + keyword + "%";
            ps.setString(1, query);
            ps.setString(2, query);
            ps.setInt(3, pageSize);
            ps.setInt(4, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                posts.add(new Post(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("summary"),
                        rs.getString("thumbnail_url"),
                        rs.getString("content"),
                        rs.getBoolean("status"),
                        rs.getInt("user_id")
                ));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error searching posts", ex);
        }
        return posts;
    }

    // Get all posts sorted by a specific column with pagination
    public List<Post> getAllPostsSortedBy(String column, String order, int page, int pageSize) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post ORDER BY " + column + " " + order + " LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                posts.add(new Post(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("summary"),
                        rs.getString("thumbnail_url"),
                        rs.getString("content"),
                        rs.getBoolean("status"),
                        rs.getInt("user_id")
                ));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching sorted posts", ex);
        }
        return posts;
    }

}
