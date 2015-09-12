package presentation;

import entities.IndividualCustomerEntities;
import exceptions.EmptyFieldException;
import exceptions.ExistenceException;
import logic.IndividualCustomerLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class IndividualCustomerPresent extends HttpServlet {
    IndividualCustomerLogic individualCustomerLogic = new IndividualCustomerLogic();
    IndividualCustomerEntities individualCustomerEntities;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName;
        String lastName;
        String birthDate;
        String nationalCode;
        String customerNumber;
        String customerId;
        ArrayList<IndividualCustomerEntities> individualCustomerList;
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
                present(response, " added successfully....", individualCustomerEntities);
            } else if (request.getRequestURL().toString().endsWith("/deleteIndividualCustomer")) {
                customerId = request.getParameter("customerId");
                System.out.println("customer number : " + customerId);
                individualCustomerLogic.deleteCustomer(customerId);
                presentDelete(response, " deleted successfully....", customerId);
            } else if (request.getRequestURL().toString().endsWith("/editIndividualCustomer")) {
                customerId = request.getParameter("customerId");
                individualCustomerEntities = individualCustomerLogic.retreiveCustomer(customerId);
                presentEditPage(response, individualCustomerEntities);
            } else if (request.getRequestURL().toString().endsWith("/updateIndividualCustomer")) {
                individualCustomerEntities = individualCustomerLogic.updateCustomer(firstName, lastName, birthDate, nationalCode, customerNumber);
                present(response, " updated successfully....", individualCustomerEntities);
            } else if (request.getRequestURL().toString().endsWith("/searchIndividualCustomer")) {
                individualCustomerList = individualCustomerLogic.searchCustomer(firstName, lastName, birthDate, nationalCode, customerNumber);
                presentSearch(response, individualCustomerList);
            }
        } catch (EmptyFieldException e) {
            presentError(response, e);
        } catch (ExistenceException e) {
            presentError(response, e);
        }
    }

    public void presentDelete(HttpServletResponse response, String message, String customerNumber) {
        try {
            response.setContentType("text/html;UTF-8");
            PrintWriter writer = response.getWriter();
            String htmlResponse;
            htmlResponse = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head lang=\"en\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <link href=\"myStyle.css\"  rel=\"stylesheet\" />\n" +
                    "</head>" +
                    "<body > " + "  customer with customer number : " + customerNumber
                    + message + "</body>" +
                    "</html>";
            writer.println(htmlResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void presentSearch(HttpServletResponse response, ArrayList<IndividualCustomerEntities> individualCustomerList) {
        try {
            response.setContentType("text/html;UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<head lang=\"en\">\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <link href=\"myStyle.css\"  rel=\"stylesheet\" />\n" +
                            "</head>\n" +
                            "<body >\n " +
                            "<table  >\n" +
                            "<tr>\n" +
                            "<th >first name</th>\n" +
                            "<th >last name</th>\n" +
                            "<th >birth date</th>\n" +
                            "<th >national code</th>\n" +
                            "<th >customer number</th>\n" +
                            "</tr>\n"
            );
            for (IndividualCustomerEntities individualCustomer : individualCustomerList) {
                writer.println("<tr>\n" +
                        "<div contenteditable = 'true'>\n" +
                        "<td>" + individualCustomer.getFirstName() +
                        "</td>" +
                        "<td>" + individualCustomer.getLastName() +
                        "</td>" +
                        "<td>" + individualCustomer.getBirthDate() +
                        "</td>" +
                        "<td>" + individualCustomer.getNationalCode() +
                        "</td>" +
                        "<td>" + individualCustomer.getCustomerNumber() +
                        "</td>" +
                        "<td>\n" +
                        "<form action=\"/editIndividualCustomer\" method=\"post\">\n" +

                        "<input type=\"submit\" name=\"editButton\" value=\"edit\">\n" +
                        "<input type=\"hidden\" name=\"customerId\" value=\"" + individualCustomer.getCustomerNumber() + "\">\n" +
                        "</form>\n" +
                        "</td>\n" +
                        "<td>\n" +
                        "<form action=\"/deleteIndividualCustomer\" method=\"post\">\n" +
                        "<input type=\"submit\" name=\"deleteButton\" value=\"delete\">\n" +
                        "<input type=\"hidden\" name=\"customerId\" value=\"" + individualCustomer.getCustomerNumber() + "\">\n" +
                        "</form>\n" +
                        "</td>\n" +
                        "</div>\n");
            }
            writer.println("</tr>\n" +
                    "</table>\n"
                    + "</body>\n" +
                    "</html>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void present(HttpServletResponse response, String message, IndividualCustomerEntities individualCustomerEntities) {
        try {
            response.setContentType("text/html;UTF-8");
            PrintWriter writer = response.getWriter();
            String htmlResponse;
            htmlResponse = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head lang=\"en\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <link href=\"myStyle.css\"  rel=\"stylesheet\" />\n" +
                    "</head>" +
                    "<body > " + "  Customer " + message + " with these information : " +
                    "<ul>\n" +
                    "<li>\n" +
                    " First name :  " + individualCustomerEntities.getFirstName() +
                    "</li>\n" +
                    "<li>\n" +
                    " Last name :  " + individualCustomerEntities.getLastName() +
                    "</li>\n" +
                    "<li>\n" +
                    " Birth date :  " + individualCustomerEntities.getBirthDate() +
                    "</li>\n" +
                    "<li>\n" +
                    " National code :  " + individualCustomerEntities.getNationalCode() +
                    "</li>\n" +
                    "<li>\n" +
                    " Customer number :  " + individualCustomerEntities.getCustomerNumber() +
                    "</li>\n" +
                    "</ul>\n" +
                    "</body>" +
                    "</html>";
            writer.println(htmlResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void presentEditPage(HttpServletResponse response, IndividualCustomerEntities individualCustomerEntities) {
        try {
            response.setContentType("text/html;UTF-8");
            PrintWriter writer = response.getWriter();
            String htmlResponse;
            htmlResponse = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head lang=\"en\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <link href=\"myStyle.css\"  rel=\"stylesheet\" />\n" +
                    "</head>" +
                    "<body > " +
                    " <form  action=\"/updateIndividualCustomer\" method=\"post\">" +
                    " First name :" +
                    " <input type=\"text\" class=\"text\" name=\"firstName\" value=\"" + individualCustomerEntities.getFirstName() +
                    "\">" +
                    " <br><br>" +
                    "   Last name :" +
                    "   <input type=\"text\" class=\"text\" name=\"lastName\" value=\"" + individualCustomerEntities.getLastName() +
                    "\">" +
                    " <br><br>" +
                    " Birth date :" +
                    "  <input type=\"text\" class=\"text\" name=\"birthDate\" value=\"" + individualCustomerEntities.getBirthDate() +
                    "\">" +
                    " <br><br>" +
                    "   National code :" +

                    " <input type=\"text\" class=\"text\" name=\"nationalCode\" value=\"" + individualCustomerEntities.getNationalCode() +
                    "\">" +
                    "<br><br>" +
                    " <input type=\"submit\" class=\"button\" name=\"update\" value=\"update\">" +
                    "</form>" +
                    "</body>" +
                    "</html>";
            writer.println(htmlResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void presentError(HttpServletResponse response, Exception e) {
        try {
            response.setContentType("text/html;UTF-8");
            PrintWriter writer = response.getWriter();
            String htmlResponse;
            htmlResponse = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head lang=\"en\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <link href=\"myStyle.css\"  rel=\"stylesheet\" />\n" +
                    "</head>" +
                    "<body > " + e.getMessage() +
                    "</body>" +
                    "</html>";
            writer.println(htmlResponse);
        } catch (IOException e1) {
            e.printStackTrace();
        }
    }
}


