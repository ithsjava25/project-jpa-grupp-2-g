package org.example.factories;

import org.example.entities.Customer;

public class CustomerFactory {

    public static Customer createCustomer(String name, String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        return customer;
    }
}
