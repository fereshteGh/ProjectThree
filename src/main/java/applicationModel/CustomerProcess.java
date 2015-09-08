package applicationModel;



public class CustomerProcess {

    private Database.CustomerDatabaseProcess customerDatabaseProcess;
    //generate customer number
    public String generateCustomerNumber() {
        if (customerDatabaseProcess.readLastCustomerNumber() == null) {
            return String.valueOf(1);
        }
        else
            return String.valueOf(customerDatabaseProcess.readLastCustomerNumber()+1);
    }
}
