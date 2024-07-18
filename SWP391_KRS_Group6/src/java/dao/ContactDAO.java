/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.Class;
import entity.User;
import entity.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ngs-MT305
 */
public class ContactDAO extends DBConnect{
    private static final Logger logger = Logger.getLogger(ContactDAO.class.getName());
    public List<Contact> getAllContact(){
        List<Contact> contactList = new ArrayList<>();
        String query = "SELECT * FROM contact"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Contact contact = new Contact(
                        rs.getInt("contact_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("subject"),
                        rs.getString("message"),
                        rs.getInt("type_id"),
                        rs.getBoolean("status")
                );
                contactList.add(contact);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all class", e);
        }
        return contactList;
    }
}
