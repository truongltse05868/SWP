/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author HuyPC
 */
public class Setting {

    private int settingId;
    private String settingName;
    private String type;
    private String description;
    private Boolean status;

    public Setting(int settingId, String settingName, String type, String description, Boolean status) {
        this.settingId = settingId;
        this.settingName = settingName;
        this.type = type;
        this.description = description;
        this.status = status;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    
    public Setting() {
    }

    @Override
    public String toString() {
        return "Setting{" + "settingId=" + settingId + ", settingName=" + settingName + ", type=" + type + ", description=" + description + ", status=" + status + '}';
    }

    
}
