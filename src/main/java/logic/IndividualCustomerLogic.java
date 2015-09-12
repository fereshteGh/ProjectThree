package logic;


import crud.IndividualCustomerCrud;
import entities.IndividualCustomerEntities;
import exceptions.EmptyFieldException;
import exceptions.ExistenceException;

import java.util.ArrayList;

public class IndividualCustomerLogic {
    private IndividualCustomerEntities individualCustomers;

    public void checkFields(IndividualCustomerEntities individualCustomer) throws EmptyFieldException, ExistenceException {
        if (individualCustomer.getFirstName() == null || individualCustomer.getFirstName().length() == 0) {
            throw new EmptyFieldException("first name could not be empty");
        }
        if (individualCustomer.getLastName() == null || individualCustomer.getLastName().length() == 0) {
            throw new EmptyFieldException("last name could not be empty");
        }
        if (individualCustomer.getBirthDate() == null || individualCustomer.getBirthDate().length() == 0) {
            throw new EmptyFieldException("birth date could not be empty");
        }
        if (individualCustomer.getNationalCode() == null || individualCustomer.getNationalCode().length() == 0) {
            throw new EmptyFieldException("national code could not be empty");
        }
        if (IndividualCustomerCrud.checkNationalCodeExistence(individualCustomer.getNationalCode())) {
            throw new ExistenceException("This national code exists....");
        }
    }

    public IndividualCustomerEntities addCustomer(String fName, String lName, String bDate, String nationalCode) throws EmptyFieldException, ExistenceException {
        individualCustomers = new IndividualCustomerEntities();
        String customerNumber = CustomerLogic.generateCustomerNumber();
        individualCustomers.setFirstName(fName);
        individualCustomers.setLastName(lName);
        individualCustomers.setBirthDate(bDate);
        individualCustomers.setNationalCode(nationalCode);
        individualCustomers.setCustomerNumber(customerNumber);
        checkFields(individualCustomers);
        return IndividualCustomerCrud.addCustomer(individualCustomers);
    }

    public IndividualCustomerEntities updateCustomer(String fName, String lName, String bDate, String nationalCode, String customerNumber) throws EmptyFieldException {
        individualCustomers = new IndividualCustomerEntities();
        individualCustomers.setFirstName(fName);
        individualCustomers.setLastName(lName);
        individualCustomers.setBirthDate(bDate);
        individualCustomers.setNationalCode(nationalCode);
        individualCustomers.setCustomerNumber(customerNumber);
        return IndividualCustomerCrud.updateCustomer(individualCustomers);
    }

    public void deleteCustomer(String customerNumber) throws EmptyFieldException {
        IndividualCustomerCrud.deleteCustomer(customerNumber);
    }

    public ArrayList<IndividualCustomerEntities> searchCustomer(String fName, String lName, String bDate, String nationalCode, String customerNumber) throws EmptyFieldException {
        ArrayList<IndividualCustomerEntities> individualCustomerList;
        individualCustomers = new IndividualCustomerEntities();
        individualCustomers.setFirstName(fName);
        individualCustomers.setLastName(lName);
        individualCustomers.setBirthDate(bDate);
        individualCustomers.setNationalCode(nationalCode);
        individualCustomers.setCustomerNumber(customerNumber);
        individualCustomerList = IndividualCustomerCrud.searchCustomer(individualCustomers);
        return individualCustomerList;
    }

    public IndividualCustomerEntities retreiveCustomer(String customerNumber) throws EmptyFieldException {
        return IndividualCustomerCrud.retreiveCustomer(customerNumber);
    }
}
