package presentation;

import applicationModel.LegalCustomerProcess;
import exceptions.FieldEmptyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LegalCustomerServlet extends HttpServlet {
    LegalCustomerProcess legalCustomerProcess;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();

        String button = request.getParameter("button");
        if (button == "اضافه کردن مشتری") {

            addCustomer(request);

        } else if (button == "به روز رسانی مشتری") {

            updateCustomer();
        } else if (button == "حذف مشتری") {

            deleteCustomer();
        } else if (button == "جستجوی مشتری") {

            searchCustomer();
        }

    }

    public void addCustomer(HttpServletRequest request){
        String companyName = null;

        Date registerDate = null;
        String economyCode = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        // response.setContentType("html");
        try {
            companyName = request.getParameter("companyName");

            registerDate = dateFormat.parse(request.getParameter("birthDate"));
            economyCode = request.getParameter("customerId");
            legalCustomerProcess.addCustomer(companyName,registerDate,economyCode);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FieldEmptyException e) {
            e.printStackTrace();
        }

    }

    public void updateCustomer(){

    }

    public void deleteCustomer(){

    }

    public void searchCustomer(){

    }
}
