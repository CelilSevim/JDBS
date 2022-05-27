package mysql1;

import java.sql.*;

public class MySQLConn1 {

    /*
        JDBS -> JAVA DATABASE CONNECTİON java ile veritabanına bağlanma


        ConnectionString

        Connection
        Statement
        RecordSet
     */

    public static void main(String[] args)  {

        // Veritabanına bağlanmak için connection oluşturuyorum

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/database3",
                    "root",
                    ""
            );

            // connection u oluşturduk
            Statement stmt = conn.createStatement();

            // İstenilen SQL sonucunu ResultSet aldık
            ResultSet rs = stmt.executeQuery("SELECT first_name,last_name,age FROM personel LIMIT 10");

            while (rs.next()){
                System.out.println(
                                rs.getString(1)+","+
                                rs.getString(2)+","+
                                        rs.getInt(3)
                );
            }

            // xamp çalıştırmayı unutma

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
