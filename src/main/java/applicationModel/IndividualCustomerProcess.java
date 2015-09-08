package applicationModel;


import domainModel.IndividualCustomer;
import exceptions.ExistenceException;
import exceptions.FieldEmptyException;

import java.util.Date;

public class IndividualCustomerProcess {
    private IndividualCustomer individualCustomer;
    private Database.IndividualCustomerDatabaseProcess individualCustomerDatabaseProcess;

    public void checkFields(IndividualCustomer individualCustomer) throws FieldEmptyException {
        if (individualCustomer.getFirstName() == null || individualCustomer.getFirstName().length() == 0) {
            throw new FieldEmptyException();
        }
        if (individualCustomer.getLastName() == null || individualCustomer.getLastName().length() == 0) {
            throw new FieldEmptyException();
        }
        if (individualCustomer.getBirthDate() == null) {
            throw new FieldEmptyException();
        }
        if (individualCustomer.getCustomerId() == null || individualCustomer.getCustomerId().length() == 0 ) {
            throw new FieldEmptyException();
        }
        if( individualCustomerDatabaseProcess.checkCustomerIDExistence(individualCustomer.getCustomerId())){
            try {
                throw new ExistenceException("This customer id exists....");
            } catch (ExistenceException e) {
                e.printStackTrace();
            }
        }
    }

    public void addCustomer(String fName, String lName, Date bDate, String customerId) throws FieldEmptyException {
        individualCustomer.setFirstName(fName);
        individualCustomer.setLastName(lName);
        individualCustomer.setBirthDate(bDate);
        individualCustomer.setCustomerId(customerId);
        checkFields(individualCustomer);
        individualCustomerDatabaseProcess.addCustomer(individualCustomer);
    }

    public void updateCustomer(String fName, String lName, Date bDate, String customerId) {
        individualCustomer.setFirstName(fName);
        individualCustomer.setLastName(lName);
        individualCustomer.setBirthDate(bDate);
        individualCustomer.setCustomerId(customerId);
        individualCustomerDatabaseProcess.updateCustomer(individualCustomer);
    }

    public void deleteCustomer(String fName, String lName, Date bDate, String customerId) {
        individualCustomer.setFirstName(fName);
        individualCustomer.setLastName(lName);
        individualCustomer.setBirthDate(bDate);
        individualCustomer.setCustomerId(customerId);
        individualCustomerDatabaseProcess.deleteCustomer(individualCustomer);
    }

    public void retrieveCustomer(String fName, String lName, Date bDate, String customerId) {
        individualCustomer.setFirstName(fName);
        individualCustomer.setLastName(lName);
        individualCustomer.setBirthDate(bDate);
        individualCustomer.setCustomerId(customerId);
        individualCustomerDatabaseProcess.retrieveCustomer(individualCustomer);

    }

}
