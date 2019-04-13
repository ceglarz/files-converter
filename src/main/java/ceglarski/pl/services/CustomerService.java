package ceglarski.pl.services;

import ceglarski.pl.models.Contact;
import ceglarski.pl.models.Customer;
import ceglarski.pl.services.interfaces.ICustomerService;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class CustomerService implements ICustomerService {

    public Connection connect(){
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            Class.forName(prop.getProperty("jdbc.driver"));
            return DriverManager.getConnection(
                    prop.getProperty("jdbc.url"),
                    prop.getProperty("jdbc.user"),
                    prop.getProperty("jdbc.password")
            );

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


    @Override
    public void insert(Customer customer) {
        String customerSQL = "INSERT INTO \"CUSTOMERS\"(\"Id\", \"Name\", \"Surname\", \"Age\")"
                + "VALUES(?,?,?,?)";
        String contactSQL = "INSERT INTO \"CONTACTS\"(\"Id\", \"Id_customer\", \"Type\", \"Contact\")"
                + "VALUES(?,?,?,?)";

        try {
            Connection conn = connect();

            if(!validateCustomer(customer)) throw new Exception("non-validated object customer");

            System.out.println(customer.toString());
            for (Contact con : customer.getContacts()) {
                System.out.println(con.toString());
            }

            PreparedStatement pstmt = conn.prepareStatement(customerSQL);

            pstmt.setString(1, customer.getId().toString());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getSurname());
            pstmt.setInt(4, customer.getAge());

            pstmt.executeUpdate();

            for (Contact contact: customer.getContacts()) {

                PreparedStatement pstmtContact = conn.prepareStatement(contactSQL);
                pstmtContact.setString(1, contact.getId().toString());
                pstmtContact.setString(2, contact.getId_customer().toString());
                pstmtContact.setInt(3, contact.getTypeValue());
                pstmtContact.setString(4, contact.getContact());

                pstmtContact.executeUpdate();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Log: " + customer.toString());
        }
    }

    @Override
    public boolean validateCustomer(Customer customer) {

        if(isNullOrEmpty(customer.getName())
                || isNullOrEmpty(customer.getSurname())) return false;

        return true;
    }

    private boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

}
