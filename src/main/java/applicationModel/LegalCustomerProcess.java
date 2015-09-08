package applicationModel;


import domainModel.LegalCustomer;
import exceptions.ExistenceException;
import exceptions.FieldEmptyException;

import java.util.Date;

public class LegalCustomerProcess {
    private LegalCustomer legalCustomer;
    private Database.LegalCustomerDatabaseProcess legalCustomerDatabaseProcess;

    public void checkFields(LegalCustomer legalCustomer) throws FieldEmptyException {
        if (legalCustomer.getCompanyName() == null || legalCustomer.getCompanyName().length() == 0) {
            throw new FieldEmptyException();
        }
        if (legalCustomer.getRegisterDate() == null ) {
            throw new FieldEmptyException();
        }
        if (legalCustomer.getEconomyId() == null || legalCustomer.getEconomyId().length() == 0 ) {
            throw new FieldEmptyException();
        }
        if(legalCustomerDatabaseProcess.checkEconomyExistence(legalCustomer.getEconomyId())){
            try {
                throw new ExistenceException("This economy code exists....");
            } catch (ExistenceException e) {
                e.printStackTrace();
            }
        }
    }

    public void addCustomer(String companyName, Date registerDate, String economyId) throws FieldEmptyException {
        legalCustomer.setCompanyName(companyName);
        legalCustomer.setRegisterDate(registerDate);
        legalCustomer.setEconomyId(economyId);
        checkFields(legalCustomer);
        legalCustomerDatabaseProcess.addCustomer(legalCustomer);
    }

    public void updateCustomer(String companyName, Date registerDate, String economyId) {
        legalCustomer.setCompanyName(companyName);
        legalCustomer.setRegisterDate(registerDate);
        legalCustomer.setEconomyId(economyId);
        legalCustomerDatabaseProcess.updateCustomer(legalCustomer);
    }

    public void deleteCustomer(String companyName, Date registerDate, String economyId) {
        legalCustomer.setCompanyName(companyName);
        legalCustomer.setRegisterDate(registerDate);
        legalCustomer.setEconomyId(economyId);
        legalCustomerDatabaseProcess.deleteCustomer(legalCustomer);
    }

    public void retrieveCustomer(String companyName, Date registerDate, String economyId) {
        legalCustomer.setCompanyName(companyName);
        legalCustomer.setRegisterDate(registerDate);
        legalCustomer.setEconomyId(economyId);
        legalCustomerDatabaseProcess.retrieveCustomer(legalCustomer);

    }

}
