package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.ConnectionProvider;
import org.example.entities.Customer;

public class CustomerRepo extends BaseRepo<Customer> {
    public CustomerRepo(){
        super(Customer.class);
    }

    public Customer fetchCustomerByFirstNameAndLastName(String firstName, String lastName) {
        try (EntityManager em =
                 ConnectionProvider.getEMF().createEntityManager()) {
            return em.createQuery("""
                    SELECT c FROM Customer c
        WHERE c.firstName = :firstName
        AND  c.lastName = :lastName
        """, Customer.class)
            .setParameter("firstName", firstName)
            .setParameter("lastName", lastName)
            .getSingleResultOrNull();
        }

    }
}
