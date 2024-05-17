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
            
            String user = "sa";
            String pass = "123";
            String url = "jdbc:sqlserver://DESKTOP-L396LB5\\SQLEXPRESS:1433;databaseName=yourdatabasename";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
