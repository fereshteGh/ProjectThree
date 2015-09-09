package crud;

import entities.IndividualCustomerEntities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IndividualCustomerCrud {
    public static boolean checkNationalCodeExistence(String nationalCode) {
        PreparedStatement statement;
        Connection connection;
        String queryString;
        ResultSet result;
        try {
            connection = Crud.getInstance().getConnection();
            //read customer id
            queryString = "SELECT * FROM individualCustomer  WHERE nationalCode = ?";
            statement = connection.prepareStatement(queryString);
            statement.setString(1, nationalCode);
            result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static IndividualCustomerEntities addCustomer(IndividualCustomerEntities individualCustomer) {
        PreparedStatement statement;
        Connection connection;
        String queryString;
        ResultSet result;
        String customerNumber;
        try {
            connection = Crud.getInstance().getConnection();
            queryString = "INSERT INTO customer(customerNumber) VALUES ( ? )";
            statement = connection.prepareStatement(queryString);
            statement.setString(1, individualCustomer.getCustomerNumber());
            statement.executeUpdate();
            //insert customer to database
            queryString = "INSERT INTO individualCustomer (firstName,lastName,birthDate,nationalCode,customerNumber) VALUES (?,?,?,?,?)";
            statement = connection.prepareStatement(queryString);
            statement.setString(1, individualCustomer.getFirstName());
            statement.setString(2, individualCustomer.getLastName());
            statement.setString(3, individualCustomer.getBirthDate());
            statement.setString(4, individualCustomer.getNationalCode());
            statement.setString(5, individualCustomer.getCustomerNumber());
            statement.executeUpdate();
            return individualCustomer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IndividualCustomerEntities updateCustomer(IndividualCustomerEntities individualCustomer) {
        PreparedStatement statement;
        Connection connection;
        String updateCustomer;
        ResultSet result;
        try {
            connection = Crud.getInstance().getConnection();
            updateCustomer = "UPDATE individualCustomer SET firstName = ? ,lastName = ? ,birthDate = ? ,nationalCode = ? WHERE   customerNumber = ?";
            statement = connection.prepareStatement(updateCustomer);
            statement.setString(1, individualCustomer.getFirstName());
            statement.setString(2, individualCustomer.getLastName());
            statement.setString(3, individualCustomer.getBirthDate());
            statement.setString(4, individualCustomer.getNationalCode());
            statement.setString(5, individualCustomer.getCustomerNumber());
            statement.executeUpdate();
            return individualCustomer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IndividualCustomerEntities deleteCustomer(IndividualCustomerEntities individualCustomer) {
        PreparedStatement statement;
        Connection connection;
        String queryString;
        try {
            connection = Crud.getInstance().getConnection();
            queryString = "DELETE FROM individualCustomer WHERE customerNumber = " + individualCustomer.getCustomerNumber();
            statement = connection.prepareStatement(queryString);
            statement.executeUpdate();
            //delete customer number from customer
            queryString = "DELETE FROM customer WHERE customerNumber = " + individualCustomer.getCustomerNumber();
            statement = connection.prepareStatement(queryString);
            statement.executeUpdate();
            return individualCustomer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<IndividualCustomerEntities> searchCustomer(IndividualCustomerEntities individualCustomer) {
        PreparedStatement statement;
        Connection connection;
        String queryString;
        ResultSet result;
        IndividualCustomerEntities individualCustomerRetrieved = null;
        ArrayList individualCustomerList = new ArrayList();
        IndividualCustomerEntities individualCustomerEntities ;
        try {
            connection = Crud.getInstance().getConnection();
            queryString = "SELECT * FROM individualCustomer inner JOIN customer ON individualCustomer.customerNumber = customer.customerNumber " +
                    "WHERE " +
                    (individualCustomer.getFirstName().length() > 0 ? " firstName = ?" : "") +
                    (individualCustomer.getLastName().length() > 0 ? " lastName = ?" : "") +
                    (individualCustomer.getBirthDate().length() > 0 ? " birthDate = ?" : "") +
                    (individualCustomer.getNationalCode().length() > 0 ? " nationalCode = ?" : "");
            statement = connection.prepareStatement(queryString);
            if (individualCustomer.getFirstName().length() > 0)
                statement.setString(1, individualCustomer.getFirstName());
            if (individualCustomer.getLastName().length() > 0)
                statement.setString(1, individualCustomer.getLastName());
            if (individualCustomer.getBirthDate().length() > 0)
                statement.setString(1, individualCustomer.getBirthDate());
            if (individualCustomer.getNationalCode().length() > 0)
                statement.setString(1, individualCustomer.getNationalCode());
            result = statement.executeQuery();
            individualCustomerEntities = new IndividualCustomerEntities();
            individualCustomerEntities.setFirstName(result.getString("firstName"));
            individualCustomerEntities.setLastName(result.getString("lastName"));
            individualCustomerEntities.setBirthDate(result.getString("birthDate"));
            individualCustomerEntities.setNationalCode(result.getString("nationalCode"));
            individualCustomerEntities.setCustomerNumber(result.getString("customerNumber"));
            individualCustomerList.add(individualCustomerEntities);
            return individualCustomerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
