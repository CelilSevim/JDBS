package mysql1;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class MySQLConn2TestNG {


    Connection conn;
    Statement stmt;


    @BeforeClass
    public void beforeClass() throws SQLException {
        // veritabanı connection

        String url ="jdbc:mysql://localhost:3306/database3";
        String username = "root";
        String pass= "";

        conn = DriverManager.getConnection(url,username,pass);
        stmt = conn.createStatement();
    }

    @Test
    public void test1() throws SQLException {
        String sql ="SELECT * FROM personel WHERE age>50 AND country LIKE 'u%'"+"ORDER BY first_name";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next(); // ilk kayda gittik
        Assert.assertEquals(rs.getString(2),"Anselm");

        rs.next(); // sonraki kayda geçtik
        Assert.assertEquals(rs.getString(6),"Ukraine");

    }
    @Test
    public void test2() throws SQLException {
        String sql ="SELECT country,gender, COUNT(*) AS count FROM personel "+"GROUP BY country,gender "+
                "ORDER BY country, gender";
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData(); //reseltset hakkında gerekli bilgileri alma örnek kolon sayısı vs
        int colCount=rsmd.getColumnCount();
        System.out.println(colCount); // kolon sayısı

        for (int i = 1; i <= colCount; i++) {
            System.out.println(rsmd.getColumnLabel(i)+","+rsmd.getColumnTypeName(i));
            System.out.printf("%-10s %-10s %-5s\n","a","b","c");  //printf fonksiyonu boşluklu yazmak için
        }

    }
    @Test
    public void test3() throws SQLException {
        // tablo formatında yazdırdık


        String sql ="SELECT country,gender, COUNT(*) AS count FROM personel "+"GROUP BY country,gender "+
                "ORDER BY country, gender";
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int col1 =rsmd.getColumnDisplaySize(1);
        int col2 =rsmd.getColumnDisplaySize(2)+3;
        int col3 =rsmd.getColumnDisplaySize(3);

        String strFormat="%-"+col1+"s %-"+col2 + "s %-"+col3+"s\n"; // boşluk için

        String title1 = rsmd.getColumnLabel(1);
        String title2 = rsmd.getColumnLabel(2);
        String title3 = rsmd.getColumnLabel(3);
        System.out.printf(strFormat, title1 , title2, title3); //başlıklar

        while (rs.next()){
            System.out.printf(strFormat,rs.getString(1),rs.getString(2),rs.getString(3));
        }
    }







    @AfterClass
    public void afterclass() throws SQLException {
        stmt.close();
        conn.close();

    }
}
