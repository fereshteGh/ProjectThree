package Database;

import domainModel.LegalCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LegalCustomerDatabaseProcess {
    Database database;

    public boolean checkEconomyExistence(String economyCode) {

        PreparedStatement statement;
        Connection connection;
        String retrieveCustomer;
        ResultSet result;

        try {
            connection = database.getConnection();
            //read economy code
            retrieveCustomer = "SELECT * FROM legalCustomer ";
            statement = connection.prepareStatement(retrieveCustomer);
            result = statement.executeQuery();
            if (result.next()) {
                if (String.valueOf(result.getInt("economyId")) == economyCode) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public LegalCustomer addCustomer(LegalCustomer legalCustomer) {
        PreparedStatement statement;
        Connection connection;
        String insertCustomer;
        String insertCustomerNumber;
        String readCustomerNumber;
        ResultSet result;
        String customerNumber;
        try {
            connection = database.getConnection();
            //connect to database
            // database.connectDatabase();
            //insert customer number to database
            insertCustomerNumber = "INSERT INTO customer (customerNumber) VALUES ('" + legalCustomer.getCustomerNumber() + "')";
            statement = connection.prepareStatement(insertCustomerNumber);
            statement.executeUpdate();
            //read customer number
            readCustomerNumber = " SELECT customerNumber FROM customer WHERE customerNumber = " + legalCustomer.getCustomerNumber();
            statement = connection.prepareStatement(readCustomerNumber);
            result = statement.executeQuery();
            customerNumber = String.valueOf(result.getInt("customerNumber"));
            //insert customer to database
            insertCustomer = "INSERT INTO individualCustomer (firstName,lastName,birthDate,customerId,customerNumber) VALUES ('" + legalCustomer.getCompanyName() + "','" + legalCustomer.getRegisterDate() + "','" + legalCustomer.getEconomyId() + "','" + customerNumber + "')";
            statement = connection.prepareStatement(insertCustomer);
            statement.executeUpdate();
            return legalCustomer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public LegalCustomer updateCustomer(LegalCustomer legalCustomerCustomer) {
        PreparedStatement statement;
        Connection connection;
        String updateCustomer;
        String readCustomerNumber;
        ResultSet result;
        String customerNumber;
        try {
            connection = database.getConnection();
            //read customer number
            readCustomerNumber = " SELECT * FROM customer ";
            statement = connection.prepareStatement(readCustomerNumber);
            result = statement.executeQuery();
            customerNumber = String.valueOf(result.getInt("customerNumber"));
            //if customer number exists update
            if (customerNumber == legalCustomerCustomer.getCustomerNumber()) {
                updateCustomer = "UPDATE individualCustomer SET firstName = " + legalCustomerCustomer.getCompanyName() + ",birthDate = " + legalCustomerCustomer.getRegisterDate() + ",customerId = " + legalCustomerCustomer.getEconomyId() + "WHERE customerNumber = " + legalCustomerCustomer.getCustomerNumber();
                statement = connection.prepareStatement(updateCustomer);
                statement.executeUpdate();
                return legalCustomerCustomer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCustomer(LegalCustomer legalCustomerCustomer) {
        PreparedStatement statement;
        Connection connection;
        String deleteCustomer;
        String deleteCustomerNumber;
        String readCustomerNumber;
        ResultSet result;
        String customerNumber;
        try {
            connection = database.getConnection();
            //read customer number
            readCustomerNumber = " SELECT * FROM customer ";
            statement = connection.prepareStatement(readCustomerNumber);
            result = statement.executeQuery();
            customerNumber = String.valueOf(result.getInt("customerNumber"));
            //if customer number exists delete
            if (customerNumber == legalCustomerCustomer.getCustomerNumber()) {
                deleteCustomer = "DELETE FROM individualCustomer WHERE customerNumber = " + legalCustomerCustomer.getCustomerNumber();
                statement = connection.prepareStatement(deleteCustomer);
                statement.executeUpdate();
                //delete customer number from customer
                deleteCustomerNumber = "DELETE FROM customer WHERE customerNumber = " + legalCustomerCustomer.getCustomerNumber();
                statement = connection.prepareStatement(deleteCustomerNumber);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public LegalCustomer retrieveCustomer(LegalCustomer legalCustomer) {
        PreparedStatement statement;
        Connection connection;
        String retrieveCustomer;
        String readCustomerNumber;
        ResultSet result;
        String customerNumber;
        LegalCustomer legalCustomerRetrieved = null;
        try {
            connection = database.getConnection();
            //read customer number
            readCustomerNumber = " SELECT * FROM customer ";
            statement = connection.prepareStatement(readCustomerNumber);
            result = statement.executeQuery();
            customerNumber = String.valueOf(result.getInt("customerNumber"));
            //if customer number exists retrieve data
            if (customerNumber == legalCustomer.getCustomerNumber()) {
                retrieveCustomer = "SELECT * FROM legalCustomer WHERE customerNumber = " + legalCustomer.getCustomerNumber();
                statement = connection.prepareStatement(retrieveCustomer);
                result = statement.executeQuery();
                if (result.next()) {
                    legalCustomerRetrieved = new LegalCustomer(result.getString("companyName"), result.getDate("registerDate"), result.getString("economyId"));
                }
                return legalCustomerRetrieved;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
