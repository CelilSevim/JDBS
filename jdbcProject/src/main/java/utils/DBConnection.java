package utils;

import java.sql.*;
import java.util.List;

public class DBConnection {

    Connection conn;
    Statement stmt;

    public DBConnection(String url, String username, String password) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
