/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    //get all post
    /**
     * Fetches all users from the database.
     *
     * @return a list of User objects
     */
    public List<Post> getAllPosts() {
        List<Post> sbjectList = new ArrayList<>();
        String query = "SELECT * FROM post"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Post posts = new Post(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("summary"),
                        rs.getString("thumbnail_url"),
                        rs.getString("content"),
                        rs.getBoolean("status"),
                        rs.getInt("user_id")
                );
                sbjectList.add(posts);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all class", e);
        }
        return sbjectList;
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
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
