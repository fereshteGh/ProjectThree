package logic;

import crud.CustomerCrud;

public class CustomerLogic {
    //generate customer number
    public static String generateCustomerNumber() {
        String customerNumber;
        String lastCustomerNumber = CustomerCrud.readLastCustomerNumber();
        if (lastCustomerNumber == null) {
            customerNumber = "1";
        } else {
            customerNumber = String.valueOf(Integer.parseInt(lastCustomerNumber) + 1);
        }
        return customerNumber;
    }
}
