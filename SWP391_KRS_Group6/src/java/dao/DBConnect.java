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
            //connection của Truonglt
            String user = "root";

//          String pass = "Mysql-suck"; //duc
//            String pass = "Simon@0410"; //máy công ty, máy dell
            String pass = "Simon0410@"; //PC
            String url = "jdbc:mysql://127.0.0.1:3306/KRS_SWP?zeroDateTimeBehavior=CONVERT_TO_NULL"; //máy dell
//            String url = "jdbc:mysql://127.0.0.1:3306/KRS_SWR?zeroDateTimeBehavior=CONVERT_TO_NULL";
//            String user = "b76e9fadcbb87f";
//            String pass = "3138654c";
//            String url = "jdbc:mysql://b76e9fadcbb87f:3138654c@us-cluster-east-01.k8s.cleardb.net/heroku_c1578a6530a02f2?reconnect=true"; //connect to DB on server
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
