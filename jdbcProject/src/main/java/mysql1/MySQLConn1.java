package mysql1;

import java.sql.*;

public class MySQLConn1 {

    /*
        JDBC ->
            veritabani yeri,
            kullanici,
            sifre


            ConnectionString

            Connection
            Statement
            RecordSet


     */


    public static void main(String[] args) {

        // Veritabanina baglanmak icin bir connection olusturuyorum
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/database3",
                    "root",
                    ""
            );

            // connnection'i oluturduk
            Statement stmt = conn.createStatement();

            // istenen SQL sonucunu ResultSet'e aliyoruz
            ResultSet rs = stmt.executeQuery("SELECT first_name, last_name, age FROM personel LIMIT 10");

            while (rs.next()){
                String name = rs.getString(1);
                String lastname = rs.getString(2);
                int age = rs.getInt("age");  // db'deki age integer olmali

                System.out.println(name + ", " + lastname + ", " + age);
            }
            // rs'de index 1 den baslar

            // veritabani baglantisini kapatiyoruz
            stmt.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





}
