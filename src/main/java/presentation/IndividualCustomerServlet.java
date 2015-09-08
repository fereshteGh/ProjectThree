package presentation;

import applicationModel.IndividualCustomerProcess;
import domainModel.IndividualCustomer;
import exceptions.FieldEmptyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class IndividualCustomerServlet extends HttpServlet {
    IndividualCustomerProcess individualCustomerProcess;
ArrayList individualCustomers ;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post....");
        String button = request.getParameter("action");
        System.out.println("action : " + button);
        if (button.compareTo("add")==0) {
            System.out.println("adding...");
            individualCustomers = getData(request);
                addCustomer(individualCustomers);
            System.out.println("added....");
                presentAdd(response);
            System.out.println("show result...");

        } else if (button.compareTo("update")==0) {
           updateCustomer();
        } else if (button.compareTo("delete")==0) {
            deleteCustomer();
        } else if (button.compareTo("search")==0) {
            searchCustomer();
        }

    }

    public void addCustomer(List individualCustomers){
    //     individualCustomerProcess.addCustomer(individualCustomers.get,lName,birthDate,customerId);

    }

    public void updateCustomer(){

    }

    public void deleteCustomer(){

    }

    public void searchCustomer(){

    }

    public void presentAdd(HttpServletResponse response){
        try {
            PrintWriter writer = response.getWriter();
            String htmlResponse = " <html>";
            htmlResponse+="<head></head>";
            htmlResponse+="<body> مشتری جدید با موفقیت اضافه شد.</body>";
            htmlResponse+="</html>";
            writer.println(htmlResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList getData(HttpServletRequest request) {

        String fName;
        String lName;
        Date birthDate;
        String customerId;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        ArrayList individualCustomerTemp = null;
        // response.setContentType("html");
        try {
            request.setCharacterEncoding("UTF-8");

            fName = request.getParameter("firstName");
            lName = request.getParameter("lastName");
            birthDate = dateFormat.parse(request.getParameter("birthDate"));
            customerId = request.getParameter("customerId");
            System.out.println(" first name : " + fName + "last name : " + lName + "birth date : " + birthDate + "customer id : " + customerId);
            individualCustomerTemp.add(fName);
            individualCustomerTemp.add(lName);
            individualCustomerTemp.add(birthDate);
            individualCustomerTemp.add(customerId);
            return individualCustomerTemp;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
