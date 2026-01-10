package backend.services;

import backend.entities.Customer;
import backend.factories.CustomerFactory;
import backend.repositories.CustomerRepo;

public class CustomerService {

    private final CustomerRepo customerRepo = new CustomerRepo();
    private final CustomerFactory customerFactory = new CustomerFactory();

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

