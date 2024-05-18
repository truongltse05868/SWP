/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

    protected Connection connection;

    public DBConnect() {
        try {
            
//            input your database username, password and url here
            // anh em sửa thành connection của mình nhé. 
            String user = "root";
            String pass = "Simon041098@";
            String url = "jdbc:mysql://127.0.0.1:3306/DB_SWP391";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
