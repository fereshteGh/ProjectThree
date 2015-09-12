package crud;

import entities.LegalCustomerEntities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LegalCustomerCrud {

    public static boolean checkEconomyExistence(String economyCode) {

        PreparedStatement statement;
        Connection connection;
        String retrieveCustomer;
        ResultSet result;

        try {
            connection = Crud.getInstance().getConnection();
            //read economy code
            retrieveCustomer = "SELECT * FROM legalCustomer WHERE customerNumber = ?";
            statement = connection.prepareStatement(retrieveCustomer);
            statement.setString(1, economyCode);
            result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (
                SQLException e
                ) {
            e.printStackTrace();
        }
        return false;
    }

    public static LegalCustomerEntities addCustomer(LegalCustomerEntities legalCustomer) {
        PreparedStatement statement;
        Connection connection;
        String insertCustomer;
        String insertCustomerNumber;
        try {
            //connect to database
            connection = Crud.getInstance().getConnection();
            //insert customer number to database
            insertCustomerNumber = "INSERT INTO customer (customerNumber) VALUES (?)";
            statement = connection.prepareStatement(insertCustomerNumber);
            statement.setString(1, legalCustomer.getCustomerNumber());
            statement.executeUpdate();
            //insert customer to database
            insertCustomer = "INSERT INTO individualCustomer (companyName,registerDate,economyCode,customerNumber) VALUES (?,?,?,?)";
            statement = connection.prepareStatement(insertCustomer);
            statement.setString(1, legalCustomer.getCompanyName());
            statement.setString(2, legalCustomer.getRegisterDate());
            statement.setString(3, legalCustomer.getEconomyId());
            statement.setString(4, legalCustomer.getCustomerNumber());
            statement.executeUpdate();
            return legalCustomer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LegalCustomerEntities updateCustomer(LegalCustomerEntities legalCustomer) {
        PreparedStatement statement;
        Connection connection;
        String queryString;
        try {
            connection = Crud.getInstance().getConnection();
            queryString = "UPDATE individualCustomer SET firstName = ? ,birthDate = ? ,economyCode = ? WHERE customerNumber = ? ";
            statement = connection.prepareStatement(queryString);
            statement.setString(1, legalCustomer.getCompanyName());
            statement.setString(2, legalCustomer.getRegisterDate());
            statement.setString(3, legalCustomer.getEconomyId());
            statement.setString(4, legalCustomer.getCustomerNumber());
            statement.executeUpdate();
            return legalCustomer;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteCustomer(LegalCustomerEntities legalCustomerCustomer) {
        PreparedStatement statement;
        Connection connection;
        String queryString;
        try {
            connection = Crud.getInstance().getConnection();
            queryString = "DELETE FROM individualCustomer WHERE customerNumber = ?";
            statement = connection.prepareStatement(queryString);
            statement.setString(1, legalCustomerCustomer.getCustomerNumber());
            statement.executeUpdate();
            //delete customer number from customer
            queryString = "DELETE FROM customer WHERE customerNumber = ?";
            statement = connection.prepareStatement(queryString);
            statement.setString(1, legalCustomerCustomer.getCustomerNumber());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static LegalCustomerEntities searchCustomer(LegalCustomerEntities legalCustomer) {
        PreparedStatement statement;
        Connection connection;
        String queryString;
        ResultSet result;
        LegalCustomerEntities legalCustomerRetrieved = new LegalCustomerEntities();
        try {
            connection = Crud.getInstance().getConnection();
            queryString = "SELECT * FROM legalCustomer WHERE customerNumber = ?";
            statement = connection.prepareStatement(queryString);
            statement.setString(1, legalCustomer.getCustomerNumber());
            result = statement.executeQuery();
            if (result.next()) {
            //    legalCustomerRetrieved.setCompanyName();

            }
            return legalCustomerRetrieved;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
