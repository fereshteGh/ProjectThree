package domainModel;

import java.util.Date;

public class IndividualCustomer extends Customer {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String customerId;

    public IndividualCustomer(String firstName, String lastName, Date birthDate, String customerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
