package ceglarski.pl.helpers;

import ceglarski.pl.models.Contact;
import ceglarski.pl.models.ContactTemp;
import ceglarski.pl.models.Customer;
import ceglarski.pl.models.Type;
import ceglarski.pl.services.CustomerService;
import ceglarski.pl.services.interfaces.ICustomerService;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ConverterXML implements IConverter {

    private ICustomerService customerService = new CustomerService();

    @Override
    public void convertCustomers(File file) {

        //System.out.println("Inside ConverterXML::convertCustomers() method.");

        String path = file.getPath();

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean nameFlag = false;
                boolean surnameFlag = false;
                boolean ageFlag = false;
                boolean contactFlag = false;

                String name = "";
                String surname = "";
                int age = 0;
                List<ContactTemp> contacts = null;

                Customer customer = null;
                Contact contact = null;
                ContactTemp contactTemp = null;

                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) throws SAXException {

                    switch(qName.toLowerCase())
                    {
                        case "person":
                            name = "";
                            surname = "";
                            age = 0;
                            break;
                        case "name":
                            nameFlag = true;
                            break;
                        case "surname":
                            surnameFlag = true;
                            break;
                        case "age":
                            ageFlag = true;
                            break;

                        case "contacts":
                            contacts = new ArrayList();
                            break;
                        case "phone":
                            contactTemp = new ContactTemp(Type.phone);
                            contactFlag = true;
                            break;
                        case "email":
                            contactTemp = new ContactTemp(Type.email);
                            contactFlag = true;
                            break;
                        case "icq":
                            contactTemp = new ContactTemp(Type.unknown);
                            contactFlag = true;
                            break;
                        case "jabber":
                            contactTemp = new ContactTemp(Type.jabber);
                            contactFlag = true;
                            break;

                        default:
                            break;
                    }
                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {

                    if (qName.equalsIgnoreCase("person")) {
                        customer = new Customer(name, surname, age);
                        for (ContactTemp contactTemp: contacts) {
                            contact = new Contact(customer.getId(), contactTemp.getContact(), contactTemp.getType());
                            customer.addContact(contact);
                        }

                        saveCustomerWithContacts(customer);
                    }

                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (nameFlag) {
                        name = new String(ch, start, length);
                        nameFlag = false;
                    }

                    if (surnameFlag) {
                        surname = new String(ch, start, length);
                        surnameFlag = false;
                    }

                    if (ageFlag) {
                        String ageString = (new String(ch, start, length));
                        age = ( tryParseInt(ageString)) ? Integer.parseInt(ageString) : 0 ;
                        ageFlag = false;
                    }

                    if (contactFlag) {
                        contactTemp.setContact(new String(ch, start, length));
                        contacts.add(contactTemp);
                        contactFlag = false;
                    }

                }

            };

            saxParser.parse(path, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
