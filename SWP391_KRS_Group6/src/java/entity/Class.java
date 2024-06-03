/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Simon
 */
public class Class {
    private int class_id;
    private int subject_id;
    private boolean status;

    public Class() {
    }

    public Class(int class_id, int subject_id, boolean status) {
        this.class_id = class_id;
        this.subject_id = subject_id;
        this.status = status;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Class{" +
                "class_id=" + class_id +
                ", subject_id=" + subject_id +
                ", status=" + status +
                '}';
    }

    
    
}
