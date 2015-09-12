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
        String queryString;
        ResultSet result;
        try {
            connection = Crud.getInstance().getConnection();
            queryString = "SELECT customerNumber FROM individualCustomer  WHERE  nationalCode = ?";
            statement = connection.prepareStatement(queryString);
            statement.setString(1, individualCustomer.getNationalCode());
            result = statement.executeQuery();
            while (result.next()) {
                System.out.println("customer number : " + result.getString("customerNumber"));
                queryString = "UPDATE individualCustomer SET firstName =? ,lastName =? ,birthDate =? ,nationalCode =? WHERE   customerNumber = ?";
                statement = connection.prepareStatement(queryString);
                statement.setString(1, individualCustomer.getFirstName());
                statement.setString(2, individualCustomer.getLastName());
                statement.setString(3, individualCustomer.getBirthDate());
                statement.setString(4, individualCustomer.getNationalCode());
                statement.setString(5, result.getString("customerNumber"));
                statement.executeUpdate();
            }

            IndividualCustomerEntities individualCustomerEntities = new IndividualCustomerEntities();
            individualCustomerEntities.setFirstName(individualCustomer.getFirstName());
            individualCustomerEntities.setLastName(individualCustomer.getLastName());
            individualCustomerEntities.setBirthDate(individualCustomer.getBirthDate());
            individualCustomerEntities.setNationalCode(individualCustomer.getNationalCode());
            individualCustomerEntities.setCustomerNumber(individualCustomer.getCustomerNumber());
            //System.out.println("individualCustomerEntities = " + individualCustomerEntities);
            return individualCustomerEntities;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IndividualCustomerEntities retreiveCustomer(String customerNumber) {
        PreparedStatement statement;
        Connection connection;
        String updateCustomer;
        ResultSet result;
        try {
            connection = Crud.getInstance().getConnection();
            updateCustomer = "SELECT * FROM individualCustomer WHERE customerNumber = ?";
            statement = connection.prepareStatement(updateCustomer);
            statement.setString(1, customerNumber);
            result = statement.executeQuery();
            while (result.next()) {
                IndividualCustomerEntities individualCustomerEntities = new IndividualCustomerEntities();
                individualCustomerEntities.setFirstName(result.getString("firstName"));
                individualCustomerEntities.setLastName(result.getString("lastName"));
                individualCustomerEntities.setBirthDate(result.getString("birthDate"));
                individualCustomerEntities.setNationalCode(result.getString("nationalCode"));
                individualCustomerEntities.setCustomerNumber(result.getString("customerNumber"));
                return individualCustomerEntities;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteCustomer(String customerNumber) {
        PreparedStatement statement;
        Connection connection;
        String queryString;
        ResultSet result;
        try {
            connection = Crud.getInstance().getConnection();
            queryString = "DELETE FROM individualCustomer WHERE customerNumber = ?";
            statement = connection.prepareStatement(queryString);
            statement.setString(1, customerNumber);
            statement.executeUpdate();
            //delete customer number from customer
            queryString = "DELETE FROM customer WHERE customerNumber = ?";
            statement = connection.prepareStatement(queryString);
            statement.setString(1, customerNumber);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<IndividualCustomerEntities> searchCustomer(IndividualCustomerEntities individualCustomer) {
        PreparedStatement statement;
        Connection connection;
        String queryString;
        ResultSet result;
        //  IndividualCustomerEntities individualCustomerRetrieved = null;
        ArrayList<IndividualCustomerEntities> individualCustomerList = new ArrayList<IndividualCustomerEntities>();
        try {
            connection = Crud.getInstance().getConnection();
            queryString = "SELECT * FROM individualCustomer JOIN customer ON individualCustomer.customerNumber = customer.customerNumber " +
                    " WHERE 1=1" +
                    (individualCustomer.getFirstName().length() > 0 ? " AND firstName = ?" : "") +
                    (individualCustomer.getLastName().length() > 0 ? " AND lastName = ?" : "") +
                    (individualCustomer.getBirthDate().length() > 0 ? " AND birthDate = ?" : "") +
                    (individualCustomer.getNationalCode().length() > 0 ? " AND nationalCode = ?" : "");
            statement = connection.prepareStatement(queryString);
            int index = 1;
            if (individualCustomer.getFirstName().length() > 0) {
                statement.setString(index, individualCustomer.getFirstName());
                index++;
            }
            if (individualCustomer.getLastName().length() > 0) {
                statement.setString(index, individualCustomer.getLastName());
                index++;
            }
            if (individualCustomer.getBirthDate().length() > 0) {
                statement.setString(index, individualCustomer.getBirthDate());
                index++;
            }
            if (individualCustomer.getNationalCode().length() > 0) {
                statement.setString(index, individualCustomer.getNationalCode());
                index++;
            }
            result = statement.executeQuery();
            while (result.next()) {
                IndividualCustomerEntities individualCustomerEntities = new IndividualCustomerEntities();
                individualCustomerEntities.setFirstName(result.getString("firstName"));
                individualCustomerEntities.setLastName(result.getString("lastName"));
                individualCustomerEntities.setBirthDate(result.getString("birthDate"));
                individualCustomerEntities.setNationalCode(result.getString("nationalCode"));
                individualCustomerEntities.setCustomerNumber(result.getString("customerNumber"));
                individualCustomerList.add(individualCustomerEntities);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return individualCustomerList;
    }
}
