package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDatabaseProcess {

    Database database;

    public String readLastCustomerNumber() {
        PreparedStatement statement;
        Connection connection;

        String customerNumber1;
        String customerNumber2;
        ResultSet result;

        try {
            connection = database.getConnection();
            database.connectDatabase();
            customerNumber1 = "SELECT * FROM customer ORDER BY customerNumber DESC LIMIT 1";
            statement = connection.prepareStatement(customerNumber1);
            result = statement.executeQuery();
            if (result.next()) {
                customerNumber2 = String.valueOf(result.getInt("customerNumber"));
                return customerNumber2;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
