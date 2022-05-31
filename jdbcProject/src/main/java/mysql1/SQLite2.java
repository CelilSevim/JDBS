package mysql1;

import java.sql.*;
import java.util.Scanner;

public class SQLite2 {

    public static void main(String[] args) throws SQLException {
        Connection conn;
        Statement stmt;
        String url = "jdbc:sqlite:src/main/resources/data.sqlite";

        conn = DriverManager.getConnection(url);
        stmt = conn.createStatement();

        Scanner sc = new Scanner(System.in);
        System.out.print("Ulke Giriniz : ");
        String ulke = sc.nextLine();

        String sql = "SELECT * FROM personel WHERE country = '" + ulke + "'";

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println(
                    rs.getString("first_name") + "\t" +
                            rs.getString("last_name") + "\t" +
                            rs.getString("country") + "\t" +
                            rs.getString("age")
            );
        }
        stmt.close();
        conn.close();
    }
}
