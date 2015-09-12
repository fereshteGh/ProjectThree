package logic;


import crud.LegalCustomerCrud;
import entities.LegalCustomerEntities;
import exceptions.EmptyFieldException;
import exceptions.ExistenceException;

public class LegalCustomerLogic {
    private LegalCustomerEntities legalCustomerEntities;
    public void checkFields(LegalCustomerEntities legalCustomer) throws EmptyFieldException {
        if (legalCustomer.getCompanyName() == null || legalCustomer.getCompanyName().length() == 0) {
            throw new EmptyFieldException("company name could not be empty");
        }
        if (legalCustomer.getRegisterDate() == null ) {
            throw new EmptyFieldException("registry date could not be empty");
        }
        if (legalCustomer.getEconomyId() == null || legalCustomer.getEconomyId().length() == 0 ) {
            throw new EmptyFieldException("economy code could not be empty");
        }
        if(LegalCustomerCrud.checkEconomyExistence(legalCustomer.getEconomyId())){
            try {
                throw new ExistenceException("This economy code exists....");
            } catch (ExistenceException e) {
                e.printStackTrace();
            }
        }
    }
    public void addCustomer(String companyName, String registerDate, String economyId) throws EmptyFieldException {

        String customerNumber = CustomerLogic.generateCustomerNumber();
        legalCustomerEntities.setCompanyName(companyName);
        legalCustomerEntities.setRegisterDate(registerDate);
        legalCustomerEntities.setEconomyId(economyId);
        checkFields(legalCustomerEntities);
        LegalCustomerCrud.addCustomer(legalCustomerEntities);
    }

    public void updateCustomer(String companyName, String registerDate, String economyId) {
        legalCustomerEntities.setCompanyName(companyName);
        legalCustomerEntities.setRegisterDate(registerDate);
        legalCustomerEntities.setEconomyId(economyId);
        LegalCustomerCrud.updateCustomer(legalCustomerEntities);
    }

    public void deleteCustomer(String companyName, String registerDate, String economyId) {
        legalCustomerEntities.setCompanyName(companyName);
        legalCustomerEntities.setRegisterDate(registerDate);
        legalCustomerEntities.setEconomyId(economyId);
        LegalCustomerCrud.deleteCustomer(legalCustomerEntities);
    }

    public void searchCustomer(String companyName, String registerDate, String economyId) {
        legalCustomerEntities.setCompanyName(companyName);
        legalCustomerEntities.setRegisterDate(registerDate);
        legalCustomerEntities.setEconomyId(economyId);
        LegalCustomerCrud.searchCustomer(legalCustomerEntities);
    }
}
