package Database;

import domainModel.IndividualCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndividualCustomerDatabaseProcess {
    Database database;

    public boolean checkCustomerIDExistence(String customerId) {

        PreparedStatement statement;
        Connection connection;
        String retrieveCustomer;
        ResultSet result;

        try {
            connection = database.getConnection();
            //read customer id

            retrieveCustomer = "SELECT * FROM Individual ";
            statement = connection.prepareStatement(retrieveCustomer);
            result = statement.executeQuery();
            if (result.next()) {
                if (String.valueOf(result.getInt("customerId")) == customerId) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public IndividualCustomer addCustomer(IndividualCustomer individualCustomer) {
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
            database.connectDatabase();
            //insert customer number to database
            insertCustomerNumber = "INSERT INTO customer (customerNumber) VALUES ('" + individualCustomer.getCustomerNumber() + "')";
            statement = connection.prepareStatement(insertCustomerNumber);
            statement.executeUpdate();
            //read customer number
            readCustomerNumber = " SELECT customerNumber FROM customer WHERE customerNumber = " + individualCustomer.getCustomerNumber();
            statement = connection.prepareStatement(readCustomerNumber);
            result = statement.executeQuery();
            customerNumber = String.valueOf(result.getInt("customerNumber"));
            //insert customer to database
            insertCustomer = "INSERT INTO individualCustomer (firstName,lastName,birthDate,customerId,customerNumber) VALUES ('" + individualCustomer.getFirstName() + "','" + individualCustomer.getLastName() + "','" + individualCustomer.getBirthDate() + "','" + individualCustomer.getCustomerId() + "','" + customerNumber + "')";
            statement = connection.prepareStatement(insertCustomer);
            statement.executeUpdate();
            return individualCustomer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public IndividualCustomer updateCustomer(IndividualCustomer individualCustomer) {
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
            //if customer number exists update data
            if (customerNumber == individualCustomer.getCustomerNumber()) {
                updateCustomer = "UPDATE individualCustomer SET firstName = " + individualCustomer.getFirstName() + ",lastName =" + individualCustomer.getLastName() + ",birthDate = " + individualCustomer.getBirthDate() + ",customerId = " + individualCustomer.getCustomerId() + "WHERE customerNumber = " + individualCustomer.getCustomerNumber();
                statement = connection.prepareStatement(updateCustomer);
                statement.executeUpdate();
                return individualCustomer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCustomer(IndividualCustomer individualCustomer) {
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
            //if customer number exists delete data
            if (customerNumber == individualCustomer.getCustomerNumber()) {
                deleteCustomer = "DELETE FROM individualCustomer WHERE customerNumber = " + individualCustomer.getCustomerNumber();
                statement = connection.prepareStatement(deleteCustomer);
                statement.executeUpdate();
                //delete customer number from customer
                deleteCustomerNumber = "DELETE FROM customer WHERE customerNumber = " + individualCustomer.getCustomerNumber();
                statement = connection.prepareStatement(deleteCustomerNumber);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public IndividualCustomer retrieveCustomer(IndividualCustomer individualCustomer) {
        PreparedStatement statement;
        Connection connection;
        String retrieveCustomer;
        String readCustomerNumber;
        ResultSet result;
        String customerNumber;
        IndividualCustomer individualCustomerRetrieved = null;
        try {
            connection = database.getConnection();
            //read customer number
            readCustomerNumber = " SELECT * FROM customer ";
            statement = connection.prepareStatement(readCustomerNumber);
            result = statement.executeQuery();
            customerNumber = String.valueOf(result.getInt("customerNumber"));
            //if customer number exists retrieve data
            if (customerNumber == individualCustomer.getCustomerNumber()) {
                retrieveCustomer = "SELECT * FROM individualCustomer WHERE customerNumber = " + individualCustomer.getCustomerNumber();
                statement = connection.prepareStatement(retrieveCustomer);
               result= statement.executeQuery();
                if(result.next()){
                   individualCustomerRetrieved = new IndividualCustomer(result.getString("firstName"),result.getString("lastName"),result.getDate("birthDate"),result.getString("customerId"));
                }
                return individualCustomerRetrieved;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
