/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.SettingDAO;
import entity.Setting;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Ngs-MT305
 */
public class SettingService extends BaseService {

    private static final Logger logger = Logger.getLogger(SettingDAO.class.getName());
    private final SettingDAO settingDAO;

    public SettingService() {
        this.settingDAO = new SettingDAO();
    }

    public List<Setting> getAllRoles() {
        return settingDAO.getAllRole();
    }
    public Setting getSettingById(int id){
        return settingDAO.getSettingById(id);
    }
    public List<Setting> getAllContactType(){
        return settingDAO.getALlContactType();
    }
}
