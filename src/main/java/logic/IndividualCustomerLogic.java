package logic;


import crud.IndividualCustomerCrud;
import entities.IndividualCustomerEntities;
import exceptions.EmptyFieldException;
import exceptions.ExistenceException;

public class IndividualCustomerLogic {
    private IndividualCustomerEntities individualCustomers;

    public void checkFields(IndividualCustomerEntities individualCustomer) throws EmptyFieldException {
        if (individualCustomer.getFirstName() == null || individualCustomer.getFirstName().length() == 0) {
            throw new EmptyFieldException();
        }
        if (individualCustomer.getLastName() == null || individualCustomer.getLastName().length() == 0) {
            throw new EmptyFieldException();
        }
        if (individualCustomer.getBirthDate() == null) {
            throw new EmptyFieldException();
        }
        if (individualCustomer.getNationalCode() == null || individualCustomer.getNationalCode().length() == 0) {
            throw new EmptyFieldException();
        }
        if (IndividualCustomerCrud.checkNationalCodeExistence(individualCustomer.getNationalCode())) {
            try {
                throw new ExistenceException("This customer id exists....");
            } catch (ExistenceException e) {
                e.printStackTrace();
            }
        }
    }

    public IndividualCustomerEntities addCustomer(String fName, String lName, String bDate, String nationalCode) throws EmptyFieldException {
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

    public IndividualCustomerEntities deleteCustomer(String fName, String lName, String bDate, String nationalCode, String customerNumber) throws EmptyFieldException {
        individualCustomers = new IndividualCustomerEntities();
        individualCustomers.setLastName(lName);
        individualCustomers.setBirthDate(bDate);
        individualCustomers.setNationalCode(nationalCode);
        individualCustomers.setCustomerNumber(customerNumber);
        return IndividualCustomerCrud.deleteCustomer(individualCustomers);
    }

    public void searchCustomer(String fName, String lName, String bDate, String nationalCode, String customerNumber) throws EmptyFieldException {
        individualCustomers = new IndividualCustomerEntities();
        individualCustomers.setFirstName(fName);
        individualCustomers.setLastName(lName);
        individualCustomers.setBirthDate(bDate);
        individualCustomers.setNationalCode(nationalCode);
        individualCustomers.setCustomerNumber(customerNumber);
        IndividualCustomerCrud.searchCustomer(individualCustomers);
    }
}
