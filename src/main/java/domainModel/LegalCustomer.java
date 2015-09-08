package domainModel;

import java.util.Date;

public class LegalCustomer extends Customer {
    private String companyName;
    private Date registerDate;
    private String economyId;

    public LegalCustomer(String companyName, Date registerDate, String economyId) {
        this.companyName = companyName;
        this.registerDate = registerDate;
        this.economyId = economyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public String getEconomyId() {
        return economyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public void setEconomyId(String economyId) {
        this.economyId = economyId;
    }
}
