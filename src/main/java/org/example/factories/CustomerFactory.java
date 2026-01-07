package org.example.factories;

import org.example.entities.Customer;
import org.example.repositories.CustomerRepo;
import org.example.services.CustomerService;

public class CustomerFactory {

    private final CustomerRepo repo = new CustomerRepo();
    private final CustomerService service = new CustomerService();

    public void createCustomer(String name, String lastName, String phoneNumber, String email){
        if(service.validateName(name) && service.validateName(lastName) &&
            service.validatePhoneNumber(phoneNumber) && service.validateEmail(email)){
            Customer customer = new Customer(name, lastName, phoneNumber, email);
            repo.add(customer);
        }
    }
}
