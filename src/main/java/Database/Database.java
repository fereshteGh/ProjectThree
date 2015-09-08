package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";
    private static final String dbName = "customer";
    Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    public void connectDatabase() {

        try {
            Class.forName(JDBC_DRIVER).newInstance();
            System.out.println("Connecting to database.....");
            connection = DriverManager.getConnection(DB_URL + dbName+"?useUnicode=true&characterEncoding=UTF-8");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found....");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}


