package presentation;

import exceptions.EmptyFieldException;
import logic.LegalCustomerLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LegalCustomerPresent extends HttpServlet {
    LegalCustomerLogic legalCustomerProcess;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String companyName;
        String registerDate;
        String economyCode;
        try {
            companyName = request.getParameter("companyName");
            registerDate = request.getParameter("birthDate");
            economyCode = request.getParameter("customerId");
            String button = request.getParameter("button");
            if (request.getRequestURL().toString().endsWith("/addIndividualCustomer")) {
                legalCustomerProcess.addCustomer(companyName, registerDate, economyCode);
            } else if (request.getRequestURL().toString().endsWith("/deleteIndividualCustomer")) {
                legalCustomerProcess.deleteCustomer(companyName, registerDate, economyCode);
            } else if (request.getRequestURL().toString().endsWith("/updateIndividualCustomer")) {
                legalCustomerProcess.updateCustomer(companyName, registerDate, economyCode);
            } else if (request.getRequestURL().toString().endsWith("/searchIndividualCustomer")) {
                legalCustomerProcess.searchCustomer(companyName, registerDate, economyCode);
            }
        } catch (EmptyFieldException e) {
            e.printStackTrace();
        }
    }
}
