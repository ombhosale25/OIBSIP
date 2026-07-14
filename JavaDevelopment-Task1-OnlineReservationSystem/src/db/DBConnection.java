package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    // Database URL
    private static final String URL =
            "jdbc:mysql://localhost:3306/reservation_system";

    // MySQL Username
    private static final String USER = "root";

    // MySQL Password
    private static final String PASSWORD = "root";

    public static Connection getConnection() {

        try {

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connected Successfully!");

            return con;

        } catch (Exception e) {

            System.out.println("Connection Failed!");

            e.printStackTrace();

            return null;
        }
    }

}