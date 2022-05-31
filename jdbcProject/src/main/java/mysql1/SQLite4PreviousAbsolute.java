package mysql1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class SQLite4PreviousAbsolute {


    Connection conn;
    Statement stmt;

    @BeforeClass
    public void beforeClass() throws SQLException {
        // veritabani connection
        //String url = "jdbc:sqlite:src/main/resources/data.sqlite";
        String url = "jdbc:mysql://localhost:3306/database3";
        String username = "root";
        String password = "";

        conn = DriverManager.getConnection(url, username, password);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }


    @Test
    public void test1() throws SQLException {
        String sql = "SELECT * FROM personel " +
                "WHERE age>50 AND country LIKE 'u%'" +
                "ORDER BY first_name";

        ResultSet rs = stmt.executeQuery(sql);

        rs.next();
        System.out.println(
                rs.getString(2) + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getString("country") + "\t" +
                        rs.getString("age")
        );

        rs.next();
        System.out.println(
                rs.getString(2) + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getString("country") + "\t" +
                        rs.getString("age")
        );

        rs.previous();
        System.out.println(
                rs.getString(2) + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getString("country") + "\t" +
                        rs.getString("age")
        );

        rs.absolute(10); // 10. row'a yani 10. kayda git
        System.out.println(
                rs.getString(2) + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getString("country") + "\t" +
                        rs.getString("age")
        );


    }


    @AfterClass
    public void afterClass() throws SQLException {
        // connection kapatilacak
        stmt.close();
        conn.close();
    }


}
