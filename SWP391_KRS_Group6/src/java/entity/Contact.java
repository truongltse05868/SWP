/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author simon
 */
public class Contact {
    int contact_id;
    String full_name;
    String email;
    String phone;
    String subject;
    String message;
    int type_id;
    boolean status;

    public Contact() {
    }

    public Contact(int contact_id, String full_name, String email, String phone, String subject, String message, int type_id, boolean status) {
        this.contact_id = contact_id;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.subject = subject;
        this.message = message;
        this.type_id = type_id;
        this.status = status;
    }
    public Contact(String full_name, String email, String phone, String subject, String message, int type_id, boolean status) {
        
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.subject = subject;
        this.message = message;
        this.type_id = type_id;
        this.status = status;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Contact{" + "contact_id=" + contact_id + ", full_name=" 
                + full_name + ", email=" + email + ", phone=" + phone 
                + ", subject=" + subject + ", message=" + message 
                + ", type_id=" + type_id + ", status=" + status + '}';
    }
    
    
}
