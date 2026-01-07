package backend.repositories;

import backend.entities.Customer;

public class CustomerRepo extends BaseRepo<Customer> {
    public CustomerRepo(){
        super(Customer.class);
    }

    public Customer fetchCustomerByFirstNameAndLastName(String firstName, String lastName) {
        return callInTransaction(em ->
            em.createQuery("""
                    SELECT c FROM Customer c
        WHERE c.firstName = :firstName
        AND  c.lastName = :lastName
        """, Customer.class)
            .setParameter("firstName", firstName)
            .setParameter("lastName", lastName)
            .getSingleResultOrNull());

    }
}
