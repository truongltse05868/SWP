/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Ngs-MT305
 */
public class Class_user {
    private int user_id;
    private int class_id;
    private String teacher_user;
    private boolean status;

    public Class_user() {
    }

    public Class_user(int user_id, int class_id, String teacher_user, boolean status) {
        this.user_id = user_id;
        this.class_id = class_id;
        this.teacher_user = teacher_user;
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getTeacher_user() {
        return teacher_user;
    }

    public void setTeacher_user(String teacher_user) {
        this.teacher_user = teacher_user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Class_user{" +
                "user_id=" + user_id +
                ", class_id=" + class_id +
                ", teacher_user='" + teacher_user + '\'' +
                ", status=" + status +
                '}';
    }
    
}
