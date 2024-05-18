/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author HuyPC
 */
public class User {
    int user_id;
    String user_name;
    String password;
    String email;
    String full_name;
    String phone;
    boolean gender;
    boolean age;
    boolean status;
    int role_id;

    public User(int user_id, String user_name, String password, String email, 
            String full_name, String phone, boolean gender, boolean age, 
            boolean status, int role_id) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.full_name = full_name;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.status = status;
        this.role_id = role_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isAge() {
        return age;
    }

    public void setAge(boolean age) {
        this.age = age;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", user_name=" + user_name + 
                ", password=" + password + ", email=" + email + ", full_name=" 
                + full_name + ", phone=" + phone + ", gender=" + gender 
                + ", age=" + age + ", status=" + status + ", role_id=" 
                + role_id + '}';
    }
    
    
}
