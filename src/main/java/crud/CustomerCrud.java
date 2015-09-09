package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerCrud {

    public static String readLastCustomerNumber() {
        PreparedStatement statement;
        Connection connection;
        String queryString;
        String lastCustomerNumber;
        ResultSet resultSet;
        try {
            connection = Crud.getInstance().getConnection();
            queryString = "SELECT * FROM customer WHERE customerNumber = (SELECT max(customerNumber) FROM customer )";
            statement = connection.prepareStatement(queryString);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                lastCustomerNumber = resultSet.getString("customerNumber");
                return lastCustomerNumber;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}