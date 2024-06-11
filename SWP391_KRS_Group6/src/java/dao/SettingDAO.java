/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.Setting;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HuyPC
 */
public class SettingDAO extends DBConnect {

    // Create
    public boolean addSetting(Setting setting) {
        String sql = "INSERT INTO setting (setting_name, type, description, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, setting.getSettingName());
            ps.setString(2, setting.getType());
            ps.setString(3, setting.getDescription());
            ps.setBoolean(4, setting.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Read
    public Setting getSettingById(int settingId) {
        String sql = "SELECT * FROM setting WHERE setting_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, settingId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Setting(
                        rs.getInt("setting_id"),
                        rs.getString("setting_name"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Update
    public boolean updateSetting(Setting setting) {
        String sql = "UPDATE setting SET setting_name = ?, type = ?, description = ?, status = ? WHERE setting_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, setting.getSettingName());
            ps.setString(2, setting.getType());
            ps.setString(3, setting.getDescription());
            ps.setBoolean(4, setting.getStatus());
            ps.setInt(5, setting.getSettingId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Delete
    public boolean deleteSetting(int settingId) {
        String sql = "DELETE FROM setting WHERE setting_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, settingId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Search
    public Vector<Setting> searchSettings(String keyword) {
        Vector<Setting> settings = new Vector<>();
        String sql = "SELECT * FROM setting WHERE setting_name LIKE ? OR type LIKE ? OR description LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String query = "%" + keyword + "%";
            ps.setString(1, query);
            ps.setString(2, query);
            ps.setString(3, query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                settings.add(new Setting(
                        rs.getInt("setting_id"),
                        rs.getString("setting_name"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return settings;
    }

    // Sort
    public Vector<Setting> getAllSettingsSortedBy(String column, String order) {
        Vector<Setting> settings = new Vector<>();
        String sql = "SELECT * FROM setting ORDER BY " + column + " " + order;
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                settings.add(new Setting(
                        rs.getInt("setting_id"),
                        rs.getString("setting_name"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return settings;
    }

    // Filter by status
    public Vector<Setting> getSettingsByStatus(boolean status) {
        Vector<Setting> settings = new Vector<>();
        String sql = "SELECT * FROM setting WHERE status = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                settings.add(new Setting(
                        rs.getInt("setting_id"),
                        rs.getString("setting_name"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return settings;
    }

    //truonglt add getallrole
    public List<Setting> getAllRole() {
        List<Setting> settings = new ArrayList<>();
        String query = "SELECT * FROM setting where type = 'Role';"; // Ensure table name matches the one in your database
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                settings.add(new Setting(
                        rs.getInt("setting_id"),
                        rs.getString("setting_name"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getBoolean("status")
                ));

            }
        } catch (SQLException e) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return settings;
    }
}
