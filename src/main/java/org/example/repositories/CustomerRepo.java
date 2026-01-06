package org.example.repositories;

import org.example.entities.Customer;

public class CustomerRepo extends BaseRepo<Customer> {
    public CustomerRepo(){
        super(Customer.class);
    }
}
