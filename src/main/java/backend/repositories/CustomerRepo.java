package backend.repositories;

import backend.entities.Customer;

public class CustomerRepo extends BaseRepo<Customer> {
    public CustomerRepo(){
        super(Customer.class);
    }
}
