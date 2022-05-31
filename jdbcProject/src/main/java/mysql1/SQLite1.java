package mysql1;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.Scanner;

public class SQLite1 {


    Connection conn;
    Statement stmt;

    @BeforeClass
    public void beforeClass() throws SQLException {
        // veritabani connection
        String url = "jdbc:sqlite:src/main/resources/data.sqlite";
        String username = "root";
        String password = "";

        conn = DriverManager.getConnection(url);
        stmt = conn.createStatement();
    }


    @Test
    public void test1() throws SQLException {
        String sql = "SELECT * FROM personel " +
                "WHERE age>50 AND country LIKE 'u%'" +
                "ORDER BY first_name";

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println(
                    rs.getString(2) + "\t" +
                            rs.getString("last_name") + "\t" +
                            rs.getString("country") + "\t" +
                            rs.getString("age")
            );
        }

    }


    @AfterClass
    public void afterClass() throws SQLException {
        // connection kapatilacak
        stmt.close();
        conn.close();
    }


}
