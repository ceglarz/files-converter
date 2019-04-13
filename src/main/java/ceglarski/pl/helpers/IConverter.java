package ceglarski.pl.helpers;

import ceglarski.pl.models.Customer;

import java.io.File;

public interface IConverter {

    void convertCustomers(File file);

    void saveCustomerWithContacts(Customer customer);

}
