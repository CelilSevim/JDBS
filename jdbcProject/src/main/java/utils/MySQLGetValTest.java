package utils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.*;

public class MySQLGetValTest {

    Connection conn;
    Statement stmt;

    @BeforeClass
    public void beforeClass() throws SQLException {
        // veritabani connection
        String url = "jdbc:mysql://localhost:3306/database3";
        String username = "root";
        String password = "";

        conn = DriverManager.getConnection(url, username, password);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    @Test
    public void test5() throws SQLException {

        // Provide full path for directory(change accordingly)
        String maindirpath = "D:\\progs";

        // File object
        File maindir = new File(maindirpath);

        if (maindir.exists() && maindir.isDirectory()) {

            // array for files and sub-directories of directory pointed by maindir
            File arr[] = maindir.listFiles();

            System.out.println("**********************************************");
            System.out.println("Files from main directory : " + maindir);
            System.out.println("**********************************************");

            // Calling recursive method
            RecursivePrint(arr, 0, 0);
        }

    }

    @Test
    public void testAbsolute() throws SQLException {
        String sql = "SELECT * FROM categories";
        ResultSet rs = stmt.executeQuery(sql);
        rs.absolute(1);
        System.out.println(rs.getString(3));
        rs.absolute(3);
        System.out.println(rs.getString(3));
        rs.absolute(5);
        System.out.println(rs.getString(3));
        rs.absolute(7);
        System.out.println(rs.getString(3));
    }


    @Test
    public void testDB() throws SQLException {
        String sql = "SELECT * FROM categories";
        ResultSet rs = stmt.executeQuery(sql);
        RecursivePrint(rs, 0, 0);
    }

    @AfterClass
    public void afterClass() throws SQLException {
        // connection kapatilacak
        stmt.close();
        conn.close();
    }

    static void RecursivePrint(ResultSet arr, int index, int level) throws SQLException {
        // terminate condition
        if (index == arr.getRow())
            return;

        // tabs for internal levels
        for (int i = 0; i < level; i++)
            System.out.print("\t");

        // for files
        if (arr.absolute(index))
            System.out.println(arr.absolute(index));

            // for sub-directories
        else if (arr.absolute(index)) {
            System.out.println("[" + arr.absolute(index) + "]");

            // recursion for sub-directories
            RecursivePrint(arr, index+1, level + 1);
        }

        // recursion for main directory
        RecursivePrint(arr, ++index, level);
    }


    static void RecursivePrint(File[] arr, int index, int level) {
        // terminate condition
        if (index == arr.length)
            return;

        // tabs for internal levels
        for (int i = 0; i < level; i++)
            System.out.print("\t");

        // for files
        if (arr[index].isFile())
            System.out.println(arr[index].getName());

            // for sub-directories
        else if (arr[index].isDirectory()) {
            System.out.println("[" + arr[index].getName() + "]");

            // recursion for sub-directories
            RecursivePrint(arr[index].listFiles(), 0,level + 1);
        }

        // recursion for main directory
        RecursivePrint(arr, ++index, level);
    }

}
