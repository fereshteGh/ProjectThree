package presentation;

import entities.IndividualCustomerEntities;
import exceptions.EmptyFieldException;
import logic.IndividualCustomerLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class IndividualCustomerPresent extends HttpServlet {
    IndividualCustomerLogic individualCustomerLogic = new IndividualCustomerLogic();
    IndividualCustomerEntities individualCustomerEntities;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName;
        String lastName;
        String birthDate;
        String nationalCode;
        String customerNumber;
        try {
            response.setContentType("text/html;UTF-8");
            request.setCharacterEncoding("UTF-8");
            firstName = request.getParameter("firstName");
            lastName = request.getParameter("lastName");
            birthDate = request.getParameter("birthDate");
            nationalCode = request.getParameter("nationalCode");
            customerNumber = request.getParameter("customerNumber");
            if (request.getRequestURL().toString().endsWith("/addIndividualCustomer")) {
                individualCustomerEntities = individualCustomerLogic.addCustomer(firstName, lastName, birthDate, nationalCode);
                present(response, " added successfully....");

            } else if (request.getRequestURL().toString().endsWith("/deleteIndividualCustomer")) {
                individualCustomerEntities = individualCustomerLogic.deleteCustomer(firstName, lastName, birthDate, nationalCode, customerNumber);
                present(response, " deleted successfully....");
            } else if (request.getRequestURL().toString().endsWith("/updateIndividualCustomer")) {
                individualCustomerLogic.updateCustomer(firstName, lastName, birthDate, nationalCode, customerNumber);
                present(response, " updated successfully....");
            } else if (request.getRequestURL().toString().endsWith("/searchIndividualCustomer")) {
                individualCustomerLogic.searchCustomer(firstName, lastName, birthDate, nationalCode, customerNumber);
                presentSearch(response);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (EmptyFieldException e) {
            e.printStackTrace();
        }
    }

    public void present(HttpServletResponse response, String message) {
        try {

            PrintWriter writer = response.getWriter();
            String htmlResponse;
            htmlResponse = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head lang=\"en\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <link href=\"myStyle.css\"  rel=\"stylesheet\" />\n" +
                    "</head>" +
                    "<body > " + " Customer name : " + individualCustomerEntities.getFirstName() + " " + individualCustomerEntities.getLastName() + " with national code : " + individualCustomerEntities.getNationalCode() + " with customer number : " + individualCustomerEntities.getCustomerNumber()
                    + message + "</body>" +
                    "</html>";
            writer.println(htmlResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void presentSearch(HttpServletResponse response) {
        try {




            PrintWriter writer = response.getWriter();
            String htmlResponse;
            htmlResponse = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head lang=\"en\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <link href=\"myStyle.css\"  rel=\"stylesheet\" />\n" +
                    "</head>" +
                    "<body > " +"<table >"+
                    "<tr>"+
                    "<th>نام </th>"+
                    "<th>نام خانوادگی </th>"+
                    "<th>تاریخ تولد</th>"+
                    "<th>کد ملی</th>"+
                    "<th colspan=\"2\"> شماره مشتری</th>"+
                    "</tr>"+"<tr>"+
                    "<div contenteditable = 'true'>"+
                    "<td><input type=\"submit\" name=\"modifyButton\" value\"ویرایش\"></td>"+
                    "<td><input type=\"submit\" name=\"modifyButton\" value\"حذف\"></td>"+
                    "</div>"+
                    "</tr>"+
                    "</table>"
                     + "</body>" +
                    "</html>";
            writer.println(htmlResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


