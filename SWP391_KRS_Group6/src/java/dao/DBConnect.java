package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

    protected Connection connection;
    
    // Define multiple sets of credentials
    private final String[][] credentials = {
        {"root", "Mysql-suck", "jdbc:mysql://127.0.0.1:3306/KRS_SWP?zeroDateTimeBehavior=CONVERT_TO_NULL"},
        {"root", "Simon@0410", "jdbc:mysql://127.0.0.1:3306/KRS_SWP?zeroDateTimeBehavior=CONVERT_TO_NULL"},
        {"root", "Simon0410@", "jdbc:mysql://127.0.0.1:3306/KRS_SWP?zeroDateTimeBehavior=CONVERT_TO_NULL"},
        // Add more credentials as needed
    };
    
    public DBConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            boolean connected = false;
            for (String[] credential : credentials) {
                try {
                    connection = DriverManager.getConnection(credential[2], credential[0], credential[1]);
                    connected = true;
                    System.out.println("Connected successfully with user: " + credential[0]);
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(DBConnect.class.getName()).log(Level.WARNING, "Failed to connect with user: " + credential[0], ex);
                }
            }
            if (!connected) {
                throw new Exception("Unable to connect to the database with any provided credentials.");
            }
        } catch (Exception ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
