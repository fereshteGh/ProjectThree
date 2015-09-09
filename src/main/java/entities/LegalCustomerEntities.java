package entities;

public class LegalCustomerEntities extends CustomerEntities {
    private String companyName;
    private String registerDate;
    private String economyId;

    public LegalCustomerEntities() {

    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getEconomyId() {
        return economyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public void setEconomyId(String economyId) {
        this.economyId = economyId;
    }
}
