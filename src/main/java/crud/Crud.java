package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Crud {
    private static Crud instance = null;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";
    private static final String dbName = "customers";
    Connection connection;

    public static Crud getInstance() {
        if (instance == null) {
            instance = new Crud();
        }
        return instance;
    }

    private Crud() {
        try {
            Class.forName(JDBC_DRIVER);
            this.connection = DriverManager.getConnection(DB_URL + dbName + "?useUnicode=true&characterEncoding=UTF-8", "root", "root");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found....");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}





