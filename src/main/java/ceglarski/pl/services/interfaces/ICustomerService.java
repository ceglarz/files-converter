package ceglarski.pl.services.interfaces;

import ceglarski.pl.models.Customer;

public interface ICustomerService {

    void insert(Customer customer);

    boolean validateCustomer(Customer customer);

}
