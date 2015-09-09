package entities;

public class IndividualCustomerEntities extends CustomerEntities {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String nationalCode;

    public IndividualCustomerEntities() {
           }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setFirstName(String firstName) {
       this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "IndividualCustomer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
