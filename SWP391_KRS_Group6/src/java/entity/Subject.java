/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Simon
 */
public class Subject {
    private int subject_id;
    private String subject_code;
    private String subject_name;
    private String description;
    private boolean status;

    public Subject() {
    }

    public Subject(int subject_id, String subject_code, String subject_name, String description, boolean status) {
        this.subject_id = subject_id;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.description = description;
        this.status = status;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subject_id=" + subject_id +
                ", subject_code='" + subject_code + '\'' +
                ", subject_name='" + subject_name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
    
}
