package backend.services;

import backend.entities.Customer;
import backend.factories.CustomerFactory;
import backend.repositories.CustomerRepo;

public class CustomerService {

    private final CustomerRepo customerRepo = new CustomerRepo();
    private final CustomerFactory customerFactory = new CustomerFactory();


/**
 * Finds an existing customer or creates a new one.
 *
 * Searches for a customer using first name, last name,
 * and phone number. If no customer is found, a new one is created
 * and saved.
 */
    public Customer createOrFetchCustomer(
        String firstName,
        String lastName,
        String phoneNumber,
        String email) {

        Customer customer = customerRepo.fetchCustomerByFirstNameLastLastNameAndPhoneNumber(firstName, lastName, phoneNumber);

        if(customer == null) {
            customer = customerFactory.createCustomer(
                firstName, lastName, phoneNumber, email
            );
            customerRepo.add(customer);
        }



        return customer;
    }

}

