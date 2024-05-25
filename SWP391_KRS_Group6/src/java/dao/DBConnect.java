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
//          String pass = "Simon041098@"; //mạc bục
            String pass = "Simon0410@"; //maý nhà truonglt
//          String pass = "Simon@0410"; //máy công ty
            String url = "jdbc:mysql://127.0.0.1:3306/KRS_SWR?zeroDateTimeBehavior=CONVERT_TO_NULL";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
//            //ae tạo thêm connection của mình rồi thêm vào, comment connection của người khác đi nhé.
//            String user = "root";
//            String pass = "Simon041098@";
//            String url = "jdbc:mysql://localhost:3306/KRS_SWR?zeroDateTimeBehavior=CONVERT_TO_NULL";
//            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (Exception ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
