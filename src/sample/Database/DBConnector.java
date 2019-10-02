package sample.Database;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

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

    //Method to execute CRUD operations to database
    public static ResultSet dbExecuteQuery(String sqlstmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlstmt);
        } catch (SQLException e) {
            System.out.println("Error during executing database Query" +e);
            e.printStackTrace();
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return null;
    }

    //The method below will return a ResultSet to retrieving the records from the database
    public static ResultSet dbExecute(String sqlQuery) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet rst = null;
        CachedRowSetImpl crs = null;

        try {
            getConnection();
            stmt = connection.createStatement();
            rst = stmt.executeQuery(sqlQuery);
            crs = new CachedRowSetImpl();
            crs.populate(rst);
        } catch (SQLException e) {
            System.out.println("Problem at dbExecute operation" + e);
            throw e;
        } finally {
            if (rst != null) {
                rst.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }
}
