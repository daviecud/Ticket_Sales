package sample.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection = null;
    private static final String serverString = "jdbc:mysql://localhost:3306/ticketsales";

    //Method to get connection to MySql database and reports any exception handling
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        //setup connection to mysql
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error with JDBC Driver");
            e.printStackTrace();
            throw e;
        }

        System.out.println("JDBC Driver loaded successfully");

        //set schema connection, authentication, and error handling
        try {
            connection = DriverManager.getConnection(serverString, "root", "WeNeed4BiggerB0at");
        } catch (SQLException e) {
            System.out.println("Connection to database Failed!!, check output console" + e);
            throw e;
        }
        return connection;
    }

    //This method is called to disconnect session once user has left program
    public static void dbDisconnect() throws SQLException {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
