package ceglarski.pl.helpers;

import ceglarski.pl.models.Contact;
import ceglarski.pl.models.Customer;
import ceglarski.pl.models.Type;
import ceglarski.pl.services.CustomerService;
import ceglarski.pl.services.interfaces.ICustomerService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConverterTXT implements IConverter {

    private ICustomerService customerService = new CustomerService();

    @Override
    public void convertCustomers(File file) {

        //System.out.println("Inside ConverterTXT::convertCustomers() method.");

        String path = file.getPath();

        FileInputStream inputStream = null;
        Scanner sc = null;
        String cvsSplitBy = ",";
        try {
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line != null){

                    String[] customerArray = line.split(cvsSplitBy);
                    prepareCustomer(customerArray);

                }
            }

            if (sc.ioException() != null) {

                if (inputStream != null) inputStream.close();
                if (sc != null) sc.close();

                throw sc.ioException();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void prepareCustomer(String[] customerArray) {

        if(customerArray.length > 3){

            int age = (tryParseInt(customerArray[2])) ? Integer.parseInt(customerArray[2]) : 0 ;
            Customer customer = new Customer(customerArray[0], customerArray[1], age);

            if(customerArray.length > 4){
                for(int i = 4; i < customerArray.length; i++) {
                    Contact contact = new Contact(customer.getId(), customerArray[i], valideType(customerArray[i]));
                    customer.addContact(contact);
                }
            }

            saveCustomerWithContacts(customer);
        }

    }

    public Type valideType(String contact){

        String regexEmail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern patternEmail = Pattern.compile(regexEmail);
        Matcher matcherEmail = patternEmail.matcher(contact);

        String regexPhone = "(?:(?:(?:\\+|00)?48)|(?:\\(\\+?48\\)))?(?:1[2-8]|2[2-69]|3[2-49]|4[1-68]|5[0-9]|6[0-35-9]|[7-8][1-9]|9[145])\\d{7}";
        Pattern patternPhone = Pattern.compile(regexPhone);
        String contactWithoutSpaces = contact.replaceAll("\\s","");
        Matcher matcherPhone = patternPhone.matcher(contactWithoutSpaces);

        if(matcherPhone.matches()){
            return Type.phone;
        }
        else if(contact.matches("jbr")){
            return Type.jabber;
        }
        else if (matcherEmail.matches()) {
            return Type.email;
        }
        return Type.unknown;
    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void saveCustomerWithContacts(Customer customer) {
        customerService.insert(customer);
    }

}
